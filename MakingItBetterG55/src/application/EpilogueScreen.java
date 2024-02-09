package application;

import javafx.scene.layout.BorderPane;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class EpilogueScreen {

    private Scene EpilogueScene;
    public Epilogue(Scene ){
    BorderPane epilogue = new BorderPane();
    epilogue.setStyle("-fx-background-image: url(/woodbackground.jpg);");
    Scene epilogueScene = new Scene(epilogue, 1200, 800);
    return epilogueScene;
    }

    public Scene getEpilogueScene(){return EpilogueScene;}
}
