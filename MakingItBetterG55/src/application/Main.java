package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//Will be moved into main screen aka stage/scene once that exists
			BoardHandler boardHandler = new BoardHandler();
			Pane board = boardHandler.makeSquareBallGroup(100, 9);
			root.add(board,0,0);
			board.setTranslateX(300);
			board.setTranslateY(300);
			primaryStage.addEventHandler(KeyEvent.KEY_RELEASED,(KeyEvent event)->{

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
			// end of section for main screen
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
