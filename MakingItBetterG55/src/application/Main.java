package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			TextRetriever textRetriever = new TextRetriever();
			GridPane homeScreen = new GridPane();
			homeScreen.setStyle("-fx-background-image: url('/woodbackground.jpg');");
			Scene homeScene = new Scene(homeScreen, 1200, 800);

			GridPane root = new GridPane();
			Scene optionsScene = new Scene(root, 1200, 800);
			optionsScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			OptionsMenu om = new OptionsMenu();
			
			GameStartupScreen gSS = new GameStartupScreen(homeScene, om);
			Scene gSSScene = gSS.getGameStartupScreen();

			homeScreen.setGridLinesVisible(false);
	        final int numCols = 40;
	        final int numRows = 40;
	        for (int i = 0; i < numCols; i++) {
	            ColumnConstraints colConst = new ColumnConstraints();
	            colConst.setPercentWidth(100.0 / numCols);
	            homeScreen.getColumnConstraints().add(colConst);
	        }
	        for (int i = 0; i < numRows; i++) {
	            RowConstraints rowConst = new RowConstraints();
	            rowConst.setPercentHeight(100.0 / numRows);
	            homeScreen.getRowConstraints().add(rowConst);         
	        }
	        Text title = new Text(textRetriever.getText(1,1));
	        title.setFont(Font.font("SansSerif", FontWeight.BOLD, 100));
	        title.setStyle("-fx-fill: black; -fx-stroke: white; -fx-stroke-width: 4px;");

			Button playButton = new Button("Play");
			playButton.setStyle("-fx-cursor: hand; -fx-border-color: #152546; -fx-border-width: 14px; -fx-background-color: #536F7B; -fx-text-fill: white;");
			playButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 40));
			playButton.setPrefSize(320, 29);
			
			playButton.setOnAction(event -> {
		    primaryStage.setScene(gSSScene);
			});
			
			Button quitButton = new Button("Quit");
			quitButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 40));
			quitButton.setStyle("-fx-cursor: hand; -fx-border-color: #152546; -fx-border-width: 14px; -fx-background-color: #536F7B; -fx-text-fill: white;");
			quitButton.setPrefSize(320, 29);
			//Adding the event to close the menu
			quitButton.setOnAction(event -> {
				System.exit(0);
			});	
						
			ImageView settingsWheel = new ImageView("/setting.png");			
			settingsWheel.setStyle("-fx-cursor: hand;");
			settingsWheel.setFitWidth(100);
			settingsWheel.setFitHeight(100);

			Button settingsButton = new Button("");
			settingsButton.setGraphic(settingsWheel);
			settingsButton.setStyle("-fx-cursor: hand;");
			settingsButton.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
			settingsButton.setOnAction(event -> {
				Scene oMScene = om.getOptionsMenu(homeScene);
				primaryStage.setScene(oMScene);
			});
			

			homeScreen.add(settingsButton, 1, 32);

			homeScreen.add(title, 8, 0, 40, 20);
			homeScreen.add(playButton, 14, 10, 40, 20);
			homeScreen.add(quitButton, 14, 12, 40, 40);

			homeScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(homeScene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}