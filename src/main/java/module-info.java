module musicplayer.musicplayer {
    requires javafx.controls;
    requires javafx.fxml;


    opens musicplayer.musicplayer to javafx.fxml;
    exports musicplayer.musicplayer;
}