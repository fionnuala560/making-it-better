package application;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class GameStartupScreen {

	String student;
	String parent;
	String teacher;
	String engineer;
	//stage.setScene(newScene);
	Button startButton = new Button("Start");
	CheckBox boxA = new CheckBox("AI Controlled");
	CheckBox boxB = new CheckBox("Player Controlled");
	Label label1 = new Label("Name:");
	TextField textField = new TextField();
	HBox hb1 = new HBox();
	//hb1.getChildren().addAll(label1, TextField);
	//hb1.setSpacing(10);
}
