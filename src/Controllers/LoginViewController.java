package Controllers;

/**
 * Controls all action events on the LoginView FXML file
 *
 * Last Updated: 11/20/2020
 *
 * @author Marc Bittle, Katelynn Urgitus
 */
import Models.SchlepUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Models.MoveScene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.sql.SQLException;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController extends SchlepUser implements Initializable {

    @FXML
    private Button loginBtn;
    @FXML
    private Button returnToCreateAcctBtn;

    @FXML
    private Label messageLbl;
    @FXML
    private TextField userEmail;
    @FXML
    private PasswordField userPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        messageLbl.setText("");
    }

    /**
     * Transition to the DeliveryInfo page
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void moveToNextPage(ActionEvent event) throws SQLException, IOException {
        // Verify the User account
        if (user.checkValidUser(userEmail.getText(), userPassword.getText()) == false) {
            messageLbl.setText("Incorrect Email/Password!");
        } else {
            MoveScene.getInstance().Move("DeliveryInfo.fxml", loginBtn);
        }
    }

    /**
     * Go back to the CreateAccount page
     *
     * @param event
     * @throws Exception
     */
    @FXML
    private void returnToCreateAcctBtn(ActionEvent event) throws Exception {
        MoveScene.getInstance().Move("CreateAccount.fxml", returnToCreateAcctBtn);
    }

}
