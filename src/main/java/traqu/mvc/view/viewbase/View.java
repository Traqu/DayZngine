package traqu.mvc.view.viewbase;

import traqu.constant.LookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static traqu.constant.Constants.APPLICATION_TITLE;

public abstract class View extends JFrame implements IView, LookAndFeel {

    public View() {
        setTitle(APPLICATION_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(Objects.requireNonNull(View.class.getResource("/graphics/icons/DayZ.png"))).getImage());
        setLocationRelativeTo(null);
        setLookAndFeel(WINDOWS);
    }

    public void setLookAndFeel(String lookAndFeel) {
        try {
            UIManager.setLookAndFeel(lookAndFeel);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Pass a JPanel, that will be the main panel of this view.
     *
     * @param panel this will be the main anchoring panel of this view.
     * @see #initializeView(JPanel)
     */

    @Override
    public void initializeView(JPanel panel) {
        add(panel);
        pack();
        setVisible(true);
        initializeController();
        setAllComponentsFocusable(panel, false);
    }

    private void setAllComponentsFocusable(Component component, boolean focusable) {
        if (component == null) return;

        if (component instanceof JTextField) {
            return;
        }

        component.setFocusable(focusable);

        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                setAllComponentsFocusable(child, focusable);
            }
        }
    }
}
