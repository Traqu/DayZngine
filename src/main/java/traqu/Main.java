package traqu;

import traqu.gui.MainView;
import traqu.utils.process.ProcessSupervisor;
import traqu.constant.Constants;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(MainView::new);
//        new MainView();
        if (ProcessSupervisor.isCurrentWindow(Constants.DAYZ)) {
            System.out.println("+");
        } else {
            System.out.println("-");
        }

    }
}