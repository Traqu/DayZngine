package traqu.io.utils.presets;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static traqu.constant.Paths.PRESETS;

public abstract class PresetHandler {

    private static final String PRESETS_FILE_PATH = PRESETS + "/presets.properties";

    public static String[] getPresetValues(String key) {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(PRESETS_FILE_PATH)) {
            properties.load(input);
            String property = properties.getProperty(key);
            String[] presetValuesArray = property.split(",");
            return presetValuesArray;
        } catch (IOException e) {
            System.err.println("Failed to load preset: " + e.getMessage());
            return null;
        }
    }

    public static void savePreset(String key, String value) {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(PRESETS_FILE_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            // File does not exist or can't be read, so we'll create a new one
        }
        properties.setProperty(key, value);
        try (FileOutputStream output = new FileOutputStream(PRESETS_FILE_PATH)) {
            properties.store(output, "Presets");
        } catch (IOException e) {
            System.err.println("Failed to save preset: " + e.getMessage());
        }
    }
}
