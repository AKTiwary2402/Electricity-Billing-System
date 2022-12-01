
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class NewCustomer extends JFrame implements ActionListener {
    
    JTextField tfName, tfAddress, tfCity, tfState, tfEmail, tfPhone;
    JButton next, cancel;
    JLabel lblMeter;
    
    NewCustomer(){
//        Frame size and location
        setSize(700, 500);
        setLocation(400, 150);
        
//        Adding panel in the frame
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        add(p);
        
//        New Customer headind added to the panel
        JLabel heading = new JLabel("New Customer");
        heading.setBounds(180, 10, 200, 25);
        heading.setFont(new Font("Tohoma", Font.PLAIN, 24));
        p.add(heading);
        
//        Adding Customer name label
        JLabel lblname = new JLabel("Customer Name");
        lblname.setBounds(100, 80, 100, 20);
        p.add(lblname);
        
//        Adding input field for customer name
        tfName = new JTextField();
        tfName.setBounds(240, 80, 200, 20);
        p.add(tfName);
        
//        Adding Meter Number label
        JLabel lblMeterNo = new JLabel("Meter Number");
        lblMeterNo.setBounds(100, 120, 100, 20);
        p.add(lblMeterNo);
        
//        To generate random meter number
        lblMeter = new JLabel("");
        lblMeter.setBounds(240, 120, 100, 20);
        p.add(lblMeter);
        
        Random ran = new Random();
        long num = ran.nextLong() % 1000000; // use modulo to get 6 digit num
        lblMeter.setText("" + Math.abs(num)); // absolute for not getting -ve num
        
//        Adding Address label
        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(100, 160, 100, 20);
        p.add(lblAddress);
        
//        Adding input field for Address
        tfAddress = new JTextField();
        tfAddress.setBounds(240, 160, 200, 20);
        p.add(tfAddress);
        
//        Adding city label
        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(100, 200, 100, 20);
        p.add(lblCity);
        
//        Adding input field for city
        tfCity = new JTextField();
        tfCity.setBounds(240, 200, 200, 20);
        p.add(tfCity);
        
//        Adding State label
        JLabel lblState = new JLabel("State");
        lblState.setBounds(100, 240, 100, 20);
        p.add(lblState);
        
//        Adding input field for State
        tfState = new JTextField();
        tfState.setBounds(240, 240, 200, 20);
        p.add(tfState);
        
//        Adding Email label
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(100, 280, 100, 20);
        p.add(lblEmail);
        
//        Adding input field for Email
        tfEmail = new JTextField();
        tfEmail.setBounds(240, 280, 200, 20);
        p.add(tfEmail);
        
//        Adding Phone label
        JLabel lblPhone = new JLabel("Phone Number");
        lblPhone.setBounds(100, 320, 100, 20);
        p.add(lblPhone);
        
//        Adding input field for Phone
        tfPhone = new JTextField();
        tfPhone.setBounds(240, 320, 200, 20);
        p.add(tfPhone);
        
//        Next Button
        next = new JButton("Next");
        next.setBounds(120, 390, 100, 25);
        next.setBackground(Color.BLUE);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);
        
//        Cancel Button
        cancel = new JButton("Cancel");
        cancel.setBounds(250, 390, 100, 25);
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);
        
        setLayout(new BorderLayout()); // Setting border layout manually
        
        add(p, "Center");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");
        
        getContentPane().setBackground(Color.WHITE); // Background color of the panel
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == next){
            String name = tfName.getText();
            String meter = lblMeter.getText();
            String address = tfAddress.getText();
            String city = tfCity.getText();
            String state = tfState.getText();
            String email = tfEmail.getText();
            String phone = tfPhone.getText();
            
            String query1 = "insert into customer values('"+name+"', '"+meter+"', '"+address+"', '"+city+"', '"+state+"', '"+email+"', '"+phone+"')";
            String query2 = "insert into login values('"+meter+"', '', '"+name+"', '', '')";  // This query to store the random generated meter num into login table.
            
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                
//                To show pop up message
                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                
                setVisible(false);
                
//                new frame
                new MeterInfo(meter);
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
        else{  // If cancel button clicked
            setVisible(false);
        }
    }
    public static void main(String[] args){
        new NewCustomer();
    }
}
