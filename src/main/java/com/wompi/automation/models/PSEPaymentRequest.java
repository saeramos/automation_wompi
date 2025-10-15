package com.wompi.automation.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Data model for PSE payment request
 * Represents the structure needed for PSE payment transactions
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PSEPaymentRequest {
    
    @JsonProperty("amount_in_cents")
    private Integer amountInCents;
    
    @JsonProperty("currency")
    private String currency;
    
    @JsonProperty("customer_email")
    private String customerEmail;
    
    @JsonProperty("payment_method")
    private PaymentMethod paymentMethod;
    
    @JsonProperty("reference")
    private String reference;
    
    @JsonProperty("payment_source_id")
    private Integer paymentSourceId;
    
    @JsonProperty("customer_data")
    private CustomerData customerData;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentMethod {
        @JsonProperty("type")
        private String type;
        
        @JsonProperty("user_type")
        private String userType;
        
        @JsonProperty("user_legal_id")
        private String userLegalId;
        
        @JsonProperty("user_legal_id_type")
        private String userLegalIdType;
        
        @JsonProperty("financial_institution_code")
        private String financialInstitutionCode;
        
        @JsonProperty("payment_description")
        private String paymentDescription;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CustomerData {
        @JsonProperty("email")
        private String email;
        
        @JsonProperty("full_name")
        private String fullName;
        
        @JsonProperty("phone_number")
        private String phoneNumber;
    }
}
