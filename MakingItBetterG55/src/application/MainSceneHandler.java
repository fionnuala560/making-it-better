package application;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
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
	private BoardHandler boardHandler;

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
	}
	
	public void landed() {
		if(movement > 0) {
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
		
		Text movementText = new Text(Integer.toString(movement));
		movementText.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		
		root.add(movementText, 0, 0);
		movementText.setTranslateX(1000);
		movementText.setTranslateY(350);

		double originalBarX = 950;
		double diceX = 950;
		double diceY = 450;
		
		ImageView diceBar = new ImageView(new Image("/riceTile.png", 128, 32, false, false));
		ImageView dice = new ImageView();
		dice. setImage(new Image("/die.png"));
		dice.setViewport(new Rectangle2D(0,0,128,128));
		diceBar.setVisible(false);
		double [] diceBarScaler = {0};

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
				dice.setTranslateX(diceX + ((Math.random() * shakeFactor * diceBarScaler[0]) - (shakeFactor / 2 * diceBarScaler[0])));
				dice.setTranslateY(diceY + ((Math.random() * shakeFactor * diceBarScaler[0]) - (shakeFactor / 2 * diceBarScaler[0])));
				diceBar.setScaleX(diceBarScaler[0]);
				diceBar.setTranslateX(translateX);
				lastUpdate = now;
			}

			@Override
			public void stop() {
				diceBar.setVisible(false);
				dice.setTranslateX(diceX);
				dice.setTranslateY(diceY);
				translateX = originalBarX;
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
				if(Math.round(scaler * 100) % 10 == 0) {
					tempMovement = (int) (Math.random() * 6) + 1;
					dice.setViewport(new Rectangle2D((tempMovement * 128) - 128, 0, 128, 128));
				}
				dice.setTranslateX(diceX + ((Math.random() * shakeFactor) - (shakeFactor / 2)));
				dice.setTranslateY(diceY + ((Math.random() * shakeFactor) - (shakeFactor / 2 )));
				dice.setRotate(scaler * rotateFactor);
				lastUpdate = now;
				if ((scaler + elaspedSeconds) > 1) {
					scaler = 1;
					stop();
				}
			}

			@Override
			public void stop() {
				dice.setRotate(0);
				dice.setTranslateX(diceX);
				dice.setTranslateY(diceY);
				movement = tempMovement;
				canMove = true;
				movementText.setText(Integer.toString(movement));
				super.stop();
			}
		};

		dice.setOnMousePressed(e -> {
			diceBarAnimation.start();
		});
		dice.setOnMouseReleased(e -> {
			diceBarAnimation.stop();
			rollAnimation.start();
		});

		root.add(dice, 0, 0);
		root.add(diceBar, 0, 0);
		dice.setTranslateX(diceX);
		dice.setTranslateY(diceY);
		diceBar.setTranslateX(originalBarX);
		diceBar.setTranslateY(550);
		
		mainScene.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {

			if (event.getCode() == KeyCode.A && canMove == true) {
				int changeInMovement = boardHandler.tryToMove(board, 0, movement);
				if (changeInMovement != -1) {
					canMove = false;
					movement -= changeInMovement;
					movementText.setText(Integer.toString(movement));
				}
			}
			if (event.getCode() == KeyCode.W && canMove == true) {
				int changeInMovement = boardHandler.tryToMove(board, 1, movement);
				if (changeInMovement != -1) {
					canMove = false;
					movement -= changeInMovement;
					movementText.setText(Integer.toString(movement));
				}
			}
			if (event.getCode() == KeyCode.D && canMove == true) {
				int changeInMovement = boardHandler.tryToMove(board, 2, movement);
				if (changeInMovement != -1) {
					canMove = false;
					movement -= changeInMovement;
					movementText.setText(Integer.toString(movement));
				}
			}
			if (event.getCode() == KeyCode.S && canMove == true) {
				int changeInMovement = boardHandler.tryToMove(board, 3, movement);
				if (changeInMovement != -1) {
					canMove = false;
					movement -= changeInMovement;
					movementText.setText(Integer.toString(movement));
				}
			}

		});

		return mainScene;
	}
}
