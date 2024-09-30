package traqu.time.utils;

public abstract class Constants {
    public static final int MILLISECOND = 1;
    /**
     * A second expressed in milliseconds
     */
    public static final int SECOND = MILLISECOND * 1000;
    /**
     * Time needed to complete the animation expressed in milliseconds before starting proper action;
     * increased by 1000 to assure that you will not finish cracking just before the time ends
     */
    public static final int SAWING_ANIMATION_ENTRY_TIME = 1100;
    /**
     * Time needed to complete the animation expressed in milliseconds before starting proper action;
     * increased by 1000 to assure that you will not finish cracking just before the time ends
     */
    public static final int TINKERING_ANIMATION_ENTRY_TIME = 1100;
}
