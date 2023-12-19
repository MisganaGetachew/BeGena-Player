package musicplayer.musicplayer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private Label songLabel;
    @FXML private Button playButton, pauseButton, nextButton, previousButton, replyButton;
     private Media media;
     private MediaPlayer mediaPlayer;

    private File musicFolder;
    private File[] files;

    private ArrayList<File> songs;

    private int songNumber = 0;


    public void initialize(URL arg0, ResourceBundle arg1) {
        initializeMedia();
    }

    private void initializeMedia() {
        songs = new ArrayList<>();
        musicFolder = new File("music");
        files = musicFolder.listFiles();

        if (files != null) {
            for (File file : files) {
                songs.add(file);
            }
        }

        if (!songs.isEmpty()) {
            media = new Media(songs.get(this.songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
        } else {
            System.out.println("No songs found.");
        }
    }

    @FXML
    void playButton() {
        if (mediaPlayer != null && media != null) {
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

    @FXML
    void replyButton(){
        System.out.println("Reply button clicked!");
    }

}