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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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
		gameStartupScene = new Scene(gridPane, 1200, 600);
		//Setting coordinates of label1 and add to scene
		label1.setFont(Font.font("SansSerif", FontWeight.BOLD, 50));
		label1.setTranslateX(300);
		label1.setTranslateY(0);
		gridPane.add(label1, 0, 0);	
		//set coordinates of label6 and add to scene
		label6.setFont(Font.font("SansSerif", FontWeight.BOLD, 16));
		label6.setTranslateX(200);
		label6.setTranslateY(360);
		gridPane.add(label6, 0, 0);
		//set coordinates of label7
		label7.setFont(Font.font("SansSerif", FontWeight.BOLD, 19));
		label7.setTranslateX(120);
		label7.setTranslateY(330);
		gridPane.add(label7, 0, 0);
		//set coordinates, size of settingsButton and add to scene
		settingsButton.setMinHeight(20);
		settingsButton.setMinWidth(35);
		settingsButton.setTranslateX(20);
		settingsButton.setTranslateY(400);
		gridPane.add(settingsButton, 0, 7);
		//set height of studentButton and add to scene
		studentButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 22));
		studentButton.setMinHeight(20);
		studentButton.setMinWidth(140);
		studentButton.setTranslateX(0);
		studentButton.setTranslateY(50);
		gridPane.add(studentButton, 0, 0);
		//set size of parentButton and add to scene
		parentButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 22));
		parentButton.setMinHeight(20);
		parentButton.setMinWidth(140);
		parentButton.setTranslateX(0);
		parentButton.setTranslateY(91);
		gridPane.add(parentButton, 0, 0);
		//set size of teacherButton and add to scene
		teacherButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 22));
		teacherButton.setMinHeight(20);
		teacherButton.setMinWidth(140);
		teacherButton.setTranslateX(0);
		teacherButton.setTranslateY(132);
		gridPane.add(teacherButton, 0, 0);
		//set size of engineerButton and add to scene
		engineerButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 22));
		engineerButton.setMinHeight(20);
		engineerButton.setMinWidth(140);
		engineerButton.setTranslateX(0);
		engineerButton.setTranslateY(173);
		gridPane.add(engineerButton, 0, 0);
		//set coordinates, size of startButton and add to scene
		startButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 20));
		startButton.setMinHeight(50);
		startButton.setMinWidth(100);
		startButton.setTranslateX(1000);
		startButton.setTranslateY(450);
		gridPane.add(startButton,0, 0);
		
		//create panes for when each player button is pressed
		Pane studentPane = new Pane();
		studentPane.getChildren().add(boxA);
		boxA.setFont(Font.font("SansSerif", FontWeight.BOLD, 20));
		boxA.setTranslateX(50);
		boxA.setTranslateY(140);
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
		label8.setFont(Font.font("SansSerif", FontWeight.BOLD, 21));
		label8.setTranslateX(470);
		label8.setTranslateY(50);
		//add Name: label
		studentPane.getChildren().add(label2);
		label2.setFont(Font.font("SansSerif", FontWeight.BOLD, 21));
		label2.setTranslateX(50);
		label2.setTranslateY(50);
		//add name text field
		studentPane.getChildren().add(textField1);
		textField1.setFont(Font.font("SansSerif", 21));
		textField1.setTranslateX(150);
		textField1.setTranslateY(45);
		gridPane.add(studentPane,0,0);
		studentPane.setTranslateX(141);
		studentPane.setTranslateY(60);
		studentPane.setVisible(false);
		/*studentPane.getChildren().add(parentButton);
		parentButton.setTranslateX(0);
		parentButton.setTranslateY(50);
		parentButton.setMaxHeight(20);
		parentButton.setMaxWidth(140);*/
		
		Pane parentPane = new Pane();
		parentPane.getChildren().add(boxB);
		boxB.setFont(Font.font("SansSerif", FontWeight.BOLD, 20));
		boxB.setTranslateX(50);
		boxB.setTranslateY(140);
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
		label9.setFont(Font.font("SansSerif", FontWeight.BOLD, 21));
		label9.setTranslateX(470);
		label9.setTranslateY(50);
		//add Name: label
		parentPane.getChildren().add(label3);
		label3.setFont(Font.font("SansSerif", FontWeight.BOLD, 21));
		label3.setTranslateX(50);
		label3.setTranslateY(50);
		//add name text field
		parentPane.getChildren().add(textField2);
		textField2.setFont(Font.font("SansSerif", 21));
		textField2.setTranslateX(150);
		textField2.setTranslateY(45);
		gridPane.add(parentPane,0,0);
		parentPane.setTranslateX(141);
		parentPane.setTranslateY(60);
		parentPane.setVisible(false);
		
		Pane teacherPane = new Pane();
		teacherPane.getChildren().add(boxC);
		boxC.setFont(Font.font("SansSerif", FontWeight.BOLD, 20));
		boxC.setTranslateX(50);
		boxC.setTranslateY(140);
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
		label10.setFont(Font.font("SansSerif", FontWeight.BOLD, 21));
		label10.setTranslateX(470);
		label10.setTranslateY(50);
		//add Name: label
		teacherPane.getChildren().add(label4);
		label4.setFont(Font.font("SansSerif", FontWeight.BOLD, 21));
		label4.setTranslateX(50);
		label4.setTranslateY(50);
		//add name text field
		teacherPane.getChildren().add(textField3);
		textField3.setFont(Font.font("SansSerif", 21));
		textField3.setTranslateX(150);
		textField3.setTranslateY(45);
		gridPane.add(teacherPane,0,0);
		teacherPane.setTranslateX(141);
		teacherPane.setTranslateY(60);
		teacherPane.setVisible(false);

		
		Pane engineerPane = new Pane();
		engineerPane.getChildren().add(boxD);
		boxD.setFont(Font.font("SansSerif", FontWeight.BOLD, 20));
		boxD.setTranslateX(50);
		boxD.setTranslateY(140);
		boxD.setOnAction(e -> {
			if(boxD.isSelected()) {
				System.out.println("is selected");
				String name = textField4.getText();
				System.out.println(name);
				isEngineerAI = true;
			}else {
				System.out.println("is not selected");
				isEngineerAI = false;
			}
		});
		//add objective
		engineerPane.getChildren().add(label11);
		label11.setFont(Font.font("SansSerif", FontWeight.BOLD, 21));
		label11.setTranslateX(470);
		label11.setTranslateY(50);
		
		//add Name: label
		engineerPane.getChildren().add(label5);
		label5.setFont(Font.font("SansSerif", FontWeight.BOLD, 21));
		label5.setTranslateX(50);
		label5.setTranslateY(50);
		//add name text field
		engineerPane.getChildren().add(textField4);
		textField4.setFont(Font.font("SansSerif", 21));
		textField4.setTranslateX(150);
		textField4.setTranslateY(45);
		gridPane.add(engineerPane,0,0);
		engineerPane.setTranslateX(141);
		engineerPane.setTranslateY(60);
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
