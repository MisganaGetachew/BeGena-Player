package musicplayer.musicplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("BeGena Player");


        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
        HelloController controller = fxmlLoader.getController();
    }

    public static void main(String[] args) {
        launch();
    }
}