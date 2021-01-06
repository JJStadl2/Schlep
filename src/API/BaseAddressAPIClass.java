package API;

/**
 * This is a BaseAPI Class for our Country, State, City API
 *
 * Last Updated 10/31/2020
 *
 * @author Katelynn Urgitus
 */
public class BaseAddressAPIClass {

    protected final static CountryStateCityAPIInterface universalAPI = new UniversalTutorialAPITranslator();
}
