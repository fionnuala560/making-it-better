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
import javafx.scene.paint.Color;

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
		Pane board = boardHandler.makeSquareBallGroup(150);
		root.setCenter(board);
		board.setTranslateX(500);
		board.setTranslateY(300);


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

		HBox bottomPlayerBox = createPlayerPanel(new Player('e', "Joe", false));
		HBox topPlayerBox = createPlayerPanel(new Player('t', "Player1", false));
		VBox rightPlayerBox = createPlayerPanelSides(new Player('p', "Player2", false));
		VBox leftPlayerBox = createPlayerPanelSides(new Player('s', "Player3", false));

		// Set positions and sizes for the player boxes

		//bottom box
		HBox.setHgrow(root, Priority.ALWAYS);
		bottomPlayerBox.setMaxWidth(1000);
		bottomPlayerBox.setPrefHeight(100);
		root.setBottom(bottomPlayerBox);
		BorderPane.setAlignment(bottomPlayerBox,Pos.CENTER);

		//top box
		topPlayerBox.setPrefHeight(60);
		topPlayerBox.setMaxWidth(500);
		root.setTop(topPlayerBox);
		BorderPane.setAlignment(topPlayerBox, Pos.TOP_CENTER);

		//right box
		VBox.setVgrow(root, Priority.ALWAYS);
		rightPlayerBox.setMaxHeight(500);
		rightPlayerBox.setPrefWidth(70);
		root.setRight(rightPlayerBox);
		BorderPane.setAlignment(rightPlayerBox, Pos.CENTER_RIGHT);

		//left box
		VBox.setVgrow(root, Priority.ALWAYS);
		leftPlayerBox.setMaxHeight(500);
		leftPlayerBox.setPrefWidth(70);
		root.setLeft(leftPlayerBox);
		BorderPane.setAlignment(leftPlayerBox, Pos.CENTER_LEFT);

		return mainScene;
	}

	private HBox createPlayerPanel(Player currentPlayer){

		String backgroundColor = getPlayerColor(currentPlayer.getPlayerType());
		String borderColor = getBorderColor(currentPlayer.getPlayerType());

		//creates a horizontal box (for the top/bottom of the screen
		HBox playerPanel = new HBox();
		playerPanel.setStyle("-fx-background-color: " +  backgroundColor + ";-fx-border-color: " + borderColor + ";-fx-border-width: 5px;");


		playerPanel.setPadding(new Insets(5));

		//creates a label for each player resource
		Label scoreLabel = new Label(Integer.toString(currentPlayer.getscore()));
		Label moneyLabel = new Label(Integer.toString(currentPlayer.getMoney()));
		Label goodsLabel = new Label(Integer.toString(currentPlayer.getGoods()));
		Label educationsLabel = new Label(Integer.toString(currentPlayer.getEducation()));
		Label healthLabel = new Label(Integer.toString(currentPlayer.getHealth()));

		//adds all of the player resources to the player panel box
		playerPanel.getChildren().addAll(scoreLabel, moneyLabel, goodsLabel, educationsLabel, healthLabel);

		return playerPanel;
	}

	private VBox createPlayerPanelSides(Player currentPlayer){

		String backgroundColor = getPlayerColor(currentPlayer.getPlayerType());
		String borderColor = getBorderColor(currentPlayer.getPlayerType());
		//creates a vertical box (for the left/right of the screen)
		VBox playerPanel = new VBox();
		playerPanel.setStyle("-fx-background-color: " +  backgroundColor + ";-fx-border-color: " + borderColor + ";-fx-border-width: 5px;");
		playerPanel.setPadding(new Insets(5));

		//creates a label for each player resource
		Label scoreLabel = new Label(Integer.toString(currentPlayer.getscore()));
		Label moneyLabel = new Label(Integer.toString(currentPlayer.getMoney()));
		Label goodsLabel = new Label(Integer.toString(currentPlayer.getGoods()));
		Label educationsLabel = new Label(Integer.toString(currentPlayer.getEducation()));
		Label healthLabel = new Label(Integer.toString(currentPlayer.getHealth()));

		//adds all of the player resources to the player panel box
		playerPanel.getChildren().addAll(scoreLabel, moneyLabel, goodsLabel, educationsLabel, healthLabel);

		return playerPanel;
	}

	private String getPlayerColor(char playerType){
		switch(playerType){
			case 'e':
				return "lightskyblue";
			case 't':
				return "lightgreen";
			case 'p':
				return "lightpink";
			case 's':
				return "mediumpurple";
		}

		return null;
	}

	private String getBorderColor(char playerType){
		switch(playerType){
			case 'e':
				return "darkblue";
			case 't':
				return "darkgreen";
			case 'p':
				return "deeppink";
			case 's':
				return "indigo";
		}

		return null;
	}


}
