package application;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;

public abstract class Tools {
	
	public static void linearFadeIn(Node n, double fadeSeconds) {
		linearFade(n, fadeSeconds, true);
	}
	
	public static void linearFadeOut(Node n, double fadeSeconds) {
		linearFade(n, fadeSeconds, false);
	}

	private static void linearFade(Node n, double fadeSeconds, boolean isFadeIn) {
		
		AnimationTimer fadeInAnimation = new AnimationTimer() {
			private long lastUpdate;
			double scaler;

			@Override
			public void start() {
				scaler = 0;
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
				super.stop();
			}
		};
		fadeInAnimation.start();
	}
	
	
}
