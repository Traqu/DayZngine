package traqu.mvc.controller;

import traqu.constant.PresetConstants;
import traqu.dayz.raidingtools.utils.CrackWorker;
import traqu.io.utils.presets.PresetHandler;
import traqu.mvc.controller.controllerbase.Controller;
import traqu.mvc.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;
import java.util.Objects;

import static traqu.time.utils.Constants.MILLISECOND;

public class MainViewController extends Controller<MainView> implements PresetConstants {

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

        int cyclesAmount;
        int cycleTime;

        if (isManualModeEnabled()) {
            try {
                cyclesAmount = Integer.parseInt(view.getCyclesAmountInput().getText());
                cycleTime = Integer.parseInt(view.getCycleTimeInput().getText());
                crackTheCodelock(cyclesAmount, cycleTime);
            } catch (NumberFormatException e) {
                System.err.println("Invalid input!");
                setActionLogTextFieldTextColor(Color.RED);
                updateActionLogTextField(LANGUAGE_MANAGER.getString("integerRequiredForm"));
                view.pack();
                Timer timer = new Timer(MILLISECOND * 2050, actionEvent -> {
                    bringActionLogToDefault();
                    view.pack();
                });
                timer.setRepeats(false);
                timer.start();
            }
        } else {
            runWithValuesForPresetByTargetType();
        }
    }

    private void runWithValuesForPresetByTargetType() {
        int cyclesAmount;
        double cycleTime; //here set to double, only because TDB server takes 15 seconds for a cycle. It is safe because can be expressed as 0.25 thus equal to 1/4 of a minute = 15 sec.
        String[] presetValuesArray = PresetHandler.getPresetValues(getSelectedPreset());
        String targetType = getChosenTarget();

        if (presetValuesArray != null) {
            if (targetType.equalsIgnoreCase("gate")) {
                cyclesAmount = Integer.parseInt(presetValuesArray[GATE_CYCLES_AMOUNT]);
                cycleTime = Integer.parseInt(presetValuesArray[GATE_CYCLE_TIME]);
                crackTheCodelock(cyclesAmount, cycleTime);
            } else if (targetType.equalsIgnoreCase("storage")) {
                cyclesAmount = Integer.parseInt(presetValuesArray[STORAGE_CYCLES_AMOUNT]);
                cycleTime = Double.parseDouble(presetValuesArray[STORAGE_CYCLE_TIME]);
                crackTheCodelock(cyclesAmount, cycleTime);
            }
        }
    }

    private void crackTheCodelock(int cyclesAmount, double cycleTime) {
        CrackWorker.crack((int) (cyclesAmount * 60 * cycleTime), this);
    }

    private void handleLanguageButton() {
        System.out.println("Language button clicked");
        //TODO example OR interface → JFrame changeFrame() //JFrame so you can popBack
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

    public String getChosenTarget() {
        ButtonGroup targetButtonGroup = view.getTargetButtonGroup();
        ButtonModel selection = targetButtonGroup.getSelection();

        for (Enumeration<AbstractButton> buttons = targetButtonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.getModel() == selection) {
                System.out.println("Target type: " + button.getText());
                return button.getText();
            }
        }
        return null;
    }

    public void restartProgressBar() {
        view.getCrackingProgressBar().setValue(0);
    }

    public void updateTimeLeft(String text) {
        JLabel timeLeft = view.getTimeLeft();
        if (!timeLeft.isVisible()) {
            timeLeft.setVisible(true);
        }
        timeLeft.setText(text);
        view.pack();
    }

    public void hideTimeLeft() {
        view.getTimeLeft().setVisible(false);
        view.pack();
    }

    public String getSelectedPreset() {
        return Objects.requireNonNull(view.getPresetsCombobox().getSelectedItem()).toString().replace(" ", "");
    }
}