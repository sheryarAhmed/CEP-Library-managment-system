import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Login {
    private static String username;
    private static String password;
    public static void window(){
        GUI win = new GUI("Account Sign", 800, 800, null);
        PanelGui panel1 = new PanelGui(null, 360, 800);
        panel1.setBounds(0, 0, 360, 800);
        
        Widgets wd1 = new Widgets();
        wd1.addImage(panel1.getPanel(),"images/loginBg.jpeg", 0, 0, 800, 800);
        win.addPanel(panel1.getPanel());
        
        PanelGui panel2 = new PanelGui(null, 800, 800);
        panel2.setBounds(360, 0, 480, 800);
        wd1.addImage(panel2.getPanel(),"images/logo.png", 120, 0, 220, 140);
        wd1.setHeading();
        wd1.setLabel(panel2.getPanel(),"Account Sign", 120, 220, 200, 50);
        wd1.setTextStyle();
        wd1.setLabel(panel2.getPanel(),"Username Or Email", 50, 320, 150, 50);
        wd1.setTextField(panel2.getPanel(),50, 370, 250, 30);
        wd1.setLabel(panel2.getPanel(),"Password", 50, 400, 100,50);
        wd1.setPasswordField(panel2.getPanel(),50, 450, 250, 30);
        wd1.setSubLabel(panel2.getPanel(),"Register Now", 50, 480, 100, 50);
        wd1.setButton(panel2.getPanel(),"Sign", 75, 530, 200, 30);
        wd1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                username = wd1.getTextField(1).getText();
                password = new String(wd1.getPasswordField(1).getPassword());
                JOptionPane.showMessageDialog(panel1.getPanel(),username);

            }
        });


        win.addPanel(panel2.getPanel());
        win.run();
        
            
        }
    public static String getUsername(){
        return username;
    }

    public static String getUserPassword(){
        return password;
    }

    
}
