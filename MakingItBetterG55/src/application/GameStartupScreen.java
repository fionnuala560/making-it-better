package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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
	private CheckBox boxB = new CheckBox("AI Controlled");
	private CheckBox boxC = new CheckBox("AI Controlled");
	private CheckBox boxD = new CheckBox("AI Controlled");
	private CheckBox boxE = new CheckBox("Player Controlled");
	private CheckBox boxF = new CheckBox("Player Controlled");
	private CheckBox boxG = new CheckBox("Player Controlled");
	private CheckBox boxH = new CheckBox("Player Controlled");
	private Label label1= new Label("Game Startup Settings");
	private Label label2 = new Label("Name:");
	private Label label3 = new Label("Name:");
	private Label label4 = new Label("Name:");
	private Label label5 = new Label("Name:");
	private Label label6 = new Label("Objective:");
	private TextField textField1 = new TextField();
	private TextField textField2 = new TextField();
	private TextField textField3 = new TextField();
	private TextField textField4 = new TextField();
	
	public GameStartupScreen() {
		GridPane gridPane = new GridPane();
		//gridPane.setHgap(10);
		scene = new Scene(gridPane, 400, 400);
		//Setting coordinates of label1 and add to scene
		label1.setTranslateX(140);
		label1.setTranslateY(0);
		gridPane.add(label1, 0, 0);	
		//set coordinates of label3 and add to scene
		label6.setTranslateX(45);
		label6.setTranslateY(15);
		gridPane.add(label6, 0, 5);
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
		startButton.setTranslateX(150);
		startButton.setTranslateY(30);
		gridPane.add(startButton,3,7);
		
		//create pane for when studentButton is pressed
		Pane studentPane = new Pane();
		//add AI Button
		studentPane.getChildren().add(boxA);
		boxA.setTranslateX(300);
		boxA.setTranslateY(50);
		//add player Button
		studentPane.getChildren().add(boxE);
		boxE.setTranslateX(170);
		boxE.setTranslateY(50);
		//add Name: label
		studentPane.getChildren().add(label2);
		label2.setTranslateX(170);
		label2.setTranslateY(93);
		//add name text field
		studentPane.getChildren().add(textField1);
		textField1.setTranslateX(220);
		textField1.setTranslateY(90);
		gridPane.add(studentPane,0,0);
		studentPane.setVisible(false);
		/*studentPane.getChildren().add(parentButton);
		parentButton.setTranslateX(0);
		parentButton.setTranslateY(50);
		parentButton.setMaxHeight(20);
		parentButton.setMaxWidth(140);*/
		
		//create pane for when parentButton is pressed
		Pane parentPane = new Pane();
		parentPane.getChildren().add(boxB);
		boxB.setTranslateX(300);
		boxB.setTranslateY(50);
		//add player Button
		parentPane.getChildren().add(boxF);
		boxF.setTranslateX(170);
		boxF.setTranslateY(50);
		//add Name: label
		parentPane.getChildren().add(label3);
		label3.setTranslateX(170);
		label3.setTranslateY(93);
		//add name text field
		parentPane.getChildren().add(textField2);
		textField2.setTranslateX(220);
		textField2.setTranslateY(90);
		gridPane.add(parentPane,0,0);
		parentPane.setVisible(false);
		
		//create pane for when teacherButton is pressed
		Pane teacherPane = new Pane();
		teacherPane.getChildren().add(boxC);
		boxC.setTranslateX(300);
		boxC.setTranslateY(50);
		//add player Button
		teacherPane.getChildren().add(boxG);
		boxG.setTranslateX(170);
		boxG.setTranslateY(50);
		//add Name: label
		teacherPane.getChildren().add(label4);
		label4.setTranslateX(170);
		label4.setTranslateY(93);
		//add name text field
		teacherPane.getChildren().add(textField3);
		textField3.setTranslateX(220);
		textField3.setTranslateY(90);
		gridPane.add(teacherPane,0,0);
		teacherPane.setVisible(false);
		
		//create pane for when engineerButton is pressed
		Pane engineerPane = new Pane();
		engineerPane.getChildren().add(boxD);
		boxD.setTranslateX(300);
		boxD.setTranslateY(50);
		//add player Button
		engineerPane.getChildren().add(boxH);
		boxH.setTranslateX(170);
		boxH.setTranslateY(50);
		//add Name: label
		engineerPane.getChildren().add(label5);
		label5.setTranslateX(170);
		label5.setTranslateY(93);
		//add name text field
		engineerPane.getChildren().add(textField4);
		textField4.setTranslateX(220);
		textField4.setTranslateY(90);
		gridPane.add(engineerPane,0,0);
		engineerPane.setVisible(false);
		
		//Show studentPane when studentButton is pressed
		EventHandler<ActionEvent> studentEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				studentPane.setVisible(true);
				parentPane.setVisible(false);
				teacherPane.setVisible(false);
				engineerPane.setVisible(false);
			}
		};
		//Show parentPane when parentButton is pressed
		EventHandler<ActionEvent> parentEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				studentPane.setVisible(false);
				parentPane.setVisible(true);
				teacherPane.setVisible(false);
				engineerPane.setVisible(false);
			}
		};
		//Show teacherPane when teacaherButton is pressed
		EventHandler<ActionEvent> teacherEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				studentPane.setVisible(false);
				parentPane.setVisible(false);
				teacherPane.setVisible(true);
				engineerPane.setVisible(false);
			}
		};
		//Show engineerPane when engineeerButton is pressed
		EventHandler<ActionEvent> engineerEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				studentPane.setVisible(false);
				parentPane.setVisible(false);
				teacherPane.setVisible(false);
				engineerPane.setVisible(true);
			}
		};
		
		studentButton.setOnAction(studentEvent);
		parentButton.setOnAction(parentEvent);
		teacherButton.setOnAction(teacherEvent);
		engineerButton.setOnAction(engineerEvent);
	}
	
	public Scene getStartupScreen(){
		return scene;
	}
}
