# Java Test Solutions

A Java 17 project containing solutions for various programming problems.

## Solutions

1. **Top K Frequent Words** - Finding the top k most frequent words from an array
2. **JSON Validator** - Validating nested JSON structure

---

## 1. Top K Frequent Words

## Problem Description

Given an array of words and an integer k, return the k most frequent words. Words should be ordered by frequency in descending order. When words have the same frequency, they should be ordered alphabetically (lexicographically).

## Examples

### Example 1
```
Input: words = ["java", "python", "java", "golang", "java", "python"], k = 2
Output: ["java", "python"]
```
- Frequency: java=3, python=2, golang=1
- Top 2: java (3), python (2)

### Example 2
```
Input: words = ["java", "rust", "python", "rust", "java", "golang", "java", "python"], k = 2
Output: ["java", "python", "rust"]
```
- Frequency: java=3, rust=2, python=2, golang=1
- Top 2 frequencies: 3 and 2
- Words with frequency 3: java
- Words with frequency 2: python, rust (alphabetically)

## Building and Running

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Build the project
```bash
mvn clean compile
```

### Run the main class
```bash
mvn exec:java -Dexec.mainClass="com.example.TopKFrequentWords"
```

### Run tests
```bash
mvn test -Dtest=TopKFrequentWordsTest
```

## Implementation

The solution uses:
1. A HashMap to count word frequencies
2. Sorting entries by frequency (descending) and then alphabetically (ascending)
3. Extracting the top k words from the sorted list

---

## 2. JSON Validator

### Problem Description

Validate if a `Map<String, Object>` is a valid JSON structure.

A valid JSON structure means:
- Map with String keys
- Values can be:
  - Another Map (nested object)
  - List or Array (nested array)
  - Primitive data types (String, Number, Boolean, or null)
- All nested elements must follow the same requirements

### Examples

#### Example 1: Valid JSON Structure
```java
Map.of("user", Map.of(
    "name", "John Doe",
    "age", 30,
    "skills", Arrays.asList("java", "python", "javascript")
))
```
Returns: `true`

#### Example 2: Invalid JSON Structure
```java
Map<String, Object> invalid = new HashMap<>();
Map<Integer, String> nested = new HashMap<>();
nested.put(1, "bag");
nested.put(2, "hat");
Map<String, Object> user = new HashMap<>();
user.put("name", "John Doe");
user.put("age", 30);
user.put("invalid", nested);  // Map with non-String keys
invalid.put("user", user);
```
Returns: `false` (because nested Map has Integer keys instead of String keys)

### Running the JSON Validator

#### Run the main class
```bash
mvn exec:java -Dexec.mainClass="com.example.JsonValidator"
```

#### Run tests
```bash
mvn test -Dtest=JsonValidatorTest
```

### Implementation

The solution uses recursive validation:
1. Checks if root is a Map
2. Validates all keys are Strings
3. Recursively validates all values (Map, List/Array, or primitives)
4. Returns false if any invalid structure is found

