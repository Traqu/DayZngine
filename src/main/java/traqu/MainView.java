package traqu;

import javax.swing.*;
import java.util.ResourceBundle;

public class MainView extends JFrame {
    private JPanel panel1;
    private JTextField exampleTextField;
    private JButton button1;
    private JTextField kurwamaćTextField;

    public MainView() {
        add(panel1);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("language");
        kurwamaćTextField.setText("kurwa");
        kurwamaćTextField.setText(resourceBundle.getString("YEAH!"));
        panel1.add(exampleTextField);
        exampleTextField.setText(resourceBundle.getString("example"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
}
