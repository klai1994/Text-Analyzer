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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


/***
 * The TextAnalyzer class handles the logic and formatting of the input. Going line by line, the text file is
 * broken down into lines and punctuation is removed, then into words and counted/sorted. 
 * @author Kevin
 *
 */
public class TextAnalyzer {
	
	Scanner scanner;
	
	/***
	 * This is the intended constructor method, with a text filed passed as an argument.
	 * @param file
	 * @throws FileNotFoundException
	 */
	public TextAnalyzer(File file) throws FileNotFoundException {
		scanner = new Scanner(file);
	}
	
	/***
	 * This constructor may optionally be used for testing purposes to pass in a string directly.
	 * @param string
	 */
	public TextAnalyzer(String string) {
		scanner = new Scanner(string);
	}
	
	private Connection getDbConnection() throws Exception {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/";
			String username = "kevin";
			String password = "1234";
			
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connection successful");
			return conn;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/***
	 * countWords() returns a HashMap with the word as the key and the number of occurrences as the value. 
	 * @return
	 */
	public HashMap<String, Integer> countWords() throws Exception {
		
		Connection conn = getDbConnection();
		Statement statement = conn.createStatement();
		statement.execute("use word_occurrences;");
		
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

				String sql = "insert into word (word) values (\'" + word + "\');";
				statement.execute(sql);
				
			}
		}
		
		conn.close();
		scanner.close();
		return wordCounts;
	}

	/***
	 * This method is called using the output of wordCounts() and the data from the map is extracted and sorted.
	 * It returns the string containing a user specified number of ranked words and their counts.
	 * @param analyzedText The output from countWords().
	 * @param rankings How many rankings to count (i.e top 10, top 20).
	 * @return The text containing the words and their occurrences in descending order.
	 */
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
