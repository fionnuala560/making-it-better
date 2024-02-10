package application;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MainSceneHandler {

	private int turnNumber = 0;
	private int movement = 0;
	private boolean canMove = false;
	private boolean canRoll = true;
	private BoardHandler boardHandler;
	private ImageView dice = new ImageView();
	private Text diceText = new Text("Click To Roll!");
	private Button endTurnButton = new Button("End Turn");
	int[] currentPlayerIndex = {0};

	public void handleTurn() {
		if (turnNumber > 0) {
			boardHandler.moveToNextPlayer(turnNumber % 4);
		}
		switch (turnNumber % 4) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		}
		turnNumber++;
	}

	public void landedNextTurn() {
		Tools.linearFadeIn(dice, 1);
		Tools.linearFadeIn(diceText, 1);
		canRoll = true;
		dice.setViewport(new Rectangle2D(((int) (Math.random() * 6) * 128), 0, 128, 128));
		diceText.setText("Click To Roll!");
	}

	public void landed() {
		if (movement > 0) {
			this.canMove = true;
		} else {
			handleTurn();
		}
	}

	public Scene makeMainScene() {
		BorderPane root  = new BorderPane();
		GridPane grid = new GridPane();
		root.setCenter(grid);
		Scene mainScene = new Scene(root, 1200, 800);
		mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		boardHandler = new BoardHandler(this);
		Pane board = boardHandler.makeSquareBallGroup(150);
		grid.add(board, 0, 0);
		board.setTranslateX(500);
		board.setTranslateY(300);

		diceText.setFont(Font.font("Arial", FontWeight.BOLD, 20));

		grid.add(diceText, 0, 0);
		diceText.setTranslateX(850);
		diceText.setTranslateY(190);

		endTurnButton.setStyle(
				"-fx-cursor: hand; -fx-border-color: #152546; -fx-border-width: 10px; -fx-background-color: #536F7B; -fx-text-fill: white;");
		endTurnButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 18));
		endTurnButton.setPrefSize(128, 48);
		endTurnButton.setVisible(false);
		endTurnButton.setTranslateX(850);
		endTurnButton.setTranslateY(200);

		endTurnButton.setOnAction(event -> {
			if (canMove && endTurnButton.isVisible()) {
				canMove = false;
				movement = 0;
				Tools.linearFadeOut(dice, .5);
				Tools.linearFadeOut(diceText, .5);
				Tools.linearFadeOut(endTurnButton, .5);
				landed();
			}
		});

		double originalBarX = 950;
		double diceX = 850;
		double diceY = 250;

		ImageView diceBarBorder = new ImageView(new Image("/diceBarBorder.png", 128, 32, false, false));
		ImageView diceBar = new ImageView(new Image("/diceBar.png", 128, 32, false, false));
		dice.setImage(new Image("/die.png"));
		dice.setViewport(new Rectangle2D(0, 0, 128, 128));
		diceBar.setVisible(false);
		diceBarBorder.setVisible(false);
		double[] diceBarScaler = { 0 };

		AnimationTimer diceBarAnimation = new AnimationTimer() {
			private long lastUpdate;
			double translateX = 0;
			double shakeFactor = 10;

			@Override
			public void start() {
				diceBarScaler[0] = 0;
				lastUpdate = System.nanoTime();
				translateX = originalBarX - 64;
				diceBar.setScaleX(0);
				diceBar.setVisible(true);
				diceBarBorder.setVisible(true);
				diceText.setVisible(false);
				super.start();
			}

			@Override
			public void handle(long now) {
				double elaspedSeconds = (now - lastUpdate) / 1_000_000_000.0;
				if ((diceBarScaler[0] + elaspedSeconds) > 1) {
					diceBarScaler[0] = 1;
					translateX = originalBarX;
				} else {
					translateX += (64 * elaspedSeconds);
					diceBarScaler[0] += elaspedSeconds;
				}
				dice.setTranslateX(diceX
						+ ((Math.random() * shakeFactor * diceBarScaler[0]) - (shakeFactor / 2 * diceBarScaler[0])));
				dice.setTranslateY(diceY
						+ ((Math.random() * shakeFactor * diceBarScaler[0]) - (shakeFactor / 2 * diceBarScaler[0])));
				diceBar.setScaleX(diceBarScaler[0]);
				diceBar.setTranslateX(translateX);
				lastUpdate = now;
			}

			@Override
			public void stop() {
				diceBar.setVisible(false);
				diceBarBorder.setVisible(false);
				dice.setTranslateX(diceX);
				dice.setTranslateY(diceY);
				translateX = originalBarX;
				canRoll = false;
				super.stop();
			}
		};

		AnimationTimer rollAnimation = new AnimationTimer() {
			private long lastUpdate;
			double scaler;
			double rotateFactor;
			double shakeFactor;
			int tempMovement = 1;

			@Override
			public void start() {
				rotateFactor = 720 + (720 * diceBarScaler[0]);
				shakeFactor = 5 + (10 * diceBarScaler[0]);
				scaler = 0;
				lastUpdate = System.nanoTime();
				super.start();
			}

			@Override
			public void handle(long now) {
				double elaspedSeconds = (now - lastUpdate) / 1_000_000_000.0;
				scaler += elaspedSeconds;
				if (Math.round(scaler * 100) % 10 == 0) {
					tempMovement = (int) (Math.random() * 6) + 1;
					dice.setViewport(new Rectangle2D((tempMovement * 128) - 128, 0, 128, 128));
				}
				dice.setTranslateX(diceX + ((Math.random() * shakeFactor) - (shakeFactor / 2)));
				dice.setTranslateY(diceY + ((Math.random() * shakeFactor) - (shakeFactor / 2)));
				dice.setRotate(scaler * rotateFactor);
				if ((scaler + elaspedSeconds) > 1) {
					scaler = 1;
					stop();
				}
				lastUpdate = now;
			}

			@Override
			public void stop() {
				dice.setRotate(0);
				dice.setTranslateX(diceX);
				dice.setTranslateY(diceY);
				movement = tempMovement;
				canMove = true;
				Tools.linearFadeIn(diceText, .5);
				Tools.linearFadeIn(endTurnButton, .5);
				diceText.setText("Movement Left:");
				super.stop();
			}
		};

		dice.setOnMousePressed(e -> {
			if (canRoll && dice.isVisible()) {
				diceBarAnimation.start();
			}
		});
		dice.setOnMouseReleased(e -> {
			if (canRoll) {
				diceBarAnimation.stop();
				rollAnimation.start();
			}
		});

		grid.add(dice, 0, 0);
		grid.add(diceBar, 0, 0);
		grid.add(diceBarBorder, 0, 0);
		grid.add(endTurnButton, 0, 0);
		dice.setTranslateX(diceX);
		dice.setTranslateY(diceY);
		diceBar.setTranslateX(originalBarX);
		diceBar.setTranslateY(550);
		diceBarBorder.setTranslateX(originalBarX);
		diceBarBorder.setTranslateY(550);
		endTurnButton.setTranslateX(850);
		endTurnButton.setTranslateY(350);



		mainScene.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {

			if (event.getCode() == KeyCode.A && canMove == true) {
				processMoveInput(board, 0);
			}
			if (event.getCode() == KeyCode.W && canMove == true) {
				processMoveInput(board, 1);
			}
			if (event.getCode() == KeyCode.D && canMove == true) {
				processMoveInput(board, 2);
			}
			if (event.getCode() == KeyCode.S && canMove == true) {
				processMoveInput(board, 3);
			}

		});

		Player Engineer = new Player('e', "Homer", false);
		Player Teacher = new Player('t', "Marge", false);
		Player Parent = new Player('p', "Bart", false);
		Player Student = new Player('s', "Lisa", false);

		Player[] allPlayers  = new Player[]{Engineer, Teacher, Parent, Student};
		endTurnButton.setOnAction(event -> switchPlayer(allPlayers, root));

		HBox bottomPlayerBox = createPlayerPanel(allPlayers[0]);
		VBox rightPlayerBox = createPlayerPanelSides(allPlayers[1]);
		HBox topPlayerBox = createPlayerPanel(allPlayers[2]);
		VBox leftPlayerBox = createPlayerPanelSides(allPlayers[3]);

		formatBottomPanel(bottomPlayerBox, root);
		formatRightPanel(rightPlayerBox, root);
		formatTopPanel(topPlayerBox, root);
		formatLeftPanel(leftPlayerBox, root);

		return mainScene;
	}

	private void switchPlayer(Player[] allPlayers, BorderPane root){
		currentPlayerIndex[0] = (currentPlayerIndex[0]+1)%4;
		HBox newBottomPlayerBox = createPlayerPanel(allPlayers[currentPlayerIndex[0]]);
		VBox newRightPlayerBox = createPlayerPanelSides(allPlayers[(currentPlayerIndex[0] + 1) % 4]);
		HBox newTopPlayerBox = createPlayerPanel(allPlayers[(currentPlayerIndex[0] + 2) % 4]);
		VBox newLeftPlayerBox = createPlayerPanelSides(allPlayers[(currentPlayerIndex[0] + 3) % 4]);

		formatBottomPanel(newBottomPlayerBox, root);
		formatRightPanel(newRightPlayerBox, root);
		formatTopPanel(newTopPlayerBox, root);
		formatLeftPanel(newLeftPlayerBox,root);
	}



	private void formatLeftPanel(VBox box, BorderPane root){
		VBox.setVgrow(root, Priority.ALWAYS);
		box.setMaxHeight(500);
		box.setPrefWidth(70);
		root.setLeft(box);
		BorderPane.setAlignment(box, Pos.CENTER_LEFT);
	}

	private void formatRightPanel(VBox box, BorderPane root){
		VBox.setVgrow(root, Priority.ALWAYS);
		box.setMaxHeight(500);
		box.setPrefWidth(70);
		root.setRight(box);
		BorderPane.setAlignment(box, Pos.CENTER_RIGHT);
	}

	private void formatTopPanel(HBox box, BorderPane root){
		box.setPrefHeight(60);
		box.setMaxWidth(500);
		root.setTop(box);
		BorderPane.setAlignment(box, Pos.TOP_CENTER);
	}

	private void formatBottomPanel(HBox box, BorderPane root){
		HBox.setHgrow(root, Priority.ALWAYS);
		box.setMaxWidth(1000);
		box.setPrefHeight(100);
		root.setBottom(box);
		BorderPane.setAlignment(box, Pos.BOTTOM_CENTER);
	}

	private HBox createPlayerPanel(Player currentPlayer){

		String backgroundColor = getPlayerColor(currentPlayer.getPlayerType());
		String borderColor = getBorderColor(currentPlayer.getPlayerType());

		//creates a horizontal box (for the top/bottom of the screen
		HBox playerPanel = new HBox();
		playerPanel.setStyle("-fx-background-color: " +  backgroundColor + ";-fx-border-color: " + borderColor + ";-fx-border-width: 5px;");


		playerPanel.setPadding(new Insets(5));

		//creates a label for each player resource
		Label nameLabel = new Label(currentPlayer.getPlayerName());
		Label scoreLabel = new Label(Integer.toString(currentPlayer.getscore()));
		Label moneyLabel = new Label(Integer.toString(currentPlayer.getMoney()));
		Label goodsLabel = new Label(Integer.toString(currentPlayer.getGoods()));
		Label educationsLabel = new Label(Integer.toString(currentPlayer.getEducation()));
		Label healthLabel = new Label(Integer.toString(currentPlayer.getHealth()));

		//adds all of the player resources to the player panel box
		playerPanel.getChildren().addAll(nameLabel, scoreLabel, moneyLabel, goodsLabel, educationsLabel, healthLabel);

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
		Label nameLabel = new Label(currentPlayer.getPlayerName());
		Label scoreLabel = new Label(Integer.toString(currentPlayer.getscore()));
		Label moneyLabel = new Label(Integer.toString(currentPlayer.getMoney()));
		Label goodsLabel = new Label(Integer.toString(currentPlayer.getGoods()));
		Label educationsLabel = new Label(Integer.toString(currentPlayer.getEducation()));
		Label healthLabel = new Label(Integer.toString(currentPlayer.getHealth()));

		//adds all of the player resources to the player panel box
		playerPanel.getChildren().addAll(nameLabel, scoreLabel, moneyLabel, goodsLabel, educationsLabel, healthLabel);

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
	private void processMoveInput(Pane board, int direction) {
		int changeInMovement = boardHandler.tryToMove(board, direction, movement);
		if (changeInMovement != -1) {
			canMove = false;
			movement -= changeInMovement;
			if ((movement * 128) - 128 < 0) {
				Tools.linearFadeOut(dice, .5);
				Tools.linearFadeOut(diceText, .5);
				Tools.linearFadeOut(endTurnButton, .5);
			} else {
				dice.setViewport(new Rectangle2D((movement * 128) - 128, 0, 128, 128));
			}
		}
	}

}
