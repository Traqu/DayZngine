package traqu.mvc.controller.controllerbase;

import traqu.mvc.view.viewbase.View;

public abstract class Controller<V extends View> implements IController<V> {
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