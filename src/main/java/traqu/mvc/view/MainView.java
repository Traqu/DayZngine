package traqu.mvc.view;

import lombok.Getter;
import traqu.mvc.controller.MainViewController;
import traqu.mvc.view.viewbase.View;

import javax.swing.*;

@Getter
public class MainView extends View {
    private JPanel mainPanel;
    private JPanel infoPanel;
    private JPanel settingsPanel;
    private JPanel toolsPanel;
    private JButton crackButton;
    private JTextField cycleTimeInput;
    private JButton languageButton;
    private JTextField cyclesAmountInput;
    private JProgressBar crackingProgressBar;
    private JButton button1;
    private JButton presetsButton;
    private JComboBox<String> presetsCombobox;
    private JPanel targetSelectionPanel;
    private JRadioButton storageRadioButton;
    private JRadioButton gateRadioButton;
    private JTextField actionLogTextField;
    private JCheckBox useManualValuesCheckBox;
    private JButton preferencesButton;

    public MainView() {
        initializeView(mainPanel);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(gateRadioButton);
        buttonGroup.add(storageRadioButton);
    }

    @Override
    public void initializeController() {
        new MainViewController(this);
    }
}
