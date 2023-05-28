import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ChatBot {
    public static void main(String[] args) {
        // Create the GUI window
        GUI gui = new GUI("Register Account", 400, 300, new FlowLayout());
        
        // Create the panel
        PanelGui panel = new PanelGui(null, 380, 220);
        panel.setBounds(10, 10, 380, 220);
        panel.setBackground(Color.white);
        
        // Create the widgets
        Widgets widgets = new Widgets();
        widgets.setLabel(panel.getPanel(), "Full Name:", 10, 10, 100, 20);
        widgets.setTextField(panel.getPanel(), 120, 10, 200, 20);
        
        widgets.setLabel(panel.getPanel(), "Email:", 10, 40, 100, 20);
        widgets.setTextField(panel.getPanel(), 120, 40, 200, 20);
        
        widgets.setLabel(panel.getPanel(), "Username:", 10, 70, 100, 20);
        widgets.setTextField(panel.getPanel(), 120, 70, 200, 20);
        
        widgets.setLabel(panel.getPanel(), "Password:", 10, 100, 100, 20);
        widgets.setPasswordField(panel.getPanel(), 120, 100, 200, 20);
        
        widgets.setLabel(panel.getPanel(), "Confirm Password:", 10, 130, 100, 20);
        widgets.setPasswordField(panel.getPanel(), 120, 130, 200, 20);
        
        widgets.setButton(panel.getPanel(), "Register", 150, 160, 80, 30);
        widgets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform registration logic here
                String fullName = widgets.getTextField(1).getText();
                String email = widgets.getTextField(2).getText();
                String username = widgets.getTextField(3).getText();
                String password = new String(widgets.getPasswordField(1).getPassword());
                String confirmPassword = new String(widgets.getPasswordField(2).getPassword());
                
                // Perform validation and registration process
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(panel.getPanel(), "Passwords do not match");
                } else {
                    // Registration logic
                    // ...
                    JOptionPane.showMessageDialog(panel.getPanel(), fullName+email+username+password+confirmPassword);
                }
            }
        });
        
        // Add the panel to the GUI
        gui.addPanel(panel.getPanel());
        
        // Run the GUI
        gui.run();
    }
}
