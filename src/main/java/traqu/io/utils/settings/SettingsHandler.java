package traqu.io.utils.settings;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static traqu.constant.Paths.SETTINGS;

public abstract class SettingsHandler {

    private static final String SETTINGS_FILE_PATH = SETTINGS + "/settings.properties";

    public static String getSetting(String key) {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(SETTINGS_FILE_PATH)) {
            properties.load(input);
            return properties.getProperty(key);
        } catch (IOException e) {
            System.err.println("Failed to load setting: " + e.getMessage());
            return null;
        }
    }

    public static void saveSetting(String key, String value) {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(SETTINGS_FILE_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            // File does not exist or can't be read, so we'll create a new one
        }
        properties.setProperty(key, value);
        try (FileOutputStream output = new FileOutputStream(SETTINGS_FILE_PATH)) {
            properties.store(output, "Settings");
        } catch (IOException e) {
            System.err.println("Failed to save setting: " + e.getMessage());
        }
    }
}
