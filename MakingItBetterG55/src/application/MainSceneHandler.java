package application;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MainSceneHandler {
	
	private int turnNumber = 0;
	private boolean canMove = true;
	
	public void handleTurn() {
		switch(turnNumber % 4) {
		case 0:
			System.out.println("holato");
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		}
		canMove = true;
		turnNumber++;
	}
	
	public Scene makeMainScene() {
		GridPane root = new GridPane();
		Scene mainScene = new Scene(root, 1200, 800);
		mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		BoardHandler boardHandler = new BoardHandler(this);
		Pane board = boardHandler.makeSquareBallGroup(200);
		root.add(board,0,0);
		board.setTranslateX(550);
		board.setTranslateY(400);
		mainScene.addEventHandler(KeyEvent.KEY_RELEASED,(KeyEvent event)->{

                if (event.getCode() == KeyCode.A && canMove == true) {
                	boardHandler.animateSquareBallMovement(board,0);
                	canMove = false;
                }
                if (event.getCode() == KeyCode.W && canMove == true) {
                	boardHandler.animateSquareBallMovement(board,1);
                	canMove = false;
                }
                if (event.getCode() == KeyCode.D && canMove == true) {
                	boardHandler.animateSquareBallMovement(board,2);
                	canMove = false;
                }
                if (event.getCode() == KeyCode.S && canMove == true) {
                	boardHandler.animateSquareBallMovement(board,3);
                	canMove = false;
                }
            
        });
		return mainScene;
	}
}
