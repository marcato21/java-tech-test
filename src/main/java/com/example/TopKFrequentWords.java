package com.example;

import java.util.*;

/**
 * Solution for Top K Frequent Words problem.
 * Returns k most frequent words ordered by frequency (descending),
 * and alphabetically (lexicographically) when frequencies are equal.
 */
public class TopKFrequentWords {

    /**
     * Returns k most frequent words from the given array.
     * Words are ordered by frequency (descending), and alphabetically
     * when they have the same frequency.
     *
     * @param words array of words
     * @param k number of top frequent words to return
     * @return list of k most frequent words
     */
    public static List<String> topKFrequent(String[] words, int k) {
        // Count frequency of each word
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Check if we have at least k distinct frequency levels
        Set<Integer> distinctFrequencies = new HashSet<>(frequencyMap.values());
        if (distinctFrequencies.size() < k) {
            // Not enough distinct frequency levels, return empty list
            return new ArrayList<>();
        }

        // Create a list of entries and sort them
        // Sort by frequency descending, then by word ascending (lexicographical)
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());
        entries.sort((a, b) -> {
            int freqCompare = b.getValue().compareTo(a.getValue());
            if (freqCompare != 0) {
                return freqCompare;
            }
            // If frequencies are equal, sort alphabetically
            return a.getKey().compareTo(b.getKey());
        });

        // Extract top k words, including all words with the same frequency as the k-th word
        List<String> result = new ArrayList<>();
        if (entries.isEmpty()) {
            return result;
        }
        
        // Add first k-1 words (or all if less than k)
        int count = 0;
        int kthFrequency = -1;
        
        for (int i = 0; i < entries.size() && count < k; i++) {
            result.add(entries.get(i).getKey());
            count++;
            if (count == k) {
                kthFrequency = entries.get(i).getValue();
            }
        }
        
        // Include all remaining words with the same frequency as the k-th word
        if (kthFrequency != -1 && count == k) {
            for (int i = k; i < entries.size(); i++) {
                if (entries.get(i).getValue().equals(kthFrequency)) {
                    result.add(entries.get(i).getKey());
                } else {
                    break; // Since sorted, no more words with this frequency
                }
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        // Example 1
        String[] words1 = {"java", "python", "java", "golang", "java", "python"};
        int k1 = 2;
        List<String> result1 = topKFrequent(words1, k1);
        System.out.println("Example 1:");
        System.out.println("Input: " + Arrays.toString(words1) + ", k = " + k1);
        System.out.println("Output: " + result1);
        System.out.println();

        // Example 2
        String[] words2 = {"java", "rust", "python", "rust", "java", "golang", "java", "python"};
        int k2 = 2;
        List<String> result2 = topKFrequent(words2, k2);
        System.out.println("Example 2:");
        System.out.println("Input: " + Arrays.toString(words2) + ", k = " + k2);
        System.out.println("Output: " + result2);
    }
}

