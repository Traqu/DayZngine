package traqu.mvc.view.viewbase;

import traqu.mvc.controller.LanguageViewController;

import javax.swing.*;

public class LanguageView extends View {
    private JPanel mainPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public LanguageView() {
        initializeView(mainPanel);
    }

    @Override
    public void initializeController() {
        new LanguageViewController(this);
    }
}
