package Controllers;

/**
 * This is a controller for the Billing FXML
 *
 * Last Updated 11/17/2020
 *
 * @author Katelynn Urgitus, Josiah Stadler
 */
import Models.MoveScene;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BillingController implements Initializable {

    @FXML
    private TextField nameOnCardField;
    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField expirationDateField;
    @FXML
    private TextField ccvCvvField;
    @FXML
    private Button nextBtn;
    @FXML
    private Button backBtn;

    /**
     * Initializes the controller class.
     *
     * @param _url
     * @param _rb
     */
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {

    }

    /**
     * Will save billing info of the User in the Schlep Database and move to the
     * next screen
     *
     * @param _event the event being handled
     */
    @FXML
    private void saveInfoAndMove(ActionEvent _event) throws IOException, SQLException {
        MoveScene.getInstance().Move("GPSView.fxml", nextBtn);
    }

    @FXML
    private void goToPrvPage(ActionEvent _event) throws IOException {
        MoveScene.getInstance().Move("BidPage.fxml", backBtn);
    }
}
