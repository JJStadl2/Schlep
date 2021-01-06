package API;

/**
 * This is our API Interface for the Country, State, City API
 *
 * Last Updated 10/31/2020
 *
 * @author Katelynn Urgitus
 */
import java.util.ArrayList;

public interface CountryStateCityAPIInterface {

    public ArrayList<String> getCountryList();

    public ArrayList<String> getStateList(String _country);

    public ArrayList<String> getCityList(String _state);
}
