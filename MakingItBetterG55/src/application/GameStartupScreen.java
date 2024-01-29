package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class GameStartupScreen {

	private String student;
	private String parent;
	private String teacher;
	private String engineer;
	private boolean studentAI;
	private boolean parentAI;
	private boolean teacherAI;
	private boolean engineerAI;
	
	private Scene scene;
	private Button studentButton = new Button("Student");
	private Button parentButton = new Button("Parent");
	private Button teacherButton = new Button("Teacher");
	private Button engineerButton = new Button("Engineer");
	private Button settingsButton = new Button();
	private Button startButton = new Button("Start");
	private CheckBox boxA = new CheckBox("AI Controlled");
	private CheckBox boxB = new CheckBox("Player Controlled");
	private Label label1= new Label("Game Startup Settings");
	private Label label2 = new Label("Name:");
	private Label label3 = new Label("Objective:");
	private TextField textField1 = new TextField();
	
	public GameStartupScreen() {
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		scene = new Scene(gridPane, 400, 400);
		label1.setTranslateX(140);label1.setTranslateY(0);gridPane.add(label1, 0, 0);	
		label3.setTranslateX(45);label3.setTranslateY(15);gridPane.add(label3, 0, 5);
		settingsButton.setMaxHeight(20);settingsButton.setMaxWidth(35);settingsButton.setTranslateX(0);settingsButton.setTranslateY(30);gridPane.add(settingsButton, 0, 7);
		studentButton.setMaxHeight(20);studentButton.setMaxWidth(140);gridPane.add(studentButton, 0, 1);
		parentButton.setMaxHeight(20);parentButton.setMaxWidth(140);gridPane.add(parentButton, 0, 2);
		teacherButton.setMaxHeight(20);teacherButton.setMaxWidth(140);gridPane.add(teacherButton, 0, 3);
		engineerButton.setMaxHeight(20);engineerButton.setMaxWidth(140);gridPane.add(engineerButton, 0, 4);
		startButton.setMaxHeight(50);startButton.setMaxWidth(100);startButton.setTranslateX(150);startButton.setTranslateY(30);gridPane.add(startButton,3,7);
	}
	
	public Scene getStartupScreen(){
		return scene;
	}
}
