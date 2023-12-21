package musicplayer.musicplayer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import java.io.File;
import java.net.URL;
import java.util.*;


public class HelloController  implements  Initializable{

    // add songs list
    private ObservableList<String> songList = FXCollections.observableArrayList();
    @FXML
    private TextField searchTextField;

    @FXML
    private Label musicName;
    @FXML
    private Label musicName1;

    @FXML
    private Slider musicProgress;
    @FXML
    private Slider musicProgress1;

    private Media media;
    private MediaPlayer mediaPlayer;
    int songNumber = 0;
    boolean isPlaying = false;
    public ArrayList<File> songs;
    @FXML
    private ListView<String> listSong;

    private HashMap<String, Integer> nameIndex = new HashMap<String, Integer>();
//    public void initialize(URL arg0, ResourceBundle arg1) {
//        initializeMedia(FilesChosen.getFiles());
//    }

    private void initializeMedia(ArrayList<File> files) {
//        change the file path according to your file's path structure
////        File musicFolder = new File("C:\\Users\\MG\\Desktop\\get-to-work\\comp.prog\\Music-Player\\music");
//        File musicFolder = new File("C:\\Users\\MG\\Desktop\\get-to-work\\comp.prog\\play-tab\\BeGena-Player\\music");
//        File[] files = musicFolder.listFiles();

            this.songs = files;
        if (files != null) {

            for (File f:songs) {
                listSong.getItems().add(f.getName());
                nameIndex.put(f.getName(), songs.indexOf(f));
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            musicName.setText(songs.get(songNumber).getName());
            musicName1.setText(songs.get(songNumber).getName());
        }}



    @FXML
    void playSong(MouseEvent event) {
        String name  = listSong.getSelectionModel().getSelectedItem();
//        System.out.println(name);
//        System.out.println(nameIndex);
        songNumber = nameIndex.get(name);
//        System.out.println(songNumber);
        mediaPlayer.pause();
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        musicName.setText(songs.get(songNumber).getName());
        musicName1.setText(songs.get(songNumber).getName());

//            mediaPlayer.pause();
        isPlaying = false;
        playButton();
    }

    @FXML
    void playButton() {
        if (!isPlaying){
            mediaPlayer.setOnPlaying(() -> {
                Duration totalDuration = media.getDuration();
                musicProgress.setMax(totalDuration.toSeconds());
                musicProgress1.setMax(totalDuration.toSeconds());
            });

            mediaPlayer.currentTimeProperty().addListener((observable, duration, newValue) -> {
                musicProgress.setValue(newValue.toSeconds());
                musicProgress1.setValue(newValue.toSeconds());
            });

            musicProgress.setOnMouseDragged(mouseEvent -> {
                mediaPlayer.seek(Duration.seconds(musicProgress.getValue()));
            });

            musicProgress1.setOnMouseDragged(mouseEvent -> {
                mediaPlayer.seek(Duration.seconds(musicProgress1.getValue()));
            });

            musicProgress.setOnMousePressed(mouseEvent -> {
                mediaPlayer.seek(Duration.seconds(musicProgress.getValue()));
            });

            musicProgress1.setOnMousePressed(mouseEvent -> {
                mediaPlayer.seek(Duration.seconds(musicProgress1.getValue()));
            });

            if (media != null) {
                mediaPlayer.play();
            } else {
                initializeMedia(FilesChosen.getFiles());
                if (mediaPlayer != null) {
                    mediaPlayer.play();
                }
            }
            isPlaying = true;
        } else {
            mediaPlayer.pause();
            isPlaying = false;
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

        } else {
            songNumber = songs.size() - 1;

        }

        mediaPlayer.stop();
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        musicName.setText(songs.get(songNumber).getName());
        musicName1.setText(songs.get(songNumber).getName());

        isPlaying = false;
        playButton();
    }

    @FXML
    void nextButton(){
        if (songNumber < songs.size() - 1) {
            songNumber++;

        } else {
            songNumber = 0;

        }
        mediaPlayer.stop();
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        musicName.setText(songs.get(songNumber).getName());
        musicName1.setText(songs.get(songNumber).getName());
        isPlaying = false;
        playButton();



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

            // Add song name to songlist to be displayed when searching
            songList.add(file.getName());
        }
//        File[] fileInFolder =  selectedFile.listFiles();
//        System.out.println(selectedFile.getName());
//
//        System.out.println(fileInFolder.toString());

        for (File file: FilesChosen.getFiles() ) {
            System.out.println(file.getName());

        }

        if(media != null){
            mediaPlayer.pause();
            this.initializeMedia(FilesChosen.getFiles());

        }
        else{
            this.initializeMedia(FilesChosen.getFiles());

        }




    }else{
        System.out.println("file can't be found");
    }


}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterList(newValue);
        });
    }
    private void filterList(String filter) {
        ObservableList<String> filteredList = FXCollections.observableArrayList();

        for (String item : songList) {
            if (item.toLowerCase().contains(filter.toLowerCase())) {
                filteredList.add(item);
            }
        }

        listSong.setItems(filteredList);
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










