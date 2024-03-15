package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EpilogueScreenHandler {

	private Scene epilogueScene;
	private Button replayButton = new Button("Replay");
	private Button quitButton = new Button("Quit");

	private Label label1a = new Label("Congratulations!!");
	private Label label2a = new Label("Good Effort :)");
	private Label label3a = new Label("An attempt was made.");
	private Label label4a = new Label("Yikes...");

	private Label label1b = new Label(
			"All objectives were successfully completed,\n   you worked very well together as a team");
	private Label label2b = new Label("Most of the objectives were completed,\n       this was a good starting point");
	private Label label3b = new Label(
			"   Some of the objectives were completed,\nmaybe next time try to work more as a team");
	private Label label4b = new Label("  Very few of the objectives were completed");

	private Label label1c = new Label(
			"A greater level of student progression is shown\n         in the coming years, as more children\n                             stay in school");
	private Label label2c = new Label(
			"More children are gaining a greater level of\n     education, however there is still more\n                       work to be done.");
	private Label label3c = new Label(
			"         Some children are staying in school,\n but more will have to be done to truly make a\n                   long lasting difference");
	private Label label4c = new Label(
			"Little difference has been made for the people\nof Pu Ngaol, maybe someone else will be able\n to put this plan into effect more successfully");

	private Label label1d = new Label("No notes for future attempts.");
	private Label label2d = new Label("Make sure to keep an eye on your resources!");
	private Label label3d = new Label(
			"Keep your objectives in mind as you progress\n                        through the game.");
	private Label label4d = new Label("Try working together to complete the tasks?");

	private Label totalScoreLabel;
	private int totalScore = 0;
	private Player[] players;
	private Label[][] arrays;
	private Label[] currentArray;

	// public void setPlayers(Player[] players){this.players = players;}

	public EpilogueScreenHandler(Player[] players){
			this.players = players;
			for (int i = 0; i < 4; i++) {
				if(players[i].getObjectives()[0] == 0) {
					totalScore += 100;
				}
				if(players[i].getObjectives()[0] >= 2) {
					totalScore += 200;
				}
			}
	        BorderPane epilogue = new BorderPane();
	        epilogue.setStyle("-fx-background-image: url(/woodbackground.jpg);");
	        epilogueScene = new Scene(epilogue, 1200, 800);

	        Text title = new Text("Epilogue :)");
	        title.setFont(Font.font("SansSerif", FontWeight.BOLD, 70));
	        title.setStyle("-fx-fill: black; -fx-stroke: white; -fx-stroke-width: 4px;");
	        title.setTranslateX(350);
	        title.setTranslateY(15);
	        epilogue.setTop(title);

	        totalScoreLabel = new Label("Total Score: " + totalScore);


	        //setting replay button to
	        replayButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 40));
	        replayButton.setStyle("-fx-cursor: hand; -fx-border-color: #152546; -fx-border-width: 14px; -fx-background-color: #536F7B; -fx-text-fill: white");
	        replayButton.setPrefSize(320,29);
	        replayButton.setTranslateX(175);
	        replayButton.setTranslateY(500);
	        epilogue.setLeft(replayButton);
	        replayButton.setOnAction(event -> {
	            Stage tempMain = (Stage) replayButton.getScene().getWindow();
	            tempMain.setScene(Main.homeScene);
	        });

	        //setting quit button to exit when pressed
	        quitButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 40));
	        quitButton.setStyle("-fx-cursor: hand; -fx-border-color: #152546; -fx-border-width: 14px; -fx-background-color: #536F7B; -fx-text-fill: white");
	        quitButton.setPrefSize(320, 29);
	        quitButton.setTranslateX(-475);
	        quitButton.setTranslateY(500);
	        epilogue.setRight(quitButton);
	        quitButton.setOnAction(event -> {
	            System.exit(0);
	        });

	        Label[] fullWinArr = {totalScoreLabel,label1a, label1b, label1c, label1d};
	        Label[] goodTryArr = {totalScoreLabel,label2a, label2b, label2c, label2d};
	        Label[] anAttemptArr = {totalScoreLabel, label3a, label3b, label3c, label3d};
	        Label[] yikesArr = {totalScoreLabel, label4a, label4b, label4c, label4d};

	        for (Label x : fullWinArr){
	            x.setFont(Font.font("SansSerif", FontWeight.BOLD, 36));
	            x.setStyle("-fx-fill: black");
	        }

	        for(Label x : goodTryArr){
	            x.setFont(Font.font("SansSerif", FontWeight.BOLD, 36));
	            x.setStyle("-fx-fill: black");
	        }

	        for (Label x : anAttemptArr){
	            x.setFont(Font.font("SansSerif", FontWeight.BOLD, 36));
	            x.setStyle("-fx-fill: black");
	        }

	        for (Label x : yikesArr){
	            x.setFont(Font.font("SansSerif", FontWeight.BOLD, 36));
	            x.setStyle("-fx-fill: black");
	        }


	        VBox epilogueText = new VBox();
	        //epilogueText.setStyle("-fx-border-width: 5px; -fx-border-color: black");
	        epilogueText.setPadding(new Insets(5));
	        epilogueText.setTranslateY(-70);
	        epilogueText.setTranslateX(-150);
	        epilogueText.setMaxHeight(400);
	        epilogueText.setMinWidth(875);


	        epilogueText.setAlignment(Pos.CENTER);
	        Label[][] arrayHolder = { new Label[0] };
	        epilogueText.getChildren().addAll(arrayHolder[0]);

	            if (totalScore < 100) {
	                epilogueText.getChildren().addAll(yikesArr);
	            } else if (totalScore < 250) {
	                epilogueText.getChildren().addAll(anAttemptArr);
	            } else if (totalScore < 500) {
	                epilogueText.getChildren().addAll(goodTryArr);
	            } else if (totalScore < 1000) {
	                epilogueText.getChildren().addAll(fullWinArr);
	            }


	        for (Node node : epilogueText.getChildren()) {
	            //((Label) node).setVisible(false);
	        }

	        int[] currentLabelIndex = { 0 };

	        epilogueScene.setOnKeyPressed(event -> {
	            if (event.getCode() == KeyCode.N) {
	                int index = currentLabelIndex[0]++;
	                if (index < epilogueText.getChildren().size()) {
	                    Node node = epilogueText.getChildren().get(index);
	                    if (node instanceof Label) {
	                        ((Label) node).setVisible(true);
	                    }
	                }
	            }
	        });

	        epilogue.setCenter(epilogueText);
	    }

	public Scene getEpilogueScene() {
		return epilogueScene;
	}

}
