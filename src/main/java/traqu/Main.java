package traqu;

import traqu.mvc.view.MainView;
import traqu.settings.utils.AppDataFilesInitializer;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {

//        new EmergencyBackoffWatcher(MouseInfo.getPointerInfo().getLocation()); //TODO remove after testing has been done
        AppDataFilesInitializer.init();
        SwingUtilities.invokeLater(MainView::new);
    }
}