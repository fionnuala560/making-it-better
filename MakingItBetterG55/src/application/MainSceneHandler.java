package application;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainSceneHandler {

	private int turnNumber = 0;
	private int movement = 0;
	private boolean canMove = false;
	private boolean canRoll = true;
	private int eventIndex = -1;

	private BoardHandler boardHandler;
	private OptionsMenu optionsMenu;
	private ImageView dice = new ImageView();
	private BorderPane root;
	private Text diceText = new Text("Click To Roll!");
	private Button endTurnButton = new Button("End Turn");
	private Player[] players;
	private BorderPane eventPane;
	private EventDisplayer eventDisplayer;
	private GridPane grid = new GridPane();



	private Event[] events = new Event[] { new Event("Twisted Ankle", "You've stepped in a small hole covered by grass"
			+ " and hurt your ankle. It should heal on it's own soon, but going forward it's probably best to stick to"
			+ " the paths when you can.",
			new EventOption[] { new EventOption("Ouch!", new int[] { -10, 0, 0, 0 }, new int[] { 0, 0, 0, 0 }) },
			false),

			new Event("Lucky Penny",
					"You've spotted a small glimer on the path. You reach down to investigate and discover"
							+ " it's a coin! Looks like luck's on your side.",
					new EventOption[] { new EventOption("Nice!", new int[] { 0, 0, 0, 5 }, new int[] { 0, 0, 0, 0 }) },
					false),

			new Event("Sticky Situation",
					"You're happy to see that there's more sap than you were expecting ready to be collected, but less"
							+ " excited to find out tonight's weather would make it a difficult and dangerous job.",
					new EventOption[] {
							new EventOption("Come back another\nday", new int[] { 0, 0, 0, 0 },
									new int[] { 0, 0, 0, 0 }),
							new EventOption("Better not stay\ntoo long", new int[] { -5, 0, 20, 0 },
									new int[] { 35, 0, 0, 0 }),
							new EventOption("Collect it ALL!", new int[] { -20, -2, 45, 0 }, new int[] { 40, 0, 0, 0 }),
							new EventOption("Collect it carefully", new int[] { -10, 0, 45, 0 },
									new int[] { 40, 50, 0, 0 }) },
					false),

			new Event("Lousy Yield",
					"After lots of work and waiting, it's finally time to harvest. Maybe it was something about the"
							+ " weather or in the soil, or just plain bad luck, but either way you can't help but feel a bit disapointed in"
							+ " your yield.",
					new EventOption[] {
							new EventOption("At least it's something", new int[] { 0, 0, 10, 0 },
									new int[] { 0, 0, 0, 0 }),
							new EventOption("Make the most of it", new int[] { 0, 0, 20, 0 },
									new int[] { 0, 60, 0, 0 }), },
					false),

			new Event("Adequate Yield",
					"After lots of work and waiting, it's finally time to harvest. It was an average crop nothing to complain"
							+ " or brag about, and you feel decidely content with your yield.",
					new EventOption[] {
							new EventOption("It's honest work", new int[] { 0, 0, 30, 0 }, new int[] { 0, 0, 0, 0 }), },
					false),

			new Event("Mighty Yield",
					"After lots of work and waiting, it's finally time to harvest. It must be something to do with how"
							+ " hard you worked or well you planned, or just plain superiority over your peers, but either way you can't"
							+ " help but feel proud of your yield.",
					new EventOption[] {
							new EventOption("Hard work pays off", new int[] { 0, 0, 50, 0 }, new int[] { 0, 0, 0, 0 }),
							new EventOption("I'm the best!", new int[] { 0, -1, 55, 0 }, new int[] { 0, 0, 0, 0 }) },
					false),

			new Event("Shop", "A place to sell your wares and purchase products with your profits.",
					new EventOption[] {
							new EventOption("Sell Goods", new int[] { 0, 0, -10, 5 }, new int[] { 0, 0, 10, 0 },
									new boolean[] { true, true, true, true }, -1, false),
							new EventOption("Buy Medicine", new int[] { 30, 0, 0, -10 }, new int[] { 0, 0, 0, 10 },
									new boolean[] { true, true, true, true }, -1, false),
							new EventOption("Buy Raspberry Pi", new int[] { 0, 0, 0, -60 }, new int[] { 0, 0, 0, 60 },
									new boolean[] { true, false, false, false }, 1, false),
							new EventOption("Buy Books", new int[] { 0, 0, 0, -20 }, new int[] { 0, 0, 0, 20 },
									new boolean[] { false, true, false, false }, -1, false),
							new EventOption("Buy Motorcycle", new int[] { 0, 0, 0, -60 }, new int[] { 0, 0, 0, 60 },
									new boolean[] { false, false, true, false }, 1, false), },
					true),

	};

	public MainSceneHandler(OptionsMenu optionsMenu, Player[] players) {
		this.players = players;
		this.optionsMenu = optionsMenu;
		eventDisplayer = new EventDisplayer(players);
	}

	public void handleTurn() {
		turnNumber++;
		boardHandler.moveToNextPlayer(turnNumber % 4);
		switchPlayerUI();
	}

	public void landedNextTurn() {
		Tools.linearFadeIn(dice, 1);
		Tools.linearFadeIn(diceText, 1);
		canRoll = true;
		dice.setViewport(new Rectangle2D(((int) (Math.random() * 6) * 128), 0, 128, 128));
		diceText.setText("Click To Roll!");
	}

	public void landed() {
		landed(-1);
	}

	public void landed(int eventIndex) {
		switch (eventIndex) {
		case -2:
			Tools.linearFadeOut(eventPane, .5d);
			this.eventIndex = -1;
			switchPlayerUI();
			// falls through
		case -1:
			if (movement > 0) {
				this.canMove = true;
			} else {
				handleTurn();
			}
			break;
		default:
			grid.getChildren().remove(eventPane);
			eventPane = eventDisplayer.openEventPopup(events[eventIndex], turnNumber % 4, this);
			eventPane.setTranslateX(275);
			eventPane.setTranslateY(100);
			if(this.eventIndex != eventIndex) {
				Tools.linearFadeIn(eventPane, .5d);
			}
			this.eventIndex = eventIndex;
			grid.add(eventPane, 0, 0);
			break;
		}
	}
	
	public int getEventIndex() {
		return eventIndex;
	}

	public Scene makeMainScene() {
		root = new BorderPane();
		root.setStyle("-fx-background-image: url('/woodbackground.jpg');");
		root.setCenter(grid);
		Scene mainScene = new Scene(root, 1200, 800);
		mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		boardHandler = new BoardHandler(this);
		Pane board = boardHandler.makeSquareBallGroup(165);
		grid.add(board, 0, 0);
		board.setTranslateX(500);
		board.setTranslateY(320);

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


		double originalBarX = 850;
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
				double elapsedSeconds = (now - lastUpdate) / 1_000_000_000.0;
				if ((diceBarScaler[0] + elapsedSeconds) > 1) {
					diceBarScaler[0] = 1;
					translateX = originalBarX;
				} else {
					translateX += (64 * elapsedSeconds);
					diceBarScaler[0] += elapsedSeconds;
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
				double elapsedSeconds = (now - lastUpdate) / 1_000_000_000.0;
				scaler += elapsedSeconds;
				if (Math.round(scaler * 100) % 10 == 0) {
					tempMovement = (int) (Math.random() * 6) + 1;
					dice.setViewport(new Rectangle2D((tempMovement * 128) - 128, 0, 128, 128));
				}
				dice.setTranslateX(diceX + ((Math.random() * shakeFactor) - (shakeFactor / 2)));
				dice.setTranslateY(diceY + ((Math.random() * shakeFactor) - (shakeFactor / 2)));
				dice.setRotate(scaler * rotateFactor);
				if ((scaler + elapsedSeconds) > 1) {
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
		diceBar.setTranslateY(350);
		diceBarBorder.setTranslateX(originalBarX);
		diceBarBorder.setTranslateY(350);
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

		Node bottomPlayerBox = createPlayerPanel(turnNumber % 4);
		Node rightPlayerBox = createPlayerPanel((turnNumber + 1) % 4);
		Node topPlayerBox = createPlayerPanel((turnNumber + 2) % 4);
		Node leftPlayerBox = createPlayerPanel((turnNumber + 3) % 4);

		formatPlayerPanel(bottomPlayerBox, 0);
		formatPlayerPanel(rightPlayerBox, 1);
		formatPlayerPanel(topPlayerBox, 2);
		formatPlayerPanel(leftPlayerBox, 3);

		eventPane = eventDisplayer.openEventPopup(events[0], turnNumber % 4, this);
		eventPane.setTranslateX(275);
		eventPane.setTranslateY(100);
		grid.add(eventPane, 0, 0);
		eventPane.setVisible(false);

		ImageView settingsButton = new ImageView(new Image("/setting.png", 64, 64, false, false));
		settingsButton.setStyle("-fx-cursor: hand;");
		settingsButton.setTranslateX(-50);
		settingsButton.setTranslateY(150);
		grid.add(settingsButton, 0, 1);
		settingsButton.setOnMouseClicked(e -> {
			Stage tempMain = (Stage) settingsButton.getScene().getWindow();
			tempMain.setScene(optionsMenu.getOptionsMenu(settingsButton.getScene()));
		});

		Button epilogueButton = new Button("Epilogue");
		epilogueButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 40));
		epilogueButton.setStyle("-fx-cursor: hand; -fx-border-color: #152546; -fx-border-width: 14px; -fx-background-color: #536F7B; -fx-text-fill: white;");
		epilogueButton.setPrefSize(320, 29);
		epilogueButton.setOnAction(event ->  {
			endGame(mainScene);
		});
		grid.add(epilogueButton,0,2);

		return mainScene;
	}

	public void switchPlayerUI() {
		Node newBottomPlayerBox = createPlayerPanel(turnNumber % 4);
		Node newRightPlayerBox = createPlayerPanel((turnNumber + 1) % 4);
		Node newTopPlayerBox = createPlayerPanel((turnNumber + 2) % 4);
		Node newLeftPlayerBox = createPlayerPanel((turnNumber + 3) % 4);

		formatPlayerPanel(newBottomPlayerBox, 0);
		formatPlayerPanel(newRightPlayerBox, 1);
		formatPlayerPanel(newTopPlayerBox, 2);
		formatPlayerPanel(newLeftPlayerBox, 3);
	}

	private void formatPlayerPanel(Node box, int panelIndex) {
		if (panelIndex % 2 == 0) {
			HBox.setHgrow(root, Priority.ALWAYS);
			((HBox) box).setMaxWidth((panelIndex == 0) ? 1000 : 500);
			((HBox) box).setPrefHeight((panelIndex == 0) ? 100 : 60);
			BorderPane.setAlignment(box, (panelIndex == 0) ? Pos.BOTTOM_CENTER : Pos.TOP_CENTER);
		} else {
			VBox.setVgrow(root, Priority.ALWAYS);
			((VBox) box).setMaxHeight(500);
			((VBox) box).setPrefWidth(70);
			BorderPane.setAlignment(box, (panelIndex == 1) ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
		}
		switch (panelIndex) {
		case 0:
			root.setBottom(box);
			break;
		case 1:
			root.setRight(box);
			break;
		case 2:
			root.setTop(box);
			break;
		case 3:
			root.setLeft(box);
		}
	}

	private Node createPlayerPanel(int playerIndex) {

		Player currentPlayer = players[playerIndex];
		String[] colors = { "lightskyblue", "lightgreen", "lightpink", "mediumpurple", "darkblue", "darkgreen",
				"deeppink", "indigo" };
		String backgroundColor = colors[playerIndex];
		String borderColor = colors[playerIndex + 4];

		// creates a label for each player resource
		Label nameLabel = new Label(currentPlayer.getPlayerName() + " ");
		Label healthLabel = new Label(Integer.toString(currentPlayer.getHealth()) + " ");
		Label educationLabel = new Label(Integer.toString(currentPlayer.getEducation()) + " ");
		Label goodsLabel = new Label(Integer.toString(currentPlayer.getGoods()) + " ");
		Label moneyLabel = new Label(Integer.toString(currentPlayer.getMoney()) + " ");
		Label scoreLabel = new Label (Integer.toString((currentPlayer.getScore())));

		Node playerPanel;

		if (Math.abs(playerIndex - turnNumber) % 2 == 0) {
			HBox hBox = new HBox();
			hBox.setPadding(new Insets(5));
			hBox.getChildren().addAll(nameLabel, healthLabel, educationLabel, goodsLabel, moneyLabel, scoreLabel);
			playerPanel = hBox;
		} else {
			VBox vBox = new VBox();
			vBox.setPadding(new Insets(5));
			vBox.getChildren().addAll(nameLabel, healthLabel, educationLabel, goodsLabel, moneyLabel, scoreLabel);
			playerPanel = vBox;
		}
		playerPanel.setStyle("-fx-background-color: " + backgroundColor + ";-fx-border-color: " + borderColor
				+ ";-fx-border-width: 5px;");

		return playerPanel;
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

	public int totalPlayerScore(Player[] players){
		int totalScore = 0;
		for(Player p : players){
			p.addGameOverScores();
			totalScore += p.getScore();
		}
		return  totalScore;
	}

	public void endGame(Scene mainScene){
		//check end of game conditions
		int totalScore = totalPlayerScore(players);
		displayTotalScore(mainScene, totalScore);
	}

	private void displayTotalScore(Scene mainScene, int totalScore){
		EpilogueScreen epilogueScreen = new EpilogueScreen(mainScene, optionsMenu);
		epilogueScreen.setTotalScore(totalScore);

		Stage primaryStage = (Stage) mainScene.getWindow();
		primaryStage.setScene(epilogueScreen.getEpilogueScene());

	}

}
