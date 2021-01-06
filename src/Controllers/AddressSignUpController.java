package Controllers;

/**
 * This is a Controller for user entering their address
 *
 * Last Updated 11/05/2020
 *
 * @author Katelynn Urgitus
 */
import Models.Address;
import Models.MoveScene;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AddressSignUpController extends Address implements Initializable {

    @FXML
    private ChoiceBox<String> countryBox;
    @FXML
    private ChoiceBox<String> stateBox;
    @FXML
    private ChoiceBox<String> cityBox;
    @FXML
    private TextField userAddressLine1;
    @FXML
    private TextField userAddressLine2;
    @FXML
    private TextField userZipcode;
    @FXML
    private Button nextBtn;
    @FXML
    private Button backBtn;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apiListen.addressListener(countryBox, stateBox, cityBox);
    }

    /**
     * Will save the User input data into the Schlep Database and move to the
     * Offloading Screen
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void saveAddressAndMove(ActionEvent event) throws SQLException, IOException {
        Address userAddress = new Address(userAddressLine1.getText(), userAddressLine2.getText(), countryBox.getValue(), stateBox.getValue(), cityBox.getValue(), userZipcode.getText());
        MoveScene.getInstance().Move("DeliveryInfo.fxml", nextBtn);

    }

    @FXML
    private void goToPrvPage(ActionEvent event) throws IOException {
        MoveScene.getInstance().Move("CreateAccount.fxml", backBtn);
    }
}
