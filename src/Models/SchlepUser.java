package Models;

/**
 *
 * The SchlepUser Class builds a User and places the User data in a text file -
 * (eventually a database)
 *
 * Last Updated: 12/01/2020
 *
 * @author Marc Bittle, Katelynn Urgitus
 */
import java.io.*;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import DB.MySQLConnector;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SchlepUser extends DB.DataObject {

    private String firstName;
    private String lastName;
    private String userEmail;
    private String userPassword;
    private String phoneNumber;
    private final MySQLConnector connector = new MySQLConnector();
    protected static SchlepUser user = new SchlepUser();

    public SchlepUser() {

    }

    /**
     *
     * @param _fname
     * @param _lname
     * @param _email
     * @param _password
     * @param _phone
     * @throws IOException
     */
    public SchlepUser(TextField _fname, TextField _lname, TextField _email, PasswordField _password, TextField _phone) throws IOException {
        this.firstName = _fname.getText();
        this.lastName = _lname.getText();
        this.userEmail = _email.getText();
        this.userPassword = _password.getText();
        this.phoneNumber = _phone.getText();

        this.setUuid(DB.DataObject.generateUuid());
        saveSchleperInfo();
    }

    /**
     * Save User to DB
     */
    public void saveSchleperInfo() throws IOException {
        HashMap<String, String> saveUser = new HashMap();
        saveUser.put("UUID", this.getUuid());
        saveUser.put("firstName", this.getFirstName());
        saveUser.put("lastName", this.getLastName());
        saveUser.put("userEmail", this.getEmail());
        saveUser.put("userPassword", this.getPassword());
        saveUser.put("phoneNumber", this.getPhoneNumber());

        connector.createObject(saveUser, "user");
    }

    /**
     *
     * @param _email to be validated.
     * @return true if valid, false if invalid.
     */
    public boolean checkValidEmail(String _email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (_email == null) {
            return false;
        }
        return pat.matcher(_email).matches();
    }

    /**
     * Verify that the User account exists
     *
     * @param _email user email
     * @param _password user password
     * @return results from query (the user if they exist, otherwise null)
     */
    public boolean checkValidUser(String _email, String _password) {
        boolean validUser = false;
        Map<String, String> checkExists = new HashMap();
        checkExists.put("userEmail", _email);
        checkExists.put("userPassword", _password);

        if (connector.readObject(checkExists, "user") != null) {
            validUser = true;
        }

        return validUser;
    }

    // ================================ GETTERS ===================================
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.userEmail;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String toString() {
        String str = this.firstName + " " + this.lastName;
        return str;
    }

    // ================================ SETTERS ====================================
    /**
     *
     * @param _fname user first name
     */
    public void setFirstName(String _fname) {
        this.firstName = _fname;
    }

    /**
     *
     * @param _lname user last name
     */
    public void setLastName(String _lname) {
        this.lastName = _lname;
    }

    /**
     *
     * @param _email user email
     */
    public void setEmail(String _email) {
        this.userEmail = _email;
    }

    /**
     *
     * @param _password user password
     */
    public void setPassword(String _password) {
        this.userPassword = _password;
    }

    /**
     *
     * @param _phone user phone number
     */
    public void setPhoneNumber(String _phone) {
        this.phoneNumber = _phone;
    }
}
