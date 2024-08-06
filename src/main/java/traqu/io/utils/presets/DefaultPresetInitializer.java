package traqu.io.utils.presets;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public abstract class DefaultPresetInitializer {
    private final static String PRESET_RESOURCE_PATH = "/presets/data";

    @SneakyThrows
    public static void initPresets(PresetsProperty presets) {
        URL resource = DefaultPresetInitializer.class.getResource(PRESET_RESOURCE_PATH);
        if (resource != null) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.openStream()))) {
                String presetData;
                while ((presetData = bufferedReader.readLine()) != null) {
                    String[] presetValuesArray = presetData.split("=");
                    if (presetValuesArray.length == 2) {
                        presets.setPreset(presetValuesArray[0], presetValuesArray[1]);
                    } else {
                        System.out.println("Invalid preset data format: " + presetData);
                    }
                }
            }
        } else {
            System.err.println("Resource not found: " + PRESET_RESOURCE_PATH);
        }
    }
}