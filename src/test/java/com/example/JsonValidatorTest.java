package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

/**
 * Test cases for JsonValidator class.
 */
public class JsonValidatorTest {
    
    @Test
    public void testValidNestedJsonStructure() {
        // Example from problem description
        Map<String, Object> validJson = Map.of(
            "user", Map.of(
                "name", "John Doe",
                "age", 30,
                "skills", Arrays.asList("java", "python", "javascript")
            )
        );
        assertTrue(JsonValidator.isValidJson(validJson));
    }
    
    @Test
    public void testInvalidNonStringKey() {
        // Example from problem description - Map with non-String keys
        Map<String, Object> invalidJson = new HashMap<>();
        Map<Integer, Object> nestedMap = new HashMap<>();
        nestedMap.put(1, "bag");
        nestedMap.put(2, "hat");
        invalidJson.put("user", Map.of(
            "name", "John Doe",
            "age", 30,
            "invalid", nestedMap
        ));
        assertFalse(JsonValidator.isValidJson(invalidJson));
    }
    
    @Test
    public void testValidPrimitiveTypes() {
        Map<String, Object> validJson = new HashMap<>();
        validJson.put("string", "value");
        validJson.put("number", 42);
        validJson.put("double", 3.14);
        validJson.put("boolean", true);
        validJson.put("nullValue", null);
        assertTrue(JsonValidator.isValidJson(validJson));
    }
    
    @Test
    public void testValidNestedMaps() {
        Map<String, Object> validJson = new HashMap<>();
        Map<String, Object> nested1 = new HashMap<>();
        nested1.put("key1", "value1");
        Map<String, Object> nested2 = new HashMap<>();
        nested2.put("key2", "value2");
        nested1.put("nested", nested2);
        validJson.put("root", nested1);
        assertTrue(JsonValidator.isValidJson(validJson));
    }
    
    @Test
    public void testValidLists() {
        Map<String, Object> validJson = new HashMap<>();
        validJson.put("strings", Arrays.asList("a", "b", "c"));
        validJson.put("numbers", Arrays.asList(1, 2, 3));
        validJson.put("booleans", Arrays.asList(true, false));
        validJson.put("mixed", Arrays.asList("string", 42, true, null));
        assertTrue(JsonValidator.isValidJson(validJson));
    }
    
    @Test
    public void testValidArrays() {
        Map<String, Object> validJson = new HashMap<>();
        validJson.put("intArray", new int[]{1, 2, 3});
        validJson.put("stringArray", new String[]{"a", "b", "c"});
        assertTrue(JsonValidator.isValidJson(validJson));
    }
    
    @Test
    public void testValidNestedLists() {
        Map<String, Object> validJson = new HashMap<>();
        List<Object> nestedList = new ArrayList<>();
        nestedList.add(Arrays.asList(1, 2, 3));
        nestedList.add(Arrays.asList("a", "b"));
        validJson.put("nestedLists", nestedList);
        assertTrue(JsonValidator.isValidJson(validJson));
    }
    
    @Test
    public void testInvalidNonMapRoot() {
        assertFalse(JsonValidator.isValidJson(null)); // Root must be a Map
        assertFalse(JsonValidator.isValidJson("string"));
        assertFalse(JsonValidator.isValidJson(123));
        assertFalse(JsonValidator.isValidJson(true));
        assertFalse(JsonValidator.isValidJson(Arrays.asList(1, 2, 3)));
    }
    
    @Test
    public void testInvalidValueType() {
        Map<String, Object> invalidJson = new HashMap<>();
        invalidJson.put("date", new Date()); // Date is not a valid JSON type
        assertFalse(JsonValidator.isValidJson(invalidJson));
    }
    
    @Test
    public void testInvalidNestedValueType() {
        Map<String, Object> invalidJson = new HashMap<>();
        Map<String, Object> nested = new HashMap<>();
        nested.put("valid", "value");
        nested.put("invalid", new Date());
        invalidJson.put("root", nested);
        assertFalse(JsonValidator.isValidJson(invalidJson));
    }
    
    @Test
    public void testInvalidListElement() {
        Map<String, Object> invalidJson = new HashMap<>();
        List<Object> list = new ArrayList<>();
        list.add("valid");
        list.add(new Date()); // Invalid type in list
        invalidJson.put("list", list);
        assertFalse(JsonValidator.isValidJson(invalidJson));
    }
    
    @Test
    public void testEmptyMap() {
        Map<String, Object> emptyMap = new HashMap<>();
        assertTrue(JsonValidator.isValidJson(emptyMap));
    }
    
    @Test
    public void testMapWithEmptyNestedMap() {
        Map<String, Object> validJson = new HashMap<>();
        validJson.put("empty", new HashMap<>());
        assertTrue(JsonValidator.isValidJson(validJson));
    }
    
    @Test
    public void testMapWithEmptyList() {
        Map<String, Object> validJson = new HashMap<>();
        validJson.put("empty", new ArrayList<>());
        assertTrue(JsonValidator.isValidJson(validJson));
    }
    
    @Test
    public void testComplexValidStructure() {
        Map<String, Object> validJson = new HashMap<>();
        Map<String, Object> user = new HashMap<>();
        user.put("name", "John");
        user.put("age", 30);
        user.put("active", true);
        user.put("tags", Arrays.asList("developer", "java"));
        Map<String, Object> address = new HashMap<>();
        address.put("street", "123 Main St");
        address.put("city", "New York");
        address.put("zip", null);
        user.put("address", address);
        validJson.put("user", user);
        assertTrue(JsonValidator.isValidJson(validJson));
    }
}

