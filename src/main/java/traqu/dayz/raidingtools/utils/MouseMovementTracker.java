package traqu.dayz.raidingtools.utils;

import lombok.SneakyThrows;

import java.awt.*;
import java.util.ArrayList;

public class MouseMovementTracker extends Thread { //TODO callback to cracker class to method responsible for cracking process

    private static final int MOUSE_MOVE_TRESHOLD = 250;

    private int x;
    private int y;
    private ArrayList<BackOffObserver> observers = new ArrayList<>();
    @SneakyThrows

    public MouseMovementTracker() {
        this.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
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

    public void addObserver(EmergencyBackOff observer) {
        observers.add(observer);
    }

    public void removeObserver(EmergencyBackOff observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (BackOffObserver observer : observers) {
            observer.notify();
        }
    }
}