package com.wompi.automation.builders;

import com.wompi.automation.models.PSEPaymentRequest;
import java.util.Properties;
import java.io.InputStream;

/**
 * Builder class for creating test data
 * Provides methods to build various PSE payment request scenarios
 */
public class TestDataBuilder {
    
    private static Properties testDataProperties;
    
    static {
        loadTestDataProperties();
    }
    
    private static void loadTestDataProperties() {
        testDataProperties = new Properties();
        try (InputStream input = TestDataBuilder.class.getClassLoader()
                .getResourceAsStream("testdata.properties")) {
            if (input != null) {
                testDataProperties.load(input);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading test data properties", e);
        }
    }
    
    /**
     * Builds a valid PSE payment request
     * @return PSEPaymentRequest with valid test data
     */
    public static PSEPaymentRequest buildValidPSEPaymentRequest() {
        return PSEPaymentRequest.builder()
                .amountInCents(Integer.parseInt(testDataProperties.getProperty("test.amount.normal")))
                .currency("COP")
                .customerEmail(testDataProperties.getProperty("valid.pse.person.email"))
                .reference(generateReference("VALID"))
                .paymentSourceId(1)
                .paymentMethod(PSEPaymentRequest.PaymentMethod.builder()
                        .type("PSE")
                        .userType("PERSON")
                        .userLegalId(testDataProperties.getProperty("valid.pse.person.document"))
                        .userLegalIdType(testDataProperties.getProperty("valid.pse.person.type"))
                        .financialInstitutionCode(testDataProperties.getProperty("valid.pse.bank.code"))
                        .paymentDescription("Valid PSE Payment Test")
                        .build())
                .customerData(PSEPaymentRequest.CustomerData.builder()
                        .email(testDataProperties.getProperty("valid.pse.person.email"))
                        .fullName(testDataProperties.getProperty("valid.pse.person.name"))
                        .phoneNumber(testDataProperties.getProperty("valid.pse.person.mobile"))
                        .build())
                .build();
    }
    
    /**
     * Builds an invalid PSE payment request with wrong bank data
     * @return PSEPaymentRequest with invalid bank data
     */
    public static PSEPaymentRequest buildInvalidBankDataPSEPaymentRequest() {
        return PSEPaymentRequest.builder()
                .amountInCents(Integer.parseInt(testDataProperties.getProperty("test.amount.normal")))
                .currency("COP")
                .customerEmail(testDataProperties.getProperty("invalid.pse.person.email"))
                .reference(generateReference("INVALID"))
                .paymentSourceId(1)
                .paymentMethod(PSEPaymentRequest.PaymentMethod.builder()
                        .type("PSE")
                        .userType("PERSON")
                        .userLegalId(testDataProperties.getProperty("invalid.pse.person.document"))
                        .userLegalIdType(testDataProperties.getProperty("invalid.pse.person.type"))
                        .financialInstitutionCode(testDataProperties.getProperty("invalid.pse.bank.code"))
                        .paymentDescription("Invalid Bank Data PSE Payment Test")
                        .build())
                .customerData(PSEPaymentRequest.CustomerData.builder()
                        .email(testDataProperties.getProperty("invalid.pse.person.email"))
                        .fullName(testDataProperties.getProperty("invalid.pse.person.name"))
                        .phoneNumber(testDataProperties.getProperty("invalid.pse.person.mobile"))
                        .build())
                .build();
    }
    
    /**
     * Builds a PSE payment request with insufficient funds scenario
     * @return PSEPaymentRequest with high amount
     */
    public static PSEPaymentRequest buildInsufficientFundsPSEPaymentRequest() {
        return PSEPaymentRequest.builder()
                .amountInCents(Integer.parseInt(testDataProperties.getProperty("test.amount.insufficient")))
                .currency("COP")
                .customerEmail(testDataProperties.getProperty("valid.pse.person.email"))
                .reference(generateReference("INSUFFICIENT"))
                .paymentSourceId(1)
                .paymentMethod(PSEPaymentRequest.PaymentMethod.builder()
                        .type("PSE")
                        .userType("PERSON")
                        .userLegalId(testDataProperties.getProperty("valid.pse.person.document"))
                        .userLegalIdType(testDataProperties.getProperty("valid.pse.person.type"))
                        .financialInstitutionCode(testDataProperties.getProperty("valid.pse.bank.code"))
                        .paymentDescription("Insufficient Funds PSE Payment Test")
                        .build())
                .customerData(PSEPaymentRequest.CustomerData.builder()
                        .email(testDataProperties.getProperty("valid.pse.person.email"))
                        .fullName(testDataProperties.getProperty("valid.pse.person.name"))
                        .phoneNumber(testDataProperties.getProperty("valid.pse.person.mobile"))
                        .build())
                .build();
    }
    
    /**
     * Builds a PSE payment request for timeout testing
     * @return PSEPaymentRequest for timeout scenario
     */
    public static PSEPaymentRequest buildTimeoutPSEPaymentRequest() {
        return PSEPaymentRequest.builder()
                .amountInCents(Integer.parseInt(testDataProperties.getProperty("test.amount.normal")))
                .currency("COP")
                .customerEmail(testDataProperties.getProperty("valid.pse.person.email"))
                .reference(generateReference("TIMEOUT"))
                .paymentSourceId(1)
                .paymentMethod(PSEPaymentRequest.PaymentMethod.builder()
                        .type("PSE")
                        .userType("PERSON")
                        .userLegalId(testDataProperties.getProperty("valid.pse.person.document"))
                        .userLegalIdType(testDataProperties.getProperty("valid.pse.person.type"))
                        .financialInstitutionCode(testDataProperties.getProperty("valid.pse.bank.code"))
                        .paymentDescription("Timeout PSE Payment Test")
                        .build())
                .customerData(PSEPaymentRequest.CustomerData.builder()
                        .email(testDataProperties.getProperty("valid.pse.person.email"))
                        .fullName(testDataProperties.getProperty("valid.pse.person.name"))
                        .phoneNumber(testDataProperties.getProperty("valid.pse.person.mobile"))
                        .build())
                .build();
    }
    
    /**
     * Generates a unique reference for test transactions
     * @param prefix The prefix for the reference
     * @return Unique reference string
     */
    private static String generateReference(String prefix) {
        return testDataProperties.getProperty("test.reference.prefix") + 
               prefix + "_" + System.currentTimeMillis();
    }
    
    /**
     * Gets test data property value
     * @param key The property key
     * @return Property value
     */
    public static String getTestDataProperty(String key) {
        return testDataProperties.getProperty(key);
    }
}
