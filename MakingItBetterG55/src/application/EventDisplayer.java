package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class EventDisplayer {

	private Player[] players;

	public EventDisplayer(Player[] players) {
		this.players = players;
	}

	public BorderPane openEventPopup(Event event, int currentPlayer, MainSceneHandler mainSceneHandler) {

		AnchorPane eventLayout = new AnchorPane();
		String[] colors = { "lightskyblue", "lightgreen", "lightpink", "mediumpurple", "darkblue", "darkgreen",
				"deeppink", "indigo" };
		String backgroundColor = colors[currentPlayer];
		String borderColor = colors[currentPlayer + 4];
		eventLayout.setStyle("-fx-border-color:" + borderColor + "; -fx-border-width: 9px; -fx-background-color: "
				+ backgroundColor + "; -fx-text-fill: white;");

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
		boolean[] hasClickedAnOption = { false };
		int indexShownOptions = 0;

		// For every option passed, create a button
		for (int i = 0; i < eventOptions.length; i++) {

			if (eventOptions[i].getPlayersVisableTo()[currentPlayer]) {
				Button optionButton = new Button();
				optionButton.setText(eventOptions[i].getText());
				optionButton.setPrefSize(155, 60);
				optionButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 11));
				optionButton.setStyle("-fx-border-color:" + borderColor
						+ ", black; -fx-border-width: 12px, 6px; -fx-background-color: " + backgroundColor
						+ "; -fx-border-radius: 5px; -fx-text-fill: white; -fx-cursor: hand;");

				double optionButtonTop = (indexShownOptions < 2) ? 325d : 230d;
				double optionButtonLeft = (indexShownOptions % 2 == 0) ? 225d : 15d;
				AnchorPane.setTopAnchor(optionButton, optionButtonTop);
				AnchorPane.setLeftAnchor(optionButton, optionButtonLeft);

				boolean meetsRequirements = true;
				for (int j = 0; j < 4; j++) {
					if (players[currentPlayer].getResources()[j] < eventOptions[i].getRequirements()[j]) {
						meetsRequirements = false;
						break;
					}
				}

				int numEffects = 0;
				for (int j = 0; j < 4; j++) {
					int effect = eventOptions[i].getEffects()[j];
					if (effect != 0) {
						Label resourceLabel = new Label(((effect > 0) ? "+" : "-") + Math.abs(effect));
						resourceLabel.setStyle((!meetsRequirements) ? "-fx-text-fill: grey;"
								: (effect > 0) ? "-fx-text-fill: green;" : "-fx-text-fill: red;");
						resourceLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 14));
						ImageView resourceLogo = new ImageView((j == 0) ? healthImage
								: (j == 1) ? educationImage : (j == 2) ? goodsImage : moneyImage);
						AnchorPane.setTopAnchor(resourceLabel, optionButtonTop + 65);
						AnchorPane.setLeftAnchor(resourceLabel, optionButtonLeft - 5.0 + (45 * numEffects));
						AnchorPane.setTopAnchor(resourceLogo, optionButtonTop + 65);
						AnchorPane.setLeftAnchor(resourceLogo, optionButtonLeft + 16.0 + (45 * numEffects));
						eventLayout.getChildren().addAll(resourceLabel, resourceLogo);
						numEffects++;
					}
				}
				
				Tooltip tooltip = new Tooltip();
				tooltip.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
				tooltip.setShowDelay(new Duration(10));
				tooltip.setShowDuration(Duration.INDEFINITE);
				tooltip.setHideDelay(Duration.ZERO);

				int[] optionIndex = { i };
				if (meetsRequirements) {
					optionButton.setOnAction(e -> {
						if (!hasClickedAnOption[0] && optionButton.isVisible()) {
							hasClickedAnOption[0] = true;
							for (int j = 0; j < 4; j++) {
								players[currentPlayer].getResources()[j] += eventOptions[optionIndex[0]]
										.getEffects()[j];
							}

							if (eventOptions[optionIndex[0]].getObjectiveIndex() != -1) {
								players[currentPlayer].getObjectives()[eventOptions[optionIndex[0]]
										.getObjectiveIndex()]++;
							}

							if (eventOptions[optionIndex[0]].getEndsEvent()) {
								mainSceneHandler.landed(-2);
							} else {
								mainSceneHandler.switchPlayerUI();
								mainSceneHandler.landed(mainSceneHandler.getEventIndex());
							}
						}
					});

					if (!eventOptions[i].getTooltip().equals("")) {
						tooltip.setText(eventOptions[i].getTooltip());
						Tooltip.install(optionButton, tooltip);
					}
				} else {
					String requirementsString = " Requires: " + ((eventOptions[i].getRequirements()[0] != 0) ? eventOptions[i].getRequirements()[0] + " Health, " : "") +
							((eventOptions[i].getRequirements()[1] != 0) ? eventOptions[i].getRequirements()[1] + " Education, " : "") +
							((eventOptions[i].getRequirements()[2] != 0) ? eventOptions[i].getRequirements()[2] + " Goods, " : "") +
							((eventOptions[i].getRequirements()[3] != 0) ? eventOptions[i].getRequirements()[3] + " Money" : "");
					if (requirementsString.substring(requirementsString.length()-2, requirementsString.length()).equals(", ")) {
						requirementsString = requirementsString.substring(0, requirementsString.length()-2);
					}
					tooltip.setText(eventOptions[i].getTooltip() + requirementsString);
					Tooltip.install(optionButton, tooltip);
				}

				eventLayout.getChildren().add(optionButton);
				indexShownOptions++;
			}
		}
		BorderPane outerLayout = new BorderPane();
		outerLayout.setStyle("-fx-border-color: black; -fx-border-width: 6px;");
		outerLayout.setCenter(eventLayout);

		return outerLayout;
	}
}
