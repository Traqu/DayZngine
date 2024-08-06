package traqu.io.utils.presets;

import java.util.Properties;

public class PresetsProperty extends Properties {

    public synchronized void setPreset(String key, String value) {
        super.setProperty(key, value);
    }
}