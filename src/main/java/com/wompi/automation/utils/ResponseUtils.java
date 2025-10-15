package com.wompi.automation.utils;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for API response validation and logging
 * Provides methods for response analysis and debugging
 */
public class ResponseUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(ResponseUtils.class);
    
    /**
     * Logs the complete API response for debugging
     * @param response The API response to log
     * @param testName The name of the test for context
     */
    public static void logResponse(Response response, String testName) {
        logger.info("=== {} Response Details ===", testName);
        logger.info("Status Code: {}", response.getStatusCode());
        logger.info("Status Line: {}", response.getStatusLine());
        logger.info("Headers: {}", response.getHeaders().asList());
        logger.info("Response Body: {}", response.getBody().asString());
        logger.info("Response Time: {} ms", response.getTime());
        logger.info("=== End Response Details ===");
    }
    
    /**
     * Validates if response indicates a successful API call
     * @param response The API response to validate
     * @return true if response indicates success
     */
    public static boolean isSuccessResponse(Response response) {
        int statusCode = response.getStatusCode();
        return statusCode >= 200 && statusCode < 300;
    }
    
    /**
     * Validates if response indicates a client error
     * @param response The API response to validate
     * @return true if response indicates client error
     */
    public static boolean isClientErrorResponse(Response response) {
        int statusCode = response.getStatusCode();
        return statusCode >= 400 && statusCode < 500;
    }
    
    /**
     * Validates if response indicates a server error
     * @param response The API response to validate
     * @return true if response indicates server error
     */
    public static boolean isServerErrorResponse(Response response) {
        int statusCode = response.getStatusCode();
        return statusCode >= 500 && statusCode < 600;
    }
    
    /**
     * Extracts error message from response
     * @param response The API response
     * @return Error message or null if not found
     */
    public static String extractErrorMessage(Response response) {
        try {
            String responseBody = response.getBody().asString();
            if (responseBody.contains("message")) {
                // Simple JSON parsing for error message
                int messageStart = responseBody.indexOf("\"message\":\"") + 11;
                int messageEnd = responseBody.indexOf("\"", messageStart);
                if (messageStart > 10 && messageEnd > messageStart) {
                    return responseBody.substring(messageStart, messageEnd);
                }
            }
        } catch (Exception e) {
            logger.warn("Could not extract error message from response", e);
        }
        return null;
    }
    
    /**
     * Validates response time is within acceptable limits
     * @param response The API response
     * @param maxTimeMs Maximum acceptable response time in milliseconds
     * @return true if response time is acceptable
     */
    public static boolean isResponseTimeAcceptable(Response response, long maxTimeMs) {
        long responseTime = response.getTime();
        return responseTime <= maxTimeMs;
    }
}
