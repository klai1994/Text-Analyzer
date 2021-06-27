package com.kevin;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

class AnalyzeTextTest {

	Scanner scanner;
	@Test
	void test(){

		HashMap<String, Integer> testMap = new HashMap<String,Integer>();
		HashMap<String, Integer> resultMap = new HashMap<String,Integer>();
		
		testMap.put("the", 56);
		testMap.put("and", 38);
		testMap.put("i", 32);
		testMap.put("my", 24);
		testMap.put("of", 22);
		
		try {
			TextAnalyzer textAnalyzer = new TextAnalyzer(new File("src\\com\\kevin\\text.txt"));
			resultMap = textAnalyzer.countWords();
		} 
		catch(FileNotFoundException exception) {
			System.out.println("File not found!");
		}

		assertTrue(testMap.get("the") == resultMap.get("the"));
		assertTrue(testMap.get("and") == resultMap.get("and"));
		assertTrue(testMap.get("i") == resultMap.get("i"));
		assertTrue(testMap.get("my") == resultMap.get("my"));
		assertTrue(testMap.get("of") == resultMap.get("of"));
		
	}

}
