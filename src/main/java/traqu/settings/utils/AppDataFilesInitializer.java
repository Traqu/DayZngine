package traqu.settings.utils;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static traqu.constant.Paths.*;

public abstract class AppDataFilesInitializer {

    public static void init() {
        initializeDirectory(SETTINGS, true);
        initializeDirectory(PREFERENCES, true);
        initializeDirectory(PRESETS, true);
    }

    @SneakyThrows
    private static void initializeDirectory(String path, boolean hide) {
        File directory = new File(path);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                if (hide) {
                    hideDirectory(directory);
                }
                System.out.println(path + " directory created.");
//                Desktop.getDesktop().open(new File(SETTINGS_PATH));
                if (directory.getName().equals("settings")) {
                    AppDataFilesInitializer.initializeDefaultSettings();
                } else if (directory.getName().equals("presets")) {
                    AppDataFilesInitializer.initializeDefaultPresets();
                }
            } else {
                System.err.println("Failed to create " + path + " directory.");
            }
        }
    }

    private static void initializeDefaultPresets() {
        File presetsFile = new File(PRESETS, "presets.properties");
        if (!presetsFile.exists()) {
            Properties presets = new Properties();
            presets.setProperty("BasicallyVanilla", "Gate,6,5,Storage,5,1");

            try (FileOutputStream output = new FileOutputStream(presetsFile)) {
                presets.store(output, "Default presets");
                System.out.println("Default settings initialized.");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Failed to initialize default settings.");
            }
        }
    }

    private static void initializeDefaultSettings() {
        File settingsFile = new File(SETTINGS, "settings.properties");
        if (!settingsFile.exists()) {
            Properties settings = new Properties();
            settings.setProperty("language", "en");
            settings.setProperty("theme", "light");

            try (FileOutputStream output = new FileOutputStream(settingsFile)) {
                settings.store(output, "Default settings");
                System.out.println("Default settings initialized.");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Failed to initialize default settings.");
            }
        }
    }

    private static void hideDirectory(File directory) {
        try {
            Process p = Runtime.getRuntime().exec("attrib +H " + directory);
            p.waitFor();
            System.out.println("Folder hidden: " + directory);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.err.println("Failed to hide folder.");
        }
    }
}