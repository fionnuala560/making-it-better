package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EventDisplayer {
	private String title;
	private String desc;
	private String[] options;

	// Feel free to change as much as this as you want, the primary goal was to serve as
	// a graphical foundation for the event popup system.
	public EventDisplayer(String title, String description, String[] options) {
		this.title = title;
		this.desc = description;
		this.options = options;
		openPopup(title, desc, options);
	}

	private void openPopup(String eventTitle, String eventDesc, String[] options) {
		Stage eventStage = new Stage();
		eventStage.initModality(Modality.APPLICATION_MODAL);
		eventStage.initStyle(StageStyle.UNDECORATED);

		AnchorPane eventLayout = new AnchorPane();
		eventLayout.setStyle(
				"-fx-border-color: #E1005C; -fx-border-width: 18px; -fx-background-color: #F79FC7; -fx-text-fill: white;");

		Label titleLabel = new Label(eventTitle);
		titleLabel.setStyle("-fx-text-fill: white;");
		titleLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 40));
		AnchorPane.setTopAnchor(titleLabel, 10.0);
		AnchorPane.setLeftAnchor(titleLabel, 10.0);
		AnchorPane.setRightAnchor(titleLabel, 10.0);
		titleLabel.setAlignment(Pos.TOP_CENTER);

		Label descriptionLabel = new Label(eventDesc);
		descriptionLabel.setStyle("-fx-text-fill: white;");
		descriptionLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 18));
		descriptionLabel.setPrefWidth(300);
		descriptionLabel.setWrapText(true);
		AnchorPane.setTopAnchor(descriptionLabel, 80.0);
		AnchorPane.setLeftAnchor(descriptionLabel, 20.0);
		AnchorPane.setRightAnchor(descriptionLabel, 20.0);
		descriptionLabel.setAlignment(Pos.TOP_CENTER);

		eventLayout.getChildren().addAll(titleLabel, descriptionLabel);

		// For every option passed, create a button
		for (int i = 0; i < options.length; i++) {
			// can identify which button is clicked based on text
			final String buttonText = options[i];
			Button button = new Button();
			button.setText(buttonText);
			button.setPrefSize(155, 60);
			button.setFont(Font.font("SansSerif", FontWeight.BOLD, 11));
			button.setStyle(
					"-fx-border-color: #E1005C, black; -fx-border-width: 12px, 6px; -fx-background-color: #F79FC7; -fx-border-radius: 5px; -fx-text-fill: white; -fx-cursor: hand;");
			// Education stat changes
			Label educationLabel = new Label("+10");
			educationLabel.setStyle("-fx-text-fill: white;");
			educationLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 14));
			ImageView educationSymbol = new ImageView("/education.png");
			educationSymbol.setFitWidth(35);
			educationSymbol.setFitHeight(35);

			// Health stat changes
			Label healthLabel = new Label("-27");
			healthLabel.setStyle("-fx-text-fill: white;");
			healthLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 14));
			ImageView healthSymbol = new ImageView("/health.png");
			healthSymbol.setFitWidth(20);
			healthSymbol.setFitHeight(20);

			// Goods stat changes
			Label goodsLabel = new Label("+1");
			goodsLabel.setStyle("-fx-text-fill: white;");
			goodsLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 14));
			ImageView goodsSymbol = new ImageView("/goods.png");
			goodsSymbol.setFitWidth(25);
			goodsSymbol.setFitHeight(25);

			// Money stat changes
			Label moneyLabel = new Label("-99");
			moneyLabel.setStyle("-fx-text-fill: white;");
			moneyLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 14));
			ImageView moneySymbol = new ImageView("/money.png");
			moneySymbol.setFitWidth(15);
			moneySymbol.setFitHeight(15);

			// Placing each button and stat changes on the screen
			switch (i) {
			// first button
			case 0:
				AnchorPane.setTopAnchor(button, 230.0);
				AnchorPane.setLeftAnchor(button, 30.0);

				AnchorPane.setTopAnchor(educationLabel, 295.0);
				AnchorPane.setLeftAnchor(educationLabel, 10.0);
				AnchorPane.setTopAnchor(educationSymbol, 285.0);
				AnchorPane.setLeftAnchor(educationSymbol, 32.0);

				AnchorPane.setTopAnchor(healthLabel, 295.0);
				AnchorPane.setLeftAnchor(healthLabel, 65.0);
				AnchorPane.setTopAnchor(healthSymbol, 295.0);
				AnchorPane.setLeftAnchor(healthSymbol, 87.0);

				AnchorPane.setTopAnchor(goodsLabel, 295.0);
				AnchorPane.setLeftAnchor(goodsLabel, 110.0);
				AnchorPane.setTopAnchor(goodsSymbol, 292.0);
				AnchorPane.setLeftAnchor(goodsSymbol, 130.0);

				AnchorPane.setTopAnchor(moneyLabel, 295.0);
				AnchorPane.setLeftAnchor(moneyLabel, 160.0);
				AnchorPane.setTopAnchor(moneySymbol, 295.0);
				AnchorPane.setLeftAnchor(moneySymbol, 183.0);
				break;
			// second button
			case 1:
				AnchorPane.setTopAnchor(button, 230.0);
				AnchorPane.setLeftAnchor(button, 230.0);

				AnchorPane.setTopAnchor(educationLabel, 295.0);
				AnchorPane.setLeftAnchor(educationLabel, 210.0);
				AnchorPane.setTopAnchor(educationSymbol, 285.0);
				AnchorPane.setLeftAnchor(educationSymbol, 232.0);

				AnchorPane.setTopAnchor(healthLabel, 295.0);
				AnchorPane.setLeftAnchor(healthLabel, 265.0);
				AnchorPane.setTopAnchor(healthSymbol, 295.0);
				AnchorPane.setLeftAnchor(healthSymbol, 287.0);

				AnchorPane.setTopAnchor(goodsLabel, 295.0);
				AnchorPane.setLeftAnchor(goodsLabel, 310.0);
				AnchorPane.setTopAnchor(goodsSymbol, 292.0);
				AnchorPane.setLeftAnchor(goodsSymbol, 330.0);

				AnchorPane.setTopAnchor(moneyLabel, 295.0);
				AnchorPane.setLeftAnchor(moneyLabel, 360.0);
				AnchorPane.setTopAnchor(moneySymbol, 295.0);
				AnchorPane.setLeftAnchor(moneySymbol, 383.0);
				break;

			// third button
			case 2:
				AnchorPane.setTopAnchor(button, 325.0);
				AnchorPane.setLeftAnchor(button, 30.0);

				AnchorPane.setTopAnchor(educationLabel, 395.0);
				AnchorPane.setLeftAnchor(educationLabel, 10.0);
				AnchorPane.setTopAnchor(educationSymbol, 385.0);
				AnchorPane.setLeftAnchor(educationSymbol, 32.0);

				AnchorPane.setTopAnchor(healthLabel, 395.0);
				AnchorPane.setLeftAnchor(healthLabel, 65.0);
				AnchorPane.setTopAnchor(healthSymbol, 395.0);
				AnchorPane.setLeftAnchor(healthSymbol, 87.0);

				AnchorPane.setTopAnchor(goodsLabel, 395.0);
				AnchorPane.setLeftAnchor(goodsLabel, 110.0);
				AnchorPane.setTopAnchor(goodsSymbol, 392.0);
				AnchorPane.setLeftAnchor(goodsSymbol, 130.0);

				AnchorPane.setTopAnchor(moneyLabel, 395.0);
				AnchorPane.setLeftAnchor(moneyLabel, 160.0);
				AnchorPane.setTopAnchor(moneySymbol, 395.0);
				AnchorPane.setLeftAnchor(moneySymbol, 183.0);
				break;

			// fourth button
			case 3:
				AnchorPane.setTopAnchor(button, 325.0);
				AnchorPane.setLeftAnchor(button, 230.0);

				AnchorPane.setTopAnchor(educationLabel, 395.0);
				AnchorPane.setLeftAnchor(educationLabel, 210.0);
				AnchorPane.setTopAnchor(educationSymbol, 385.0);
				AnchorPane.setLeftAnchor(educationSymbol, 232.0);

				AnchorPane.setTopAnchor(healthLabel, 395.0);
				AnchorPane.setLeftAnchor(healthLabel, 265.0);
				AnchorPane.setTopAnchor(healthSymbol, 395.0);
				AnchorPane.setLeftAnchor(healthSymbol, 287.0);

				AnchorPane.setTopAnchor(goodsLabel, 395.0);
				AnchorPane.setLeftAnchor(goodsLabel, 310.0);
				AnchorPane.setTopAnchor(goodsSymbol, 392.0);
				AnchorPane.setLeftAnchor(goodsSymbol, 330.0);

				AnchorPane.setTopAnchor(moneyLabel, 395.0);
				AnchorPane.setLeftAnchor(moneyLabel, 360.0);
				AnchorPane.setTopAnchor(moneySymbol, 395.0);
				AnchorPane.setLeftAnchor(moneySymbol, 383.0);
				break;

			default:
				break;
			}
			eventLayout.getChildren().add(button);
			eventLayout.getChildren().addAll(educationLabel, healthLabel, goodsLabel, moneyLabel);
			eventLayout.getChildren().addAll(educationSymbol, healthSymbol, goodsSymbol, moneySymbol);
		}
		BorderPane outerLayout = new BorderPane();
		outerLayout.setStyle("-fx-border-color: black; -fx-border-width: 12px;");
		outerLayout.setCenter(eventLayout);

		Scene eventScene = new Scene(outerLayout, 480, 480);
		eventStage.setScene(eventScene);
		eventStage.showAndWait();
	}
}
