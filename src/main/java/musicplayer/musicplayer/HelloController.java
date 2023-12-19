package musicplayer.musicplayer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private Label songLabel;
    @FXML private Button playButton, pauseButton, nextButton, previousButton, replyButton;
     private Media media;
     private MediaPlayer mediaPlayer;
    int songNumber = 0;

    public void initialize(URL arg0, ResourceBundle arg1) {
        initializeMedia();
    }

    private void initializeMedia() {
        File musicFolder = new File("musics");
        File[] files = musicFolder.listFiles();

        if (files != null) {
            ArrayList<File> songs = new ArrayList<>(Arrays.asList(files));
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
//            String songName = songs.get(0).getName();
//            mediaPlayer.setOnReady(() -> {
//                songLabel.setText(songName);
//            });
        }
    }
    @FXML
    void playButton() {


        if (media != null) {
            mediaPlayer.play();
        } else {
            initializeMedia();
            if (mediaPlayer != null) {
                mediaPlayer.play();
            }
        }
    }

    @FXML
    void pauseButton(){
        System.out.println("working on pause button....");
    }

    @FXML
    void previousButton(){
        System.out.println("working on previous button....");
    }

    @FXML
    void nextButton(){
        System.out.println("working on next button....");
    }

    @FXML
    void replyButton(){
        System.out.println("working on reply button....");
    }

}