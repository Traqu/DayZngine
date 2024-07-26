package traqu.dayz.raidingtools;

import traqu.mvc.controller.MainViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;

import static traqu.constant.Constants.SECOND;

public abstract class CrackWorker {
    private static final Robot ROBOT;

    static {
        try {
            ROBOT = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static void crack(int cyclesAmount, int cycleTime, MainViewController controller) {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                int totalCrackingTime = cyclesAmount * 60 * cycleTime;

                SwingUtilities.invokeLater(() -> {
                    controller.updateProgressMaximum(totalCrackingTime);
                    controller.updateProgress(0);
                });

                ROBOT.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                for (int i = 0; i < totalCrackingTime; i++) {
                    Thread.sleep(SECOND);
                    final int progress = i + 1;
                    SwingUtilities.invokeLater(() -> controller.updateProgress(progress));
                    System.out.println("Progress: " + progress + "/" + totalCrackingTime);
                }
                ROBOT.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                return null;
            }

            @Override
            protected void done() {
                SwingUtilities.invokeLater(controller::crackingComplete);
            }
        };
        worker.execute();
    }
}
