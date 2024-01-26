package application;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class BoardHandler {
	private int shouldMove =0;
	
	public void animateSquareBallMovement(Pane ball, int direction) {
		AnimationTimer animator = new AnimationTimer(){
			  @Override
			  public void handle(long now) {
				  if(shouldMove!=50) {
			    moveSquareBallGroup(ball,direction);
			    shouldMove++;
				  } else {
					  shouldMove=0;
					  this.stop();
				  }
			    }
			};
			animator.start();
	}
	
	private void reorderSquareBallDepth(Pane ball) {
		boolean ordered = false;
		Object[] tiles = ball.getChildren().toArray();
		while (!ordered) {
			ordered=true;
			for(int i= 0; i<tiles.length-1;i++) {
				
				float[] data =(float[])((Node)tiles[i]).getUserData();
				float[] data1 =(float[])((Node)tiles[i+1]).getUserData();
				
				if(		Math.abs(data[0]-data[3])+Math.abs(data[1]-data[4])>
						Math.abs(data1[0]-data1[3])+Math.abs(data1[1]-data1[4])
						) {
					Object temp = tiles[i];
					tiles[i]=tiles[i+1];
					tiles[i+1]=temp;
					ordered=false;
				}
				((Node)tiles[i]).setViewOrder(i);
				if(i==tiles.length-2) {
					((Node)tiles[i+1]).setViewOrder(i+1);
				}
			}
		}
	}
	
	private void moveSquareBallGroup(Pane ball, int direction) {
		final float ROT_SPEED =.02f;
		for(Node p: ball.getChildren()) {
			// uncomment for fun ;)((Polygon)p).setFill(new Color(Math.random(),Math.random(),Math.random(),1));
			float[] data= (float[])p.getUserData();
			switch(direction) {
			case 0:
				data[1]+=ROT_SPEED;
				if(data[1]-data[3]>data[5]/2) {data[1]-=data[5];}
				break;
			case 1:
				data[0]+=ROT_SPEED;
				if(data[0]-data[4]>data[5]/2) {data[0]-=data[5];}
				break;
			case 2:
				data[1]-=ROT_SPEED;
				if(data[3]-data[1]>data[5]/2) {data[1]+=data[5];}
				break;
			case 3:
				data[0]-=ROT_SPEED;
				if(data[4]-data[0]>data[5]/2) {data[0]+=data[5];}
				break;
			
			}
			float[] data1=data.clone(); data1[1]++;
			float[] data2=data.clone(); data2[0]++; data2[1]++;
			float[] data3=data.clone(); data3[0]++;
			if (p instanceof Polygon) {
			((Polygon) p).getPoints().setAll(new Double[]{
					(double) calculateBallPointPosition(data)[0], (double) calculateBallPointPosition(data)[1],
					(double) calculateBallPointPosition(data1)[0], (double) calculateBallPointPosition(data1)[1],
					(double) calculateBallPointPosition(data2)[0], (double) calculateBallPointPosition(data2)[1],
					(double) calculateBallPointPosition(data3)[0], (double) calculateBallPointPosition(data3)[1]});;
			}
		}
		reorderSquareBallDepth(ball);
	}
	
	public Pane makeSquareBallGroup(float sideLengthScaler, int gridSize) {
		Pane ball = new Pane();
		float[] origin = new float[] {gridSize/2f,gridSize/2f};
		float[][][] points = new float[gridSize+1][gridSize+1][2];
		for(int i=0; i<=gridSize;i++) {
			for(int j=0; j<=gridSize;j++) {
				float[] pos = calculateBallPointPosition(i,j,sideLengthScaler,origin[0],origin[1]);
				points[i][j][0]=pos[0];
				points[i][j][1]=pos[1];
			}
		}
		for(int i=0; i<gridSize;i++) {
			for(int j=0; j<gridSize;j++) {
				Node quad;
				//if(i==3&&j==3) {
				if(false) {
					Image image = new Image("/assets/Medalgold.png",sideLengthScaler,sideLengthScaler, false, false);
					quad= new ImageView(image);
				} else {
				quad = new Polygon();
				((Polygon)quad).getPoints().addAll(new Double[]{
						(double) points[i][j][0], (double) points[i][j][1],
						(double) points[i][j+1][0], (double) points[i][j+1][1],
						(double) points[i+1][j+1][0], (double) points[i+1][j+1][1],
						(double) points[i+1][j][0], (double) points[i+1][j][1],});
				((Polygon)quad).setFill(new Color(new Random().nextFloat(0, .5f),Math.random(),new Random().nextFloat(0, .5f),1));
				((Polygon)quad).setStroke(Color.BLACK);
				}
				
				ball.getChildren().add(quad);
				quad.setUserData(new float[] {i,j, sideLengthScaler, origin[0], origin[1], gridSize});
			}
		}
		reorderSquareBallDepth(ball);
		return ball;
	}
	
	private float[] calculateBallPointPosition(float i, float j, float sideLengthScaler, float originX, float originY) {
		float[] pos = new float[2];
		final float number =1.13f;
		float dist=(j-originX)*(j-originX)+(i-originY)*(i-originY);
		dist = (float) Math.sqrt(dist);
		pos[0]=(float) (originX+((j-originX)*sideLengthScaler*(1/Math.pow(number, Math.pow(dist, 1.3)))));
		pos[1]=(float) (originY+((i-originY)*sideLengthScaler*(1/Math.pow(number, Math.pow(dist, 1.3)))));
		return pos;
	}
	
	private float[] calculateBallPointPosition(float[] data) {
		return calculateBallPointPosition(data[0],data[1],data[2],data[3],data[4]);
	}
}
