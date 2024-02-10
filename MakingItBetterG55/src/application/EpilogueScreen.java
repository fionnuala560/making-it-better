package application;

import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;

public class EpilogueScreen {

    private Scene epilogueScene;

    /*public Epilogue(Scene homeScene){
    BorderPane epilogue = new BorderPane();
    epilogue.setStyle("-fx-background-image: url(/woodbackground.jpg);");
    EpilogueScene = new Scene(epilogue, 1200, 800);
    }*/
    public EpilogueScreen(){
        BorderPane epilogue = new BorderPane();
        epilogue.setStyle("-fx-background-image: url(/woodbackground.jpg);");
        epilogueScene = new Scene(epilogue, 1200, 800);
    }

    public Scene getEpilogueScene(){return epilogueScene;}
}
