
package electricity.billing.system;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignUp extends JFrame implements ActionListener {
    JButton create, back;
    Choice accountType;
    JTextField meter, username, name, password;
    SignUp(){
//        Creating frame
        setBounds(450, 150, 700, 400);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        
//        Creating panel
        JPanel panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
//        Setting Create Account border line across panel
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), "Create Account", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(173, 216, 230)));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34, 139, 34));
        add(panel);
        
//        Add heading(create account as) in JPanel.
        JLabel heading = new JLabel("Create Account As");
        heading.setBounds(100, 50, 140, 20);
        panel.add(heading);
        
//        Adding drop down for create account as.
        accountType = new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(260, 50, 150, 20);
        panel.add(accountType);
        
//        Adding meter number option in JPanel
        JLabel lblMeter = new JLabel("Meter Number");
        lblMeter.setBounds(100, 90, 140, 20);
        lblMeter.setVisible(false);
        panel.add(lblMeter);
        
//        Adding username option in JPanel
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(100, 130, 140, 20);
        panel.add(lblUsername);
        
//        Adding name option in JPanel
        JLabel lblName = new JLabel("Name");
        lblName.setBounds(100, 170, 140, 20);
        panel.add(lblName);        
        
//        Adding password option in JPanel
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(100, 210, 140, 20);
        panel.add(lblPassword);
        
//        To add input field in the panel
        meter = new JTextField();
        meter.setBounds(260, 90,150, 20);
        meter.setVisible(false);
        panel.add(meter);
        
//        To add input field in the panel
        username = new JTextField();
        username.setBounds(260, 130,150, 20);
        panel.add(username);
        
//        To add input field in the panel
        name = new JTextField();
        name.setBounds(260, 170,150, 20);
        panel.add(name);
        
        meter.addFocusListener(new FocusListener(){
            @Override
           public void focusGained(FocusEvent fe){
               
           }
           public void focusLost(FocusEvent fe){
               try{
                   Conn c = new Conn();
                   ResultSet rs = c.s.executeQuery("select * from login where meter_no = '"+meter.getText()+"'");
                   
                   while(rs.next()){
                       name.setText(rs.getString("name")); // it return/set name if enter meter number for customer
                   }
                   
               }catch(Exception e){
                   e.printStackTrace();
               }
           }
            
        });
        
//        To add input field in the panel
        password = new JTextField();
        password.setBounds(260, 210,150, 20);
        panel.add(password);
        
        accountType.addItemListener(new ItemListener(){
            
            public void itemStateChanged(ItemEvent ae){
                String user = accountType.getSelectedItem();
                if(user.equals("Customer")){
                    lblMeter.setVisible(true); // Meter number(label) is visible for customer only
                    meter.setVisible(true); // text field for meter is visible
                    name.setEditable(false);  // name is not editable for customer
                }else{
                    lblMeter.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(true);
                }
            }
        });
        
//        Add button (create)
        create = new JButton("Create");
        create.setBounds(140, 260, 120, 25);
        panel.add(create);
        create.addActionListener(this); // to perform action on create button
        
//        Add button (back)
        back = new JButton("Back");
        back.setBounds(300, 260, 120, 25);
        panel.add(back);
        back.addActionListener(this); // to perform action at back button
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2 = i1.getImage().getScaledInstance(250,250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(415, 30, 250, 250);
        panel.add(image);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == create){
            String atype = accountType.getSelectedItem();
            String susername = username.getText();
            String sname = name.getText();
            String spassword = password.getText();
            String smeter = meter.getText();
            
            try{
                Conn c = new Conn();
                
                String query = null;
                
                if(atype.equals("Admin")){
                    query = "insert into login values('"+smeter+"', '"+susername+"', '"+sname+"', '"+spassword+"', '"+atype+"')";
                }
                else{
                    query = "update login set username = '"+susername+"', password = '"+spassword+"', user = '"+atype+"' where meter_no = '"+smeter+"'";
                }
//                Executing mySQL queries.
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                
                setVisible(false);
                new Login(); // Go to the Login class.
                
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else if(ae.getSource() == back){
            setVisible(false);
            new Login();
        }
    }
    public static void main(String[] args){
        new SignUp();
    }
}
