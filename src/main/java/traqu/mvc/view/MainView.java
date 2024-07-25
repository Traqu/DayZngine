package traqu.mvc.view;

import traqu.mvc.controller.MainViewController;
import traqu.mvc.view.viewbase.View;

import javax.swing.*;

public class MainView extends View {
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
    private JComboBox<String> presetsCombobox;

    public MainView() {
        initializeView(mainPanel);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getInfoPanel() {
        return infoPanel;
    }

    public JPanel getSettingsPanel() {
        return settingsPanel;
    }

    public JPanel getToolsPanel() {
        return toolsPanel;
    }

    public JButton getCrackButton() {
        return crackButton;
    }

    public JTextField getSecondsAmtInput() {
        return secondsAmtInput;
    }

    public JButton getLanguageButton() {
        return languageButton;
    }

    public JTextField getCirclesAmtInput() {
        return circlesAmtInput;
    }

    public JProgressBar getProgressBar1() {
        return progressBar1;
    }

    public JButton getButton1() {
        return button1;
    }

    public JButton getPresetsButton() {
        return presetsButton;
    }

    public JComboBox<String> getPresetsCombobox() {
        return presetsCombobox;
    }

    @Override
    public void initializeController() {
        new MainViewController(this);
    }
}
