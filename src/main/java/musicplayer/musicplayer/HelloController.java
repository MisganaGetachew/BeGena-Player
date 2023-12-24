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

import java.io.*;
import java.net.URL;
import java.util.*;


public class HelloController  implements  Initializable{

    @FXML private Slider volume1;
    @FXML private Slider volume2;
    @FXML private Label volumeLabel1;
    @FXML private Label volumeLabel2;
    @FXML private Label currentTime1;
    @FXML private Label currentTime2;
    @FXML private Label totalTime1;
    @FXML private Label totalTime2;
    @FXML private TextField searchTextField;
    @FXML private Label musicName;
    @FXML private Label musicName1;
    @FXML private Slider musicProgress;
    @FXML private Slider musicProgress1;
    @FXML private ListView<String> listSong;

    private Media media;
    private MediaPlayer mediaPlayer;
    int songNumber = 0;
    boolean isPlaying = false;
    double totaTimeDuration;
    double newvalue;
    public ArrayList<File> songs;
    public final ObservableList<String> songList = FXCollections.observableArrayList();
    private HashMap<String, Integer> nameIndex = new HashMap<String, Integer>();

    private void initializeMedia(ArrayList<File> files) {

        this.songs = files;
        if (this.songs != null && !this.songs.isEmpty()) {
            listSong.getItems().clear();
            songList.clear();

            for (File f:songs) {
                listSong.getItems().add(f.getName());
                nameIndex.put(f.getName(), songs.indexOf(f));
                songList.add(f.getName());
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            musicName.setText(songs.get(songNumber).getName());
            musicName1.setText(songs.get(songNumber).getName());
        }
    }

    // Event handler for playing a selected song from the list
    @FXML
    void playSong(MouseEvent event) {
        try {
            String name  = listSong.getSelectionModel().getSelectedItem();
            songNumber = nameIndex.get(name);
            mediaPlayer.pause();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            musicName.setText(songs.get(songNumber).getName());
            musicName1.setText(songs.get(songNumber).getName());
            isPlaying = false;
            playButton();

        }catch (Exception e){

        }
    }
    void nextSong(){
        if(newvalue >= totaTimeDuration){
        nextButton();
    }}

    // Event handler for the play button
    @FXML
    void playButton() {

        if (mediaPlayer != null){
        try{
            // Bind the volume1 slider property bidirectionally to the volume2 slider property
            volume1.valueProperty().bindBidirectional(volume2.valueProperty());

            // Add a listener to control the volume
            volume1.valueProperty().addListener((observableValue, number, t1) -> {
                mediaPlayer.setVolume(volume1.getValue() * 0.01);
                volumeLabel1.setText(String.valueOf(Math.round(volume1.getValue())));
            });

            volume2.valueProperty().addListener((observableValue, number, t1) -> {
                mediaPlayer.setVolume(volume2.getValue() * 0.01);
                volumeLabel2.setText(String.valueOf(Math.round(volume2.getValue())));
            });
//            mediaPlayer.setVolume(volume1.getValue() * 0.01);
//            mediaPlayer.setVolume(volume2.getValue() * 0.01);
            if (!isPlaying){
                // Initialize total duration and set maximum values for progress bars
                mediaPlayer.setOnPlaying(() -> {
                    Duration totalDuration = media.getDuration();
                    totaTimeDuration = totalDuration.toMinutes();

                    musicProgress.setMax(totalDuration.toSeconds());
                    musicProgress1.setMax(totalDuration.toSeconds());

                    long minutes = Math.round(totalDuration.toMinutes());
                    double seconds = Math.round((totalDuration.toSeconds() % 60));

                    //display total time
                    totalTime1.setText(String.format("%02d:%02.0f", minutes, seconds));
                    totalTime2.setText(String.format("%02d:%02.0f", minutes, seconds));
                });

                mediaPlayer.currentTimeProperty().addListener((observable, duration, newValue) -> {
                    musicProgress.setValue(newValue.toSeconds());
                    musicProgress1.setValue(newValue.toSeconds());

                    long minutes = Math.round(newValue.toMinutes());
                    double seconds = Math.round((newValue.toSeconds() % 60));

                    //to display current time
                    currentTime1.setText(String.format("%02d:%02.0f", minutes, seconds));
                    currentTime2.setText(String.format("%02d:%02.0f", minutes, seconds));

                    newvalue = newValue.toMinutes() + 0.02;
                    String NewValue = String.format("%.2f", newvalue);
                    String newTotalTime = String.format("%.2f", totaTimeDuration);

                    if(NewValue.equals(newTotalTime)){
                        nextButton();
                    }
                });

                // Allow user to drag progress bar to seek to a specific position
                musicProgress.setOnMouseDragged(mouseEvent -> {
                    mediaPlayer.seek(Duration.seconds(musicProgress.getValue()));
                });

                musicProgress1.setOnMouseDragged(mouseEvent -> {
                    mediaPlayer.seek(Duration.seconds(musicProgress1.getValue()));
                });

                // Allow user to click on progress bar to seek to a specific position
                musicProgress.setOnMousePressed(mouseEvent -> {
                    mediaPlayer.seek(Duration.seconds(musicProgress.getValue()));
                });

                musicProgress1.setOnMousePressed(mouseEvent -> {
                    mediaPlayer.seek(Duration.seconds(musicProgress1.getValue()));
                });
                // If media is not null, play it; otherwise, initialize and play media
                if (media != null) {
                    mediaPlayer.play();
                } else {
                    initializeMedia(Myfile.reader());
                    if (mediaPlayer != null) {
                        mediaPlayer.play();
                    }
                }
                isPlaying = true;
            } else {
                mediaPlayer.pause();
                isPlaying = false;
            }

        } catch (NullPointerException e){
            musicName.setText("Empty playlist, add songs!");
            musicName1.setText("Empty playlist, add songs!");
        }}
        else{
            musicName.setText("Empty playlist, add songs!");
            musicName1.setText("Empty playlist, add songs!");
        }
    }

    // Event handler for the pause button
    @FXML
    void pauseButton(){
        mediaPlayer.pause();
    }

    // Event handler for the previous button
    @FXML
    void previousButton(){
        try{
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

        } catch (NullPointerException e){

            musicName.setText("Empty playlist, add songs!");
            musicName1.setText("Empty playlist, add songs!");

        }
    }
    // Event handler for the next button
    @FXML
    void nextButton(){
        try{
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

        } catch (NullPointerException e){

            musicName.setText("Empty playlist, add songs!");
            musicName1.setText("Empty playlist, add songs!");

        }
    }
    // Event handler for the reply button
    @FXML
    void replyButton() {
        try {
            mediaPlayer.seek(Duration.seconds(0));

        } catch (NullPointerException e) {

            musicName.setText("Empty playlist, add songs!");
            musicName1.setText("Empty playlist, add songs!");

        }
    }
    @FXML
    void choose_file(){

        FileChooser chosen = new FileChooser();
        chosen.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("mp3 files", "*.mp3"));
        List<File> selectedFiles = chosen.showOpenMultipleDialog(null);

        if (selectedFiles != null){
            Myfile.writer(selectedFiles);
            for(File file: selectedFiles ){
                System.out.println(file.getName());
            }

            if(media != null){
                pauseButton();
                this.initializeMedia(Myfile.reader());
            }
            else{
                this.initializeMedia(Myfile.reader());
            }
        }else{
            System.out.println("file can't be found");
        }

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterList(newValue);
        });
    }

    // Initialization method for the controller
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterList(newValue);
        });

        File file  = new File("music.txt");

        if(file.exists()){

            initializeMedia(Myfile.reader());

            for (File currentFile:Myfile.reader()
                 ) {
                System.out.println("printing");
                System.out.println(currentFile.getName());
//                listSong.getItems().add(currentFile.getName());
                songList.add(currentFile.getName());
            }
            this.songNumber = 0;
        }
       else {
            try {
                file.createNewFile();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Method to filter the song list based on user input
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
//  a class to store/retrieve media file's path as text

class Myfile {

//    writer method writes the every of media file's path in to music.txt
    public static void writer(List<File> files) {
        ArrayList<String> filePaths = new ArrayList<>();
        for (File file : files) {
            filePaths.add(file.getPath());
        }
        String filename = "music.txt";
        File file = new File(filename);

        try {
            ArrayList<File> existingFiles = Myfile.reader();

            FileWriter writer = new FileWriter(file, true);
            for (String data : filePaths) {
                File currentFile = new File(data);
                if (!existingFiles.contains(currentFile)) {
                    writer.write(data + '\n');
                    existingFiles.add(currentFile); // Update the existing files list
                    System.out.println("File path '" + data + "' written successfully.");
                }
            }
            writer.close(); // Close the writer after use
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

//    reader file reads the text file and finds the media files with specific path and returns it
    public static ArrayList<File> reader() {
        try (BufferedReader reader = new BufferedReader(new FileReader("music.txt"))) {
            String line;
            ArrayList<File> fileList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                File filenew = new File(line);
                fileList.add(filenew);
            }
            return fileList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}










