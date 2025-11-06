package traqu.dayz.raidingtools.utils;

import java.awt.*;

public class EmergencyBackoffWatcher {
    public EmergencyBackoffWatcher(Point currentMouseLocation) {
        MouseMovementTracker mouseMovementTracker = new MouseMovementTracker(currentMouseLocation);
        mouseMovementTracker.addObserver(this);
    }

    /**
     * This method is being called by any class that is responsible for tracking
     * backoff conditions in order to increase cohesion.
     */
    protected void callWorkerToBackOff() {
        CrackWorker.breakCracking();
    }
}