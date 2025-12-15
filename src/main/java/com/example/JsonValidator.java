package com.example;

import java.util.*;

/**
 * Solution for Validated Nested JSON Structure problem.
 * Validates if a Map<String, Object> is a valid JSON structure.
 * 
 * Valid JSON structure means:
 * - Map with String keys
 * - Values can be: Map (nested object), List/Array (nested array), 
 *   or primitive types (String, Number, Boolean, null)
 * - All nested elements must follow the same requirements
 */
public class JsonValidator {
    
    /**
     * Validates if an Object is a valid JSON structure.
     * 
     * @param input The object to validate
     * @return true if the input is a valid JSON structure, false otherwise
     */
    public static boolean isValidJson(Object input) {
        // Root must be a Map, null is not valid
        if (input == null) {
            return false;
        }
        
        // Check if it's a Map
        if (input instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) input;
            
            // Validate all keys are Strings
            for (Object key : map.keySet()) {
                if (!(key instanceof String)) {
                    return false; // Key must be String type
                }
            }
            
            // Recursively validate all values
            for (Object value : map.values()) {
                if (!isValidJsonValue(value)) {
                    return false;
                }
            }
            
            return true;
        }
        
        // If input is not a Map, it's invalid (root must be a Map)
        return false;
    }
    
    /**
     * Validates if a value is a valid JSON value.
     * Valid JSON values are: Map, List/Array, or primitive types (String, Number, Boolean, null)
     * 
     * @param value The value to validate
     * @return true if the value is valid, false otherwise
     */
    private static boolean isValidJsonValue(Object value) {
        if (value == null) {
            return true; // null is valid
        }
        
        // Check if it's a Map (nested object)
        if (value instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) value;
            
            // Validate all keys are Strings
            for (Object key : map.keySet()) {
                if (!(key instanceof String)) {
                    return false; // Key must be String type
                }
            }
            
            // Recursively validate all values
            for (Object nestedValue : map.values()) {
                if (!isValidJsonValue(nestedValue)) {
                    return false;
                }
            }
            
            return true;
        }
        
        // Check if it's a List or Array (nested array)
        if (value instanceof List) {
            List<?> list = (List<?>) value;
            for (Object item : list) {
                if (!isValidJsonValue(item)) {
                    return false;
                }
            }
            return true;
        }
        
        if (value.getClass().isArray()) {
            // Handle array types
            int length = java.lang.reflect.Array.getLength(value);
            for (int i = 0; i < length; i++) {
                Object item = java.lang.reflect.Array.get(value, i);
                if (!isValidJsonValue(item)) {
                    return false;
                }
            }
            return true;
        }
        
        // Check if it's a primitive JSON type: String, Number, or Boolean
        if (value instanceof String || value instanceof Number || value instanceof Boolean) {
            return true;
        }
        
        // Anything else is invalid
        return false;
    }
    
    public static void main(String[] args) {
        // Example 1: Valid JSON structure
        Map<String, Object> validJson1 = Map.of(
            "user", Map.of(
                "name", "John Doe",
                "age", 30,
                "skills", Arrays.asList("java", "python", "javascript")
            )
        );
        System.out.println("Example 1:");
        System.out.println("Input: " + validJson1);
        System.out.println("Result: " + isValidJson(validJson1));
        System.out.println();
        
        // Example 2: Invalid JSON structure (non-String key in nested Map)
        Map<String, Object> invalidJson1 = new HashMap<>();
        Map<Integer, String> invalidNestedMap = new HashMap<>();
        invalidNestedMap.put(1, "bag");
        invalidNestedMap.put(2, "hat");
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("name", "John Doe");
        userMap.put("age", 30);
        userMap.put("invalid", invalidNestedMap);  // Invalid: Map with Integer keys
        invalidJson1.put("user", userMap);
        System.out.println("Example 2:");
        System.out.println("Input: " + invalidJson1);
        System.out.println("Result: " + isValidJson(invalidJson1));
        System.out.println();
        
        // Example 3: Valid JSON with null values
        Map<String, Object> validJson2 = new HashMap<>();
        validJson2.put("name", "John");
        validJson2.put("age", null);
        validJson2.put("active", true);
        System.out.println("Example 3:");
        System.out.println("Input: " + validJson2);
        System.out.println("Result: " + isValidJson(validJson2));
        System.out.println();
        
        // Example 4: Invalid JSON (non-Map root)
        System.out.println("Example 4:");
        System.out.println("Input: \"just a string\"");
        System.out.println("Result: " + isValidJson("just a string"));
        System.out.println();
        
        // Example 5: Valid JSON with array
        Map<String, Object> validJson3 = Map.of(
            "numbers", new int[]{1, 2, 3},
            "names", Arrays.asList("Alice", "Bob")
        );
        System.out.println("Example 5:");
        System.out.println("Input: " + validJson3);
        System.out.println("Result: " + isValidJson(validJson3));
    }
}

