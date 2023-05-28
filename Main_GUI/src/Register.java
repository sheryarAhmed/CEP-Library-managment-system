import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class Register {
    public static void window() {
        GUI win = new GUI("Register Account", 800, 800, null);

        PanelGui panel1 = new PanelGui(null, 360, 800);
        panel1.setBounds(0, 0, 800, 160);
        Widgets wd1 = new Widgets();
        wd1.addImage(panel1.getPanel(),"images/loginBg.jpeg", 0, -400, 800, 600);
        win.addPanel(panel1.getPanel());

        PanelGui panel2 = new PanelGui(new FlowLayout(), 800, 140);
        panel2.setBounds(0, 160, 800, 160);
        wd1.addImage(panel2.getPanel(),"images/logo.png", 0, 0, 220, 160);
        win.addPanel(panel2.getPanel());

    
        PanelGui panel3 = new PanelGui(new GridLayout(11,0), 800, 336);
        panel3.setBounds(160, 320, 480, 336);
        wd1.setLabel(panel3.getPanel(),"Full Name", 0, 0, 0, 0);
        wd1.setTextField(panel3.getPanel(),0, 0, 0, 0);
        wd1.setLabel(panel3.getPanel(),"Email", 0, 0, 0, 0);
        wd1.setTextField(panel3.getPanel(),0, 0, 0, 0);
        wd1.setLabel(panel3.getPanel(),"Username", 0, 0, 0, 0);
        wd1.setTextField(panel3.getPanel(),0, 0, 0, 0);
        wd1.setLabel(panel3.getPanel(),"Password", 0, 0, 0, 0);
        wd1.setPasswordField(panel3.getPanel(),0, 0, 0, 0);
        wd1.setLabel(panel3.getPanel(),"Confirm password", 0, 0, 0, 0);
        wd1.setPasswordField(panel3.getPanel(),0, 0, 0, 0);
        win.addPanel(panel3.getPanel());

        PanelGui panel4 = new PanelGui(new FlowLayout(), 800, 144);
        panel4.setBounds(0, 656, 800, 144);
        wd1.setButton(panel4.getPanel(),"Register", 0, 0, 0, 0);
        win.addPanel(panel4.getPanel());
       wd1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            String username = wd1.getTextField(1).getText();
            String email = wd1.getTextField(2).getText();
            String fullName = wd1.getTextField(3).getText();
            String password = new String(wd1.getPasswordField(1).getPassword());
            String cPassword = new String(wd1.getPasswordField(2).getPassword());

            if(password.equals(cPassword)){
                JOptionPane.showMessageDialog(panel4.getPanel(), username+"/n"+fullName);
            
            }else{
                JOptionPane.showMessageDialog(panel4.getPanel(),email);
            }
        }
       });
        
        win.run();
        
        }
}
