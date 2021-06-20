package com.kevin;

import java.io.*;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	
	Button button;
	Text text;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Text Analyzer by Kevin Lai");
		
		button = new Button();
		button.setText("Run Analyzer");
		text = new Text();
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					TextAnalyzer textAnalyzer = new TextAnalyzer(new File("src\\com\\kevin\\poem.txt"));	
					text.setText(textAnalyzer.analyzeText());
				} 
				catch(FileNotFoundException e) {
					text.setText("poem.txt not found!");
				}
			}
		});

		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		layout.getChildren().add(text);
		
		Scene scene = new Scene(layout, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	
}

