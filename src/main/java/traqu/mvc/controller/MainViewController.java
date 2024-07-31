package traqu.mvc.controller;

import traqu.dayz.raidingtools.CrackWorker;
import traqu.mvc.controller.controllerbase.Controller;
import traqu.mvc.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static traqu.constant.Constants.SECOND;

public class MainViewController extends Controller<MainView> {

    private final Color DETAULT_FOREGROUND_COLOR;

    public MainViewController(MainView view) {
        super(view);
        DETAULT_FOREGROUND_COLOR = view.getActionLogTextField().getForeground();
    }

    @Override
    public void bindActions() {
        view.getCrackButton().addActionListener(e -> handleCrackButton());

        view.getLanguageButton().addActionListener(e -> handleLanguageButton());

        view.getButton1().addActionListener(e -> handleButton1());

        view.getPresetsButton().addActionListener(e -> handlePresetsButton());

        view.getPresetsCombobox().addActionListener(e -> handlePresetsCombobox());

        view.getUseManualValuesCheckBox().addActionListener(e -> handleUseManualValuesCheckBox());
    }

    private void handleUseManualValuesCheckBox() {
        if (!view.getUseManualValuesCheckBox().isSelected()) { //looks stupid but works...
            enableManualSelection();
        } else {
            disableManualSelection();
        }
    }

    private void handleCrackButton() {
        String selectedPreset = Objects.requireNonNull(view.getPresetsCombobox().getSelectedItem()).toString();
        System.out.println(selectedPreset);

        int cyclesAmount;
        int cycleTime;

        if (isManualModeEnabled()) {
            try {
                cyclesAmount = Integer.parseInt(view.getCyclesAmountInput().getText());
                cycleTime = Integer.parseInt(view.getCycleTimeInput().getText());
                CrackWorker.crack(cyclesAmount, cycleTime, this);
            } catch (NumberFormatException e) {
                System.err.println("Invalid input!");
                    setActionLogTextFieldTextColor(Color.RED);
                    updateActionLogTextField(LANGUAGE_MANAGER.getString("integerRequiredForm"));
                    view.pack();
                Timer timer = new Timer(SECOND * 2, actionEvent -> {
                    bringActionLogToDefault();
                    view.pack();
                });
                timer.setRepeats(false);
                timer.start();
            }
        } else { //TODO fetch from presets through JComboBox...
            cyclesAmount = 0;
            cycleTime = 0;
            CrackWorker.crack(cyclesAmount, cycleTime, this);
        }
    }

    private void handleLanguageButton() {
        System.out.println("Language button clicked");
        //TODO example
        // view.dispose();
        // SwingUtilities.invokeLater(LanguageView::new);

        view.getButton1().setText(LANGUAGE_MANAGER.getString("settings"));
        view.pack();
    }

    private void handleButton1() {
        System.out.println("Button1 clicked");
    }

    private void handlePresetsButton() {
        System.out.println("Presets button clicked");
    }

    private void handlePresetsCombobox() {
        String selectedPreset = Objects.requireNonNull(view.getPresetsCombobox().getSelectedItem()).toString();
        System.out.println("Selected preset: " + selectedPreset);
    }

    public void updateProgress(int value) {
        view.getCrackingProgressBar().setValue(value);
        view.repaint();
    }

    public void updateProgressMaximum(int max) {
        view.getCrackingProgressBar().setMaximum(max);
    }

    public void updateActionLogTextField(String text) {
        view.getActionLogTextField().setText(text);
    }

    public void setActionLogTextFieldTextColor(Color color) {
        view.getActionLogTextField().setDisabledTextColor(color);
    }

    public void restoreActionLogTextFieldTextColor() {
        view.getActionLogTextField().setDisabledTextColor(DETAULT_FOREGROUND_COLOR);
    }

    private void bringActionLogToDefault() {
        restoreActionLogTextFieldTextColor();
        clearActionLogText();
    }

    public void crackingComplete() {
        view.getCrackingProgressBar().setValue(view.getCrackingProgressBar().getMaximum());
        view.repaint();
        System.out.println("Cracking complete!");
    }

    public void clearActionLogText() {
        view.getActionLogTextField().setText("");
    }

    public void disableCrackingButton() {
        view.getCrackButton().setEnabled(false);
    }

    public void enableCrackingButton() {
        view.getCrackButton().setEnabled(true);
    }

    public void enableManualSelection() {
        view.getCyclesAmountInput().setEnabled(false);
        view.getCycleTimeInput().setEnabled(false);
    }

    public void disableManualSelection() {
        view.getCyclesAmountInput().setEnabled(true);
        view.getCycleTimeInput().setEnabled(true);
    }

    public boolean isManualModeEnabled() {
        return view.getUseManualValuesCheckBox().isSelected();
    }

    private static void handleManualMode(MainView view) {
        if (view.getUseManualValuesCheckBox().isSelected()) {
            System.out.println("Manual mode enabled");
        } else {
            System.out.println("Manual mode disabled");
        }
    }
}