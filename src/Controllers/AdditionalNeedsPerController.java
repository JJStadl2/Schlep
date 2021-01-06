package Controllers;

/**
 * Controller for 'AdditionalNeedsPer.fxml'. This is where a customer selects
 * items they might need for offloading items that are delivered. this includes
 * assistance from the driver, which is an additional fee.
 *
 * Last Updated: 11/18/2020
 *
 * @author Josiah Stadler, Katelynn Urgitus
 */
import Models.MoveScene;
import Models.PassCost;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class AdditionalNeedsPerController extends PassCost implements Initializable {

    //Gets the one hour wage for driver assistance with offloading from the PassCost model.
    public double wage = getWAGE();
    private static final double HALFHR = .5;
    private static final double ONEHR = 1.0;
    private static final double TWOHR = 2.0;
    private static final double THREEHR = 3.0;

   
    private final double halfHrWage = wage * HALFHR;
    private final double twoHrWage = wage * TWOHR;
    private final double threeHrWage = wage * THREEHR;
    public static double assistTime;
    private double subtotal;
    private String time;
    private String itemsMsg;
    private String costMsg;
    private String assistance = "Driver Assistance";
    private String needsOut;
    private String costOut;

    @FXML
    private AnchorPane anchor;

    @FXML
    private ImageView logo;

    @FXML
    private MenuButton additionalItemMnBtn;

    @FXML
    private CheckMenuItem dollyCheckMnBtn;

    @FXML
    private CheckMenuItem handTruckCheckMnBtn;

    @FXML
    private CheckMenuItem rampCheckMnItem;

    @FXML
    private CheckMenuItem assistCheckMnItem;

    @FXML
    private MenuButton AssistTimeAmountMnBtn;

    @FXML
    private RadioMenuItem time30Rb;

    @FXML
    private RadioMenuItem time1HrRb;

    @FXML
    private RadioMenuItem time2HrRb;

    @FXML
    private RadioMenuItem time3HrRb;

    @FXML
    private Button processBtn;

    @FXML
    private Label confirmedLbl;

    @FXML
    private Button nextBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Label costLbl;

    @FXML
    private VBox outputVBox;

    @FXML
    private Label needsLbl;

    @Override
    public void initialize(URL _url, ResourceBundle _rb) {
        outputVBox.setVisible(false);
        AssistTimeAmountMnBtn.setVisible(false);
        processBtn.setDisable(true);
        nextBtn.setDisable(true);
        needsLbl.setText("Additional Items: ");
        costLbl.setVisible(false);
        needsOut = needsLbl.getText();
        costOut = costLbl.getText();
        assistTime = 0;

    }

    @FXML
    private void checkItem(ActionEvent _event) {
        outputVBox.setVisible(false);
        CheckMenuItem source = (CheckMenuItem) _event.getSource();
        String id = source.getId();
        if (id.equals(assistCheckMnItem.getId())) {
            AssistTimeAmountMnBtn.setVisible(true);
        }
        processBtn.setDisable(false);
    }

    void CheckDriverAssist(ActionEvent _event) {
        AssistTimeAmountMnBtn.setVisible(true);
    }

    @FXML
    void movePrvPage(ActionEvent _event) throws IOException {
        MoveScene.getInstance().Move("DeliveryInfo.fxml", backBtn);
    }

    @FXML
    void moveToNextPage(ActionEvent _event) throws IOException {
        MoveScene.getInstance().Move("PickupAddress.fxml", nextBtn);
    }

    @FXML
    void selectTime(ActionEvent _event) {
        RadioMenuItem source = (RadioMenuItem) _event.getSource();
        String id = source.getId();
        AssistTimeAmountMnBtn.setText(source.getText());
        switch (id) {
            case "time30":
                subtotal = halfHrWage;
                time = time30Rb.getText();
                assistTime = HALFHR;
                time1HrRb.setSelected(false);
                time2HrRb.setSelected(false);
                time3HrRb.setSelected(false);
                break;
            case "time1Hr":
                subtotal = wage;
                time = time1HrRb.getText();
                assistTime = ONEHR;
                time30Rb.setSelected(false);
                time2HrRb.setSelected(false);
                time3HrRb.setSelected(false);
                break;
            case "time2Hr":
                subtotal = twoHrWage;
                time = time2HrRb.getText();
                assistTime = TWOHR;
                time1HrRb.setSelected(false);
                time30Rb.setSelected(false);
                time3HrRb.setSelected(false);
                break;
            case "time3Hr":
                subtotal = threeHrWage;
                time = time3HrRb.getText();
                assistTime = THREEHR;
                time1HrRb.setSelected(false);
                time2HrRb.setSelected(false);
                time30Rb.setSelected(false);
                break;
            default:
                subtotal = 0;
                assistTime = 0;
                time = "";
        }
        setAssistCost(subtotal);
        processBtn.setDisable(false);
    }

    @FXML
    void showCost(ActionEvent _event) {
        itemsMsg = "";
        costMsg = "";
        outputVBox.setVisible(false);
        if (dollyCheckMnBtn.isSelected()) {
            itemsMsg += dollyCheckMnBtn.getText() + " ";

        }
        if (rampCheckMnItem.isSelected()) {
            itemsMsg += rampCheckMnItem.getText() + " ";

        }
        if (handTruckCheckMnBtn.isSelected()) {
            itemsMsg += handTruckCheckMnBtn.getText() + " ";

        }
        if (assistCheckMnItem.isSelected()) {
            itemsMsg += assistance;
            costMsg = "$" + String.format("%.2f", getAssistCost());
            costLbl.setVisible(true);
            AssistTimeAmountMnBtn.setVisible(true);
        }
        if (!assistCheckMnItem.isSelected()) {
            costMsg = "";
            AssistTimeAmountMnBtn.setVisible(false);
        }
        needsLbl.setText(needsOut + itemsMsg);
        costLbl.setText(costOut + costMsg);

        outputVBox.setVisible(true);
        nextBtn.setDisable(false);
    }
}
