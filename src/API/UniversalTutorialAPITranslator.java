package API;

/**
 * This is a Translator for the Universal Tutorial API
 *
 * Last Updated 10/31/2020
 *
 * @author Katelynn Urgitus
 */
import java.util.ArrayList;

public class UniversalTutorialAPITranslator extends UniversalTutorialAPI implements CountryStateCityAPIInterface {

    protected String country;
    protected String state;

    /**
     *
     * @return The entire list of countries provided by the API
     */
    @Override
    public ArrayList<String> getCountryList() {
        return this.getDataFromAPI("country", "");
    }

    /**
     *
     * @param _selectedCountry the country selected by the user
     * @return the entire list of states for the selected country
     */
    @Override
    public ArrayList<String> getStateList(String _selectedCountry) {
        this.country = _selectedCountry;
        return this.getDataFromAPI("state", country);
    }

    /**
     *
     * @param _selectedState the state selected by the user
     * @return the entire list of cities for the selected state
     */
    @Override
    public ArrayList<String> getCityList(String _selectedState) {
        this.state = _selectedState;
        return this.getDataFromAPI("city", state);
    }
}
