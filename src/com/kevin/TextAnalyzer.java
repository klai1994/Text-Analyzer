package com.kevin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

public class TextAnalyzer {
	
	Scanner scanner;

	public TextAnalyzer(File file) throws FileNotFoundException {
		scanner = new Scanner(file);
	}
	
	public TextAnalyzer(String string) {
		scanner = new Scanner(string);
	}
	
	public HashMap<String, Integer> countWords() {

		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		HashMap<String, Integer> wordCounts = new HashMap<String, Integer>();

		while (scanner.hasNextLine()) {
			String parsedLine[] = scanner.nextLine().replaceAll("[^a-zA-Z']", " ").toLowerCase().split("\\s+");

			for (String word : parsedLine) {
				if (word == "")
					continue;
				if (!wordCounts.containsKey(word)) {
					wordCounts.put(word, 1);
				} else {
					wordCounts.put(word, wordCounts.get(word) + 1);
				}

			}
		}
		scanner.close();
		return wordCounts;
	}

	public String sortText(HashMap<String, Integer> analyzedText, int rankings) {

		List<Entry<String, Integer>> wordCounts = new LinkedList<Entry<String, Integer>>(analyzedText.entrySet());
		Collections.sort(wordCounts, new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> count1, Entry<String, Integer> count2) {
				return count2.getValue().compareTo(count1.getValue());
			}
		});

		String sortedText = "";
		int rank = 1;
		for (Entry<String, Integer> entry : wordCounts) {
			if (rank > rankings) break; 
			sortedText += formatLine(entry, rank);
			rank++;
		}

		return sortedText;
	}
	
	private String formatLine(Entry<String, Integer> word, int rank) {
		
		// Text must be monospaced to format correctly
		String formattedLine;
		String capitalizedWord = word.getKey().substring(0, 1).toUpperCase() + word.getKey().substring(1);
		String indent = "                  "; // 20 spaces

		
		formattedLine = rank + ") " + capitalizedWord;
		formattedLine += indent.substring(0, indent.length() - formattedLine.length());
		formattedLine += word.getValue() + "\n";
		
		return formattedLine;
	}
}
