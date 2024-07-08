package traqu;

import traqu.utils.process.ProcessSupervisor;
import traqu.constant.Constants;

public class Main {

    public static void main(String[] args) {

        new MainView();

        if (ProcessSupervisor.isCurrentWindow(Constants.DAYZ)) {
            System.out.println("+");
        } else {
            System.out.println("-");
        }

    }
}