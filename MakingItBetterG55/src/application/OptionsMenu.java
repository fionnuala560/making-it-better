package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public abstract class OptionsMenu extends Application{

	public static void main(String args[]) {
		launch(args);
	}
	
	public static void options() {
		//creating new stage
		Stage stage = new Stage();
		GridPane optionsScreen = new GridPane();
		optionsScreen.setStyle("-fx-background-image: url('/woodbackground.jpg');");
		Scene optionsScene = new Scene(optionsScreen, 1200, 800);
		
		optionsScreen.setGridLinesVisible(false);
        final int numCols = 40;
        final int numRows = 40;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            optionsScreen.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            optionsScreen.getRowConstraints().add(rowConst);         
        }
        
        //lower banner
        Rectangle r1 = new Rectangle();
        r1.setWidth(1200);
        r1.setHeight(20);
        optionsScreen.add(r1, 0, 8);
        r1.setFill(Color.web("#152546"));
        
        //top banner
        Rectangle r2 = new Rectangle();
        r2.setWidth(1200);
        r2.setHeight(300);
        optionsScreen.add(r2,0, 0);
        r2.setFill(Color.web("#536F7B"));
        
        //title
        Text title = new Text("Options");
        title.setFont(Font.font("SansSerif", FontWeight.BOLD, 64));
        title.setStyle("-fx-fill: white;");
        optionsScreen.add(title, 16, 4);
        
        //back button
		Button back = new Button("Back");
		back.setStyle("-fx-cursor: hand; -fx-border-color: #152546; -fx-border-width: 14px; -fx-background-color: #536F7B; -fx-text-fill: white;");
		back.setFont(Font.font("SansSerif", FontWeight.BOLD, 40));
		back.setPrefSize(320, 29);
		optionsScreen.add(back, 28, 32, 32, 32);
		back.setOnAction(event -> {
			stage.close();
		});	
		
		stage.setTitle("Settings");
		stage.setScene(optionsScene);
		stage.showAndWait();
	}
}
