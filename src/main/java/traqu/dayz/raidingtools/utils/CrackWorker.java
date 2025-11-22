package traqu.dayz.raidingtools.utils;

import traqu.language.LanguageManager;
import traqu.mvc.controller.MainViewController;
import traqu.mvc.view.MainView;
import traqu.process.utils.ProcessSupervisor;
import traqu.time.utils.TimeCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;

import static traqu.constant.Constants.*;
import static traqu.time.utils.Constants.*;

public abstract class CrackWorker {
    private static final Robot ROBOT;
    private static final LanguageManager LANGUAGE_MANAGER = LanguageManager.getInstance();
    private static boolean hasCrackingBeenBroken = false;

    static {
        try {
            ROBOT = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static void crack(int totalCrackingTime, MainViewController controller) {
        MainView view = controller.getView();
        controller.disableCrackingButton();

//        new EmergencyBackoffWatcher(MouseInfo.getPointerInfo().getLocation());

        SwingWorker<Void, Void> combinedWorker = new SwingWorker<>() {


            @Override
            protected Void doInBackground() throws Exception {
                int countdownTime = 5;
                for (int i = countdownTime; i > 0; i--) {
                    controller.setActionLogTextFieldTextColor(Color.RED);
                    controller.updateActionLogTextField(java.text.MessageFormat.format(LANGUAGE_MANAGER.getString("timeLeftToFocus"), i));
                    view.pack();
                    System.out.println(LANGUAGE_MANAGER.getString("timeLeftToFocus") + i);
                    Thread.sleep(SECOND);
                }
                controller.updateActionLogTextField(LANGUAGE_MANAGER.getString("crackingInProgress"));
                view.pack();

                if (!ProcessSupervisor.isCurrentWindow(DAYZ)) {
                    System.out.println("You are not focused on DayZ!");
                    controller.updateActionLogTextField(LANGUAGE_MANAGER.getString("incorrectProcess"));
                    view.pack();
                    return null;        //Abort the cracking; commence only when you are focused on the game
                }

                new EmergencyBackoffWatcher(MouseInfo.getPointerInfo().getLocation());


                controller.updateProgressMaximum(totalCrackingTime);
                controller.updateProgress(0);

                ROBOT.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                Thread.sleep(handleAnimationByTarget(controller)); //holding button for additional milliseconds, to not lose time on entry animation

                final long startTime = TimeCalculator.getStartTime();

                for (int i = 0; i < totalCrackingTime; i++) {
                    if (hasCrackingBeenBroken) {
                        hasCrackingBeenBroken = false;
                        break;
                    }
                    Thread.sleep(SECOND);
                    int elapsedTime = TimeCalculator.calculateElapsedTime(startTime);
                    final int progress = i + 1;
                    controller.updateProgress(progress);
                    String timeLeft = TimeCalculator.formatTimeLeft(totalCrackingTime, elapsedTime);
                    controller.updateTimeLeft(java.text.MessageFormat.format(LANGUAGE_MANAGER.getString("timeLeft"), timeLeft));
                    System.out.println("Progress: " + progress + "/" + totalCrackingTime);
                }
                ROBOT.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                return null;
            }

            @Override
            protected void done() {
                controller.crackingComplete();
                controller.restoreActionLogTextFieldTextColor();
                controller.enableCrackingButton();
                controller.restartProgressBar();

                SwingUtilities.invokeLater(() -> {
                    Timer timer = new Timer(SECOND * 2, e -> {
                        controller.clearActionLogText();
                        controller.enableCrackingButton();
                        controller.hideTimeLeft();
                    });
                    timer.setRepeats(false);
                    timer.start();
                });
            }
        };
        combinedWorker.execute();
    }

    private static int handleAnimationByTarget(MainViewController controller) {
        String targetType = controller.getChosenTargetType();
        if (targetType.equalsIgnoreCase("gate")) {
            System.out.println("Currently cracking gate!");
            return SAWING_ANIMATION_ENTRY_TIME;
        } else if (targetType.equalsIgnoreCase("storage")) {
            System.out.println("Currently cracking storage!");
            return TINKERING_ANIMATION_ENTRY_TIME;
        } else return 0;
    }

    public static void breakCracking() {
        SwingUtilities.invokeLater(() -> {
            ROBOT.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        });
        hasCrackingBeenBroken = true;
    }
}