package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;

public abstract class Tools {
	
	public static ArrayList<Node> nodesAnimating = new ArrayList<Node>();
	public static ArrayList<AnimationTimer> activeAnimations = new ArrayList<AnimationTimer>();
	
	public static void linearFadeIn(Node n, double fadeSeconds) {
		linearFade(n, fadeSeconds, true);
	}
	
	public static void linearFadeOut(Node n, double fadeSeconds) {
		linearFade(n, fadeSeconds, false);
	}

	private static void linearFade(Node n, double fadeSeconds, boolean isFadeIn) {
		
		AnimationTimer fadeAnimation = new AnimationTimer() {
			private long lastUpdate;
			double scaler;

			@Override
			public void start() {
				
				int nodeIndex = nodesAnimating.indexOf(n);
				if(nodeIndex != -1) {
					scaler = Double.parseDouble(activeAnimations.get(nodeIndex).toString());
					activeAnimations.get(nodeIndex).stop();
				} else {
					scaler = 0;
				}
				nodesAnimating.add(n);
				nodeIndex = nodesAnimating.indexOf(n);
				activeAnimations.add(nodeIndex, this);
				
				lastUpdate = System.nanoTime();
				if(isFadeIn) {
					n.setVisible(true);
					n.setOpacity(0);
				} else {
					n.setOpacity(1);
				}
				super.start();
			}
			
			@Override
			public String toString() {
				return Double.toString(scaler);
			}

			@Override
			public void handle(long now) {
				double elapsedSeconds = (now - lastUpdate) / 1_000_000_000.0;
				scaler += elapsedSeconds;
				if (isFadeIn) {
					n.setOpacity(scaler / fadeSeconds);
				} else {
					n.setOpacity(1 - scaler / fadeSeconds);
				}
				if(scaler >= fadeSeconds) {
					stop();
				}
				lastUpdate = now;
			}
			
			@Override
			public void stop() {
				if(!isFadeIn) {
					n.setVisible(false);
				}
				int nodeIndex = nodesAnimating.indexOf(n);
				activeAnimations.remove(nodeIndex);
				nodesAnimating.remove(nodeIndex);
				super.stop();
			}
		};
		fadeAnimation.start();
	}
	
	
}
