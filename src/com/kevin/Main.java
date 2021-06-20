package com.kevin;

import java.io.*;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage window) throws Exception {
		
		window.setTitle("Text Analyzer by Kevin Lai");
		Button button = new Button("Run Analyzer");
		
		Font font = Font.font ("Monospaced", 12);
		Label label = new Label();
		Label records = new Label();
		
		button.setFont(font);
		label.setFont(font);
		records.setFont(font);
		
		button.setOnAction(e-> {
				try {
					TextAnalyzer textAnalyzer = new TextAnalyzer(new File("src\\com\\kevin\\text.txt"));
					label.setText("Top 20 words");
					records.setText(textAnalyzer.analyzeText(20));
				} 
				catch(FileNotFoundException exception) {
					records.setText("Text file not found!");
				}
			});

		VBox layout = new VBox(20);
		layout.getChildren().addAll(button, label, records);
		layout.setPadding(new Insets(20, 20, 20, 20));
		
		Scene scene = new Scene(layout, 500, 500);
		window.setScene(scene);
		window.show();
		
	}

	
}

