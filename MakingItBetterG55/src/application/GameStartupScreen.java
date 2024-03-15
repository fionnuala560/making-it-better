package application;

import java.util.Scanner;

import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameStartupScreen {

	Scanner scanner = new Scanner(System.in);

	private Scene gameStartupScene;
	private Button studentButton = new Button("Student");
	private Button parentButton = new Button("Parent");
	private Button teacherButton = new Button("Teacher");
	private Button engineerButton = new Button("Engineer");
	//private ImageView settingsButton = new ImageView("/setting.png");
	private Button startButton = new Button("Start");
	private Button backButton = new Button("<");
	private CheckBox boxA = new CheckBox("AI Controlled");
	private CheckBox boxB = new CheckBox("AI Controlled");
	private CheckBox boxC = new CheckBox("AI Controlled");
	private CheckBox boxD = new CheckBox("AI Controlled");
	private Text title = new Text("Game Startup Settings");
	private Label label2 = new Label("Name:");
	private Label label3 = new Label("Name:");
	private Label label4 = new Label("Name:");
	private Label label5 = new Label("Name:");
	private Label label6 = new Label(
			"Pu Ngaol is a small village in Cambodia with only 547 residents. It is surrounded "
					+ "by forrested hills with the Ou Te River running through the centre of the village. There is only one primary "
					+ "school (ages 5 to 11) which includes students from Pu Ngaol and two other local villages. Only 10% of the "
					+ "children of Pu Ngaol attend the primary school with a 96% dropout rate when going from primary school into "
					+ "secondary school as children are needed by their families to work on farms, local business, or as labourers. "
					+ "The eldest daughter is expected to be the home-maker meaning they take care of their siblings and complete "
					+ "housework rather than prioritise their education. This system needs to change and we need your help! "
					+ "You must work as a team and implement a sustainable solution which involves installing a computer lab in the "
					+ "primary school so students have a wide range of learning resources, install solar pannels so the school doesn't "
					+ "have to pay for electricity, and encourage the student to progress in their education. You each have objectives "
					+ "which must be met in order to win the game, but don't forget, you must work as a team and solve problems together. "
					+ "Good Luck!!");
	private Label label7 = new Label("Team's Objective: ");
	
	private String[] studentObjectives = {"Maintain at least 80 health all "
			+ "throughout the whole game.", "Study at school 10 times.", "Reach certain 90 education."};
	
	private Label label8 = new Label("Objectives:\n\n- " + studentObjectives[0] + "\n -" + studentObjectives[1] + "\n -" + studentObjectives[2]);
	
	private String[] parentObjectives = {"Keep at least 30 goods throughout the whole game.", "Buy 2 improvements from the shop or family's home.", "Interact with student a certain 10 times."};
	
	private Label label9 = new Label("Objectives:\n\n- " + parentObjectives[0] + "\n -" + parentObjectives[1] + "\n -" + parentObjectives[2]);
	
	private String[] teacherObjectives = {"Keep at least 60 education throughout the whole game.", "Teach at the school 10 times.", "Eat 3 apples collected from the student."};
	
	private Label label10 = new Label("Objectives:\n\n- "+ teacherObjectives[0] + "\n -" + teacherObjectives[1] + "\n -" + teacherObjectives[2]);
	
	private String[] engineerObjectives = {"Keep at least 40 money throughout the whole game.", "Install certain 2 improvements for the school.", "Train the teacher 5 times."};
	
	private Label label11 = new Label("Objectives:\n\n- " + engineerObjectives[0] + "\n -" + engineerObjectives[1] + "\n -" + engineerObjectives[2]);
	private TextField textField1 = new TextField("Student");
	private TextField textField2 = new TextField("Parent");
	private TextField textField3 = new TextField("Teacher");
	private TextField textField4 = new TextField("Engineer");

	private boolean isStudentAI;
	private boolean isParentAI;
	private boolean isTeacherAI;
	private boolean isEngineerAI;

	public GameStartupScreen(Scene homeScene, OptionsMenu optionsMenu) {

		GridPane gridPane = new GridPane();
		gridPane.setStyle("-fx-background-image: url('/woodbackground.jpg');");
		gameStartupScene = new Scene(gridPane, 1200, 800);
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(50);

		gridPane.getRowConstraints().addAll(row1);

		// Setting coordinates of label1 and add to scene
		title.setFont(Main.fredokaOne);
		title.setStyle("-fx-font-size: 50; -fx-fill: white; -fx-effect: dropshadow(gaussian, black, 5 , 1.0, 0, 0);");
		title.setTranslateX(300);
		title.setTranslateY(20);
		gridPane.setValignment(title, VPos.TOP);
		gridPane.add(title, 0, 0);

		// set coordinates of label6 and add to scene
		label6.setFont(Font.font("SansSerif", FontWeight.BOLD, 16));
		label6.setStyle("-fx-text-fill: white;");

		// label6.setStyle("-fx-border-color: #152546;");
		label6.setMaxHeight(500);
		label6.setMaxWidth(800);
		label6.setWrapText(true);
		label6.setTranslateX(160);
		label6.setTranslateY(330);
		gridPane.setValignment(label6, VPos.TOP);
		gridPane.add(label6, 0, 0);

		// set coordinates of label7
		label7.setFont(Font.font("SansSerif", FontWeight.BOLD, 19));
		label7.setStyle("-fx-text-fill: white;");
		label7.setTranslateX(100);
		label7.setTranslateY(370);
		gridPane.setValignment(label7, VPos.TOP);
		gridPane.add(label7, 0, 0);

		// set height of studentButton and add to scene
		studentButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 22));
		studentButton.setMinHeight(20);
		studentButton.setMinWidth(140);
		studentButton.setTranslateX(0);
		studentButton.setTranslateY(91);
		gridPane.setValignment(studentButton, VPos.TOP);
		gridPane.add(studentButton, 0, 0);

		// set size of parentButton and add to scene
		parentButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 22));
		parentButton.setMinHeight(20);
		parentButton.setMinWidth(140);
		parentButton.setTranslateX(0);
		parentButton.setTranslateY(132);
		gridPane.setValignment(parentButton, VPos.TOP);
		gridPane.add(parentButton, 0, 0);

		// set size of teacherButton and add to scene
		teacherButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 22));
		teacherButton.setMinHeight(20);
		teacherButton.setMinWidth(140);
		teacherButton.setTranslateX(0);
		teacherButton.setTranslateY(173);
		gridPane.setValignment(teacherButton, VPos.TOP);
		gridPane.add(teacherButton, 0, 0);

		// set size of engineerButton and add to scene
		engineerButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 22));
		engineerButton.setMinHeight(20);
		engineerButton.setMinWidth(140);
		engineerButton.setTranslateX(0);
		engineerButton.setTranslateY(214);
		gridPane.setValignment(engineerButton, VPos.TOP);
		gridPane.add(engineerButton, 0, 0);

		// set coordinates, size of startButton and add to scene
		startButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 25));
		startButton.setStyle("-fx-cursor: hand; -fx-border-color: #152546; -fx-border-width: "
				+ "14px; -fx-background-color: #536F7B; -fx-text-fill: white;");
		startButton.setPrefSize(200, 25);
		startButton.setTranslateX(960);
		startButton.setTranslateY(650);
		gridPane.setValignment(startButton, VPos.TOP);
		gridPane.add(startButton, 0, 0);

		// set coordinates, size of backButton and add to scene
		backButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 20));
		backButton.setPrefSize(50, 10);
		backButton.setTranslateX(5);
		backButton.setTranslateY(25);
		gridPane.setValignment(backButton, VPos.TOP);
		gridPane.add(backButton, 0, 0);
		backButton.setOnAction(e -> {
			Stage tempMain = (Stage) backButton.getScene().getWindow();
			tempMain.setScene(homeScene);
		});

		// create panes for when each player button is pressed
		Pane studentPane = new Pane();
		//studentPane.getChildren().add(boxA);
		boxA.setFont(Font.font("SansSerif", FontWeight.BOLD, 20));
		boxA.setStyle("-fx-text-fill: white;");
		boxA.setTranslateX(50);
		boxA.setTranslateY(140);
		boxA.setOnAction(e -> {
			if (boxA.isSelected()) {
				isStudentAI = true;
			} else {
				isStudentAI = false;
			}
		});

		// add objective
		studentPane.getChildren().add(label8);
		label8.setFont(Font.font("SansSerif", FontWeight.BOLD, 21));
		label8.setStyle("-fx-text-fill: white;");
		label8.setTranslateX(470);
		label8.setTranslateY(50);
		label8.setMaxHeight(400);
		label8.setMaxWidth(450);
		label8.setWrapText(true);

		// add Name: label
		studentPane.getChildren().add(label2);
		label2.setFont(Font.font("SansSerif", FontWeight.BOLD, 21));
		label2.setStyle("-fx-text-fill: white;");
		label2.setTranslateX(50);
		label2.setTranslateY(50);

		// add name text field
		studentPane.getChildren().add(textField1);
		textField1.setFont(Font.font("SansSerif", 21));
		textField1.setTranslateX(150);
		textField1.setTranslateY(45);
		gridPane.add(studentPane, 0, 0);
		studentPane.setTranslateX(141);
		studentPane.setTranslateY(90);
		studentPane.setVisible(false);

		Pane parentPane = new Pane();
		//parentPane.getChildren().add(boxB);
		boxB.setFont(Font.font("SansSerif", FontWeight.BOLD, 20));
		boxB.setStyle("-fx-text-fill: white;");
		boxB.setTranslateX(50);
		boxB.setTranslateY(140);
		boxB.setOnAction(e -> {
			if (boxB.isSelected()) {
				isParentAI = true;
			} else {
				isParentAI = false;
			}
		});

		// add objective
		parentPane.getChildren().add(label9);
		label9.setFont(Font.font("SansSerif", FontWeight.BOLD, 21));
		label9.setStyle("-fx-text-fill: white;");
		label9.setTranslateX(470);
		label9.setTranslateY(50);
		label9.setMaxHeight(400);
		label9.setMaxWidth(450);
		label9.setWrapText(true);

		// add Name: label
		parentPane.getChildren().add(label3);
		label3.setFont(Font.font("SansSerif", FontWeight.BOLD, 21));
		label3.setStyle("-fx-text-fill: white;");
		label3.setTranslateX(50);
		label3.setTranslateY(50);

		// add name text field
		parentPane.getChildren().add(textField2);
		textField2.setFont(Font.font("SansSerif", 21));
		textField2.setTranslateX(150);
		textField2.setTranslateY(45);
		gridPane.add(parentPane, 0, 0);
		parentPane.setTranslateX(141);
		parentPane.setTranslateY(90);
		parentPane.setVisible(false);

		Pane teacherPane = new Pane();
		//teacherPane.getChildren().add(boxC);
		boxC.setFont(Font.font("SansSerif", FontWeight.BOLD, 20));
		boxC.setStyle("-fx-text-fill: white;");
		boxC.setTranslateX(50);
		boxC.setTranslateY(140);
		boxC.setOnAction(e -> {
			if (boxC.isSelected()) {
				isTeacherAI = true;
			} else {
				isTeacherAI = false;
			}
		});

		// add objective
		teacherPane.getChildren().add(label10);
		label10.setFont(Font.font("SansSerif", FontWeight.BOLD, 21));
		label10.setStyle("-fx-text-fill: white;");
		label10.setTranslateX(470);
		label10.setTranslateY(50);
		label10.setMaxHeight(400);
		label10.setMaxWidth(450);
		label10.setWrapText(true);

		// add Name: label
		teacherPane.getChildren().add(label4);
		label4.setFont(Font.font("SansSerif", FontWeight.BOLD, 21));
		label4.setStyle("-fx-text-fill: white;");
		label4.setTranslateX(50);
		label4.setTranslateY(50);

		// add name text field
		teacherPane.getChildren().add(textField3);
		textField3.setFont(Font.font("SansSerif", 21));
		textField3.setTranslateX(150);
		textField3.setTranslateY(45);
		gridPane.add(teacherPane, 0, 0);
		teacherPane.setTranslateX(141);
		teacherPane.setTranslateY(90);
		teacherPane.setVisible(false);

		Pane engineerPane = new Pane();
		//engineerPane.getChildren().add(boxD);
		boxD.setFont(Font.font("SansSerif", FontWeight.BOLD, 20));
		boxD.setStyle("-fx-text-fill: white;");
		boxD.setTranslateX(50);
		boxD.setTranslateY(140);
		boxD.setOnAction(e -> {
			if (boxD.isSelected()) {
				isEngineerAI = true;
			} else {
				isEngineerAI = false;
			}
		});

		// add objective
		engineerPane.getChildren().add(label11);
		label11.setFont(Font.font("SansSerif", FontWeight.BOLD, 21));
		label11.setStyle("-fx-text-fill: white;");
		label11.setTranslateX(470);
		label11.setTranslateY(50);
		label11.setMaxHeight(400);
		label11.setMaxWidth(450);
		label11.setWrapText(true);

		// add Name: label
		engineerPane.getChildren().add(label5);
		label5.setFont(Font.font("SansSerif", FontWeight.BOLD, 21));
		label5.setStyle("-fx-text-fill: white;");
		label5.setTranslateX(50);
		label5.setTranslateY(50);

		// add name text field
		engineerPane.getChildren().add(textField4);
		textField4.setFont(Font.font("SansSerif", 21));
		textField4.setTranslateX(150);
		textField4.setTranslateY(45);
		gridPane.add(engineerPane, 0, 0);
		engineerPane.setTranslateX(141);
		engineerPane.setTranslateY(90);
		engineerPane.setVisible(false);

		// Show studentPane when studentButton is pressed
		studentButton.setOnAction(e -> {
			studentPane.setVisible(true);
			parentPane.setVisible(false);
			teacherPane.setVisible(false);
			engineerPane.setVisible(false);
			// Player studentPlayer = new Player('s', textField1.getText(),
			// boxA.isPressed());
		});

		// Show parentPane when parentButton is pressed
		parentButton.setOnAction(e -> {
			studentPane.setVisible(false);
			parentPane.setVisible(true);
			teacherPane.setVisible(false);
			engineerPane.setVisible(false);
		});

		// Show teacherPane when teacaherButton is pressed
		teacherButton.setOnAction(e -> {
			studentPane.setVisible(false);
			parentPane.setVisible(false);
			teacherPane.setVisible(true);
			engineerPane.setVisible(false);
		});

		// Show engineerPane when engineeerButton is pressed
		engineerButton.setOnAction(e -> {
			studentPane.setVisible(false);
			parentPane.setVisible(false);
			teacherPane.setVisible(false);
			engineerPane.setVisible(true);
		});

		startButton.setOnAction(e -> {
			Player[] players = { new Player('e', textField4.getText(), isEngineerAI, engineerObjectives),
					new Player('t', textField3.getText(), isTeacherAI, teacherObjectives),
					new Player('p', textField2.getText(), isParentAI, parentObjectives),
					new Player('s', textField1.getText(), isStudentAI, studentObjectives) };

			Stage tempStage = (Stage) startButton.getScene().getWindow();
			MainSceneHandler mainSceneHandler = new MainSceneHandler(players);
			Scene mainScene = mainSceneHandler.makeMainScene();
			tempStage.setScene(mainScene);
			tempStage.centerOnScreen();
		});
	}

	public Scene getGameStartupScreen() {
		return gameStartupScene;
	}
}
