# Java Test Solutions

A Java 17 project containing solutions for various programming problems.

## Solutions

1. **Top K Frequent Words** - Finding the top k most frequent words from an array
2. **JSON Validator** - Validating nested JSON structure
3. **Word Counter** - Counting word frequencies in a text

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

---

## 3. Word Counter

### Problem Description

Count the frequency of every word in a given text. Words are counted case-insensitively and sorted alphabetically. Punctuation is preserved with the words.

### Features

- Case-insensitive word counting (e.g., "Hello", "hello", "HELLO" are treated as the same word)
- Words are sorted alphabetically in the output
- Punctuation is preserved as part of the word
- Handles null and empty input gracefully (no output)

### Example

#### Example Input
```
"This is a test. This is only a test."
```

#### Example Output
```
a -> 2
is -> 2
only -> 1
test. -> 2
this -> 2
```

Note: Words are sorted alphabetically (lowercase). The word "test." appears twice with the period preserved. "This" and "this" are counted together (case-insensitive).

### Running the Word Counter

#### Run the main class
```bash
mvn exec:java -Dexec.mainClass="com.example.WordCounter"
```

#### Run tests
```bash
mvn test -Dtest=WordCounterTest
```

### Implementation

The solution:
1. Splits the text by whitespace to extract words
2. Uses a HashMap with lowercase keys to count word frequencies (case-insensitive)
3. Sorts the words alphabetically for display
4. Prints each word with its frequency count
