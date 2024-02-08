package application;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MainSceneHandler {

	private int turnNumber = 0;
	private int movement = 0;
	private boolean canMove = false;
	private boolean canRoll = true;
	private BoardHandler boardHandler;
	private ImageView dice = new ImageView();
	private Text diceText = new Text("Click To Roll!");
	private Button endTurnButton = new Button("End Turn");

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
		GridPane root = new GridPane();
		Scene mainScene = new Scene(root, 1200, 800);
		mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		boardHandler = new BoardHandler(this);
		Pane board = boardHandler.makeSquareBallGroup(200);
		root.add(board, 0, 0);
		board.setTranslateX(550);
		board.setTranslateY(400);

		diceText.setFont(Font.font("Arial", FontWeight.BOLD, 20));

		root.add(diceText, 0, 0);
		diceText.setTranslateX(950);
		diceText.setTranslateY(390);

		endTurnButton.setStyle(
				"-fx-cursor: hand; -fx-border-color: #152546; -fx-border-width: 10px; -fx-background-color: #536F7B; -fx-text-fill: white;");
		endTurnButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 18));
		endTurnButton.setPrefSize(128, 48);
		endTurnButton.setVisible(false);

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
		double diceX = 950;
		double diceY = 450;

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

		root.add(dice, 0, 0);
		root.add(diceBar, 0, 0);
		root.add(diceBarBorder, 0, 0);
		root.add(endTurnButton, 0, 0);
		dice.setTranslateX(diceX);
		dice.setTranslateY(diceY);
		diceBar.setTranslateX(originalBarX);
		diceBar.setTranslateY(550);
		diceBarBorder.setTranslateX(originalBarX);
		diceBarBorder.setTranslateY(550);
		endTurnButton.setTranslateX(originalBarX);
		endTurnButton.setTranslateY(560);

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

		return mainScene;
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
