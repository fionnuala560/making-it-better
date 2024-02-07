package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application implements EventHandler<ActionEvent>{
	
	int groupScoreValue = 78;
	
	String epilogueCase1 = new String("You did horrible :(");
	String epilogueCase2 = new String("You did pretty okay :/");
	String epilogueCase3 = new String("You did good :)");
	String epilogueCase4 = new String("You did amazing :D");
	
			
	@Override
	public void start(Stage window) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			window.setScene(scene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		window.setTitle("Epilogue");
		
		GridPane epilogueScreen = new GridPane();
		epilogueScreen.setStyle("-fx-background-image: url('/woodbackground.jpg');");
		Scene scene = new Scene(epilogueScreen, 1200, 800);
		
		epilogueScreen.setGridLinesVisible(false);
        final int numCols = 40;
        final int numRows = 40;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            epilogueScreen.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            epilogueScreen.getRowConstraints().add(rowConst);   
        }
        //Title 
        Text title = new Text("Congratulations!");
        GridPane.setConstraints(title, 9, 3);
        title.setFont(Font.font("SansSerif", FontWeight.BOLD, 100));
        
        //Group Scores display
        Text groupScoreTitle = new Text("Group Score");
        GridPane.setConstraints(groupScoreTitle, 18, 8);
        groupScoreTitle.setFont(Font.font("SansSerif", FontWeight.NORMAL, 30));
        
        Text groupScore = new Text("78%");
        GridPane.setConstraints(groupScore, 19, 13);
        groupScore.setFont(Font.font("SansSerif", FontWeight.NORMAL, 55));
        
        //Player name display
        Text p1 = new Text("Student: ");
        GridPane.setConstraints(p1, 4, 18);
        p1.setFont(Font.font("SansSerif", FontWeight.NORMAL, 30));
        
        Text p2  = new Text("Teacher: ");
        GridPane.setConstraints(p2, 12, 18);
        p2.setFont(Font.font("SansSerif", FontWeight.NORMAL, 30));
        
        Text p3 = new Text("Parent: ");
        GridPane.setConstraints(p3, 20, 18);
        p3.setFont(Font.font("SansSerif", FontWeight.NORMAL, 30));
        
        Text p4  = new Text("Engineer: ");
        GridPane.setConstraints(p4, 28, 18);
        p4.setFont(Font.font("SansSerif", FontWeight.NORMAL, 30));
        
        //Individual score display 
        Text p1Score = new Text("21%");
        GridPane.setConstraints(p1Score, 9, 18);
        p1Score.setFont(Font.font("SansSerif", FontWeight.NORMAL, 30));
        
        Text p2Score  = new Text("16%");
        GridPane.setConstraints(p2Score, 17, 18);
        p2Score.setFont(Font.font("SansSerif", FontWeight.NORMAL, 30));
        
        Text p3Score = new Text("24%");
        GridPane.setConstraints(p3Score, 24, 18);
        p3Score.setFont(Font.font("SansSerif", FontWeight.NORMAL, 30));
        
        Text p4Score  = new Text("23%");
        GridPane.setConstraints(p4Score, 33, 18);
        p4Score.setFont(Font.font("SansSerif", FontWeight.NORMAL, 30));
        
        //Epilogue 
        if (groupScoreValue> 75) {
        	 Text epilogue = new Text(epilogueCase1);
        	 GridPane.setConstraints(epilogue, 9, 25);
             epilogue.setFont(Font.font("SansSerif", FontWeight.NORMAL, 20));
             
        }else if (groupScoreValue> 50) {
        	Text epilogue = new Text(epilogueCase2);
       	 	GridPane.setConstraints(epilogue, 9, 25);
            epilogue.setFont(Font.font("SansSerif", FontWeight.NORMAL, 20));
        	
        } else if (groupScoreValue> 25) {
        	Text epilogue = new Text(epilogueCase3);
          	 GridPane.setConstraints(epilogue, 9, 25);
               epilogue.setFont(Font.font("SansSerif", FontWeight.NORMAL, 20));
               
        }else {Text epilogue = new Text(epilogueCase4);
     	 GridPane.setConstraints(epilogue, 9, 25);
         epilogue.setFont(Font.font("SansSerif", FontWeight.NORMAL, 20));
        }
        
        Button quitButton = new Button("Quit");
        GridPane.setConstraints(quitButton, 5, 30);
        
        
        epilogueScreen.getChildren().add(title);
        epilogueScreen.getChildren().add(groupScoreTitle);
        epilogueScreen.getChildren().add(groupScore);
        epilogueScreen.getChildren().add(p1);
        epilogueScreen.getChildren().add(p2);
        epilogueScreen.getChildren().add(p3);
        epilogueScreen.getChildren().add(p4);
        epilogueScreen.getChildren().add(p1Score);
        epilogueScreen.getChildren().add(p2Score);
        epilogueScreen.getChildren().add(p3Score);
        epilogueScreen.getChildren().add(p4Score);
       // epilogueScreen.getChildren().add(epilogue); not just working 100% oop
        epilogueScreen.getChildren().add(quitButton);
        
        window.setScene(scene);
		window.show();
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void handle(ActionEvent arg0) {
		
	}
}
