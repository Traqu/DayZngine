package traqu.dayz.raidingtools.utils;

public class EmergencyBackOff {
    public EmergencyBackOff() {
    }
    private void backOff() {
        CrackWorker.breakCracking();
    }
}

class BackOffObserver {
    public BackOffObserver() {
        MouseMovementTracker mouseMovementTracker = new MouseMovementTracker();
        mouseMovementTracker.addObserver();
    }
}
