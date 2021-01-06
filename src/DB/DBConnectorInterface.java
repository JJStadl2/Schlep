package DB;

/**
 * The Database Interface to implement the CRUD operations that allow us to use
 * any database
 *
 * Last Updated: 10/31/2020
 *
 * @author Katelynn Urgitus
 */
import java.util.HashMap;
import java.util.Map;

public interface DBConnectorInterface {

    public abstract int createObject(Map<String, String> _keyValuePair, String _table);

    public abstract HashMap<String, Object> readObject(Map<String, String> _keyValuePair, String _table);

    public abstract Boolean updateObject(Map<String, String> _keyValuePair, String _uuid, String _table);

    public abstract Boolean deleteObject(String _uuid);
}
