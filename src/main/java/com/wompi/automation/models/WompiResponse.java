package com.wompi.automation.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Data model for Wompi API response
 * Represents the structure of responses from Wompi API
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WompiResponse {
    
    @JsonProperty("data")
    private TransactionData data;
    
    @JsonProperty("meta")
    private MetaData meta;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransactionData {
        @JsonProperty("id")
        private String id;
        
        @JsonProperty("amount_in_cents")
        private Integer amountInCents;
        
        @JsonProperty("reference")
        private String reference;
        
        @JsonProperty("customer_email")
        private String customerEmail;
        
        @JsonProperty("currency")
        private String currency;
        
        @JsonProperty("payment_method_type")
        private String paymentMethodType;
        
        @JsonProperty("payment_method")
        private PaymentMethodData paymentMethod;
        
        @JsonProperty("status")
        private String status;
        
        @JsonProperty("status_message")
        private String statusMessage;
        
        @JsonProperty("shipping_address")
        private Object shippingAddress;
        
        @JsonProperty("payment_link_id")
        private String paymentLinkId;
        
        @JsonProperty("payment_source_id")
        private Integer paymentSourceId;
        
        @JsonProperty("payment_source_name")
        private String paymentSourceName;
        
        @JsonProperty("pse")
        private PSEData pse;
        
        @JsonProperty("created_at")
        private String createdAt;
        
        @JsonProperty("finalized_at")
        private String finalizedAt;
        
        @JsonProperty("tax_in_cents")
        private Integer taxInCents;
        
        @JsonProperty("total_paid")
        private Integer totalPaid;
        
        @JsonProperty("redirect_url")
        private String redirectUrl;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentMethodData {
        @JsonProperty("type")
        private String type;
        
        @JsonProperty("extra")
        private Object extra;
        
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
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PSEData {
        @JsonProperty("bin")
        private String bin;
        
        @JsonProperty("bank")
        private String bank;
        
        @JsonProperty("account_type")
        private String accountType;
        
        @JsonProperty("account_number")
        private String accountNumber;
        
        @JsonProperty("account_holder")
        private String accountHolder;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MetaData {
        @JsonProperty("platform_id")
        private String platformId;
        
        @JsonProperty("platform_name")
        private String platformName;
        
        @JsonProperty("platform_version")
        private String platformVersion;
        
        @JsonProperty("library_version")
        private String libraryVersion;
        
        @JsonProperty("library_name")
        private String libraryName;
    }
}
