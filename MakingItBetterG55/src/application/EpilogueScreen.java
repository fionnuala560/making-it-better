package application;

import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;

public class EpilogueScreen {

    private Scene EpilogueScene;

    /*public Epilogue(Scene homeScene){
    BorderPane epilogue = new BorderPane();
    epilogue.setStyle("-fx-background-image: url(/woodbackground.jpg);");
    EpilogueScene = new Scene(epilogue, 1200, 800);
    }*/
    public void Epilogue(/*Scene homeScene*/){
        BorderPane epilogue = new BorderPane();
        epilogue.setStyle("-fx-background-image: url(/woodbackground.jpg);");
        EpilogueScene = new Scene(epilogue, 1200, 800);
    }

    public Scene getEpilogueScene(){return EpilogueScene;}
}
