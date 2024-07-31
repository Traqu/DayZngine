package traqu.dayz.raidingtools;

import lombok.SneakyThrows;
import traqu.language.LanguageManager;
import traqu.mvc.controller.MainViewController;
import traqu.mvc.view.MainView;
import traqu.process.utils.ProcessSupervisor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;

import static traqu.constant.Constants.DAYZ;
import static traqu.constant.Constants.SECOND;

public abstract class CrackWorker {
    private static final Robot ROBOT;
    private static final LanguageManager LANGUAGE_MANAGER = LanguageManager.getInstance();
    private static int totalCrackingTime;
    static {
        try {
            ROBOT = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static void crack(int cyclesAmount, int cycleTime, MainViewController controller) {

        MainView view = controller.getView();

        controller.disableCrackingButton();

        handleManualMode(cyclesAmount, cycleTime, controller); //TODO

        SwingWorker<Void, Void> progressBarWorker = new SwingWorker<>() {


            @Override
            protected Void doInBackground() throws Exception {

                controller.disableCrackingButton();
                controller.updateProgressMaximum(totalCrackingTime);
                controller.updateProgress(0);

                ROBOT.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                for (int i = 0; i < totalCrackingTime; i++) {
                    Thread.sleep(SECOND);
                    final int progress = i + 1;
                    controller.updateProgress(progress);
                    System.out.println("Progress: " + progress + "/" + totalCrackingTime);
                }
                ROBOT.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                return null;
            }

            @Override
            protected void done() {
                controller.crackingComplete();
                controller.enableCrackingButton();
            }
        };

        SwingWorker<Void, Void> actionLogCountdownWorker = new SwingWorker<>() {

            @Override
            protected Void doInBackground() {
                int countdownTime = 5;
                for (int i = countdownTime; i > 0; i--) {
                    try {
                        controller.setActionLogTextFieldTextColor(Color.RED);
                        controller.updateActionLogTextField(java.text.MessageFormat.format(LANGUAGE_MANAGER.getString("timeLeftToFocus"), i));
                        view.pack();
                        System.out.println(LANGUAGE_MANAGER.getString("timeLeftToFocus") + i);
                        Thread.sleep(SECOND);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (!ProcessSupervisor.isCurrentWindow(DAYZ)) {
                    System.out.println("You are not focused on DayZ!");
                    controller.updateActionLogTextField(LANGUAGE_MANAGER.getString("invalidProcess"));
                    view.pack();
                } else {
                    progressBarWorker.execute();
                }
                return null;
            }

            @SneakyThrows
            @Override
            protected void done() {
                super.done();
                controller.restoreActionLogTextFieldTextColor();
                Thread.sleep(SECOND * 3);
                controller.clearActionLogText();
                controller.enableCrackingButton();
            }
        };
        actionLogCountdownWorker.execute();
    }

    private static void handleManualMode(int cyclesAmount, int cycleTime, MainViewController controller) {
        if (controller.isManualModeEnabled()) {
            totalCrackingTime = cyclesAmount * 60 * cycleTime;
        } else {
            System.out.println("Disabled manual mode");
        }
    }
}