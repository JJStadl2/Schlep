package Controllers;

/**
 * FXML Controller class This is a controller for entering the address of where
 * an item is to be picked up.
 *
 * Last Updated: 11/9/2020
 *
 * @author Josiah Stadler, Katelynn Urgitus
 */
import Models.Address;
import Models.MoveScene;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PickupAddressController extends Address implements Initializable {

    private String instructionStr;
    private String streetAddress;
    private String orderNumber;
    private String vendorName;
    private String zip;
    private String orderInfo;
    private String selectedCity;
    private String selectedState;
    private String selectedCountry;

    @FXML
    private ImageView logo;
    @FXML
    private TextField specialInstructionsTxFld;
    @FXML
    private Button nextBtn;
    @FXML
    private Button backBtn;

    @FXML
    private Label selectCityLbl;
    @FXML
    private Label selectStateLbl;
    @FXML
    private MenuButton selectVendorMnBx;
    @FXML
    private MenuItem prvtSellerMnItem;
    @FXML
    private MenuItem commercialVendorMnItem;
    @FXML
    private AnchorPane anchor;
    @FXML
    private TextField vendorNameTxFld;
    @FXML
    private TextField streetNameTxFld;
    @FXML
    private TextField zipCodeTxFld;
    @FXML
    private TextField orderConfirmationTxFld;
    @FXML
    private Label orderProcessedMsgLbl;
    @FXML
    private ChoiceBox<String> selectCityChoiceBx;
    @FXML
    private ChoiceBox<String> selectStateChoiceBX;
    @FXML
    private Label selectVendorLbL;
    @FXML
    private Button confirmationBtn;
    @FXML
    private Label detailLbl;
    @FXML
    private Label countryLbl;
    @FXML
    private ChoiceBox<String> selectCountryChoiceBx;

    /**
     * Initializes the controller class.
     *
     * @param _url to load API data for Drop down.
     * @param _rb resources for page.
     */
    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
        apiListen.addressListener(selectCountryChoiceBx, selectStateChoiceBX, selectCityChoiceBx);
    }

    @FXML
    private void confirmOrder(ActionEvent _event) {
        selectedCountry = selectCountryChoiceBx.getValue();
        selectedState = selectStateChoiceBX.getValue();
        selectedCity = selectCityChoiceBx.getValue();
        instructionStr = specialInstructionsTxFld.getText();
        vendorName = vendorNameTxFld.getText();
        streetAddress = streetNameTxFld.getText();
        zip = zipCodeTxFld.getText();
        orderNumber = orderConfirmationTxFld.getText();
        orderInfo = "Vendor Name: " + vendorName + "\n" + "Country: " + selectedCountry + "\n"
                + "State: " + selectedState + "\n" + "City: " + selectedCity + "\n" + "Address: "
                + streetAddress + "\n" + "ZipCode: " + zip + "\n" + "Order Number from seller: "
                + orderNumber + "\n" + "instructions: " + instructionStr;
        detailLbl.setText(orderInfo);
        orderProcessedMsgLbl.setVisible(true);
        detailLbl.setVisible(true);
        nextBtn.setDisable(false);

    }

    @FXML
    private void moveToNxtPage(ActionEvent _event) throws IOException {
        MoveScene.getInstance().Move("BidPage.fxml", nextBtn);
    }

    @FXML
    private void goToPrvPage(ActionEvent _event) throws IOException {
        MoveScene.getInstance().Move("AdditionalNeedsPer.fxml", backBtn);
    }

    @FXML
    private void vendorSelected(ActionEvent _event) {
        MenuItem source = (MenuItem) _event.getSource();
        String vendorStr = source.getText();
        selectVendorMnBx.setText(vendorStr);

    }

}
