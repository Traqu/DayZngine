package traqu.dayz.raidingtools.utils;

import com.sun.jna.platform.KeyboardUtils;
import com.sun.jna.platform.win32.Win32VK;
import lombok.SneakyThrows;

import java.awt.*;
import java.util.ArrayList;

public class MouseMovementTracker extends Thread { //TODO callback to cracker class to method responsible for cracking process

    private static final int MOUSE_MOVE_TRESHOLD = 15;

    private int x;
    private int y;
    private Point mouseOnStartLocation;
    private ArrayList<EmergencyBackoffWatcher> observers = new ArrayList<>();

    private volatile boolean isThreadRedundant = false;

    @SneakyThrows
    public MouseMovementTracker(Point location) {
        mouseOnStartLocation = location;
        this.start();
    }

    @Override
    public void run() {
        try {
            while (!isThreadRedundant) {
                trackMouseMovement();

                Point mousePosition = MouseInfo.getPointerInfo().getLocation();
                x = mousePosition.x;
                y = mousePosition.y;

                System.out.println("x: " + x + " | y: " + y);

                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void trackMouseMovement() {
        double mouseMoved = countDistanceBetweenPoints(
                mouseOnStartLocation,
                MouseInfo.getPointerInfo().getLocation()
        );

        if (!isKeyPressed(Win32VK.VK_MENU)) {
            if (mouseMoved > MOUSE_MOVE_TRESHOLD) {
                System.out.println("Mouse has been moved more than " + MOUSE_MOVE_TRESHOLD + " units → [" + mouseMoved + "], and ALT was not being held.");
                notifyObservers();
                terminateThread();
            }
        }
    }

    private void terminateThread() {
        isThreadRedundant = true;
    }

    boolean isKeyPressed(Win32VK key) {
        boolean pressed = KeyboardUtils.isPressed(key.ordinal());
        return pressed;
    }

    private double countDistanceBetweenPoints(Point startingMousePosition, Point currentMousePosition) {
        double distance = Math.sqrt(Math.pow((startingMousePosition.x - currentMousePosition.x), 2) + Math.pow((startingMousePosition.y - currentMousePosition.y), 2));
        return distance;
    }

    public void addObserver(EmergencyBackoffWatcher observer) {
        observers.add(observer);
    }

    public void removeObserver(EmergencyBackoffWatcher observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (EmergencyBackoffWatcher observer : observers) {
            System.out.println("NOTIFYING OBSERVER: " + observer);
            observer.callWorkerToBackOff();
        }
    }
}