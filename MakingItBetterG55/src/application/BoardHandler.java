package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BoardHandler {
	private final float TOTAL_ROT_TICKS = 100.0f;
	private final float ROT_FACTOR = 3.0f;
	private int shouldMove = 0;
	private MainSceneHandler mainSceneHandler;
	private Pane ball;
	private Image[] tileImages = { new Image("/WaterTile.png", 256, 256, false, false),
			new Image("/GrassTile.png", 256, 256, false, false), new Image("/TreesTile.png", 256, 256, false, false),
			new Image("/DirtTile.png", 256, 256, false, false), new Image("/RiceTile.png", 256, 256, false, false),
			new Image("/HouseTile.png", 256, 256, false, false) };
	private ImageView playerImageViews[] = { new ImageView(new Image("/EngineerBot.png", 185, 185, false, false)),
			new ImageView(new Image("/TeacherBot.png", 185, 185, false, false)),
			new ImageView(new Image("/ParentBot.png", 185, 185, false, false)),
			new ImageView(new Image("/StudentBot.png", 185, 185, false, false)) };

	public BoardHandler(MainSceneHandler mainSceneHandler) {
		this.mainSceneHandler = mainSceneHandler;
	}

	private void animateSquareBallMovement(Pane ball, int direction) {
		// direction 0 = left; 1 = up; 2 = right; 3 = down;
		AnimationTimer animator = new AnimationTimer() {

			private long lastUpdate;

			@Override
			public void start() {
				lastUpdate = System.nanoTime();
				super.start();
			}

			@Override
			public void handle(long now) {
				double elaspedSeconds = (now - lastUpdate) / 1_000_000_000.0;

				if (shouldMove != TOTAL_ROT_TICKS) {
					int ticks = (int) ((Math.round(ROT_FACTOR * elaspedSeconds * TOTAL_ROT_TICKS)
							+ shouldMove > TOTAL_ROT_TICKS) ? TOTAL_ROT_TICKS - shouldMove
									: (Math.round(ROT_FACTOR * elaspedSeconds * TOTAL_ROT_TICKS) == 0) ? 1
											: Math.round(ROT_FACTOR * elaspedSeconds * TOTAL_ROT_TICKS));
					moveSquareBallGroup(ball, direction, ticks);
					shouldMove += ticks;
				} else {
					shouldMove = 0;
					mainSceneHandler.landed();
					roundAllCoords();
					this.stop();
				}
				lastUpdate = now;
			}
		};
		animator.start();
	}

	private void animateMoveToPlayerHori(Pane ball, int direction, int numMoves, int direction2, int numMoves2,
			int currentPlayer) {
		// direction 0 = left; 1 = up; 2 = right; 3 = down;
		int numMovements = Math.abs(numMoves);
		int numMovements2 = Math.abs(numMoves2);
		if (numMovements <= 0) {
			if (numMovements2 > 0) {
				animateMoveToPlayerVerti(ball, direction2, numMovements2, currentPlayer);
			} else {
				setPlayersData(currentPlayer);
				roundAllCoords();
				mainSceneHandler.landedNextTurn();
			}
			return;
		}
		AnimationTimer animator = new AnimationTimer() {
			private long lastUpdate;

			@Override
			public void start() {
				lastUpdate = System.nanoTime();
				super.start();
			}

			@Override
			public void handle(long now) {
				double elaspedSeconds = (now - lastUpdate) / 1_000_000_000.0;

				if (shouldMove != TOTAL_ROT_TICKS) {
					int ticks = (int) ((Math.round(ROT_FACTOR * elaspedSeconds * TOTAL_ROT_TICKS)
							+ shouldMove > TOTAL_ROT_TICKS) ? TOTAL_ROT_TICKS - shouldMove
									: (Math.round(ROT_FACTOR * elaspedSeconds * TOTAL_ROT_TICKS) == 0) ? 1
											: Math.round(ROT_FACTOR * elaspedSeconds * TOTAL_ROT_TICKS));
					moveSquareBallGroup(ball, direction, ticks);
					shouldMove += ticks;
				} else {
					shouldMove = 0;
					this.stop();
					if (numMovements > 1) {
						animateMoveToPlayerHori(ball, direction, numMovements - 1, direction2, numMovements2,
								currentPlayer);
					} else if (numMovements2 > 0) {
						animateMoveToPlayerVerti(ball, direction2, numMovements2, currentPlayer);
					} else {
						setPlayersData(currentPlayer);
						roundAllCoords();
						mainSceneHandler.landedNextTurn();
					}
				}
				lastUpdate = now;
			}
		};
		animator.start();
	}

	private void animateMoveToPlayerVerti(Pane ball, int direction, int numMoves, int currentPlayer) {
		// direction 0 = left; 1 = up; 2 = right; 3 = down;
		int numMovements = numMoves;
		AnimationTimer animator = new AnimationTimer() {
			private long lastUpdate;

			@Override
			public void start() {
				lastUpdate = System.nanoTime();
				super.start();
			}

			@Override
			public void handle(long now) {
				double elaspedSeconds = (now - lastUpdate) / 1_000_000_000.0;

				if (shouldMove != TOTAL_ROT_TICKS) {
					int ticks = (int) ((Math.round(ROT_FACTOR * elaspedSeconds * TOTAL_ROT_TICKS)
							+ shouldMove > TOTAL_ROT_TICKS) ? TOTAL_ROT_TICKS - shouldMove
									: (Math.round(ROT_FACTOR * elaspedSeconds * TOTAL_ROT_TICKS) == 0) ? 1
											: Math.round(ROT_FACTOR * elaspedSeconds * TOTAL_ROT_TICKS));
					moveSquareBallGroup(ball, direction, ticks);
					shouldMove += ticks;
				} else {
					shouldMove = 0;
					this.stop();
					if (numMovements > 1) {
						animateMoveToPlayerVerti(ball, direction, numMovements - 1, currentPlayer);
					} else {
						setPlayersData(currentPlayer);
						roundAllCoords();
						mainSceneHandler.landedNextTurn();
					}
				}
				lastUpdate = now;
			}
		};
		animator.start();
	}

	public void moveToNextPlayer(int currentPlayer) {
		setPlayersData(-1);
		float[] previousPlayerData = (float[]) playerImageViews[(currentPlayer + 3) % 4].getUserData();
		float[] currentPlayerData = (float[]) playerImageViews[currentPlayer].getUserData();
		int iDifference = Math.round(currentPlayerData[0]) - Math.round(previousPlayerData[0]);
		int jDifference = Math.round(currentPlayerData[1]) - Math.round(previousPlayerData[1]);
		int iDir = -1;
		int jDir = -1;

		if (iDifference > 0) {
			iDir = 3;
		} else {
			iDir = 1;
		}
		if (jDifference > 0) {
			jDir = 2;
		} else {
			jDir = 0;
		}
		animateMoveToPlayerHori(ball, jDir, jDifference, iDir, iDifference, currentPlayer);
	}

	private void setPlayersData(int currentPlayer) {
		for (int i = 0; i < 4; i++) {
			((float[]) playerImageViews[i].getUserData())[7] = (currentPlayer == i) ? 1 : 0;
		}
	}
	
	private void roundAllCoords() {
		for(Node n : ball.getChildren()) {
			float[] nodeData = (float[]) n.getUserData();
			nodeData[0] = Math.round(nodeData[0]);
			nodeData[1] = Math.round(nodeData[1]);
		}
	}

	public int tryToMove(Pane ball, int direction, int movement) {
		
		int changeInMovement = 0;
		ImageView currentPlayerIV = null;
		for (int i = 0; i < 4; i++) {
			if (((float[]) playerImageViews[i].getUserData())[7] == 1) {
				currentPlayerIV = playerImageViews[i];
			}
		}
		float[] currentPlayerData = (float[]) currentPlayerIV.getUserData();

		for (Node n : ball.getChildren()) {
			float[] nodeData = (float[]) n.getUserData();
			boolean willHit = false;
			
			switch (direction) {
			case 0:
				willHit = (nodeData[0] == currentPlayerData[0] && nodeData[1] == currentPlayerData[1]-1);
				break;
			case 1:
				willHit = (nodeData[0] == currentPlayerData[0]-1 && nodeData[1] == currentPlayerData[1]);
				break;
			case 2:
				willHit = (nodeData[0] == currentPlayerData[0] && nodeData[1] == currentPlayerData[1]+1);
				break;
			case 3:
				willHit = (nodeData[0] == currentPlayerData[0]+1 && nodeData[1] == currentPlayerData[1]);
				break;
			}
			
			if (willHit) {
				switch ((int) nodeData[6]) {
				case 0:
					return -1;
				case 1:
					changeInMovement = 1;
					break;
				case 2:
					if(movement >= 2) {
						changeInMovement = 2;
					} else {
						return -1;
					}
					break;
				case 3:
					changeInMovement = 1;
					break;
				case 69:
					return -1;
				default:
					changeInMovement = 1;
				}
			}
		}

		animateSquareBallMovement(ball, direction);
		return changeInMovement;
	}

	private void moveSquareBallGroup(Pane ball, int direction, int ticks) {
		final float ROT_SPEED = .01f;
		for (Node p : ball.getChildren()) {

			float[] data = (float[]) p.getUserData();
			if (data.length != 8 || data[7] == 0) {
				switch (direction) {
				case 0:
					data[1] += ROT_SPEED * ticks;
					if (data[1] - data[3] > data[5] / 2) {
						data[1] -= data[5];
					}
					break;
				case 1:
					data[0] += ROT_SPEED * ticks;
					if (data[0] - data[4] > data[5] / 2) {
						data[0] -= data[5];
					}
					break;
				case 2:
					data[1] -= ROT_SPEED * ticks;
					if (data[3] - data[1] > data[5] / 2) {
						data[1] += data[5];
					}
					break;
				case 3:
					data[0] -= ROT_SPEED * ticks;
					if (data[4] - data[0] > data[5] / 2) {
						data[0] += data[5];
					}
					break;

				}
				float[] data1 = data.clone();
				data1[1]++;
				float[] data2 = data.clone();
				data2[0]++;
				data2[1]++;
				float[] data3 = data.clone();
				data3[0]++;
				PerspectiveTransform pT = (PerspectiveTransform) p.getEffect();
				pT.setUlx((double) calculateBallPointPosition(data)[0]);
				pT.setUly((double) calculateBallPointPosition(data)[1]);
				pT.setUrx((double) calculateBallPointPosition(data1)[0]);
				pT.setUry((double) calculateBallPointPosition(data1)[1]);
				pT.setLrx((double) calculateBallPointPosition(data2)[0]);
				pT.setLry((double) calculateBallPointPosition(data2)[1]);
				pT.setLlx((double) calculateBallPointPosition(data3)[0]);
				pT.setLly((double) calculateBallPointPosition(data3)[1]);
				if (pT.getUrx() - pT.getLlx() < 1 || pT.getLry() - pT.getUly() < 1 || pT.getLrx() - pT.getUlx() < 1
						|| pT.getLly() - pT.getUry() < 1) {
					p.setVisible(false);
				} else {
					p.setVisible(true);
				}
			}
		}
	}

	public Pane makeSquareBallGroup(float sideLengthScaler) {
		int gridSize = 9;
		ball = new Pane();
		float[] origin = new float[] { gridSize / 2f, gridSize / 2f };
		float[][][] points = new float[gridSize + 1][gridSize + 1][2];
		for (int i = 0; i <= gridSize; i++) {
			for (int j = 0; j <= gridSize; j++) {
				float[] pos = calculateBallPointPosition(i, j, sideLengthScaler, origin[0], origin[1]);
				points[i][j][0] = pos[0];
				points[i][j][1] = pos[1];
			}
		}

		final int[][] tileTypes = getTileTypes();

		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				Node tile;

				Image image = tileImages[tileTypes[i][j]];
				tile = new ImageView(image);
				PerspectiveTransform pT = new PerspectiveTransform();
				pT.setUlx((double) points[i][j][0]);
				pT.setUly((double) points[i][j][1]);
				pT.setUrx((double) points[i][j + 1][0]);
				pT.setUry((double) points[i][j + 1][1]);
				pT.setLrx((double) points[i + 1][j + 1][0]);
				pT.setLry((double) points[i + 1][j + 1][1]);
				pT.setLlx((double) points[i + 1][j][0]);
				pT.setLly((double) points[i + 1][j][1]);
				tile.setEffect(pT);
				if (pT.getUrx() - pT.getLlx() < 1 || pT.getLry() - pT.getUly() < 1 || pT.getLrx() - pT.getUlx() < 1
						|| pT.getLly() - pT.getUry() < 1) {
					tile.setVisible(false);
				} else {
					tile.setVisible(true);
				}

				ball.getChildren().add(tile);
				tile.setUserData(
						new float[] { i, j, sideLengthScaler, origin[0], origin[1], gridSize, tileTypes[i][j] });
			}
		}

		for (int i = 0; i < 4; i++) {

			Node player = ball;
			int a = 0;
			int b = 0;

			switch (i) {
			case 0:
				player = playerImageViews[0];
				a = 4;
				b = 4;
				break;
			case 1:
				player = playerImageViews[1];
				a = 2;
				b = 2;
				break;
			case 2:
				player = playerImageViews[2];
				a = 3;
				b = 6;
				break;
			case 3:
				player = playerImageViews[3];
				a = 1;
				b = 3;
				break;
			}
			PerspectiveTransform pT = new PerspectiveTransform();
			pT.setUlx((double) points[a][b][0]);
			pT.setUly((double) points[a][b][1]);
			pT.setUrx((double) points[a][b + 1][0]);
			pT.setUry((double) points[a][b + 1][1]);
			pT.setLrx((double) points[a + 1][b + 1][0]);
			pT.setLry((double) points[a + 1][b + 1][1]);
			pT.setLlx((double) points[a + 1][b][0]);
			pT.setLly((double) points[a + 1][b][1]);
			player.setEffect(pT);
			if (pT.getUrx() - pT.getLlx() < 1 || pT.getLry() - pT.getUly() < 1 || pT.getLrx() - pT.getUlx() < 1
					|| pT.getLly() - pT.getUry() < 1) {
				player.setVisible(false);
			} else {
				player.setVisible(true);
			}

			ball.getChildren().add(player);
			player.setUserData(
					new float[] { a, b, sideLengthScaler, origin[0], origin[1], gridSize, 69, (i == 0) ? 1 : 0 });
		}

		return ball;
	}

	private float[] calculateBallPointPosition(float i, float j, float sideLengthScaler, float originX, float originY) {
		float[] pos = new float[2];
		final float number = 1.13f;
		float dist = (j - originX) * (j - originX) + (i - originY) * (i - originY);
		dist = (float) Math.sqrt(dist);
		pos[0] = (float) (originX + ((j - originX) * sideLengthScaler * (1 / Math.pow(number, Math.pow(dist, 1.3)))));
		pos[1] = (float) (originY + ((i - originY) * sideLengthScaler * (1 / Math.pow(number, Math.pow(dist, 1.3)))));
		return pos;
	}

	private float[] calculateBallPointPosition(float[] data) {
		return calculateBallPointPosition(data[0], data[1], data[2], data[3], data[4]);
	}

	private int[][] getTileTypes() {

		int[][] tileTypes = new int[9][9];

		try (BufferedReader bufferedReader = new BufferedReader(
				new FileReader(System.getProperty("user.dir") + "/MakingItBetterG55/src/tiles.txt"))) {

			String line = bufferedReader.readLine();
			for (int i = 0; i < 9; i++) {
				line = bufferedReader.readLine();
				for (int j = 0; j < 9; j++) {
					tileTypes[i][j] = Character.getNumericValue(line.charAt(j));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tileTypes;
	}

}
