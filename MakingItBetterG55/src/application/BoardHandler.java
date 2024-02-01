package application;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BoardHandler {
	private int shouldMove = 0;

	public void animateSquareBallMovement(Pane ball, int direction) {
		AnimationTimer animator = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (shouldMove != 50) {
					moveSquareBallGroup(ball, direction);
					shouldMove++;
				} else {
					shouldMove = 0;
					this.stop();
				}
			}
		};
		animator.start();
	}

	private void moveSquareBallGroup(Pane ball, int direction) {
		final float ROT_SPEED = .02f;
		for (Node p : ball.getChildren()) {

			float[] data = (float[]) p.getUserData();
			switch (direction) {
			case 0:
				data[1] += ROT_SPEED;
				if (data[1] - data[3] > data[5] / 2) {
					data[1] -= data[5];
				}
				break;
			case 1:
				data[0] += ROT_SPEED;
				if (data[0] - data[4] > data[5] / 2) {
					data[0] -= data[5];
				}
				break;
			case 2:
				data[1] -= ROT_SPEED;
				if (data[3] - data[1] > data[5] / 2) {
					data[1] += data[5];
				}
				break;
			case 3:
				data[0] -= ROT_SPEED;
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

	public Pane makeSquareBallGroup(float sideLengthScaler, int gridSize) {
		Pane ball = new Pane();
		float[] origin = new float[] { gridSize / 2f, gridSize / 2f };
		float[][][] points = new float[gridSize + 1][gridSize + 1][2];
		for (int i = 0; i <= gridSize; i++) {
			for (int j = 0; j <= gridSize; j++) {
				float[] pos = calculateBallPointPosition(i, j, sideLengthScaler, origin[0], origin[1]);
				points[i][j][0] = pos[0];
				points[i][j][1] = pos[1];
			}
		}
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				Node tile;

				Image image;
				Random r = new Random();
				float val = r.nextFloat();
				if (val > .83f) {
					image = new Image("/GrassTile.png", sideLengthScaler, sideLengthScaler, false, false);
				} else if (val > .66f) {
					image = new Image("/WaterTile.png", sideLengthScaler, sideLengthScaler, false, false);
				} else if (val > .5f) {
					image = new Image("/DirtTile.png", sideLengthScaler, sideLengthScaler, false, false);
				} else if (val > .33f) {
					image = new Image("/RiceTile.png", sideLengthScaler, sideLengthScaler, false, false);
				} else if (val > .16f) {
					image = new Image("/TreesTile.png", sideLengthScaler, sideLengthScaler, false, false);
				} else {
					image = new Image("/HouseTile.png", sideLengthScaler, sideLengthScaler, false, false);
				}
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
				tile.setUserData(new float[] { i, j, sideLengthScaler, origin[0], origin[1], gridSize });
			}
		}

		for (int i = 0; i < 4; i++) {

			Node tile = ball;
			int a = 0;
			int b = 0;
			Image image;

			switch(i) {
			case 0:
				image = new Image("/TeacherBot.png", sideLengthScaler, sideLengthScaler, false, false);
				tile = new ImageView(image);
				a = 4;
				b = 4;
				break;
			case 1:
				image = new Image("/EngineerBot.png", sideLengthScaler, sideLengthScaler, false, false);
				tile = new ImageView(image);
				a = 2;
				b = 2;
				break;
			case 2:
				image = new Image("/StudentBot.png", sideLengthScaler, sideLengthScaler, false, false);
				tile = new ImageView(image);
				a = 3;
				b = 6;
				break;
			case 3:
				image = new Image("/ParentBot.png", sideLengthScaler, sideLengthScaler, false, false);
				tile = new ImageView(image);
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
			tile.setEffect(pT);
			if (pT.getUrx() - pT.getLlx() < 1 || pT.getLry() - pT.getUly() < 1 || pT.getLrx() - pT.getUlx() < 1
					|| pT.getLly() - pT.getUry() < 1) {
				tile.setVisible(false);
			} else {
				tile.setVisible(true);
			}

			ball.getChildren().add(tile);
			tile.setUserData(new float[] { a, b, sideLengthScaler, origin[0], origin[1], gridSize });
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
}
