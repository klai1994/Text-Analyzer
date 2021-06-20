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
	
	File file;
	
	public TextAnalyzer(File file) {
		this.file = file;
	}
	
	public String analyzeText() throws FileNotFoundException {

		Scanner scanner = new Scanner(file);
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
		return sortText(wordCounts);
	}

	private String sortText(HashMap<String, Integer> analyzedText) {

		List<Entry<String, Integer>> wordCounts = new LinkedList<Entry<String, Integer>>(analyzedText.entrySet());
		Collections.sort(wordCounts, new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> count1, Entry<String, Integer> count2) {
				return count2.getValue().compareTo(count1.getValue());
			}
		});

		String sortedText = "";
		for (Entry<String, Integer> count : wordCounts) {
			sortedText += count + "\n";
		}

		return sortedText;
	}
}
