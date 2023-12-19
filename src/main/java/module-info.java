module musicplayer.musicplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens musicplayer.musicplayer to javafx.fxml;
    exports musicplayer.musicplayer;
}