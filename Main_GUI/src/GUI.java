import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI {
    static JFrame frame;
    public GUI (String windowName,int width,int height,LayoutManager layout){
        frame = new JFrame(windowName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(layout);
        frame.setSize(width, height);
        frame.setResizable(false);
        
    }
    public void addPanel(JPanel panel){
        frame.add(panel);
    }
    public void run(){
        frame.setVisible(true);
        

    }
}
class PanelGui {
    static JPanel panel;
    public PanelGui(LayoutManager layout, int width ,int height){
        panel = new JPanel(layout);
        panel.setPreferredSize(new Dimension(width, height));
        
    }
    public void setBounds(int x,int y,int width,int height){
        panel.setBounds(x, y, width, height);
        
    }
    public void addWidgets(Component widgets){
        panel.add(widgets);
    }
    
    public void setBackground(Color color) {
        panel.setBackground(color);
    }

    public JPanel getPanel() {
        return panel;
    }
}
class Widgets{
    private int sizeLabel = 0;int sizeField = 0;int sizePass=0;int sizeBtn =0;
    private JLabel[] label = new JLabel[10];
    private JTextField[] field = new JTextField[5];
    private JPasswordField[] passwordField = new JPasswordField[5];
    private JButton[] button = new JButton[5];
    private Font font = new Font("Roboto",Font.CENTER_BASELINE,14);
    
    

    public void setLabel(JPanel panel, String name, int x, int y, int width, int height) {
        label[sizeLabel] = new JLabel();
        label[sizeLabel].setText(name);
        label[sizeLabel].setBounds(x, y, width, height);
        label[sizeLabel].setFont(font);
        panel.add(label[sizeLabel]);
        sizeLabel++;
    }

    public void setSubLabel(JPanel panel, String name, int x, int y, int width, int height) {
        font = new Font("Roboto", Font.CENTER_BASELINE, 12);
        setLabel(panel, name, x, y, width, height);
    }

    public void setTextField(JPanel panel, int x, int y, int width, int height) {
        field[sizeField] = new JTextField();
        field[sizeField].setBounds(x, y, width, height);
        panel.add(field[sizeField]);
        sizeField++;
    }

    public void setPasswordField(JPanel panel, int x, int y, int width, int height) {
        passwordField[sizePass] = new JPasswordField();
        passwordField[sizePass].setBounds(x, y, width, height);
        panel.add(passwordField[sizePass]);
        sizePass++;
    }

    public void setButton(JPanel panel, String name, int x, int y, int width, int height) {
        button[sizeBtn] = new JButton(name);
        button[sizeBtn].setBackground(Color.WHITE);
        button[sizeBtn].setForeground(Color.BLACK);
        button[sizeBtn].setFont(font);
        button[sizeBtn].setBounds(x, y, width, height);
        panel.add(button[sizeBtn]);
        sizeBtn++;
    }

    public void setHeading(){
        font = new Font("Roboto",Font.BOLD,24);
    }
    public void setTextStyle(){
        font = new Font("Roboto",Font.CENTER_BASELINE,14);

    }
    public void addImage(JPanel panel ,String pathname,int x ,int y , int width, int height){
        File file = new File(pathname);
    
        try{
            BufferedImage image = ImageIO.read(file);
            JLabel pic = new JLabel();
            pic.setIcon(new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
            pic.setBounds(x, y, width, height);
            panel.add(pic);
            
        }catch(Exception e){
            setLabel(panel,"image not found", x, y, width, height);
        }
    }
    
    public void addActionListener(ActionListener listener) {
        button[sizeBtn-1].addActionListener(listener);
    }

    public JTextField getTextField(int num) {
        return field[num-1];
    }

    public JPasswordField getPasswordField(int num) {
        return passwordField[num-1];
    }

    
}
