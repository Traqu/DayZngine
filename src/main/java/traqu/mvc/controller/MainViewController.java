package traqu.mvc.controller;

import traqu.mvc.controller.controllerbase.Controller;
import traqu.mvc.view.MainView;

import java.util.Objects;



public class MainViewController extends Controller<MainView> {
    public MainViewController(MainView view) {
        super(view);
    }

    @Override
    public void bindActions() {
        view.getCrackButton().addActionListener(e -> handleCrackButton());

        view.getLanguageButton().addActionListener(e -> handleLanguageButton());

        view.getButton1().addActionListener(e -> handleButton1());

        view.getPresetsButton().addActionListener(e -> handlePresetsButton());

        view.getPresetsCombobox().addActionListener(e -> handlePresetsCombobox());
    }

    private void handleCrackButton() {
        String selectedPreset = Objects.requireNonNull(view.getPresetsCombobox().getSelectedItem()).toString();
        System.out.println(selectedPreset);
    }

    private void handleLanguageButton() {
        System.out.println("Language button clicked");
        //TODO example
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
}