package traqu.utils.helpers;

import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

public class ActionListenerHelper {
    public static void addListener(ActionListener listener, Component component) {
        try {
            Method method = component.getClass().getMethod("addActionListener", ActionListener.class);
            method.invoke(component, listener);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Component does not support ActionListener: " + component.getClass());
        }
    }
}
