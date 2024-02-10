package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EpilogueScreen {

    private Scene epilogueScene;
    private Button replayButton = new Button("Replay");
    private Button quitButton = new Button("Quit");

    private Label label1a = new Label("Congratulations!!");
    private Label label2a = new Label("Good Effort :)");
    private Label label3a = new Label("An attempt was made.");
    private Label label4a = new Label("Yikes...");

    private Label label1b = new Label("All objectives were successfully completed,\nyou worked very well together as a team");
    private Label label2b = new Label("Most of the objectives were completed,\nthis was a good starting point");
    private Label label3b = new Label("Some of the objectives were completed,\nmaybe next time try to work more as a team");
    private Label label4b = new Label("Very few of the objectives were completed,\nmaybe someone else can fix what you've done");

    private Label label1c = new Label("A greater level of student progression is shown in the coming years,\n as more children stay in school");
    private Label label2c = new Label("More children are gaining a greater level of education,\nhowever there is still more work to be done.");
    private Label label3c = new Label("Some children are staying in school,\n but more will have to be done to truly make a long lasting difference");
    private Label label4c = new Label("Very little difference has been made for the people of Pu Ngaol,\n maybe someone else will be able to put this plan into effect more successfully");

    private Label label1d = new Label("No notes for future attempts.");
    private Label label2d = new Label("Make sure to keep an eye on your resources!");
    private Label label3d = new Label("Keep your objectives in mind as you progress through the game.");
    private Label label4d = new Label("Try working together to complete the tasks?");

    private int currentLabelIndex = 0;

    /*public Epilogue(Scene homeScene){
    BorderPane epilogue = new BorderPane();
    epilogue.setStyle("-fx-background-image: url(/woodbackground.jpg);");
    EpilogueScene = new Scene(epilogue, 1200, 800);
    }*/
    public EpilogueScreen(Scene homeScene){
        BorderPane epilogue = new BorderPane();
        epilogue.setStyle("-fx-background-image: url(/woodbackground.jpg);");
        epilogueScene = new Scene(epilogue, 1200, 800);

        Text title = new Text("Epilogue :)");
        title.setFont(Font.font("SansSerif", FontWeight.BOLD, 100));
        title.setStyle("-fx-fill: black; -fx-stroke: white; -fx-stroke-width: 4px;");
        title.setTranslateX(350);
        title.setTranslateY(15);
        epilogue.setTop(title);

        //setting replay button to
        replayButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 40));
        replayButton.setStyle("-fx-cursor: hand; -fx-border-color: #152546; -fx-border-width: 14px; -fx-background-color: #536F7B; -fx-text-fill: white");
        replayButton.setPrefSize(320,29);
        replayButton.setTranslateX(175);
        replayButton.setTranslateY(500);
        epilogue.setLeft(replayButton);
        replayButton.setOnAction(event -> {
            Stage tempMain = (Stage) replayButton.getScene().getWindow();
            tempMain.setScene(homeScene);
        });

        //setting quit button to exit when pressed
        quitButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 40));
        quitButton.setStyle("-fx-cursor: hand; -fx-border-color: #152546; -fx-border-width: 14px; -fx-background-color: #536F7B; -fx-text-fill: white");
        quitButton.setPrefSize(320, 29);
        quitButton.setTranslateX(-175);
        quitButton.setTranslateY(500);
        epilogue.setRight(quitButton);
        quitButton.setOnAction(event -> {
            System.exit(0);
        });

        Label[] fullWinArr = {label1a, label1b, label1c, label1d};
        Label[] goodTryArr = {label2a, label2b, label2c, label2d};
        Label[] anAttemptArr = {label3a, label3b, label3c, label3d};
        Label[] yikesArr = {label4a, label4b, label4c, label4d};

        VBox epilogueText = new VBox();
        epilogueText.setStyle("-fx-border-width: 5px; -fx-border-color: black");
        epilogueText.setPadding(new Insets(5));
        epilogueText.setTranslateY(-100);
        epilogueText.setMaxHeight(400);
        epilogueText.setPrefWidth(950);
        epilogueText.getChildren().addAll(fullWinArr);
        for(Label i : fullWinArr){
            i.setVisible(false);
        }
        epilogueScene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.N){
                if(currentLabelIndex < fullWinArr.length){
                    fullWinArr[currentLabelIndex].setVisible(true);
                    currentLabelIndex++;
                }
            }
        });
        epilogue.setCenter(epilogueText);
    }

    public Scene getEpilogueScene(){return epilogueScene;}
}
