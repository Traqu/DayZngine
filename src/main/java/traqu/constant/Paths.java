package traqu.constant;

import java.io.File;

import static traqu.constant.Constants.APPLICATION_TITLE;

public abstract class Paths {
    public static final String APP_DATA = System.getenv("APPDATA") + File.separator + APPLICATION_TITLE + File.separator;
    public static final String SETTINGS = APP_DATA + "settings";
    public static final String PREFERENCES = APP_DATA + "preferences";
    public static final String PRESETS = APP_DATA + "presets";
}
