package application;

import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GameStartupScreen {

	Scanner scanner = new Scanner(System.in);
	private String student;
	private String parent;
	private String teacher;
	private String engineer;
	private boolean isStudentAI;
	private boolean isParentAI;
	private boolean isTeacherAI;
	private boolean isEngineerAI;
	
	private Scene gameStartupScene;
	private Button studentButton = new Button("Student");
	private Button parentButton = new Button("Parent");
	private Button teacherButton = new Button("Teacher");
	private Button engineerButton = new Button("Engineer");
	private Button settingsButton = new Button();
	private Button startButton = new Button("Start");
	private CheckBox boxA = new CheckBox("AI Controlled");
	private CheckBox boxB = new CheckBox("AI Controlled");
	private CheckBox boxC = new CheckBox("AI Controlled");
	private CheckBox boxD = new CheckBox("AI Controlled");
	private Label label1= new Label("Game Startup Settings");
	private Label label2 = new Label("Name:");
	private Label label3 = new Label("Name:");
	private Label label4 = new Label("Name:");
	private Label label5 = new Label("Name:");
	private Label label6 = new Label("Objective:");
	private Label label7 = new Label("Team's Objective");
	private Label label8 = new Label("Objective:");
	private Label label9 = new Label("Objective:");
	private Label label10 = new Label("Objective:");
	private Label label11 = new Label("Objective:");
	private TextField textField1 = new TextField();
	private TextField textField2 = new TextField();
	private TextField textField3 = new TextField();
	private TextField textField4 = new TextField();
	
	public GameStartupScreen() {
		GridPane gridPane = new GridPane();
		gameStartupScene = new Scene(gridPane, 400, 400);
		//Setting coordinates of label1 and add to scene
		label1.setTranslateX(140);
		label1.setTranslateY(0);
		gridPane.add(label1, 0, 0);	
		//set coordinates of label3 and add to scene
		label6.setTranslateX(45);
		label6.setTranslateY(30);
		gridPane.add(label6, 0, 5);
		//set coordinates of label7
		label7.setTranslateX(60);
		label7.setTranslateY(30);
		gridPane.add(label7, 0, 6);
		//set coordinates, size of settingsButton and add to scene
		settingsButton.setMaxHeight(20);
		settingsButton.setMaxWidth(35);
		settingsButton.setTranslateX(0);
		settingsButton.setTranslateY(30);
		gridPane.add(settingsButton, 0, 7);
		//set height of studentButton and add to scene
		studentButton.setMaxHeight(20);
		studentButton.setMaxWidth(140);
		gridPane.add(studentButton, 0, 1);
		//set size of parentButton and add to scene
		parentButton.setMaxHeight(20);
		parentButton.setMaxWidth(140);
		gridPane.add(parentButton, 0, 2);
		//set size of teacherButton and add to scene
		teacherButton.setMaxHeight(20);
		teacherButton.setMaxWidth(140);
		gridPane.add(teacherButton, 0, 3);
		//set size of engineerButton and add to scene
		engineerButton.setMaxHeight(20);
		engineerButton.setMaxWidth(140);
		gridPane.add(engineerButton, 0, 4);
		//set coordinates, size of startButton and add to scene
		startButton.setMaxHeight(50);
		startButton.setMaxWidth(100);
		startButton.setTranslateX(60);
		startButton.setTranslateY(30);
		gridPane.add(startButton,3,7);
		
		//create panes for when each player button is pressed
		Pane studentPane = new Pane();
		studentPane.getChildren().add(boxA);
		boxA.setTranslateX(20);
		boxA.setTranslateY(75);
		boxA.setOnAction(e -> {
			if(boxA.isSelected()) {
				System.out.println("is selected");
				isStudentAI = true;
			}else {
				System.out.println("is not selected");
				isStudentAI = false;
			}
		});
		//add objective
		studentPane.getChildren().add(label8);
		label8.setTranslateX(20);
		label8.setTranslateY(105);
		//add Name: label
		studentPane.getChildren().add(label2);
		label2.setTranslateX(20);
		label2.setTranslateY(40);
		//add name text field
		studentPane.getChildren().add(textField1);
		textField1.setTranslateX(80);
		textField1.setTranslateY(37);
		gridPane.add(studentPane,1,0);
		studentPane.setVisible(false);
		/*studentPane.getChildren().add(parentButton);
		parentButton.setTranslateX(0);
		parentButton.setTranslateY(50);
		parentButton.setMaxHeight(20);
		parentButton.setMaxWidth(140);*/
		
		Pane parentPane = new Pane();
		parentPane.getChildren().add(boxB);
		boxB.setTranslateX(20);
		boxB.setTranslateY(75);
		boxB.setOnAction(e -> {
			if(boxB.isSelected()) {
				System.out.println("is selected");
				isParentAI = true;
			}else {
				System.out.println("is not selected");
				isParentAI = false;
			}
		});
		//add objective
		parentPane.getChildren().add(label9);
		label9.setTranslateX(20);
		label9.setTranslateY(105);
		//add Name: label
		parentPane.getChildren().add(label3);
		label3.setTranslateX(20);
		label3.setTranslateY(40);
		//add name text field
		parentPane.getChildren().add(textField2);
		textField2.setTranslateX(80);
		textField2.setTranslateY(37);
		gridPane.add(parentPane,1,0);
		parentPane.setVisible(false);
		
		Pane teacherPane = new Pane();
		teacherPane.getChildren().add(boxC);
		boxC.setTranslateX(20);
		boxC.setTranslateY(75);
		boxC.setOnAction(e -> {
			if(boxC.isSelected()) {
				System.out.println("is selected");
				isTeacherAI = true;
			}else {
				System.out.println("is not selected");
				isTeacherAI = false;
			}
		});
		//add objective
		teacherPane.getChildren().add(label10);
		label10.setTranslateX(20);
		label10.setTranslateY(105);
		//add Name: label
		teacherPane.getChildren().add(label4);
		label4.setTranslateX(20);
		label4.setTranslateY(40);
		//add name text field
		teacherPane.getChildren().add(textField3);
		textField3.setTranslateX(80);
		textField3.setTranslateY(37);
		gridPane.add(teacherPane,1,0);
		teacherPane.setVisible(false);

		
		Pane engineerPane = new Pane();
		engineerPane.getChildren().add(boxD);
		boxD.setTranslateX(20);
		boxD.setTranslateY(75);
		boxD.setOnAction(e -> {
			if(boxD.isSelected()) {
				System.out.println("is selected");
				isEngineerAI = true;
			}else {
				System.out.println("is not selected");
				isEngineerAI = false;
			}
		});
		//add objective
		engineerPane.getChildren().add(label11);
		label11.setTranslateX(20);
		label11.setTranslateY(105);
		
		//add Name: label
		engineerPane.getChildren().add(label5);
		label5.setTranslateX(20);
		label5.setTranslateY(40);
		//add name text field
		engineerPane.getChildren().add(textField4);
		textField4.setTranslateX(80);
		textField4.setTranslateY(37);
		gridPane.add(engineerPane,1,0);
		engineerPane.setVisible(false);
		
		//Show studentPane when studentButton is pressed
		studentButton.setOnAction(e -> {
			studentPane.setVisible(true);
			parentPane.setVisible(false);
			teacherPane.setVisible(false);
			engineerPane.setVisible(false);
			//Player studentPlayer = new Player('s', textField1.getText(), boxA.isPressed());
		});
		/*EventHandler<ActionEvent> studentEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				studentPane.setVisible(true);
				parentPane.setVisible(false);
				teacherPane.setVisible(false);
				engineerPane.setVisible(false);
			}
		};*/
		
		//Show parentPane when parentButton is pressed
		parentButton.setOnAction(e -> {
			studentPane.setVisible(false);
			parentPane.setVisible(true);
			teacherPane.setVisible(false);
			engineerPane.setVisible(false);
		});
		
		//Show teacherPane when teacaherButton is pressed
		teacherButton.setOnAction(e -> {
			studentPane.setVisible(false);
			parentPane.setVisible(false);
			teacherPane.setVisible(true);
			engineerPane.setVisible(false);
		});
		
		//Show engineerPane when engineeerButton is pressed
		engineerButton.setOnAction(e -> {
			studentPane.setVisible(false);
			parentPane.setVisible(false);
			teacherPane.setVisible(false);
			engineerPane.setVisible(true);
		});
	}
	
	public Scene getGameStartupScreen(){
		return gameStartupScene;
	}
}
