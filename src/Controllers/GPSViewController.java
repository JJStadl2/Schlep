package Controllers;

/**
 * FXML Controller class
 *
 * Last updated: 11/17/2020
 *
 * @author Josiah Stadler
 */
import Models.MoveScene;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class GPSViewController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private Label label;
    @FXML
    private Button nextBtn;
    @FXML
    private Button backBtn;

    @FXML
    private MediaView gpsMediaView;

    /**
     * Initializes the controller class.
     *
     * @param _url
     * @param _rb
     */
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {

        String videoUrl = ("file:/C:/Users/josia/OneDrive/Documents/NetBeansProjects/Schlep/src/Documents/GPS_Stub.mp4");

        Media media = new Media(videoUrl);
        MediaPlayer player = new MediaPlayer(media);
        gpsMediaView.setMediaPlayer(player);
        player.setAutoPlay(true);
        player.play();
        
    }

    @FXML
    private void moveToNextPage(ActionEvent _event) throws IOException {
        MoveScene.getInstance().Move("Timer.fxml", nextBtn);
    }

    @FXML
    private void moveToPrvPage(ActionEvent _event) throws IOException {
        MoveScene.getInstance().Move("Billing.fxml", backBtn);
    }

}
