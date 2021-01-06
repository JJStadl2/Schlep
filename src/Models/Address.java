package Models;

/**
 * This is a class for the Address for a user
 *
 * Last Updated 11/11/2020
 *
 * @author Katelynn Urgitus
 */
import DB.MySQLConnector;
import java.sql.SQLException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ChoiceBox;
import java.util.HashMap;
import java.util.Map;

public class Address extends API.BaseAddressAPIClass {

    protected String addressLine1;
    protected String addressLine2;
    protected String country;
    protected String state;
    protected String city;
    protected String zipcode;
    protected String uuid;
    protected final static Address apiListen = new Address();
    private String selectedCountry;
    private String selectedState;
    private final static MySQLConnector con = new MySQLConnector();

    public Address() {

    }

    public Address(String _addressLine1, String _addressLine2, String _country, String _state, String _city, String _zipcode) throws SQLException {
        this.addressLine1 = _addressLine1;
        this.addressLine2 = _addressLine2;
        this.country = _country;
        this.state = _state;
        this.city = _city;
        this.zipcode = _zipcode;
        this.uuid = SchlepUser.user.getUuid();
        saveAddress();
        updateAddressId();
    }

    /**
     *
     * @param _countryBox
     * @param _stateBox
     * @param _cityBox
     */
    public void addressListener(ChoiceBox<String> _countryBox, ChoiceBox<String> _stateBox, ChoiceBox<String> _cityBox) {

        _countryBox.getItems()
                .addAll(universalAPI.getCountryList());
        _countryBox.valueProperty()
                .addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue ov, String oldValue,
                            String newValue
                    ) {
                        if (newValue == null) {
                            selectedCountry = oldValue;

                        } else {
                            selectedCountry = newValue;

                        }
                        _stateBox.getItems().addAll(universalAPI.getStateList(selectedCountry));
                    }
                }
                );
        _stateBox.valueProperty()
                .addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue ov, String oldValue,
                            String newValue
                    ) {
                        if (newValue == null) {
                            selectedState = oldValue;

                        } else {
                            selectedState = newValue;
                        }
                        _cityBox.getItems().addAll(universalAPI.getCityList(selectedState));
                    }
                }
                );
    }

    /**
     *
     * @throws SQLException
     */
    private void saveAddress() throws SQLException {
        HashMap<String, String> pairs = new HashMap();
        pairs.put("addressLine1", this.getAddressLine1());
        pairs.put("addressLine2", this.getAddressLine2());
        pairs.put("country", this.getCountry());
        pairs.put("state", this.getState());
        pairs.put("city", this.getCity());
        pairs.put("zipcode", this.getZipcode());
        pairs.put("UUID", this.getUUID());
        con.createObject(pairs, "address");
    }

    /**
     *
     */
    private void updateAddressId() {
        Map<String, String> map = new HashMap();
        map.put("address_id", "address.UUID");
        con.updateObject(map, this.getUUID(), "user", "address");
    }

// ================================ GETTERS ====================================
    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getUUID() {
        return uuid;
    }

// ================================ SETTERS ====================================
    public void setAddressLine1(String _addressLine1) {
        this.addressLine1 = _addressLine1;
    }

    public void setAddressLine2(String _addressLine2) {
        this.addressLine2 = _addressLine2;
    }

    public void setCountry(String _country) {
        this.country = _country;
    }

    public void setState(String _state) {
        this.state = _state;
    }

    public void setCity(String _city) {
        this.city = _city;
    }

    public void setZipcode(String _zipcode) {
        this.zipcode = _zipcode;
    }

}
