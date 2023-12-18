package musicplayer.musicplayer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    void playButton(){
        System.out.println("Play button clicked!");
    }
    @FXML
    void pauseButton(){
        System.out.println("pause button clicked!");
    }

    @FXML
    void previousButton(){
        System.out.println("previous button clicked!");
    }

    @FXML
    void nextButton(){
        System.out.println("next button clicked!");
    }
}