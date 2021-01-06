package Models;

/**
 * This is a singleton class for moving from one FXML to another
 *
 * Last Updated 11/05/2020
 *
 * @author Katelynn Urgitus
 */
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MoveScene {

    private static volatile MoveScene instance = null;

    private MoveScene() {
    }

    /**
     * This method gets the instance above
     *
     * @return the single instance of the MoveScene class.
     */
    public static MoveScene getInstance() {
        // Double check locking principle.
        // If there is no instance available, create new one (i.e. lazy initialization).
        if (instance == null) {

            // To provide thread-safe implementation.
            synchronized (MoveScene.class) {

                // Check again as multiple threads can reach above step.
                if (instance == null) {
                    instance = new MoveScene();
                }
            }
        }
        return instance;
    }

    /**
     * Moves the current scene to a new or previous scene.
     *
     * @param _nextFXML the fxml you want to go to.
     * @param _button the button being pressed to get there.
     * @throws IOException
     */
    public void Move(String _nextFXML, Button _button) throws IOException {
        Scene scene;
        Stage stageTheLabelBelongs = (Stage) _button.getScene().getWindow();
        URL urlForDropdownFX = new File("src/View/" + _nextFXML).toURI().toURL();
        Parent root = FXMLLoader.load(urlForDropdownFX);
        scene = new Scene(root);
        stageTheLabelBelongs.setScene(scene);
    }

}
