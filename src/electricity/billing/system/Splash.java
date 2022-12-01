
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable{
    Thread t;
    Splash(){
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));
        Image i2 = i1.getImage().getScaledInstance(730, 550, Image.SCALE_DEFAULT); // To scale the image.
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        
        setSize(730, 550);  // same size as image.
        setLocation(350, 100);
        
        t = new Thread(this);
        t.start();  // Calling run method of Runnable interface.
        
        setVisible(true);
        
    }
    
    @Override
    public void run(){
        try{
            Thread.sleep(7000);
            
            setVisible(false);
            new Login();  // go to Login class
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new Splash();
    }
}
