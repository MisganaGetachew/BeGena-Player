package musicplayer.musicplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 320, 240);

        stage.setTitle("Begena Player");
        stage.setHeight(500);
        stage.setWidth(600);
        Image icon=new Image("C:\\Users\\seid5\\Desktop\\interfaces\\BeGena-Player\\src\\logo1.jpg");
        stage.getIcons().add(icon);
//        ImageView myImageView = (ImageView) root.lookup("#imageview");
//        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("C:\\Users\\seid5\\Desktop\\interfaces\\BeGena-Player\\src\\image5.png")));
//        myImageView.setImage(image);


        stage.setScene(scene);
        stage.setX(100);
        stage.setY(50);
        stage.show();
        Object controller = fxmlLoader.getController();
    }

    public static void main(String[] args) {
        launch();
    }
}