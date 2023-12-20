package musicplayer.musicplayer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import java.io.File;
import java.net.URL;
import java.util.*;

public class HelloController {
    @FXML
    private Label musicName;
    @FXML private Button playButton, pauseButton, nextButton, previousButton, replyButton;
    private Media media;
    private MediaPlayer mediaPlayer;
    int songNumber = 0;
    private ArrayList<File> songs;

    public void initialize(URL arg0, ResourceBundle arg1) {
        initializeMedia();
    }

    private void initializeMedia() {
//        change the file path according to your file's path structure
//        File musicFolder = new File("C:\\Users\\MG\\Desktop\\get-to-work\\comp.prog\\Music-Player\\music");
        File musicFolder = new File("musics");
        File[] files = musicFolder.listFiles();

        if (files != null) {
            songs = new ArrayList<>(Arrays.asList(files));
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            musicName.setText(songs.get(songNumber).getName());
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
        mediaPlayer.pause();
    }

    @FXML
    void previousButton(){
        if (songNumber > 0) {
            songNumber--;
            mediaPlayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            musicName.setText(songs.get(songNumber).getName());
            playButton();
        } else {
            songNumber = songs.size() - 1;
            mediaPlayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            musicName.setText(songs.get(songNumber).getName());
            playButton();
        }
    }

    @FXML
    void nextButton(){
        if (songNumber < songs.size() - 1) {
            songNumber++;
            mediaPlayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            musicName.setText(songs.get(songNumber).getName());
            playButton();
        } else {
            songNumber = 0;
            mediaPlayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            musicName.setText(songs.get(songNumber).getName());
            playButton();
        }
    }

    @FXML
    void replyButton(){
        mediaPlayer.seek(Duration.seconds(0));
    }


//samy don't touch this method
@FXML
void choose_file(){

    FileChooser chosen = new FileChooser();
//    File selectedFile = chosen.showOpenDialog(null);
//    System.out.println("file chooser selected ");

    chosen.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("mp3 files", "*.mp3"));
//    File selectedFile = chosen.showOpenDialog(null);
//    letting the user pick files (multiple files)
    List<File> selectedFiles = chosen.showOpenMultipleDialog(null);


    if (selectedFiles != null){

        for(File file: selectedFiles ){
            FilesChosen.addFiles(file);
            System.out.println(file.getName());
        }
//        File[] fileInFolder =  selectedFile.listFiles();
//        System.out.println(selectedFile.getName());
//
//        System.out.println(fileInFolder.toString());

        for (File file: FilesChosen.getFiles() ) {
            System.out.println(file.getName());
        }


    }else{
        System.out.println("file can't be found");
    }


}
}


    class FilesChosen{
        private static ArrayList<File> files = new ArrayList<File>();

        public static ArrayList<File> getFiles(){

            return files;

        }

        public static void addFiles(File file){

            files.add(file);
        }

    }










