package traqu;

import traqu.mvc.view.MainView;
import traqu.settings.utils.AppDataFilesInitializer;

import javax.swing.SwingUtilities;


public class Main {

    public static void main(String[] args) {

        AppDataFilesInitializer.init();
        SwingUtilities.invokeLater(MainView::new);
    }
}