package traqu.time.utils;

public abstract class TimeCalculator {

    public static String formatTimeLeft(int totalTimeInSeconds, int elapsedTimeInSeconds) {
        int remainingTimeInSeconds = totalTimeInSeconds - elapsedTimeInSeconds;
        int minutes = remainingTimeInSeconds / 60;
        int seconds = remainingTimeInSeconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    public static int calculateElapsedTime(long startTime) {
        return (int) ((System.currentTimeMillis() - startTime) / 1000);
    }

    public static long getStartTime() {
        return System.currentTimeMillis();
    }
}