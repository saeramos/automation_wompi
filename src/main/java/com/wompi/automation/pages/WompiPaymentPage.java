package com.wompi.automation.pages;

import com.wompi.automation.config.ConfigManager;
import com.wompi.automation.models.PSEPaymentRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Page Object for Wompi Payment API
 * Encapsulates all payment-related API interactions
 */
public class WompiPaymentPage {
    
    private final ConfigManager config;
    private final RequestSpecification requestSpec;
    
    public WompiPaymentPage() {
        this.config = ConfigManager.getInstance();
        this.requestSpec = RestAssured.given()
                .baseUri(config.getUatPrincipalUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + config.getPrivateKey());
    }
    
    /**
     * Creates a PSE payment transaction
     * @param paymentRequest The PSE payment request data
     * @return Response from the API
     */
    public Response createPSEPayment(PSEPaymentRequest paymentRequest) {
        return requestSpec
                .body(paymentRequest)
                .when()
                .post("/transactions")
                .then()
                .extract()
                .response();
    }
    
    /**
     * Gets transaction status by ID
     * @param transactionId The transaction ID
     * @return Response containing transaction status
     */
    public Response getTransactionStatus(String transactionId) {
        return requestSpec
                .when()
                .get("/transactions/" + transactionId)
                .then()
                .extract()
                .response();
    }
    
    /**
     * Gets transaction status by reference
     * @param reference The transaction reference
     * @return Response containing transaction status
     */
    public Response getTransactionByReference(String reference) {
        return requestSpec
                .queryParam("reference", reference)
                .when()
                .get("/transactions")
                .then()
                .extract()
                .response();
    }
    
    /**
     * Creates a PSE payment request with valid test data
     * @return PSEPaymentRequest with valid test data
     */
    public PSEPaymentRequest createValidPSEPaymentRequest() {
        return PSEPaymentRequest.builder()
                .amountInCents(Integer.parseInt(config.getTestAmount()))
                .currency(config.getTestCurrency())
                .customerEmail("test@example.com")
                .reference(config.getTestReference() + "_" + System.currentTimeMillis())
                .paymentSourceId(1)
                .paymentMethod(PSEPaymentRequest.PaymentMethod.builder()
                        .type("PSE")
                        .userType("PERSON")
                        .userLegalId("12345678")
                        .userLegalIdType("CC")
                        .financialInstitutionCode("1007")
                        .paymentDescription("Test PSE Payment")
                        .build())
                .customerData(PSEPaymentRequest.CustomerData.builder()
                        .email("test@example.com")
                        .fullName("Test User")
                        .phoneNumber("3001234567")
                        .build())
                .build();
    }
    
    /**
     * Creates a PSE payment request with invalid bank data
     * @return PSEPaymentRequest with invalid bank data
     */
    public PSEPaymentRequest createInvalidBankDataPSEPaymentRequest() {
        return PSEPaymentRequest.builder()
                .amountInCents(Integer.parseInt(config.getTestAmount()))
                .currency(config.getTestCurrency())
                .customerEmail("test@example.com")
                .reference(config.getTestReference() + "_INVALID_" + System.currentTimeMillis())
                .paymentSourceId(1)
                .paymentMethod(PSEPaymentRequest.PaymentMethod.builder()
                        .type("PSE")
                        .userType("PERSON")
                        .userLegalId("INVALID_ID")
                        .userLegalIdType("CC")
                        .financialInstitutionCode("9999") // Invalid bank code
                        .paymentDescription("Test Invalid PSE Payment")
                        .build())
                .customerData(PSEPaymentRequest.CustomerData.builder()
                        .email("test@example.com")
                        .fullName("Test User")
                        .phoneNumber("3001234567")
                        .build())
                .build();
    }
    
    /**
     * Creates a PSE payment request with insufficient funds scenario
     * @return PSEPaymentRequest with high amount to simulate insufficient funds
     */
    public PSEPaymentRequest createInsufficientFundsPSEPaymentRequest() {
        return PSEPaymentRequest.builder()
                .amountInCents(999999999) // Very high amount
                .currency(config.getTestCurrency())
                .customerEmail("test@example.com")
                .reference(config.getTestReference() + "_INSUFFICIENT_" + System.currentTimeMillis())
                .paymentSourceId(1)
                .paymentMethod(PSEPaymentRequest.PaymentMethod.builder()
                        .type("PSE")
                        .userType("PERSON")
                        .userLegalId("12345678")
                        .userLegalIdType("CC")
                        .financialInstitutionCode("1007")
                        .paymentDescription("Test Insufficient Funds PSE Payment")
                        .build())
                .customerData(PSEPaymentRequest.CustomerData.builder()
                        .email("test@example.com")
                        .fullName("Test User")
                        .phoneNumber("3001234567")
                        .build())
                .build();
    }
    
    /**
     * Sets invalid credentials for testing authentication errors
     */
    public void setInvalidCredentials() {
        RestAssured.given()
                .baseUri(config.getUatPrincipalUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer INVALID_TOKEN");
    }
}
