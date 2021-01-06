package API;

/**
 * This is an API for a drop-down selecting Country, State, and then City
 *
 * Last Updated 10/31/2020
 *
 * @author Katelynn Urgitus
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

public class UniversalTutorialAPI {

    private final String apiKey;
    private URL url;
    private String authToken;

    /**
     * Constructor for the CountryStateCityDropdownAPI class
     */
    public UniversalTutorialAPI() {
        this.apiKey = "Zpsw77iAWu38UL09ho0j3_e2v5SYkWeGttihzA6HB_sx7JcAuWZz7IGWbm4DDx_60fc";
        this.authToken = setAuthToken(this.apiKey, "katelynn.urgitus@yahoo.com");
    }

    /**
     *
     * @param _type the type of list we are looking for(i.e. country, state,
     * city)
     * @param _selected the country or state _selected by the user in the
     * drop-down
     * @return an array list for the given type
     */
    protected ArrayList<String> getDataFromAPI(String _type, String _selected) {
        String callAction = "";
        switch (_type) {
            case "country":
                callAction = "/api/countries/";
                break;
            case "state":
                callAction = "/api/states/" + _selected;
                break;
            case "city":
                callAction = "/api/cities/" + _selected;
                break;
            default:
                throw new IllegalStateException("Unexpected Value: " + _type + ". Try again.");
        }

        String baseUrl = "https://www.universal-tutorial.com";
        String urlString = baseUrl + callAction;
        ArrayList<String> notSoEmptyList = new ArrayList();
        try {
            url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + authToken);
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            StringBuffer content;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
            }
            con.disconnect();
            JSONArray obj = new JSONArray(content.toString());

            for (int i = 0; i < obj.length() - 1; i++) {
                JSONObject temp = new JSONObject(obj.get(i).toString());
                notSoEmptyList.add(temp.getString(_type + "_name"));

            }
        } catch (IOException | JSONException ex) {
            Logger.getLogger(UniversalTutorialAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notSoEmptyList;
    }

    /**
     *
     * @param _apiKey
     * @param _email
     * @return the authorization token needed to access the countries, states,
     * and cities
     */
    private String setAuthToken(String _apiKey, String _email) {
        String baseUrl = "https://www.universal-tutorial.com";
        String callActionAuth = "/api/getaccesstoken";
        URL urlAuth;
        try {
            urlAuth = new URL(baseUrl + callActionAuth);
            HttpURLConnection con = (HttpURLConnection) urlAuth.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("api-token", _apiKey);
            con.setRequestProperty("user-email", _email);
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            StringBuffer content;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
            }
            con.disconnect();

            JSONObject obj = new JSONObject(content.toString());
            authToken = obj.getString("auth_token");
            return authToken;
        } catch (IOException | JSONException ex) {
            Logger.getLogger(UniversalTutorialAPI.class.getName()).log(Level.SEVERE, null, ex);
            return "failed to connect";
        }
    }
}
