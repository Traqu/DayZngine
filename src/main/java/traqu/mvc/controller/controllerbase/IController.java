package traqu.mvc.controller.controllerbase;

import traqu.mvc.view.viewbase.View;

public interface IController<V extends View> {
    void initialize();

    void bindActions();

    V getView();
}