package com.wompi.automation.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NequiPaymentRequest {
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

        @JsonProperty("phone_number")
        private String phoneNumber;

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
