package Controllers;

/**
 * Controls all action events on the Timer FXML file
 *
 * Last Updated 11/17/2020
 *
 * @author Marc Bittle
 */
import java.net.URL;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Marc Bittle
 */
public class TimerController implements Initializable {

    @FXML
    private AnchorPane menuPane;
    @FXML
    private AnchorPane timerPane;
    @FXML
    private ComboBox<Integer> hoursInput;
    @FXML
    private ComboBox<Integer> minutesInput;
    @FXML
    private ComboBox<Integer> secondsInput;
    @FXML
    private Button startButton;
    @FXML
    private Text hoursTimer;
    @FXML
    private Text minutesTimer;
    @FXML
    private Text secondsTimer;
    @FXML
    private Button cancelBtn;

    private Map<Integer, String> numberMap;
    private Integer currentSeconds;
    private Thread thread;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> hoursList = FXCollections.observableArrayList();
        ObservableList<Integer> minutesAndSecondsList = FXCollections.observableArrayList();

        // Populate the hoursList and the minutesAndSecondsList with applicable values
        // (i.e , 0-24 for hours, 0-60 for minutes and seconds)
        for (int i = 0; i <= 60; i++) {
            if (0 <= i && i <= 24) {
                hoursList.add(i);
            }
            minutesAndSecondsList.add(i);
        }

        // add the hoursList and the minutesAndSecondsList as item to the input menu - default all values to zero
        hoursInput.setItems(hoursList);
        hoursInput.setValue(0);

        minutesInput.setItems(minutesAndSecondsList);
        minutesInput.setValue(0);

        secondsInput.setItems(minutesAndSecondsList);
        secondsInput.setValue(0);

        // generate a TreepMap whose Keys are Integers (0 - 60) and the Values are a string
        // representation of the Keys
        numberMap = new TreeMap<Integer, String>();
        for (Integer i = 0; i <= 60; i++) {
            if (0 <= i && i <= 9) {
                numberMap.put(i, "0" + i.toString());
            } else {
                numberMap.put(i, i.toString());
            }
        }
    }

    /**
     * Convert hours and minutes to seconds
     *
     * @param _hrs hours
     * @param _min minutes
     * @param _sec seconds
     * @return total of seconds
     */
    public Integer hmsToSeconds(Integer _hrs, Integer _min, Integer _sec) {
        Integer hrsToSeconds = _hrs * 3600;
        Integer minToSeconds = _min * 60;
        Integer totalSec = hrsToSeconds + minToSeconds + _sec;
        return totalSec;
    }

    /**
     * Convert seconds to hours and minutes
     *
     * @param _currentSecond
     * @return a LinkedList with three nodes - hours(Node 0), minutes(Node 1),
     * seconds(Node 3)
     */
    public LinkedList<Integer> secondsToHms(Integer _currentSecond) {
        Integer hrs = _currentSecond / 3600;
        _currentSecond = _currentSecond % 3600;
        Integer minutes = _currentSecond / 60;
        _currentSecond = _currentSecond % 60;
        Integer seconds = _currentSecond;
        LinkedList<Integer> hmsNodes = new LinkedList<>();
        hmsNodes.add(hrs);
        hmsNodes.add(minutes);
        hmsNodes.add(seconds);
        return hmsNodes;
    }

    /**
     * Run the countdown for the timer
     */
    public void startTimer() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        // set the output for the timer
                        setOutput();
                        Thread.sleep(1000);
                        if (currentSeconds == 0) {
                            System.out.println("Finished!");
                            scrollDown();
                            thread.stop();
                        }
                        // decrement seconds
                        currentSeconds -= 1;
                    }
                } catch (InterruptedException e) {
                    // TODO
                }
            }
        });
        thread.start();
    }

    /**
     * Set the output for the timer (i.e, the time remaining until the timer is
     * finished)
     */
    public void setOutput() {
        LinkedList<Integer> currentHms = secondsToHms(currentSeconds);
        System.out.println(currentHms.get(0) + "-" + currentHms.get(1) + "-" + currentHms.get(2));
        hoursTimer.setText(numberMap.get(currentHms.get(0)));
        minutesTimer.setText(numberMap.get(currentHms.get(1)));
        secondsTimer.setText(numberMap.get(currentHms.get(2)));
    }

    /**
     * Set the current number of seconds based on the menu input and reset input
     * fields. Then transition to the timer
     *
     * @param event
     */
    @FXML
    private void start(ActionEvent event) {
        currentSeconds = hmsToSeconds(hoursInput.getValue(), minutesInput.getValue(), secondsInput.getValue());
        hoursInput.setValue(0);
        minutesInput.setValue(0);
        secondsInput.setValue(0);
        scrollUp();
    }

    /**
     * Stop the thread that is executing the timer and return to the timer menu
     *
     * @param event
     */
    @FXML
    private void cancelTimer(ActionEvent event) {
        thread.stop();
        scrollDown();
        System.out.println("Timer Canceled!");
    }

    /**
     * Transition from the timer menu to the timer output. Start the timer
     */
    private void scrollUp() {
        TranslateTransition menu = new TranslateTransition();
        menu.setDuration(Duration.millis(100));
        menu.setToX(0);
        menu.setToY(-300);
        menu.setNode(menuPane);

        TranslateTransition timer = new TranslateTransition();
        timer.setDuration(Duration.millis(100));
        timer.setFromX(0);
        timer.setFromY(300);
        timer.setToX(0);
        timer.setToY(0);
        timer.setNode(timerPane);

        ParallelTransition toTimer = new ParallelTransition(menu, timer);
        toTimer.setOnFinished(event -> {
            try {
                System.out.println("Start counter...");
                startTimer();
            } catch (Exception e) {
                // TODO
            }
        });
        toTimer.play();
    }

    /**
     * Transition from the timer output to the timer menu
     */
    private void scrollDown() {
        TranslateTransition timer = new TranslateTransition();
        timer.setDuration(Duration.millis(100));
        timer.setToX(0);
        timer.setToY(300);
        timer.setNode(timerPane);

        TranslateTransition menu = new TranslateTransition();
        menu.setDuration(Duration.millis(100));
        menu.setFromX(0);
        menu.setFromY(300);
        menu.setToX(0);
        menu.setToY(0);
        menu.setNode(menuPane);

        ParallelTransition para1 = new ParallelTransition(timer, menu);
        para1.play();
    }
}
