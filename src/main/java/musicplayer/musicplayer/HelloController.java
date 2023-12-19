package musicplayer.musicplayer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
//        change the file path according to your file's path structure
        File musicFolder = new File("C:\\Users\\MG\\Desktop\\get-to-work\\comp.prog\\Music-Player\\music");
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




//samy don't touch this method
@FXML
void choose_file(){

    FileChooser chosen = new FileChooser();
    File selectedFile = chosen.showOpenDialog(null);
//    System.out.println("file chooser selected ");

    chosen.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("mp4 files", "*.mp4"));
//    File selectedFile = chosen.showOpenDialog(null);
//    letting the user pick files (multiple files)
    List<File> selectedFiles = chosen.showOpenMultipleDialog(null);

    if (selectedFile != null){
        System.out.println(selectedFile.getName());
        if (selectedFiles != null){

            for(File file: selectedFiles ){
                FilesChosen.addFiles(file);
                System.out.println(file.getName());
            }
//        File[] fileInFolder =  selectedFile.listFiles();
//        System.out.println(selectedFile.getName());
//
//        System.out.println(fileInFolder.toString());
        }
        else{
            System.out.println("file can't be found");
        }
    }

}


    class FilesChosen{
        private static ArrayList<File> files = new ArrayList<File>();

        public static ArrayList<File> GetFiles(){

            return files;


        }

        public static void addFiles(File file){

            files.add(file);
        }

    }
}


