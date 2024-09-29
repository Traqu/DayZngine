package traqu.dayz.raidingtools.utils;

import java.awt.*;

public class EmergencyBackOffWatcher {
    public EmergencyBackOffWatcher(Point location) {
        MouseMovementTracker mouseMovementTracker = new MouseMovementTracker(location);
        mouseMovementTracker.addObserver(this);
    }

    /**
     * This method is being called by any class that is responsible for tracking
     * back off conditions in order to increase cohesion. */
    protected void callWorkerToBackOff() {
        CrackWorker.breakCracking();
    }
}