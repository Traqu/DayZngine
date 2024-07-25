package traqu.language;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {

    /** //TODO
     *      EXAMPLES:
     * <p>
     *       Locale.setDefault(new Locale("pl", "PL"));
     * <p>
     *       Locale currentLocale = new Locale("de", "DE");
     *       ResourceBundle resourceBundle = ResourceBundle.getBundle("localization", currentLocale);
     */

    private static LanguageManager instance;
    private ResourceBundle resourceBundle;

    private LanguageManager() {
        setLocale(Locale.getDefault());
    }

    public static synchronized LanguageManager getInstance() {
        if (instance == null) {
            instance = new LanguageManager();
        }
        return instance;
    }

    public void setLocale(Locale locale) {
        this.resourceBundle = ResourceBundle.getBundle("localization", locale);
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}