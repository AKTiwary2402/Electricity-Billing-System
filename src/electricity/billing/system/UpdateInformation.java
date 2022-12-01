
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class UpdateInformation extends JFrame implements ActionListener {
    
    String meter;
    JTextField tfAddress, tfState, tfCity, tfEmail, tfPhone;
    JButton update, cancel;
    JLabel name;
    
    UpdateInformation(String meter){
        this.meter = meter;
        setBounds(150, 150, 1050, 450);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        //        Update Customer Information heading label
        JLabel heading = new JLabel("UPDATE CUSTOMER INFORMATION");
        heading.setBounds(110, 0, 400, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);
        
//        Name label
        JLabel lblName = new JLabel("Name");
        lblName.setBounds(30, 70, 100, 20);
        add(lblName);
        
        name = new JLabel("");
        name.setBounds(230, 70, 200, 20);
        add(name);
        
//        Meter Number label
        JLabel lblMeterNumber = new JLabel("Meter Number");
        lblMeterNumber.setBounds(30, 110, 100, 20);
        add(lblMeterNumber);
        
        JLabel meterNumber = new JLabel("");
        meterNumber.setBounds(230, 110, 200, 20);
        add(meterNumber);
        
//        Address label
        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(30, 150, 100, 20);
        add(lblAddress);
        
        tfAddress = new JTextField();
        tfAddress.setBounds(230, 150, 200, 20);
        add(tfAddress);
        
//        City label
        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(30, 190, 100, 20);
        add(lblCity);
        
        tfCity = new JTextField();
        tfCity.setBounds(230, 190, 200, 20);
        add(tfCity);
        
//        State label
        JLabel lblState = new JLabel("State");
        lblState.setBounds(30, 230, 100, 20);
        add(lblState);
        
        tfState = new JTextField();
        tfState.setBounds(230, 230, 200, 20);
        add(tfState);
        
//        Email label
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(30, 270, 100, 20);
        add(lblEmail);
        
         tfEmail = new JTextField();
        tfEmail.setBounds(230, 270, 200, 20);
        add(tfEmail);
        
//        Phone label
        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(30, 310, 100, 20);
        add(lblPhone);
        
        tfPhone = new JTextField();
        tfPhone.setBounds(230, 310, 200, 20);
        add(tfPhone);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("Select * from customer where meter_no = '"+meter+"'");
            while(rs.next()){
                name.setText(rs.getString("name"));
                meterNumber.setText(rs.getString("meter_no"));
                tfAddress.setText(rs.getString("address"));
                tfCity.setText(rs.getString("city"));
                tfState.setText(rs.getString("state"));
                tfEmail.setText(rs.getString("email"));
                tfPhone.setText(rs.getString("phone"));
                
                
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
//        Update Button
        update = new JButton("Update");
        update.setBounds(70, 360, 100, 25);
        update.setBackground(Color.BLUE);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);
        
//        cancel Button
        cancel = new JButton("Cancel");
        cancel.setBounds(230, 360, 100, 25);
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);
        
//        Adding image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(550, 50, 400, 300);
        add(image);
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource() == update){
            String address = tfAddress.getText();
            String city = tfCity.getText();
            String state = tfState.getText();
            String email = tfEmail.getText();
            String phone = tfPhone.getText();
            
            try{
                Conn c = new Conn();
                c.s.execute("update customer set address = '"+address+"', city = '"+city+"', state = '"+state+"', email = '"+email+"', phone = '"+phone+"' where meter_no = '"+meter+"'");
                
                JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
                setVisible(false);
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new UpdateInformation("");
        
    }
}
