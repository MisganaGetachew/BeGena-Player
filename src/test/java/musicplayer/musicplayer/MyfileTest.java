// after testing the APP make sure the 'music.txt' file is empty before running the app it's found in "BeGena-player/music.txt"

package musicplayer.musicplayer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class MyfileTest {



    private static final String TEST_FILE_PATH = "test-music.txt";

    @BeforeEach
    public void setUp() throws IOException {
        // Create or clear the test file before each test
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
        else {
            testFile.createNewFile();
        }
    }

    @Test
    public void testWriter() {
        // Prepare a list of files to be written
        List<File> inputFiles = List.of(
                new File("file1.txt"),
                new File("file2.txt"),
                new File("file3.txt")
        );

        // Call the 'writer' method in the 'Myfile' class with the prepared list
        Myfile new1=new Myfile();
        new1.writer(inputFiles);

        // Read the file and check if it contains the expected content
        ArrayList<File> resultFiles = new1.reader();

        // Assert that the size of the resultFiles list is the same as the inputFiles list
        assertEquals(inputFiles.size(), resultFiles.size());

        // Assert that the resultFiles list contains all files from the inputFiles list
        assertTrue(resultFiles.containsAll(inputFiles));
    }

    @Test
    public void testReader() {
        // Prepare a list of file paths to be written

        List<File> inputFiles = List.of(
                new File("file1.txt"),
                new File("file2.txt"),
                new File("file3.txt")
        );
        Myfile new1= new Myfile();
        new1.writer(inputFiles);

        ArrayList<File> resultFiles = Myfile.reader();

        // Assert that the size of the resultFiles list is the same as the lines list
        assertEquals(inputFiles.size(), resultFiles.size());

        // Assert that each file path in the resultFiles list matches the corresponding line in the lines list
        for (int i = 0; i < inputFiles.size(); i++) {
            assertEquals(inputFiles.get(i).getPath(), resultFiles.get(i).getPath());
        }

    }



}
