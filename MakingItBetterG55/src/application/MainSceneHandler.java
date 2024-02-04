package application;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MainSceneHandler {
	
	public Scene makeMainScene() {
		GridPane root = new GridPane();
		Scene mainScene = new Scene(root, 1200, 800);
		mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		BoardHandler boardHandler = new BoardHandler();
		Pane board = boardHandler.makeSquareBallGroup(200);
		root.add(board,0,0);
		board.setTranslateX(550);
		board.setTranslateY(400);
		mainScene.addEventHandler(KeyEvent.KEY_RELEASED,(KeyEvent event)->{

                if (event.getCode() == KeyCode.A) {
                	boardHandler.animateSquareBallMovement(board,0);
                }
                if (event.getCode() == KeyCode.W) {
                	boardHandler.animateSquareBallMovement(board,1);
                }
                if (event.getCode() == KeyCode.D) {
                	boardHandler.animateSquareBallMovement(board,2);
                }
                if (event.getCode() == KeyCode.S) {
                	boardHandler.animateSquareBallMovement(board,3);
                }
            
        });
		return mainScene;
	}
}
