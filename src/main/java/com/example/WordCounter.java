package com.example;

import java.util.*;

/**
 * Utility class for counting words in a text.
 */
public class WordCounter {
    
    /**
     * Counts the frequency of every word in the given text.
     * Words are counted case-insensitively and sorted alphabetically.
     * Punctuation is preserved with the words.
     * 
     * @param text The input sentence to count words from
     */
    public static void countWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return;
        }
        
        // Split the text into words (preserving punctuation)
        String[] words = text.split("\\s+");
        
        // Map to store word frequencies (case-insensitive)
        Map<String, Integer> wordCount = new HashMap<>();
        
        // Count words (convert to lowercase for counting, but preserve original for display)
        Map<String, String> originalCaseMap = new HashMap<>();
        
        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            String lowerWord = word.toLowerCase();
            
            // Store original case for first occurrence
            if (!originalCaseMap.containsKey(lowerWord)) {
                originalCaseMap.put(lowerWord, word);
            }
            
            wordCount.put(lowerWord, wordCount.getOrDefault(lowerWord, 0) + 1);
        }
        
        // Sort words alphabetically
        List<String> sortedWords = new ArrayList<>(wordCount.keySet());
        Collections.sort(sortedWords);
        
        // Print the results
        for (String word : sortedWords) {
            System.out.println(word + " -> " + wordCount.get(word));
        }
    }
    
    public static void main(String[] args) {
        String input = "This is a test. This is only a test.";
        System.out.println("Input: \"" + input + "\"");
        System.out.println("Output:");
        countWords(input);
    }
}

