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

public class OptionsMenu {
	private Scene scene;
	
	public OptionsMenu(Scene optionsScene, Scene homeScene, Stage primaryStage) {
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
			Stage tempMain = (Stage) back.getScene().getWindow();
			tempMain.setScene(homeScene);
		});	

		//colour blind
		Text cb = new Text("Colourblind Mode");
		cb.setFont(Font.font("sansSerif", FontWeight.BOLD, 32));
		cb.setStyle("-fx-fill: white;");
		optionsScreen.add(cb, 21, 13);
	
		MenuButton colours = new MenuButton("Colour Options");
		colours.setMinSize(400, 62);
		colours.setStyle("-fx-cursor: hand; -fx-border-color: #152546; -fx-border-width: 4px; -fx-background-color: #536F7B; -fx-text-fill: white;");
		colours.setFont(Font.font("SansSerif", FontWeight.BOLD, 28));
		MenuItem protanopia = new MenuItem("Protanopia (Red-Green)");
		protanopia.setOnAction(event ->{
			colours.setText("Protanopia (Red-Green)");
		});
		MenuItem deuteranopia = new MenuItem ("Deuteranopia (Red-Green)");
		deuteranopia.setOnAction(event ->{
			colours.setText("Deuteranopia (Red-Green)");
		});
		MenuItem tritanopia = new MenuItem("Tritanopia (Blue-Yellow)");
		tritanopia.setOnAction(event ->{
			colours.setText("Tritanopia (Blue-Yellow)");
		});
		colours.getItems().addAll(protanopia, deuteranopia, tritanopia);
		optionsScreen.add(colours, 21, 16);
	
		//language
		Text lang = new Text("Language");
		lang.setFont(Font.font("sansSerif", FontWeight.BOLD, 32));
		lang.setStyle("-fx-fill: white;");
		optionsScreen.add(lang, 21, 22);
		
		MenuButton languages = new MenuButton("English");
		languages.setMinSize(400, 62);
		languages.setStyle("-fx-cursor: hand; -fx-border-color: #152546; -fx-border-width: 4px; -fx-background-color: #536F7B; -fx-text-fill: white;");
		languages.setFont(Font.font("SansSerif", FontWeight.BOLD, 28));
		MenuItem english = new MenuItem("English");
		english.setOnAction(event ->{
			languages.setText("English");
		});
		MenuItem spanish = new MenuItem("Spanish");
		spanish.setOnAction(event ->{
			languages.setText("Spanish");
		});
		MenuItem german = new MenuItem("German");
		german.setOnAction(event ->{
			languages.setText("German");
		});
		MenuItem italian = new MenuItem("Italian");
		italian.setOnAction(event ->{
			languages.setText("Italian");
		});
		languages.getItems().addAll(english, spanish, german, italian);
		optionsScreen.add(languages, 21, 25);
	
		//fullscreen
		Text fullscreen = new Text("Full Screen");
		fullscreen.setFont(Font.font("sansSerif", FontWeight.BOLD, 32));
		fullscreen.setStyle("-fx-fill: white;");
		optionsScreen.add(fullscreen, 3, 14);
		
		CheckBox fsCheckBox = new CheckBox("");
		optionsScreen.add(fsCheckBox, 2, 14);
		fsCheckBox.setOnAction(event ->{
			if (fsCheckBox.isSelected()) {
				stage.setFullScreen(true);
				r1.setWidth(primaryStage.getWidth());
				r1.setHeight(primaryStage.getHeight() / 40);
				r2.setWidth(primaryStage.getWidth());
				r2.setHeight(primaryStage.getHeight()*3 / 8);
			}
			if (!(fsCheckBox.isSelected())) {
				stage.setFullScreen(false);
				r1.setWidth(1200);
				r1.setHeight(20);
				r2.setWidth(1200);
				r2.setHeight(300);
			}
		});
	
		//music
		Text music = new Text("Music");
		music.setFont(Font.font("sansSerif", FontWeight.BOLD, 32));
		music.setStyle("-fx-fill: white;");
		optionsScreen.add(music, 3, 19);
		
		CheckBox musicCheckBox = new CheckBox("");
		optionsScreen.add(musicCheckBox, 2, 19);

		//volume
		Text volume = new Text("Volume");
		volume.setFont(Font.font("sansSerif", FontWeight.BOLD, 32));
		volume.setStyle("-fx-fill: white;");
		optionsScreen.add(volume, 2, 24);
		
		Slider slider = new Slider();
		slider.setMinSize(200, 50);
		optionsScreen.add(slider, 2, 27);
	}

	public Scene getOptionsMenu() {
		return scene;
	}
	
}
