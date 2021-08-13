package com.kevin;

import java.io.*;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/***
 * This application takes in text from a file as input and counts how many times each word has occurred.
 * The program will automatically format the text - any text file with space separated words will be valid. 
 * This main creates a window and interfaces with the user.
 * It then creates a sorted tally, showing the top 10 most common words. 
 * @author Kevin
 * 
 */
public class Main extends Application {
	
	File text;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage window) throws Exception {
		
		setStage(window);
		
	}
	
	private void setStage(Stage window) {
		// Set window
		window.setTitle("Text Analyzer");
		Button runButton = new Button("Run Analyzer");
		Button uploadButton = new Button("Choose text");
		Label label = new Label("Choose a file");
		Label records = new Label("Top 20 Words");
		
		// Set fonts (must be monospaced to line up correctly
		Font font = Font.font ("Monospaced", 12);	
		runButton.setFont(font);
		uploadButton.setFont(font);;
		label.setFont(font);
		records.setFont(font);
		
		// Upload file button on click event handler
		uploadButton.setOnAction(e ->{
			FileChooser fileChooser = new FileChooser();
			File selectedFile = fileChooser.showOpenDialog(null);
			if (selectedFile != null) {
				text = selectedFile;
				label.setText(selectedFile.getName());
			}
		});
		
		// Run button on click event handler
		runButton.setOnAction(e-> {
				try {
					TextAnalyzer textAnalyzer = new TextAnalyzer(text);
					records.setText(textAnalyzer.sortText(textAnalyzer.countWords(), 20));
				} 
				catch(Exception exception) {
					label.setText("Invalid file");
				}
			});

		// Button pane
		BorderPane pane = new BorderPane();
		pane.setLeft(runButton);
		pane.setCenter(uploadButton);
		pane.setPadding(new Insets(10, 10, 0, 0));
		
		// Arrange and show window
		VBox vBox = new VBox(20);
		vBox.getChildren().addAll(pane, label, records);
		vBox.setPadding(new Insets(15, 15, 15, 15));
		Scene scene = new Scene(vBox, 250, 450);
		window.setScene(scene);
		window.show();
	}

	
}

