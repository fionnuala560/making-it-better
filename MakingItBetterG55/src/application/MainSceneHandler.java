package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainSceneHandler {

	private TextRetriever tr = new TextRetriever();

	private int turnNumber = 0;
	private int movement = 0;
	private int modalTypeActive = -1;
	private boolean canMove = false;
	private boolean canRoll = true;
	private boolean endReleased = false;
	private boolean canPressEvent = true;
	private int eventIndex = -1;
	private int screenWidth = 1280;
	private int screenHeight = 720;
	private int language = 1;
	private int fullscreen = 0;
	private float widthPortion = (float) screenWidth / 3840.0f;
	private float heightPortion = (float) screenHeight / 2160.0f;

	private BoardHandler boardHandler;
	private ImageView die = new ImageView();

	private Text[] playerText = new Text[24];
	private Text[] eventText = new Text[22];
	private Text[] resourceTextDescs = new Text[] { new Text(), new Text(), new Text(), new Text() };

	private ImageView playerHUDs = new ImageView(new Image("/HUDs.png"));
	private ImageView nextUI = new ImageView(new Image("/nextUI.png"));
	private ImageView eventUI = new ImageView(new Image("/eventUI.png"));
	private ImageView endButtonTop = new ImageView(new Image("/buttonTops.png"));
	private ImageView endButtonBottom = new ImageView(new Image("/buttonBottoms.png"));
	private ImageView timeUI = new ImageView(new Image("/timeUI.png"));
	private Text timeText = new Text();
	private ImageView[] items = new ImageView[3];
	private ImageView[] eventButtons = new ImageView[8];
	private ImageView[] resourceLogos = new ImageView[16];
	private ImageView[] iconButtons = { new ImageView(new Image("/settingsIcon.png")),
			new ImageView(new Image("/helpIcon.png")), new ImageView(new Image("/objectivesIcon.png")) };
	private Node[] modalNodes = { new ImageView(new Image("/modalUI.png")), new Text(), new Text(), new Text(),
			new Text(), new Text(), new Text(), new Text(), new Text(), new Text(), new Text(),
			new ImageView(new Image("/flags.png")), new ImageView(new Image("/modalButton.png")),
			new ImageView(new Image("/modalButton.png")), new ImageView(new Image("/modalButton.png")), new Text(),
			new Text(), new Text(), new ImageView(new Image("/checkBox.png"))};

	ArrayList<Node> eventNodes = new ArrayList<Node>();

	private Player[] players;
	private Pane pane = new Pane();

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
									new boolean[] { true, false, false, false }, 1, false, "", 0),
							new EventOption("Buy Books", new int[] { 0, 0, 0, -20 }, new int[] { 0, 0, 0, 20 },
									new boolean[] { false, true, false, false }, -1, false),
							new EventOption("Buy Motorcycle", new int[] { 0, 0, 0, -60 }, new int[] { 0, 0, 0, 60 },
									new boolean[] { false, false, true, false }, 1, false,
									"Always roll a 3 or above for movement.", 0), },
					true),
			
			new Event("School", "A place where young pupils are inspired and educated which the community is looking to improve.",
					new EventOption[] {
							new EventOption("Study", new int[] { 0, 8, 10, 0 }, new int[] { 0, 0, 10, 0 },
									new boolean[] { false, false, false, true }, -1, false, "", 1),
							new EventOption("Install Solar Panels", new int[] { 0, 0, 0, -60 }, new int[] { 0, 0, 0, 60 },
									new boolean[] { true, false, false, false }, 1, false, "", 1),
							new EventOption("Stock Nutrients", new int[] { 0, 0, 0, -60 }, new int[] { 0, 0, 0, 60 },
									new boolean[] { true, false, false, false }, 1, false, "", 1),
							new EventOption("Teach", new int[] { 0, -5, -5, 0 }, new int[] { 0, 60, 5, 0 },
									new boolean[] { false, true, false, false }, 1, false),
							new EventOption("Review Material", new int[] { 0, 5, -5, 0 }, new int[] { 0, 0, 5, 0 },
									new boolean[] { false, true, false, false }, -1, false),},
					true),
			
			new Event("Teacher's Home", "This is where the teacher lives when they're not at the school.",
					new EventOption[] {
							new EventOption("Rest", new int[] { 10, 0, 10, 0 }, new int[] { 0, 0, 10, 0 },
									new boolean[] { false, true, false, false }, -1, false), },
					true),
			
			new Event("Family's Home", "A humble home for the parent and student surrounded by their farmland.",
					new EventOption[] {
							new EventOption("Study", new int[] { 0, 5, 10, 0 }, new int[] { 0, 0, 10, 0 },
									new boolean[] { true, false, false, false }, -1, false),
							new EventOption("Rest", new int[] { 10, 0, 10, 0 }, new int[] { 0, 0, 10, 0 },
									new boolean[] { false, false, true, true }, -1, false),
							new EventOption("Get Push-Tractor", new int[] { 0, 0, 0, -60 }, new int[] { 0, 0, 0, 60 },
									new boolean[] { false, false, true, false }, 1, false,
									"Always get Mighty Yields.", 2),
							new EventOption("Get Electricity", new int[] { 0, 0, 0, -60 }, new int[] { 0, 0, 0, 60 },
									new boolean[] { false, false, true, false }, 1, false,
									"The home provides greater benefits.", 1),},
					true),

	};

	public MainSceneHandler(Player[] players) {
		this.players = players;
		for (int i = 0; i < 20; i++) {
			playerText[i] = new Text();
		}
		if(players[turnNumber % 4].getResources()[0] <= 0) {
			endGame();
		}
		
		switch(turnNumber % 4) {
		case 0:
			if(players[turnNumber % 4].getResources()[3] < 40) {
				players[turnNumber % 4].getObjectives()[0] = -1;
			}
			break;
		case 1:
			if(players[turnNumber % 4].getResources()[1] < 60) {
				players[turnNumber % 4].getObjectives()[0] = -1;
			}
			break;
		case 2:
			if(players[turnNumber % 4].getResources()[2] < 30) {
				players[turnNumber % 4].getObjectives()[0] = -1;
			}
			break;
		case 3:
			if(players[turnNumber % 4].getResources()[0] < 80) {
				players[turnNumber % 4].getObjectives()[0] = -1;
			}
			break;
		}
		
		playerText[20] = new Text();
		playerText[21] = new Text();
		playerText[22] = new Text();
		playerText[22].setVisible(false);
		playerText[23] = new Text();
		playerText[23].setVisible(false);
		endButtonTop.setVisible(false);
		endButtonBottom.setVisible(false);
		for (int i = 0; i < iconButtons.length; i++) {
			iconButtons[i].setViewport(new Rectangle2D(0, 0, 172, 171));
		}

		for (int i = 0; i < eventText.length; i++) {
			eventText[i] = new Text();
			eventText[i].setVisible(false);
		}

		Image buttonTop = new Image("/buttonTops.png");
		Image buttonBottom = new Image("/buttonBottoms.png");
		for (int i = 0; i < eventButtons.length; i++) {
			eventButtons[i] = new ImageView((i >= 4) ? buttonTop : buttonBottom);
			eventButtons[i].setVisible(false);
		}

		eventUI.setVisible(false);
		for (int i = 0; i < resourceLogos.length; i++) {
			resourceLogos[i] = new ImageView(new Image("/resourceIcons.png"));
			resourceLogos[i].setVisible(false);
		}
		
		for (int i = 0; i < items.length; i++) {
		items[i] = new ImageView(new Image("/items.png"));
		}

		updateUI();
	}

	public void handleTurn() {
		turnNumber++;
		boardHandler.moveToNextPlayer(turnNumber % 4);
	}

	public void landedNextTurn() {
		for (Node n : eventNodes) {
			n.setVisible(false);
		}
		updateUI();
		Tools.linearFadeIn(die, 1);
		Tools.linearFadeIn(playerText[21], 1);
		canRoll = true;
		endReleased = false;
		canPressEvent = true;
		die.setViewport(new Rectangle2D(((int) (Math.random() * 6) * 128), 0, 128, 128));
	}

	public void landed() {
		landed(-1);
	}

	public void landed(int eventIndex) {
		switch (eventIndex) {
		case -2:
			canPressEvent = false;
			for (Node n : eventNodes) {
				Tools.linearFadeOut(n, .5d);
			}
			updateUI();
			this.eventIndex = -1;
			// falls through
		case -1:
			if (movement > 0) {
				this.canMove = true;
			} else {
				handleTurn();
			}
			break;
		default:
			if (this.eventIndex != eventIndex) {
				canPressEvent = true;
				this.eventIndex = eventIndex;
				updateUI();
				for (Node n : eventNodes) {
					Tools.linearFadeIn(n, .5d);
				}
			} else {
				for (Node n : eventNodes) {
					n.setVisible(true);
				}
			}
			this.eventIndex = eventIndex;
			break;
		}
	}

	public Scene makeMainScene() {
		Scene mainScene = new Scene(pane, screenWidth, screenHeight);
		ImageView background = new ImageView(new Image("/background.png"));
		pane.getChildren().add(background);
		background.setScaleX(widthPortion);
		background.setScaleY(heightPortion);
		background.setLayoutX((1.0f - widthPortion) * -3840 / 2);
		background.setLayoutY((1.0f - heightPortion) * -2160 / 2);

		ImageView boardShadow = new ImageView(new Image("/boardShadow.png"));
		pane.getChildren().add(boardShadow);
		boardShadow.setScaleX(widthPortion * 2);
		boardShadow.setScaleY(heightPortion * 2);
		boardShadow.setLayoutX((1.0f - widthPortion * 2) * -1020 / 2 + .2355f * (float) screenWidth);
		boardShadow.setLayoutY((1.0f - heightPortion * 2) * -975 / 2);

		boardHandler = new BoardHandler(this);
		Pane board = boardHandler.makeSquareBallGroup(470 * heightPortion);
		pane.getChildren().add(board);
		board.setLayoutX(.5f * (float) screenWidth);
		board.setLayoutY(.42f * (float) screenHeight);

		ImageView boardShading = new ImageView(new Image("/boardShading.png"));
		pane.getChildren().add(boardShading);
		boardShading.setScaleX(widthPortion * 2);
		boardShading.setScaleY(heightPortion * 2);
		boardShading.setLayoutX((1.0f - widthPortion * 2) * -1020 / 2 + .2355f * (float) screenWidth);
		boardShading.setLayoutY((1.0f - heightPortion * 2) * -975 / 2);

		EventHandler<MouseEvent> endTurn = (e -> {
			if (canMove && endButtonTop.isVisible()) {
				AnimationTimer pressAnimation = new AnimationTimer() {
					private long lastUpdate;
					double scaler;

					@Override
					public void start() {
						lastUpdate = System.nanoTime();
						scaler = 0;
						super.start();
					}

					@Override
					public void handle(long now) {
						double elapsedSeconds = (now - lastUpdate) / 1_000_000_000.0;
						scaler += elapsedSeconds * 2;
						if (scaler >= 1) {
							stop();
						}
						if (scaler >= .5 && endReleased == false) {
							scaler = .5;
						}
						endButtonTop.setTranslateY(
								((scaler < .5) ? (scaler) : (1 - scaler)) * .017f * (float) screenHeight);
						playerText[23].setTranslateY(
								((scaler < .5) ? (scaler) : (1 - scaler)) * .017f * (float) screenHeight);
						lastUpdate = now;
					}

					@Override
					public void stop() {
						endButtonTop.setTranslateY(0);
						playerText[23].setTranslateY(0);
						super.stop();
					}
				};
				pressAnimation.start();

			}
		});

		EventHandler<MouseEvent> endTurnReleased = (e -> {
			if (canMove && endButtonTop.isVisible()) {
				endReleased = true;
				canMove = false;
				movement = 0;
				Tools.linearFadeOut(die, .5);
				Tools.linearFadeOut(playerText[22], .5);
				Tools.linearFadeOut(playerText[23], .5);
				Tools.linearFadeOut(endButtonTop, .5);
				Tools.linearFadeOut(endButtonBottom, .5);
				landed();
			}
		});

		playerText[23].setOnMousePressed(endTurn);
		endButtonTop.setOnMousePressed(endTurn);
		endButtonBottom.setOnMousePressed(endTurn);
		playerText[23].setOnMouseReleased(endTurnReleased);
		endButtonTop.setOnMouseReleased(endTurnReleased);
		endButtonBottom.setOnMouseReleased(endTurnReleased);

		ImageView dieBar = new ImageView(new Image("/diceBar.png", 128, 32, false, false));
		dieBar.setScaleX(widthPortion * 2.5);
		dieBar.setScaleY(heightPortion * 2.5);
		dieBar.setLayoutX((1.0f - (widthPortion * 2.5)) * -128 / 2 + .82f * (float) screenWidth);
		dieBar.setLayoutY((1.0f - (heightPortion * 2.5)) * -32 / 2 + .67f * (float) screenHeight);
		pane.getChildren().add(dieBar);
		dieBar.setVisible(false);
		double[] dieBarScaler = { 0 };

		ImageView dieBarBorder = new ImageView(new Image("/diceBarBorder.png", 128, 32, false, false));
		dieBarBorder.setScaleX(widthPortion * 2.5);
		dieBarBorder.setScaleY(heightPortion * 2.5);
		dieBarBorder.setLayoutX((1.0f - (widthPortion * 2.5)) * -128 / 2 + .82f * (float) screenWidth);
		dieBarBorder.setLayoutY((1.0f - (heightPortion * 2.5)) * -32 / 2 + .67f * (float) screenHeight);
		pane.getChildren().add(dieBarBorder);
		dieBarBorder.setVisible(false);

		die.setImage(new Image("/die.png"));
		die.setViewport(new Rectangle2D(0, 0, 128, 128));
		die.setScaleX(widthPortion * 2.5);
		die.setScaleY(heightPortion * 2.5);
		die.setLayoutX((1.0f - (widthPortion * 2.5)) * -128 / 2 + .82f * (float) screenWidth);
		die.setLayoutY((1.0f - (heightPortion * 2.5)) * -128 / 2 + .52f * (float) screenHeight);
		die.setStyle("-fx-cursor: hand;");
		pane.getChildren().add(die);

		AnimationTimer dieBarAnimation = new AnimationTimer() {
			private long lastUpdate;
			double translateX = 0;
			double shakeFactor = 10;

			@Override
			public void start() {
				Tools.linearFadeOut(playerText[21], .5);
				dieBarScaler[0] = 0;
				lastUpdate = System.nanoTime();
				translateX = (-160 * heightPortion);
				dieBar.setScaleX(0);
				dieBar.setVisible(true);
				dieBarBorder.setVisible(true);
				super.start();
			}

			@Override
			public void handle(long now) {
				double elapsedSeconds = (now - lastUpdate) / 1_000_000_000.0;
				if ((dieBarScaler[0] + elapsedSeconds) > 1) {
					dieBarScaler[0] = 1;
					translateX = 0;
				} else {
					translateX += (160 * heightPortion * elapsedSeconds);
					dieBarScaler[0] += elapsedSeconds;
				}
				die.setTranslateX(
						(Math.random() * shakeFactor * dieBarScaler[0]) - (shakeFactor / 2 * dieBarScaler[0]));
				die.setTranslateY(
						(Math.random() * shakeFactor * dieBarScaler[0]) - (shakeFactor / 2 * dieBarScaler[0]));
				dieBar.setTranslateX(translateX);
				dieBar.setScaleX(dieBarScaler[0] * 2.5 * widthPortion);
				lastUpdate = now;
			}

			@Override
			public void stop() {
				dieBar.setVisible(false);
				dieBarBorder.setVisible(false);
				die.setTranslateX(0);
				die.setTranslateY(0);
				translateX = 0;
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
				rotateFactor = 720 + (720 * dieBarScaler[0]);
				shakeFactor = 5 + (10 * dieBarScaler[0]);
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
					die.setViewport(new Rectangle2D((tempMovement * 128) - 128, 0, 128, 128));
				}
				die.setTranslateX((Math.random() * shakeFactor) - (shakeFactor / 2));
				die.setTranslateY((Math.random() * shakeFactor) - (shakeFactor / 2));
				die.setRotate(scaler * rotateFactor);
				if ((scaler + elapsedSeconds) > 1) {
					scaler = 1;
					stop();
				}
				lastUpdate = now;
			}

			@Override
			public void stop() {
				die.setRotate(0);
				die.setTranslateX(0);
				die.setTranslateY(0);
				movement = tempMovement;
				canMove = true;
				Tools.linearFadeIn(playerText[23], .5);
				Tools.linearFadeIn(endButtonTop, .5);
				Tools.linearFadeIn(endButtonBottom, .5);
				Tools.linearFadeIn(playerText[22], .5);
				super.stop();
			}
		};

		playerText[21].setOnMousePressed(e -> {
			if (canRoll && die.isVisible()) {
				dieBarAnimation.start();
			}
		});
		playerText[21].setOnMouseReleased(e -> {
			if (canRoll) {
				dieBarAnimation.stop();
				rollAnimation.start();
			}
		});
		die.setOnMousePressed(e -> {
			if (canRoll && die.isVisible()) {
				dieBarAnimation.start();
			}
		});
		die.setOnMouseReleased(e -> {
			if (canRoll) {
				dieBarAnimation.stop();
				rollAnimation.start();
			}
		});

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
			if (event.getCode() == KeyCode.ESCAPE && fullscreen == 1) {
				fullscreen = 0;
				updateUI();
			}

		});

		ImageView backHUD = new ImageView(new Image("/backHUD.png"));
		pane.getChildren().add(backHUD);
		backHUD.setScaleX(widthPortion);
		backHUD.setScaleY(heightPortion);
		backHUD.setLayoutX((1.0f - widthPortion) * -3840 / 2);
		backHUD.setLayoutY((1.0f - heightPortion) * -583 / 2 + .795f * (float) screenHeight);

		pane.getChildren().add(playerHUDs);
		pane.getChildren().add(nextUI);
		pane.getChildren().add(eventUI);
		pane.getChildren().add(endButtonBottom);
		pane.getChildren().add(endButtonTop);
		pane.getChildren().add(timeUI);
		pane.getChildren().add(timeText);

		iconButtons[0].setOnMouseClicked(e -> {
			modalTypeActive = (modalTypeActive == 0) ? -1 : 0;
			updateUI();
		});
		iconButtons[1].setOnMouseClicked(e -> {
			modalTypeActive = (modalTypeActive == 1) ? -1 : 1;
			updateUI();
		});
		iconButtons[2].setOnMouseClicked(e -> {
			modalTypeActive = (modalTypeActive == 2) ? -1 : 2;
			updateUI();
		});

		modalNodes[11].setOnMouseClicked(e -> {
			language = (language == 1) ? 2 : 1;
			updateUI();
		});
		
		modalNodes[18].setOnMouseClicked(e -> {
			fullscreen = (fullscreen == 1) ? 0 : 1;
		Stage tempStage = (Stage) modalNodes[18].getScene().getWindow();
		tempStage.setFullScreen((fullscreen == 0) ? false : true);
		updateUI();
		});
		
		for(int i = 0; i < items.length; i++) {
			pane.getChildren().add(items[i]);
		}

		for (int i = 0; i < iconButtons.length; i++) {
			pane.getChildren().add(iconButtons[i]);
		}

		for (int i = 0; i < eventButtons.length; i++) {
			pane.getChildren().add(eventButtons[i]);
		}

		for (int i = 0; i < resourceLogos.length; i++) {
			pane.getChildren().add(resourceLogos[i]);
		}

		for (int i = 0; i < playerText.length; i++) {
			pane.getChildren().add(playerText[i]);
		}
		for (int i = 0; i < resourceTextDescs.length; i++) {
			pane.getChildren().add(resourceTextDescs[i]);
		}

		for (int i = 0; i < eventText.length; i++) {
			pane.getChildren().add(eventText[i]);
		}

		for (int i = 0; i < modalNodes.length; i++) {
			pane.getChildren().add(modalNodes[i]);
		}

		return mainScene;
	}

	public void updateUI() {
		
		if (turnNumber / 4 > 4) {
			endGame();
		}

		for (int i = 0; i < playerText.length; i++) {
			playerText[i].setStyle("");
		}
		for (int i = 0; i < resourceTextDescs.length; i++) {
			resourceTextDescs[i].setStyle("");
		}
		for (Node t : modalNodes) {
			t.setStyle("");
		}
		
		timeText.setStyle("");

		String[] colors = { "#18286b", "#196b18", "#ba2362", "#53186b", "#2842e1", "#2be028", "#e12875", "#9928e1",
				"#d3d7ff", "#d4ffd4", "#fac4d2", "#f4d3ff" };

		updateEventUI(colors);

		playerHUDs.setViewport(new Rectangle2D((turnNumber % 4) * 2144, 0, 2144, 569));

		nextUI.setViewport(new Rectangle2D((turnNumber % 4) * 805, 0, 805, 838));

		playerHUDs.setScaleX(widthPortion);
		playerHUDs.setScaleY(heightPortion);
		playerHUDs.setLayoutX((1.0f - widthPortion) * -2144 / 2 + .023f * (float) screenWidth);
		playerHUDs.setLayoutY((1.0f - heightPortion) * -569 / 2 + .7f * (float) screenHeight);

		nextUI.setScaleX(widthPortion);
		nextUI.setScaleY(heightPortion);
		nextUI.setLayoutX((1.0f - widthPortion) * -805 / 2 + .791f * (float) screenWidth);
		nextUI.setLayoutY((1.0f - heightPortion) * -838 / 2);

		for (int i = 0; i < 20; i++) {
			playerText[i].setText((i % 5 == 0) ? players[i / 5].getPlayerName()
					: Integer.toString(players[i / 5].getResources()[i % 5 - 1]));
			if (i / 5 == turnNumber % 4) {
				if (i % 5 == 0) {
					playerText[i].setVisible(true);
					playerText[i].setStyle("-fx-font-size: " + (int) (74 * widthPortion)
							+ "; -fx-effect: dropshadow(gaussian, " + colors[i / 5] + "," + 6 * widthPortion
							+ ", 1.0, 0, 0); -fx-fill: #ffffff; -fx-text-alignment: center;");
					playerText[i].setFont(Main.fredokaOne);
					playerText[i].setLayoutX(-playerText[i].getBoundsInParent().getWidth() / (1.0f / widthPortion)
							+ .125f * (float) screenWidth);
					playerText[i].setLayoutY(.762f * (float) screenHeight);
				} else {
					playerText[i].setStyle("-fx-font-size: " + (int) (106 * widthPortion) + "; "
							+ "-fx-effect: dropshadow(gaussian, " + colors[i / 5] + "," + 6 * widthPortion
							+ ", 1.0, 0, 0); -fx-stroke-type: outside; -fx-fill: #ffffff; -fx-text-alignment: center;");
					playerText[i].setFont(Main.fredokaOne);
					playerText[i].setLayoutX(-playerText[i].getBoundsInParent().getWidth() / (1.0f / widthPortion)
							+ .128f * (float) screenWidth + ((i % 5 - 1) * .132f * (float) screenWidth));
					playerText[i].setLayoutY(.875f * (float) screenHeight);
				}
			} else {
				if (i % 5 == 0) {
					playerText[i].setVisible(false);
				} else {
					playerText[i].setFont(Main.fredokaOne);
					playerText[i].setStyle("-fx-font-size: " + (int) (76 * widthPortion)
							+ "; -fx-effect: dropshadow(gaussian, " + colors[i / 5 + 4] + "," + 6 * widthPortion
							+ ", 1.0, 0, 0); -fx-fill: #ffffff; -fx-text-alignment: center;");
					playerText[i].setLayoutX(-playerText[i].getBoundsInParent().getWidth() / (1.0f / widthPortion)
							+ .823f * (float) screenWidth + ((i % 5 - 1) * .05f * (float) screenWidth));
					playerText[i].setLayoutY(.0765f * (float) screenHeight
							+ ((((i / 5 - turnNumber % 4) < 0) ? 3 + (i / 5 - turnNumber % 4)
									: (i / 5 - turnNumber % 4) - 1) * .128f * (float) screenHeight));
				}
			}
		}
		resourceTextDescs[0].setText(tr.getText(2, language));
		resourceTextDescs[1].setText(tr.getText(3, language));
		resourceTextDescs[2].setText(tr.getText(4, language));
		resourceTextDescs[3].setText(tr.getText(5, language));
		for (int i = 0; i < resourceTextDescs.length; i++) {

			resourceTextDescs[i].setStyle("-fx-font-size: " + (int) (44 * widthPortion)
					+ "; -fx-text-alignment: center; -fx-fill:" + colors[turnNumber % 4] + ";");
			resourceTextDescs[i].setFont(Main.arialRoundedMTBold);

			resourceTextDescs[i].setLayoutX(-resourceTextDescs[i].getBoundsInParent().getWidth() / (1.0f / widthPortion)
					+ .093f * (float) screenWidth
					+ ((i) * .135f * (27.0f / (26.0f + Math.min(i, 2))) * (float) screenWidth));
			resourceTextDescs[i].setLayoutY(.913f * (float) screenHeight);

		}

		endButtonBottom.setViewport(new Rectangle2D((turnNumber % 4) * 563, 0, 563, 184));
		endButtonTop.setViewport(new Rectangle2D((turnNumber % 4) * 563, 0, 563, 184));

		endButtonBottom.setScaleX(widthPortion);
		endButtonBottom.setScaleY(heightPortion);
		endButtonBottom.setLayoutX((1.0f - widthPortion) * -563 / 2 + .79f * (float) screenWidth);
		endButtonBottom.setLayoutY((1.0f - heightPortion) * -184 / 2 + .68f * (float) screenHeight);
		endButtonBottom.setStyle("-fx-cursor: hand;");

		endButtonTop.setScaleX(widthPortion);
		endButtonTop.setScaleY(heightPortion);
		endButtonTop.setLayoutX((1.0f - widthPortion) * -563 / 2 + .79f * (float) screenWidth);
		endButtonTop.setLayoutY((1.0f - heightPortion) * -184 / 2 + .68f * (float) screenHeight);
		endButtonTop.setStyle("-fx-cursor: hand;");

		playerText[20].setText(tr.getText(13, language));
		playerText[21].setText(tr.getText(14, language));
		playerText[22].setText(tr.getText(15, language));
		playerText[23].setText(tr.getText(16, language));

		// next up
		playerText[20]
				.setStyle("-fx-font-size: " + (int) (60 * widthPortion) + "; -fx-effect: dropshadow(gaussian, #000000,"
						+ 6 * widthPortion + ", 1.0, 0, 0); -fx-fill: #ffffff; -fx-text-alignment: center;");
		playerText[20].setFont(Main.fredokaOne);
		playerText[20].setLayoutX(
				-playerText[20].getBoundsInParent().getWidth() / (1.0f / widthPortion) + .76f * (float) screenWidth);
		playerText[20].setLayoutY(.07f * (float) screenHeight);

		// click to roll
		playerText[21].setStyle("-fx-font-size: " + (int) (60 * widthPortion)
				+ "; -fx-effect: dropshadow(gaussian, #000000," + 6 * widthPortion
				+ ", 1.0, 0, 0); -fx-fill: #ffffff; -fx-text-alignment: center; -fx-cursor: hand;");
		playerText[21].setFont(Main.fredokaOne);
		playerText[21].setLayoutX(
				-playerText[21].getBoundsInParent().getWidth() / (1.0f / widthPortion) + .87f * (float) screenWidth);
		playerText[21].setLayoutY(.62f * (float) screenHeight);

		// movement left
		playerText[22]
				.setStyle("-fx-font-size: " + (int) (60 * widthPortion) + "; -fx-effect: dropshadow(gaussian, #000000,"
						+ 6 * widthPortion + ", 1.0, 0, 0); -fx-fill: #ffffff; -fx-text-alignment: center;");
		playerText[22].setFont(Main.fredokaOne);
		playerText[22].setLayoutX(
				-playerText[22].getBoundsInParent().getWidth() / (1.0f / widthPortion) + .87f * (float) screenWidth);
		playerText[22].setLayoutY(.52f * (float) screenHeight);

		// end turn
		playerText[23].setStyle("-fx-font-size: " + (int) (60 * widthPortion) + "; -fx-effect: dropshadow(gaussian, "
				+ colors[turnNumber % 4] + "," + 5 * widthPortion
				+ ", 1.0, 0, 0); -fx-fill: #ffffff; -fx-text-alignment: center;  -fx-cursor: hand;");
		playerText[23].setFont(Main.fredokaOne);
		playerText[23].setLayoutX(
				-playerText[23].getBoundsInParent().getWidth() / (1.0f / widthPortion) + .87f * (float) screenWidth);
		playerText[23].setLayoutY(.725f * (float) screenHeight);
		
		timeUI.setScaleX(widthPortion);
		timeUI.setScaleY(heightPortion);
		timeUI.setLayoutX((1.0f - widthPortion) * -1250 / 2 + .339f * (float) screenWidth);
		timeUI.setLayoutY((1.0f - heightPortion) * -192 / 2);
		
		timeText.setText(tr.getText(20 + (turnNumber / 4), language));
		timeText.setStyle(
				"-fx-font-size: " + (int) (80 * widthPortion) + "; -fx-fill: #ffffff; -fx-text-alignment: center;");
		timeText.setFont(Main.fredokaOne);
		timeText.setLayoutX(
				-timeText.getBoundsInParent().getWidth() / (1.0f / widthPortion) + .5f * (float) screenWidth);
		timeText.setLayoutY(.05f * (float) screenHeight);

		for (int i = 0; i < iconButtons.length; i++) {
			iconButtons[i].setScaleX(widthPortion);
			iconButtons[i].setScaleY(heightPortion);
			iconButtons[i].setLayoutX(
					(1.0f - widthPortion) * -172 / 2 + .03f * (float) screenWidth + i * .065f * (float) screenWidth);
			iconButtons[i].setLayoutY((1.0f - heightPortion) * -171 / 2 + .025f * (float) screenHeight);
			iconButtons[i].setStyle("-fx-cursor: hand;");
			int[] index = { i };
			iconButtons[i].setOnMouseEntered(e -> {
				iconButtons[index[0]].setViewport(new Rectangle2D(172, 0, 172, 171));
			});
			iconButtons[i].setOnMouseExited(e -> {
				iconButtons[index[0]].setViewport(new Rectangle2D(0, 0, 172, 171));
			});
		}
		
		for (int i = 0; i < items.length; i++) {
			if((turnNumber % 4 == 0) || (turnNumber % 4 == 2)) {
				items[i].setVisible(true);
				items[i].setViewport(new Rectangle2D((i % 3) * 462 + ((players[turnNumber % 4].getItems()[i % 3]) ? 0 : 1386), (turnNumber % 4 == 0) ? 0 : 391, 462, 391));

				items[i].setScaleX(widthPortion);
				items[i].setScaleY(heightPortion);
				items[i].setLayoutX((1.0f - widthPortion) * -462 / 2 + .61f * (float) screenWidth + (i % 3) *.125f * (float) screenWidth);
				items[i].setLayoutY((1.0f - heightPortion) * -391 / 2 + .81f * (float) screenHeight + ((i % 3 == 1) ? -.015f * (float) screenHeight : 0));
			} else {
				items[i].setVisible(false);
			}
		}

		ImageView modalUI = (ImageView) modalNodes[0];
		modalUI.setViewport(new Rectangle2D((turnNumber % 4) * 3841, 0, 3841, 2160));
		modalUI.setScaleX(widthPortion);
		modalUI.setScaleY(heightPortion);
		modalUI.setLayoutX((1.0f - widthPortion) * -3841 / 2);
		modalUI.setLayoutY((1.0f - heightPortion) * -2160 / 2);

		for (Node n : modalNodes) {
			n.setVisible(false);
		}
		switch (modalTypeActive) {
		case 0: {
			modalUI.setVisible(true);

			((Text) modalNodes[7]).setText(tr.getText(9, language));
			modalNodes[7].setStyle(
					"-fx-font-size: " + (int) (80 * widthPortion) + "; -fx-fill: #ffffff; -fx-text-alignment: center;");
			((Text) modalNodes[7]).setFont(Main.fredokaOne);
			modalNodes[7].setLayoutX(
					-modalNodes[7].getBoundsInParent().getWidth() / (1.0f / widthPortion) + .5f * (float) screenWidth);
			modalNodes[7].setLayoutY(.2f * (float) screenHeight);
			modalNodes[7].setVisible(true);

			for (int i = 0; i < 3; i++) {
				((Text) modalNodes[8 + i]).setText(tr.getText(10 + i, language));
				modalNodes[8 + i].setStyle("-fx-font-size: " + (int) (60 * widthPortion)
						+ "; -fx-text-alignment: left; -fx-fill: #ffffff;");
				((Text) modalNodes[8 + i]).setFont(Main.arialRoundedMTBold);
				modalNodes[8 + i].setLayoutX(.27f * (float) screenWidth);
				modalNodes[8 + i].setLayoutY(.29f * (float) screenHeight + i * .2f * (float) screenHeight);
				((Text) modalNodes[8]).setWrappingWidth(1750 * widthPortion);
				modalNodes[8 + i].setVisible(true);
			}

			((ImageView) modalNodes[11]).setViewport(new Rectangle2D((language - 1) * 572, 0, 572, 328));
			modalNodes[11].setStyle("-fx-cursor: hand;");
			modalNodes[11].setScaleX(widthPortion);
			modalNodes[11].setScaleY(heightPortion);
			modalNodes[11].setLayoutX((1.0f - widthPortion) * -572 / 2 + .38f * (float) screenWidth);
			modalNodes[11].setLayoutY((1.0f - heightPortion) * -328 / 2 + .64f * (float) screenHeight);
			modalNodes[11].setVisible(true);
			modalNodes[11].setOnMouseEntered(e -> {
				((ImageView) modalNodes[11]).setViewport(new Rectangle2D((language - 1) * 572 + 1144, 0, 572, 328));
			});
			modalNodes[11].setOnMouseExited(e -> {
				((ImageView) modalNodes[11]).setViewport(new Rectangle2D((language - 1) * 572, 0, 572, 328));
			});

			for (int i = 0; i < 3; i++) {
				((ImageView) modalNodes[12 + i]).setViewport(new Rectangle2D(0, 0, 573, 328));
				modalNodes[12 + i].setStyle("-fx-cursor: hand;");
				modalNodes[12 + i].setScaleX(widthPortion);
				modalNodes[12 + i].setScaleY(heightPortion);
				modalNodes[12 + i].setLayoutX((1.0f - widthPortion) * -573 / 2 + .26f * (float) screenWidth
						+ i * .165f * (float) screenWidth);
				modalNodes[12 + i].setLayoutY((1.0f - heightPortion) * -328 / 2 + .512f * (float) screenHeight);
				modalNodes[12 + i].setVisible(true);
				int[] index = { i };
				modalNodes[12 + i].setOnMouseEntered(e -> {
					((ImageView) modalNodes[12 + index[0]]).setViewport(new Rectangle2D(573, 0, 572, 328));
				});
				modalNodes[12 + i].setOnMouseExited(e -> {
					((ImageView) modalNodes[12 + index[0]]).setViewport(new Rectangle2D(0, 0, 573, 328));
				});
				modalNodes[12 + i].setOnMouseClicked(e -> {
					switch (index[0]) {
					case 0:
						screenWidth = 960;
						screenHeight = 540;
						break;
					case 1:
						screenWidth = 1280;
						screenHeight = 720;
						break;
					case 2:
						screenWidth = 1920;
						screenHeight = 1080;
						break;
					}
					widthPortion = (float) screenWidth / 3840.0f;
					heightPortion = (float) screenHeight / 2160.0f;
					fullscreen = 0;
					Scene tempScene = modalNodes[12 + index[0]].getScene();
					Stage tempStage = (Stage) tempScene.getWindow();
					Pane tempPane = (Pane) tempScene.getRoot();
					tempPane = null;
					pane = new Pane();
					tempScene = null;
					tempStage.setScene(makeMainScene());
					tempStage.centerOnScreen();
					updateUI();
				});

				((Text) modalNodes[15 + i]).setText(tr.getText(17 + i, language));
				modalNodes[15 + i].setStyle("-fx-font-size: " + (int) (60 * widthPortion)
						+ "; -fx-text-alignment: left; -fx-fill: #ffffff; -fx-cursor: hand;");
				((Text) modalNodes[15 + i]).setFont(Main.fredokaOne);
				modalNodes[15 + i].setLayoutX(.3f * (float) screenWidth + i * .16f * (float) screenWidth);
				modalNodes[15 + i].setLayoutY(.555f * (float) screenHeight);
				modalNodes[15 + i].setVisible(true);

				modalNodes[15 + i].setOnMouseEntered(e -> {
					((ImageView) modalNodes[12 + index[0]]).setViewport(new Rectangle2D(573, 0, 572, 328));
				});
				modalNodes[15 + i].setOnMouseExited(e -> {
					((ImageView) modalNodes[12 + index[0]]).setViewport(new Rectangle2D(0, 0, 573, 328));
				});
				modalNodes[15 + i].setOnMouseClicked(e -> {
					switch (index[0]) {
					case 0:
						screenWidth = 960;
						screenHeight = 540;
						break;
					case 1:
						screenWidth = 1280;
						screenHeight = 720;
						break;
					case 2:
						screenWidth = 1920;
						screenHeight = 1080;
						break;
					}
					widthPortion = (float) screenWidth / 3840.0f;
					heightPortion = (float) screenHeight / 2160.0f;
					fullscreen = 0;
					Scene tempScene = modalNodes[12 + index[0]].getScene();
					Stage tempStage = (Stage) tempScene.getWindow();
					Pane tempPane = (Pane) tempScene.getRoot();
					tempPane = null;
					pane = new Pane();
					tempScene = null;
					tempStage.setScene(makeMainScene());
					tempStage.centerOnScreen();
					updateUI();
				});
			}
			((ImageView) modalNodes[18]).setViewport(new Rectangle2D((fullscreen) * 310, 0, 155, 144));
			modalNodes[18].setStyle("-fx-cursor: hand;");
			modalNodes[18].setScaleX(widthPortion);
			modalNodes[18].setScaleY(heightPortion);
			modalNodes[18].setLayoutX((1.0f - widthPortion) * -155 / 2 + .29f * (float) screenWidth);
			modalNodes[18].setLayoutY((1.0f - heightPortion) * -144 / 2 + .34f * (float) screenHeight);
			modalNodes[18].setVisible(true);
			modalNodes[18].setOnMouseEntered(e -> {
				((ImageView) modalNodes[18]).setViewport(new Rectangle2D((fullscreen) * 310 + 155 , 0, 155, 144));
			});
			modalNodes[18].setOnMouseExited(e -> {
				((ImageView) modalNodes[18]).setViewport(new Rectangle2D((fullscreen) * 310, 0, 155, 144));
			});
			
		}
			break;
		case 1: {
			modalUI.setVisible(true);

			((Text) modalNodes[1]).setText(tr.getText(6, language));
			modalNodes[1].setStyle(
					"-fx-font-size: " + (int) (80 * widthPortion) + "; -fx-fill: #ffffff; -fx-text-alignment: center;");
			((Text) modalNodes[1]).setFont(Main.fredokaOne);
			modalNodes[1].setLayoutX(
					-modalNodes[1].getBoundsInParent().getWidth() / (1.0f / widthPortion) + .5f * (float) screenWidth);
			modalNodes[1].setLayoutY(.2f * (float) screenHeight);
			modalNodes[1].setVisible(true);

			((Text) modalNodes[2]).setText(tr.getText(7, language));
			modalNodes[2].setStyle(
					"-fx-font-size: " + (int) (60 * widthPortion) + "; -fx-text-alignment: center; -fx-fill: #ffffff;");
			((Text) modalNodes[2]).setFont(Main.arialRoundedMTBold);
			modalNodes[2].setLayoutX(.27f * (float) screenWidth);
			modalNodes[2].setLayoutY(.27f * (float) screenHeight);
			((Text) modalNodes[2]).setWrappingWidth(1750 * widthPortion);
			modalNodes[2].setVisible(true);

		}
			break;
		case 2: {
			modalUI.setVisible(true);

			((Text) modalNodes[3]).setText(tr.getText(8, language));
			modalNodes[3].setStyle(
					"-fx-font-size: " + (int) (80 * widthPortion) + "; -fx-fill: #ffffff; -fx-text-alignment: center;");
			((Text) modalNodes[3]).setFont(Main.fredokaOne);
			modalNodes[3].setLayoutX(
					-modalNodes[3].getBoundsInParent().getWidth() / (1.0f / widthPortion) + .5f * (float) screenWidth);
			modalNodes[3].setLayoutY(.2f * (float) screenHeight);
			modalNodes[3].setVisible(true);

			for (int i = 0; i < 3; i++) {
				((Text) modalNodes[4 + i]).setText("- " + players[turnNumber % 4].getObjectivesText()[i]);
				modalNodes[4 + i].setStyle("-fx-font-size: " + (int) (60 * widthPortion)
						+ "; -fx-text-alignment: left; -fx-fill: #ffffff;");
				((Text) modalNodes[4 + i]).setFont(Main.arialRoundedMTBold);
				modalNodes[4 + i].setLayoutX(.27f * (float) screenWidth);
				modalNodes[4 + i].setLayoutY(.27f * (float) screenHeight + i * .2f * (float) screenHeight);
				((Text) modalNodes[4]).setWrappingWidth(1750 * widthPortion);
				modalNodes[4 + i].setVisible(true);
			}
		}
			break;
		}

	}

	private void updateEventUI(String[] colors) {

		int turnNumberOG = turnNumber;
		for (int i = 0; i < eventText.length; i++) {
			eventText[i].setStyle("");
		}

		eventNodes.clear();

		eventUI.setViewport(new Rectangle2D((turnNumberOG % 4) * 1666, 0, 1666, 1220));
		eventNodes.add(eventUI);

		eventUI.setScaleX(widthPortion);
		eventUI.setScaleY(heightPortion);
		eventUI.setLayoutX((1.0f - widthPortion) * -1666 / 2);
		eventUI.setLayoutY((1.0f - heightPortion) * -1220 / 2 + .12f * (float) screenHeight);

		eventText[20].setText((eventIndex != -1) ? events[eventIndex].getTitle() : "");
		eventText[20].setStyle("-fx-font-size: " + (int) (80 * widthPortion) + "; -fx-effect: dropshadow(gaussian, "
				+ colors[turnNumberOG % 4] + "," + 6 * widthPortion
				+ ", 1.0, 0, 0); -fx-fill: #ffffff; -fx-text-alignment: center;");
		eventText[20].setFont(Main.fredokaOne);
		eventText[20].setLayoutX(
				-eventText[20].getBoundsInParent().getWidth() / (1.0f / widthPortion) + .183f * (float) screenWidth);
		eventText[20].setLayoutY(.191f * (float) screenHeight);
		eventNodes.add(eventText[20]);

		eventText[21].setText((eventIndex != -1) ? events[eventIndex].getTextBody() : "");
		eventText[21].setStyle("-fx-font-size: " + (int) (44 * widthPortion) + "; -fx-text-alignment: center; -fx-fill:"
				+ colors[turnNumberOG % 4] + ";");
		eventText[21].setFont(Main.arialRoundedMTBold);
		eventText[21].setLayoutX(.025f * (float) screenWidth);
		eventText[21].setLayoutY(.258f * (float) screenHeight);
		eventText[21].setWrappingWidth(1200 * widthPortion);
		eventNodes.add(eventText[21]);

		EventOption[] eventOptions = (eventIndex != -1) ? events[eventIndex].getOptions()
				: new EventOption[] { new EventOption("", new int[] { 0, 0, 0, 0 }, new int[] { 0, 0, 0, 0 }) };
		boolean[] hasClickedAnOption = { false };
		int indexShownOptions = 0;
		for (int i = 0; i < eventOptions.length; i++) {
			if (indexShownOptions >= 4) {
				break;
			}

			if (eventOptions[i].getPlayersVisableTo()[turnNumberOG % 4]) {

				double rootX = (indexShownOptions % 2 == 0) ? .226f * (float) screenWidth : .042f * (float) screenWidth;
				double rootY = (indexShownOptions < 2) ? .52f * (float) screenHeight : .39f * (float) screenHeight;

				eventText[indexShownOptions * 5].setText(eventOptions[i].getText());
				eventText[indexShownOptions * 5].setStyle("-fx-font-size: " + (int) (40 * widthPortion)
						+ "; -fx-effect: dropshadow(gaussian, " + colors[turnNumberOG % 4] + "," + 4 * widthPortion
						+ ", 1.0, 0, 0); -fx-fill: #ffffff; -fx-cursor: hand; -fx-text-alignment: center;");
				eventText[indexShownOptions * 5].setFont(Main.fredokaOne);
				eventText[indexShownOptions * 5].setLayoutX(rootX + .02f * (float) screenWidth);
				eventText[indexShownOptions * 5].setLayoutY(rootY + .033f * (float) screenHeight);
				eventNodes.add(eventText[indexShownOptions * 5]);

				eventButtons[indexShownOptions].setViewport(new Rectangle2D((turnNumberOG % 4) * 563, 0, 563, 184));
				eventNodes.add(eventButtons[indexShownOptions]);

				eventButtons[indexShownOptions + 4].setViewport(new Rectangle2D((turnNumberOG % 4) * 563, 0, 563, 184));
				eventNodes.add(eventButtons[indexShownOptions + 4]);

				eventButtons[indexShownOptions].setScaleX(widthPortion);
				eventButtons[indexShownOptions].setScaleY(heightPortion);
				eventButtons[indexShownOptions].setLayoutX((1.0f - widthPortion) * -563 / 2 + rootX);
				eventButtons[indexShownOptions].setLayoutY((1.0f - heightPortion) * -184 / 2 + rootY);
				eventButtons[indexShownOptions].setStyle("-fx-cursor: hand;");

				eventButtons[indexShownOptions + 4].setScaleX(widthPortion);
				eventButtons[indexShownOptions + 4].setScaleY(heightPortion);
				eventButtons[indexShownOptions + 4].setLayoutX((1.0f - widthPortion) * -563 / 2 + rootX);
				eventButtons[indexShownOptions + 4].setLayoutY((1.0f - heightPortion) * -184 / 2 + rootY);
				eventButtons[indexShownOptions + 4].setStyle("-fx-cursor: hand;");

				boolean meetsRequirements = true;
				for (int j = 0; j < 4; j++) {
					if (players[turnNumberOG % 4].getResources()[j] < eventOptions[i].getRequirements()[j]) {
						meetsRequirements = false;
						break;
					}
				}

				int numEffects = 0;
				for (int j = 0; j < 4; j++) {
					int effect = eventOptions[i].getEffects()[j];
					if (effect != 0) {
						eventText[indexShownOptions * 5 + numEffects + 1]
								.setText(((effect > 0) ? "+" : "-") + Math.abs(effect));
						eventText[indexShownOptions * 5 + numEffects + 1].setStyle("-fx-font-size: "
								+ (int) (40 * widthPortion) + "; -fx-effect: dropshadow(gaussian, "
								+ colors[turnNumberOG % 4 + 8] + "," + 5 * widthPortion + ", 1.0, 0, 0); -fx-fill: "
								+ ((!meetsRequirements) ? "grey;" : (effect > 0) ? "#50aa41;" : "#d50606;")
								+ " -fx-text-alignment: center;");
						eventText[indexShownOptions * 5 + numEffects + 1].setFont(Main.arialRoundedMTBold);
						eventText[indexShownOptions * 5 + numEffects + 1].setLayoutX(
								-eventText[indexShownOptions * 5 + numEffects + 1].getBoundsInParent().getWidth()
										/ (1.0f / widthPortion) + .01f * (float) screenWidth + rootX
										+ (numEffects * .04f * (float) screenWidth));
						eventText[indexShownOptions * 5 + numEffects + 1]
								.setLayoutY(rootY + .1025f * (float) screenHeight);
						eventNodes.add(eventText[indexShownOptions * 5 + numEffects + 1]);

						ImageView resourceLogo = resourceLogos[numEffects + indexShownOptions * 4];
						resourceLogo.setViewport(new Rectangle2D((turnNumberOG % 4) * 80, j * 65, 80, 65));
						resourceLogo.setScaleX(widthPortion);
						resourceLogo.setScaleY(heightPortion);
						resourceLogo.setLayoutX((1.0f - widthPortion) * -80 / 2 + rootX + .013f * (float) screenWidth
								+ (numEffects * .04f * (float) screenWidth));
						resourceLogo
								.setLayoutY((1.0f - heightPortion) * -65 / 2 + .085f * (float) screenHeight + rootY);
						eventNodes.add(resourceLogo);
						numEffects++;
					}
				}

				Tooltip tooltip = new Tooltip();
				tooltip.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
				tooltip.setShowDelay(new Duration(10));
				tooltip.setShowDuration(Duration.INDEFINITE);
				tooltip.setHideDelay(Duration.ZERO);

				int[] optionIndex = { i };
				int[] optionIndexShown = { indexShownOptions };

				EventHandler<MouseEvent> eventEnter = (e -> {
					eventButtons[optionIndexShown[0] + 4]
							.setViewport(new Rectangle2D((turnNumberOG % 4) * 563 + 2252, 0, 563, 184));
				});
				EventHandler<MouseEvent> eventLeave = (e -> {
					eventButtons[optionIndexShown[0] + 4]
							.setViewport(new Rectangle2D((turnNumberOG % 4) * 563, 0, 563, 184));
				});

				eventButtons[indexShownOptions].setOnMouseEntered(eventEnter);
				eventButtons[indexShownOptions].setOnMouseExited(eventLeave);
				eventButtons[indexShownOptions + 4].setOnMouseEntered(eventEnter);
				eventButtons[indexShownOptions + 4].setOnMouseExited(eventLeave);
				eventText[indexShownOptions * 5].setOnMouseEntered(eventEnter);
				eventText[indexShownOptions * 5].setOnMouseExited(eventLeave);

				if (meetsRequirements) {
					EventHandler<MouseEvent> eventClick = (e -> {
						if (!hasClickedAnOption[0] && eventButtons[optionIndexShown[0]].isVisible() && canPressEvent) {
							hasClickedAnOption[0] = true;
							for (int j = 0; j < 4; j++) {
								players[turnNumberOG % 4].getResources()[j] += eventOptions[optionIndex[0]]
										.getEffects()[j];
							}

							if (eventOptions[optionIndex[0]].getObjectiveIndex() != -1) {
								players[turnNumberOG % 4].getObjectives()[eventOptions[optionIndex[0]]
										.getObjectiveIndex()]++;
							}
							
							if (eventOptions[optionIndex[0]].getItemIndex() != -1) {
								players[turnNumberOG % 4].getItems()[eventOptions[optionIndex[0]]
										.getItemIndex()] = true;
							}

							if (eventOptions[optionIndex[0]].getEndsEvent()) {
								landed(-2);
							} else {
								landed(eventIndex);
								updateUI();
							}
						}
					});
					EventHandler<MouseEvent> eventPressed = (e -> {
						if (!hasClickedAnOption[0] && eventButtons[optionIndexShown[0]].isVisible() && canPressEvent) {

							AnimationTimer pressAnimation = new AnimationTimer() {
								private long lastUpdate;
								double scaler;

								@Override
								public void start() {
									lastUpdate = System.nanoTime();
									scaler = 0;
									super.start();
								}

								@Override
								public void handle(long now) {
									double elapsedSeconds = (now - lastUpdate) / 1_000_000_000.0;
									scaler += elapsedSeconds * 2;
									if (scaler >= 1) {
										stop();
									}
									if (scaler >= .5 && hasClickedAnOption[0] == false) {
										scaler = .5;
									}
									eventButtons[optionIndexShown[0] + 4].setTranslateY(
											((scaler < .5) ? (scaler) : (1 - scaler)) * .017f * (float) screenHeight);
									eventText[optionIndexShown[0] * 5].setTranslateY(
											((scaler < .5) ? (scaler) : (1 - scaler)) * .017f * (float) screenHeight);
									lastUpdate = now;
								}

								@Override
								public void stop() {
									eventButtons[optionIndexShown[0] + 4].setTranslateY(0);
									eventText[optionIndexShown[0] * 5].setTranslateY(0);
									super.stop();
								}
							};
							pressAnimation.start();
						}
					});

					eventButtons[indexShownOptions].setOnMousePressed(eventPressed);
					eventButtons[indexShownOptions].setOnMouseReleased(eventClick);
					eventButtons[indexShownOptions + 4].setOnMousePressed(eventPressed);
					eventButtons[indexShownOptions + 4].setOnMouseReleased(eventClick);
					eventText[indexShownOptions * 5].setOnMousePressed(eventPressed);
					eventText[indexShownOptions * 5].setOnMouseReleased(eventClick);

					if (!eventOptions[i].getTooltip().equals("")) {
						tooltip.setText(eventOptions[i].getTooltip());
						Tooltip.install(eventButtons[indexShownOptions], tooltip);
						Tooltip.install(eventButtons[indexShownOptions + 4], tooltip);
					}
				} else {
					eventButtons[indexShownOptions].setOnMousePressed(null);
					eventButtons[indexShownOptions].setOnMouseReleased(null);
					eventButtons[indexShownOptions + 4].setOnMousePressed(null);
					eventButtons[indexShownOptions + 4].setOnMouseReleased(null);
					eventText[indexShownOptions * 5].setOnMousePressed(null);
					eventText[indexShownOptions * 5].setOnMouseReleased(null);
					String requirementsString = " Requires: "
							+ ((eventOptions[i].getRequirements()[0] != 0)
									? eventOptions[i].getRequirements()[0] + " Health, "
									: "")
							+ ((eventOptions[i].getRequirements()[1] != 0)
									? eventOptions[i].getRequirements()[1] + " Education, "
									: "")
							+ ((eventOptions[i].getRequirements()[2] != 0)
									? eventOptions[i].getRequirements()[2] + " Goods, "
									: "")
							+ ((eventOptions[i].getRequirements()[3] != 0)
									? eventOptions[i].getRequirements()[3] + " Money"
									: "");
					if (requirementsString.substring(requirementsString.length() - 2, requirementsString.length())
							.equals(", ")) {
						requirementsString = requirementsString.substring(0, requirementsString.length() - 2);
					}
					tooltip.setText(eventOptions[i].getTooltip() + requirementsString);
					Tooltip.install(eventButtons[indexShownOptions], tooltip);
					Tooltip.install(eventButtons[indexShownOptions + 4], tooltip);
				}

				indexShownOptions++;
			}
		}

	}

	private void processMoveInput(Pane board, int direction) {
		int changeInMovement = boardHandler.tryToMove(board, direction, movement);
		if (changeInMovement != -1) {
			canMove = false;
			movement -= changeInMovement;
			if ((movement * 128) - 128 < 0) {
				Tools.linearFadeOut(die, .5);
				Tools.linearFadeOut(playerText[22], .5);
				Tools.linearFadeOut(playerText[23], .5);
				Tools.linearFadeOut(endButtonTop, .5);
				Tools.linearFadeOut(endButtonBottom, .5);
			} else {
				die.setViewport(new Rectangle2D((movement * 128) - 128, 0, 128, 128));
			}
		}
	}
	
	private void endGame() {
		Stage tempStage = (Stage) pane.getScene().getWindow();
		EpilogueScreenHandler eSH = new EpilogueScreenHandler(players);
		//MainSceneHandler mainSceneHandler = new MainSceneHandler(players);
		Scene mainScene = eSH.getEpilogueScene();
		tempStage.setScene(mainScene);
		tempStage.centerOnScreen();
	}

}
