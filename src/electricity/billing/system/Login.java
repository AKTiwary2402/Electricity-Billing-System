
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JButton login, signup, cancel;
    JTextField username, password;
    Choice loginIn;
    Login(){
        super("Login Page");
//        Backgrong Color of frame
        getContentPane().setBackground(Color.WHITE);
        
        setLayout(null); // To set layout of JLabel (manually), if not placeby swing automatically
        
//        JLable use to add context on the frame.
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(300, 20, 100, 20); // setBounds works only if setLayout is null.
        add(lblUsername);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(300, 60, 100, 20); 
        add(lblPassword);
        
        JLabel lblLoginInAs = new JLabel("Log In As");
        lblLoginInAs.setBounds(300, 100, 100, 20); 
        add(lblLoginInAs);
        
//        To add Input field in the frame
        username = new JTextField();
        username.setBounds(400, 20, 150, 20);
        add(username);
        
        password = new JTextField();
        password.setBounds(400, 60, 150, 20);
        add(password);
        
//        To add dropdown menu in the frame. 
        loginIn = new Choice();
        loginIn.add("Admin");
        loginIn.add("Customer");
        loginIn.setBounds(400, 100, 150, 20);
        add(loginIn);
        
//        To add Buttons in the frame.
        login = new JButton("Log In");
        login.setBounds(330, 160, 100, 20);
        add(login);
        login.addActionListener(this);  // to perform action on login button
        
        signup = new JButton("Sign Up");
        signup.setBounds(450, 160, 100, 20);
        add(signup);
        signup.addActionListener(this);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(380, 200, 100, 20);
        add(cancel);
        cancel.addActionListener(this);
        
//        Adding image to the frame.
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 250, 250);
        add(image);
        
        setSize(640, 300);
        setLocation(400, 200);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == login){
            String sUsername = username.getText();
            String sPassword = password.getText();
            String sUser = loginIn.getSelectedItem();
            
            try{
                Conn c = new Conn();
                String query = "select * from login where username = '"+sUsername+"' and password = '"+sPassword+"' and user = '"+sUser+"'";

//                To store the executed query data from mySQL, we use ResultSet class.
                ResultSet rs = c.s.executeQuery(query);
                
//                If we get query data
                if(rs.next()){
                    String meter = rs.getString("meter_no");
                    setVisible(false);
                    new Project(sUser, meter); // go to project class
                }else{ 
//                    If query does not match. Pop up invalid login
                    JOptionPane.showMessageDialog(null, "Invalid Login");
//                    After pop up.Set Text fied as empty.
                    username.setText("");
                    password.setText("");
                }
                
                
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
        else if(ae.getSource() == signup){
            setVisible(false);
            new SignUp();
        }
        else if(ae.getSource() == cancel){
            setVisible(false);
        }
    }
    public static void main(String[] args){
        new Login();
    }
}

