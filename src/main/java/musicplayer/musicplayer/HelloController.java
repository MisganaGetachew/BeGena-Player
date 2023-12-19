package musicplayer.musicplayer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
public class HelloController {
    @FXML
    private Button btn1;
    private  Button btn2;

    @FXML
    protected void ButtonClicked()

    {
//        welcomeText.setText("Welcome to JavaFX Application!");
        System.out.println("button Clicked !!!");
    }
@FXML
    void playMusic(){
    System.out.println("play music is cliked");
}

}



