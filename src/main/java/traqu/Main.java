package traqu;

import traqu.mvc.view.MainView;
import traqu.utils.process.ProcessSupervisor;
import traqu.constant.Constants;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(MainView::new);

        if (ProcessSupervisor.isCurrentWindow(Constants.DAYZ)) {
            System.out.println("+");
        } else {
            System.out.println("-");
        }
    }
}