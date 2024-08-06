package traqu.io.utils.settings;

import java.util.Properties;

public class SettingsProperty extends Properties {
    public synchronized void setSetting(String key, String value) {
        super.setProperty(key, value);
    }
}