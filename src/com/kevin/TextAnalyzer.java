package com.kevin;

import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class TextAnalyzer {
	
	public static void main(String[] args) {
		
		File file = new File("C:\\Users\\SupaD\\eclipse-workspace\\Text-Analyzer\\src\\com\\kevin\\poem.txt");
		
		try {
			sortAndPrintText(AnalyzeText(file));			
		} 
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
		
	}
	
	public static HashMap<String, Integer> AnalyzeText(File file) throws FileNotFoundException {
		
		Scanner scanner = new Scanner(file);
		HashMap<String, Integer> wordCounts = new HashMap<String, Integer>();
		
		while (scanner.hasNextLine()) {	
			// Removes punctuation
			String parsedLine[] = scanner.nextLine().replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");

			for (String word : parsedLine) {
				// Skips empty lines
				if (word == "") {continue;}
				
				if(!wordCounts.containsKey(word)) {
					wordCounts.put(word, 1);
				} else {
					wordCounts.put(word, wordCounts.get(word) + 1);
				}
				
			}
		}
		
		return wordCounts;	
	}
	
	public static void sortAndPrintText (HashMap<String, Integer> analyzedText){
		List<Entry<String, Integer>> sortedText = new LinkedList<Entry<String, Integer>>(analyzedText.entrySet());
		
		Collections.sort(sortedText, new Comparator<Entry<String, Integer>>(){
			@Override
			public int compare(Entry<String,Integer> o1, Entry<String, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		// Print words by frequency in descending order
		Collections.reverse(sortedText);
		for (Entry<String, Integer> count : sortedText) {
			System.out.println(count);
		}
		
	}
	
}
