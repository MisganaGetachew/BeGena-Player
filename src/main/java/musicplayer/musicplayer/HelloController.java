package musicplayer.musicplayer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;

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

@FXML
    void choose_file(){

    FileChooser chosen = new FileChooser();
    File selectedFile = chosen.showOpenDialog(null);
//    System.out.println("file chooser selected ");

    if (selectedFile != null){
        System.out.println(selectedFile.getName());
    }
    else{
        System.out.println("file can't be found");
    }
}

}



