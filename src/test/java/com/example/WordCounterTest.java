package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class WordCounterTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testBasicWordCounting() {
        String text = "hello world hello";
        WordCounter.countWords(text);
        String output = outputStream.toString();
        
        assertTrue(output.contains("hello -> 2"));
        assertTrue(output.contains("world -> 1"));
    }

    @Test
    void testCaseInsensitiveCounting() {
        String text = "Hello hello HELLO";
        WordCounter.countWords(text);
        String output = outputStream.toString();
        
        // All should be counted as one word (case-insensitive)
        // Since sorted alphabetically, it should appear as "hello"
        assertTrue(output.contains("hello -> 3"));
        // Should only appear once (not three times)
        String[] lines = output.trim().split("\n");
        assertEquals(1, lines.length);
    }

    @Test
    void testAlphabeticalSorting() {
        String text = "zebra apple banana";
        WordCounter.countWords(text);
        String output = outputStream.toString();
        String[] lines = output.trim().split("\n");
        
        // Should be sorted alphabetically: apple, banana, zebra
        assertTrue(lines[0].startsWith("apple"));
        assertTrue(lines[1].startsWith("banana"));
        assertTrue(lines[2].startsWith("zebra"));
    }

    @Test
    void testWithPunctuation() {
        String text = "hello! world. hello?";
        WordCounter.countWords(text);
        String output = outputStream.toString();
        
        // Punctuation should be preserved with words
        assertTrue(output.contains("hello! -> 1") || output.contains("hello -> 2"));
        assertTrue(output.contains("world.") || output.contains("world"));
    }

    @Test
    void testMainMethodExample() {
        String text = "This is a test. This is only a test.";
        WordCounter.countWords(text);
        String output = outputStream.toString();
        
        // Should count: a=2, is=2, only=1, test.=2, this=2
        // Sorted alphabetically: a, is, only, test., this
        assertTrue(output.contains("a -> 2"));
        assertTrue(output.contains("is -> 2"));
        assertTrue(output.contains("only -> 1"));
        assertTrue(output.contains("test. -> 2") || output.contains("test -> 2"));
        assertTrue(output.contains("this -> 2"));
    }

    @Test
    void testEmptyString() {
        String text = "";
        WordCounter.countWords(text);
        String output = outputStream.toString();
        
        // Should produce no output
        assertEquals("", output.trim());
    }

    @Test
    void testWhitespaceOnly() {
        String text = "   ";
        WordCounter.countWords(text);
        String output = outputStream.toString();
        
        // Should produce no output
        assertEquals("", output.trim());
    }

    @Test
    void testNullInput() {
        WordCounter.countWords(null);
        String output = outputStream.toString();
        
        // Should produce no output
        assertEquals("", output.trim());
    }

    @Test
    void testSingleWord() {
        String text = "hello";
        WordCounter.countWords(text);
        String output = outputStream.toString();
        
        assertTrue(output.contains("hello -> 1"));
        String[] lines = output.trim().split("\n");
        assertEquals(1, lines.length);
    }

    @Test
    void testMultipleSpaces() {
        String text = "hello    world     test";
        WordCounter.countWords(text);
        String output = outputStream.toString();
        
        // Should handle multiple spaces correctly
        assertTrue(output.contains("hello -> 1"));
        assertTrue(output.contains("world -> 1"));
        assertTrue(output.contains("test -> 1"));
    }

    @Test
    void testMixedCaseWithAlphabeticalOrder() {
        String text = "Zebra apple Banana";
        WordCounter.countWords(text);
        String output = outputStream.toString();
        String[] lines = output.trim().split("\n");
        
        // Should be sorted by lowercase: apple, banana, zebra
        assertTrue(lines[0].startsWith("apple"));
        assertTrue(lines[1].startsWith("banana"));
        assertTrue(lines[2].startsWith("zebra"));
    }

    @Test
    void testNumbersAsWords() {
        String text = "123 456 123";
        WordCounter.countWords(text);
        String output = outputStream.toString();
        
        assertTrue(output.contains("123 -> 2"));
        assertTrue(output.contains("456 -> 1"));
    }
}

