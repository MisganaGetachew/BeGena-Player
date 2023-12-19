package musicplayer.musicplayer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private Button btn1;
    private  Button btn2;

    @FXML
    protected void ButtonClicked()

    {
//        welcomeText.setText("Welcome to JavaFX Application!");
        System.out.println("button Clicked !!!");
    }
@FXML
    void playMusic(){
    System.out.println("play music is cliked");
}

@FXML
    void choose_file(){

    FileChooser chosen = new FileChooser();

    chosen.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("mp4 files", "*.mp4"));
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


