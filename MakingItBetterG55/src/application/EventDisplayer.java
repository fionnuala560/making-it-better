package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EventDisplayer {
	
	private Player[] players;
	
	public EventDisplayer(Player[] players) {
		this.players = players;
	}

	public BorderPane openEventPopup(Event event, int currentPlayer, MainSceneHandler mainSceneHandler) {

		AnchorPane eventLayout = new AnchorPane();
		eventLayout.setStyle(
				"-fx-border-color: #E1005C; -fx-border-width: 18px; -fx-background-color: #F79FC7; -fx-text-fill: white;");

		Label titleLabel = new Label(event.getTitle());
		titleLabel.setStyle("-fx-text-fill: white;");
		titleLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 40));
		AnchorPane.setTopAnchor(titleLabel, 10.0);
		AnchorPane.setLeftAnchor(titleLabel, 10.0);
		AnchorPane.setRightAnchor(titleLabel, 10.0);
		titleLabel.setAlignment(Pos.TOP_CENTER);

		Label descriptionLabel = new Label(event.getTextBody());
		descriptionLabel.setStyle("-fx-text-fill: white;");
		descriptionLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 18));
		descriptionLabel.setPrefWidth(300);
		descriptionLabel.setWrapText(true);
		AnchorPane.setTopAnchor(descriptionLabel, 80.0);
		AnchorPane.setLeftAnchor(descriptionLabel, 20.0);
		AnchorPane.setRightAnchor(descriptionLabel, 20.0);
		descriptionLabel.setAlignment(Pos.TOP_CENTER);

		eventLayout.getChildren().addAll(titleLabel, descriptionLabel);
		
		Image healthImage = new Image("/health.png", 20, 20, false, false);
		Image educationImage = new Image("/education.png", 20, 20, false, false);
		Image goodsImage = new Image("/goods.png", 20, 20, false, false);
		Image moneyImage = new Image("/money.png", 20, 20, false, false);
		EventOption[] eventOptions = event.getOptions();
		boolean[] hasClickedAnOption = {false};
		int indexShownOptions = 0;
		
		// For every option passed, create a button
		for (int i = 0; i < eventOptions.length; i++) {
			
			if (eventOptions[i].getPlayersVisableTo()[currentPlayer]) {
			Button optionButton = new Button();
			optionButton.setText(eventOptions[i].getText());
			optionButton.setPrefSize(155, 60);
			optionButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 11));
			optionButton.setStyle(
					"-fx-border-color: #E1005C, black; -fx-border-width: 12px, 6px; -fx-background-color: #F79FC7; -fx-border-radius: 5px; -fx-text-fill: white; -fx-cursor: hand;");
			
			double optionButtonTop = (indexShownOptions < 2) ? 325d : 230d;
			double optionButtonLeft = (indexShownOptions % 2 == 0) ? 225d : 15d;
			AnchorPane.setTopAnchor(optionButton, optionButtonTop);
			AnchorPane.setLeftAnchor(optionButton, optionButtonLeft);
			
			boolean meetsRequirements = true;
			for (int j = 0; j < 4; j++) {
				if(players[currentPlayer].getResources()[j] < eventOptions[i].getRequirements()[j]) {
					meetsRequirements = false;
					break;
				}
			}
			
			int numEffects = 0;
			for(int j = 0; j < 4; j++) {
				int effect = eventOptions[i].getEffects()[j];
				if(effect != 0) {
					Label resourceLabel = new Label(((effect > 0) ? "+" : "-") + Math.abs(effect));
					resourceLabel.setStyle( (!meetsRequirements) ? "-fx-text-fill: grey;" : (effect > 0) ? "-fx-text-fill: green;" : "-fx-text-fill: red;");
					resourceLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 14));
					ImageView resourceLogo = new ImageView((j == 0) ? healthImage : (j == 1) ? educationImage : (j == 2) ? goodsImage : moneyImage);
					AnchorPane.setTopAnchor(resourceLabel, optionButtonTop + 65);
					AnchorPane.setLeftAnchor(resourceLabel, optionButtonLeft - 5.0 + (45 * numEffects));
					AnchorPane.setTopAnchor(resourceLogo, optionButtonTop + 65);
					AnchorPane.setLeftAnchor(resourceLogo, optionButtonLeft + 12.0 + (45 * numEffects));
					eventLayout.getChildren().addAll(resourceLabel, resourceLogo);
					numEffects++;
				}
			}
			
			int[] optionIndex = {i};
			if(meetsRequirements) {
				optionButton.setOnAction(e -> {
					if (!hasClickedAnOption[0] && optionButton.isVisible()) {
						hasClickedAnOption[0] = true;
						for(int j = 0; j < 4; j++) {
							players[currentPlayer].getResources()[j] += eventOptions[optionIndex[0]].getEffects()[j];
						}
						mainSceneHandler.landed(-2);
					}
				});
			}
			
			eventLayout.getChildren().add(optionButton);
			indexShownOptions++;
			}
		}
		BorderPane outerLayout = new BorderPane();
		outerLayout.setStyle("-fx-border-color: black; -fx-border-width: 12px;");
		outerLayout.setCenter(eventLayout);

		return outerLayout;
	}
}
