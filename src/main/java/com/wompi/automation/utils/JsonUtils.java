package com.wompi.automation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Utility class for JSON operations
 * Provides methods for JSON serialization and deserialization
 */
public class JsonUtils {
    
    private static final ObjectMapper objectMapper;
    
    static {
        objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
    
    /**
     * Converts object to JSON string
     * @param object The object to convert
     * @return JSON string representation
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Error converting object to JSON", e);
        }
    }
    
    /**
     * Converts JSON string to object
     * @param json The JSON string
     * @param clazz The target class
     * @return Object of specified class
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON to object", e);
        }
    }
    
    /**
     * Pretty prints JSON string
     * @param json The JSON string to format
     * @return Formatted JSON string
     */
    public static String prettyPrint(String json) {
        try {
            Object jsonObject = objectMapper.readValue(json, Object.class);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        } catch (Exception e) {
            return json; // Return original if formatting fails
        }
    }
}
