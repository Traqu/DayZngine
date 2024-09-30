package traqu;

import traqu.dayz.raidingtools.utils.EmergencyBackOffWatcher;

import java.awt.*;


public class Main {

    public static void main(String[] args) {
        new EmergencyBackOffWatcher(MouseInfo.getPointerInfo().getLocation()); //TODO remove after testing has been done
        //        AppDataFilesInitializer.init();
//        SwingUtilities.invokeLater(MainView::new);
    }
}