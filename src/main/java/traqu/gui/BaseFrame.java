package traqu.gui;

import traqu.constant.LookAndFeel;

import javax.swing.*;

import static traqu.constant.Constants.APPLICATION_TITLE;

public abstract class BaseFrame extends JFrame implements LookAndFeel {

    public BaseFrame() {
        setTitle(APPLICATION_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    protected void setLookAndFeel(String lookAndFeel) {
        try {
            UIManager.setLookAndFeel(lookAndFeel);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
