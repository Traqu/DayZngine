package traqu.mvc.controller.controllerbase;

import traqu.language.LanguageManager;
import traqu.mvc.view.viewbase.View;

public abstract class Controller<V extends View> implements IController<V> {

    protected static final LanguageManager LANGUAGE_MANAGER = LanguageManager.getInstance();
    protected V view;

    public Controller(V view) {
        this.view = view;
        initialize();
    }

    @Override
    public void initialize() {
        bindActions();
    }

    @Override
    public V getView() {
        return view;
    }
}