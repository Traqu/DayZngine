package traqu.language;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {

    public LanguageManager() {

        /**examples*/
        Locale.setDefault(new Locale("pl", "PL"));

        Locale currentLocale = new Locale("de", "DE");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("language", currentLocale);

    }
}
