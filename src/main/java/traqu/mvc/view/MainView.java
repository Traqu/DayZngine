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

    @Override
    public void initializeController() {
        new MainViewController(this);
    }
}
