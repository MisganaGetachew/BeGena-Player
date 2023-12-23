# BeGena-Player

####    Music Player Application

Introduction:
Welcome to the BeGena-Player Application!
This Java-based application offers a delightful and easy-to-use platform for playing, pausing,  and replaying
your favorite songs. With a user-friendly interface, you can also add new songs to your playlist,
search for specific tracks, and adjust the playback progress using sliders.



##### *Tools Used:*
Java programming Language
Javafx GUI
Junit 


##### Configuration :



### Installation
1. Clone the repository: `git clone https://github.com/MisganaGetachew/BeGena-Player.git`
2. Navigate to the project directory: `cd BeGena-Player`

##### JavaFX:
Definition: JavaFX is a powerful Java library for creating desktop applications with stunning user interfaces.
Purpose: Provides UI controls and layout containers for developing interactive and visually captivating applications.

##### Media and MediaPlayer:
Definition: The Media class represents media resources (e.g., audio files), while the MediaPlayer class controls media playback.
Purpose: Handles audio file playback within the application.

##### File Handling:
Definition: File handling techniques are used to read and write data.
Purpose: Enables reading a list of songs from a text file and adding new songs to the playlist.

##### Code Structure:
The code is organized into two main classes: **HelloApplication** and **HelloController**.

###### HelloApplication:
Description: The main class that serves as the entry point for the application.
Responsibilities: Sets up the user interface, loads the FXML file, creates the application window, and displays it.

###### HelloController:
Description: The controller class responsible for handling the logic and event handling of the user interface.
Responsibilities: Implements the Initialize interface, initializes the user interface, and sets up the initial state.


Code Examples:
Here are some code examples extracted from our application:

**Loading the FXML file and creating a scene:**

     FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
     Scene scene = new Scene(fxmlLoader.load());
     stage.setScene(scene);


**Initializing the media and media player:**

     media = new Media(songs.get(songNumber).toURI().toString());
     mediaPlayer = new MediaPlayer(media);

**Playing or pausing the song:**

     @FXML
      void playButton() {
      if (!isPlaying) {
       mediaPlayer.play();
       isPlaying = true;
       } else {
       mediaPlayer.pause();
        isPlaying = false;
      }
    }

**Adding a song to the playlist:**

     @FXML
       void choose_file() {
        FileChooser chosen = new FileChooser();
         List<File> selectedFiles = chosen.showOpenMultipleDialog(null);

                 if (selectedFiles != null) {
                   Myfile.writer(selectedFiles);
                     for (File file : selectedFiles) {
                         listSong.getItems().add(file.getName());
                       }
                 }
         }

Conclusion:
In summary, our Music Player Application utilizes JavaFX and FXML to create an intuitive interface for playing and managing songs.
It provides essential functionalities such as playback control, playlist management, and the ability to add new songs.
The code is organized into classes and components that handle different aspects of the application,
offering customization and extension possibilities for specific needs.
