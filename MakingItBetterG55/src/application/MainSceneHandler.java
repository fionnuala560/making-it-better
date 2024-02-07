package application;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public class MainSceneHandler {

	private int turnNumber = 0;
	private boolean canMove = true;
	private BoardHandler boardHandler;

	public void handleTurn() {
		if(turnNumber > 0) {
			boardHandler.moveToNextPlayer(turnNumber % 4);
		}
		switch(turnNumber % 4) {
			case 0:
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
		}
		canMove = true;
		turnNumber++;
	}

	public Scene makeMainScene() {
		BorderPane root = new BorderPane();
		Scene mainScene = new Scene(root, 1200, 800);
		root.setStyle("-fx-background-image: url('/woodbackground.jpg');");
		mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		boardHandler = new BoardHandler(this);
		Pane board = boardHandler.makeSquareBallGroup(200);
		root.setCenter(board);
		board.setTranslateX(550);
		board.setTranslateY(400);


		mainScene.addEventHandler(KeyEvent.KEY_RELEASED,(KeyEvent event)->{

			if (event.getCode() == KeyCode.A && canMove == true) {
				boardHandler.animateSquareBallMovement(board,0);
				canMove = false;
			}
			if (event.getCode() == KeyCode.W && canMove == true) {
				boardHandler.animateSquareBallMovement(board,1);
				canMove = false;
			}
			if (event.getCode() == KeyCode.D && canMove == true) {
				boardHandler.animateSquareBallMovement(board,2);
				canMove = false;
			}
			if (event.getCode() == KeyCode.S && canMove == true) {
				boardHandler.animateSquareBallMovement(board,3);
				canMove = false;
			}
			if (event.getCode() == KeyCode.P && canMove == true) {
				boardHandler.moveToNextPlayer(turnNumber % 4);
				canMove = true;
			}

		});



		HBox bottomPlayerBox = createPlayerPanel(new Player('s', "Joe", false));
		HBox topPlayerBox = createPlayerPanel(new Player('s', "Player1", false));
		VBox rightPlayerBox = createPlayerPanelSides(new Player('s', "Player2", false));
		VBox leftPlayerBox = createPlayerPanelSides(new Player('s', "Player3", false));

		// Set positions for the player HBoxes
		HBox.setHgrow(root, Priority.ALWAYS);
		bottomPlayerBox.setMaxWidth(1000);
		bottomPlayerBox.setPrefHeight(75);
		root.setBottom(bottomPlayerBox);

		BorderPane.setAlignment(bottomPlayerBox,Pos.CENTER);


		topPlayerBox.setPrefHeight(60);
		root.setTop(topPlayerBox);
		BorderPane.setAlignment(topPlayerBox, Pos.TOP_CENTER);

		VBox.setVgrow(root, Priority.ALWAYS);
		rightPlayerBox.setMaxHeight(500);
		rightPlayerBox.setPrefWidth(70);
		root.setRight(rightPlayerBox);
		BorderPane.setAlignment(rightPlayerBox, Pos.CENTER_RIGHT);


		VBox.setVgrow(root, Priority.ALWAYS);
		leftPlayerBox.setMaxHeight(500);
		leftPlayerBox.setPrefWidth(70);
		root.setLeft(leftPlayerBox);
		BorderPane.setAlignment(leftPlayerBox, Pos.CENTER_LEFT);




		return mainScene;
	}

	private HBox createPlayerPanel(Player currentPlayer){
		HBox playerPanel = new HBox(10);
		playerPanel.setPadding(new Insets(10));

		Label playerName = new Label(currentPlayer.getPlayerName() + ", " + currentPlayer.getPlayerType());
		String playerStatsString = currentPlayer.getscore() + " " + currentPlayer.getMoney() + " " + currentPlayer.getGoods() + " " + currentPlayer.getEducation() + " " + currentPlayer.getHealth();
		Label playerStats = new Label(playerStatsString);

		playerPanel.getChildren().addAll(playerName,playerStats);

		playerPanel.setMaxWidth(500);
		playerPanel.setMaxHeight(1000);
		playerPanel.setStyle("-fx-background-color: lightgreen;-fx-border-color: darkgreen; -fx-border-width: 5px;");

		HBox.setHgrow(playerPanel,Priority.NEVER);

		return playerPanel;
	}

	private VBox createPlayerPanelSides(Player currentPlayer){
		VBox playerPanel = new VBox();
		playerPanel.setStyle("-fx-background-color: lightgreen;-fx-border-color: darkgreen; -fx-border-width: 5px;");
		playerPanel.setPadding(new Insets(5));

		Label playerName = new Label(currentPlayer.getPlayerName() + ", " + currentPlayer.getPlayerType());
		String playerStatsString = currentPlayer.getscore() + " " + currentPlayer.getMoney() + " " + currentPlayer.getGoods() + " " + currentPlayer.getEducation() + " " + currentPlayer.getHealth();
		Label playerStats = new Label(playerStatsString);



		playerPanel.getChildren().addAll(playerName,playerStats);

		playerPanel.setMaxWidth(100);


		return playerPanel;
	}
}
