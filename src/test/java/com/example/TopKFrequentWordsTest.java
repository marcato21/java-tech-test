package com.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TopKFrequentWordsTest {

    @Test
    void testExample1() {
        String[] words = {"java", "python", "java", "golang", "java", "python"};
        int k = 2;
        List<String> result = TopKFrequentWords.topKFrequent(words, k);
        
        assertEquals(2, result.size());
        assertEquals("java", result.get(0));
        assertEquals("python", result.get(1));
    }

    @Test
    void testExample2() {
        String[] words = {"java", "rust", "python", "rust", "java", "golang", "java", "python"};
        int k = 2;
        List<String> result = TopKFrequentWords.topKFrequent(words, k);
        
        // java=3, rust=2, python=2, golang=1
        // Top 2 frequencies: 3 and 2
        // Words with frequency 3: java
        // Words with frequency 2: python, rust (alphabetically)
        assertEquals(3, result.size());
        assertEquals("java", result.get(0));
        assertEquals("python", result.get(1));
        assertEquals("rust", result.get(2));
    }

    @Test
    void testSingleWord() {
        String[] words = {"java", "java", "java"};
        int k = 1;
        List<String> result = TopKFrequentWords.topKFrequent(words, k);
        
        assertEquals(1, result.size());
        assertEquals("java", result.get(0));
    }

    @Test
    void testAllSameFrequency() {
        String[] words = {"python", "java", "golang", "rust"};
        int k = 2;
        List<String> result = TopKFrequentWords.topKFrequent(words, k);
        
        // All have frequency 1 (only 1 distinct frequency level), k=2 requires 2 distinct levels
        // So should return empty list
        assertTrue(result.isEmpty());
    }

    @Test
    void testKGreaterThanUniqueWords() {
        String[] words = {"java", "python"};
        int k = 5;
        List<String> result = TopKFrequentWords.topKFrequent(words, k);
        
        // Only 1 distinct frequency level (both have frequency 1), k=5 requires 5 distinct levels
        // So should return empty list
        assertTrue(result.isEmpty());
    }
}

