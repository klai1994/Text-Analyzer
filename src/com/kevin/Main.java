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
		Label label = new Label();
		Label records = new Label();
		
		Font font = Font.font ("Monospaced", 12);	
		button.setFont(font);
		label.setFont(font);
		records.setFont(font);
		label.setText("Top 20 Words");
		
		button.setOnAction(e-> {
				try {
					TextAnalyzer textAnalyzer = new TextAnalyzer(new File("src\\com\\kevin\\text.txt"));
					records.setText(textAnalyzer.sortText(textAnalyzer.countWords(), 20));
				} 
				catch(FileNotFoundException exception) {
					records.setText("Text file not found!");
				}
			});

		VBox vBox = new VBox(20);
		vBox.getChildren().addAll(button, label, records);
		vBox.setPadding(new Insets(20, 20, 20, 20));
		
		Scene scene = new Scene(vBox, 500, 500);
		window.setScene(scene);
		window.show();
		
	}

	
}

