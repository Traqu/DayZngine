package traqu.gui;

import javax.swing.*;
import java.util.ResourceBundle;


public class MainView extends BaseFrame {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("localization");
    private JPanel mainPanel;
    private JPanel infoPanel;
    private JPanel settingsPanel;
    private JPanel toolsPanel;
    private JButton crackButton;
    private JTextField secondsAmtInput;
    private JButton languageButton;
    private JTextField circlesAmtInput;
    private JProgressBar progressBar1;
    private JButton button1;
    private JButton presetsButton;
    private JComboBox presetsCombobox;

    public MainView() {
        setLookAndFeel(WINDOWS);
//        System.out.println(SwingUtilities.isEventDispatchThread());
        add(mainPanel);
        progressBar1.setValue(50);
        pack();
        setVisible(true);
    }

}
