
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CalculateBill extends JFrame implements ActionListener {
    
    JTextField tfName, tfAddress, tfUnit, tfState, tfEmail, tfPhone;
    JButton submit, cancel;
    JLabel labelName, labelAddress;
    Choice meterNumber, cMonth;
    
    CalculateBill(){
//        Frame size and location
        setSize(700, 500);
        setLocation(400, 150);
        
//        Adding panel in the frame
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        add(p);
        
//        New Customer headind added to the panel
        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(100, 10, 400, 25);
        heading.setFont(new Font("Tohoma", Font.PLAIN, 24));
        p.add(heading);
        
//        Adding Meter num label
        JLabel lblMeterNum = new JLabel("Meter Number");
        lblMeterNum.setBounds(100, 80, 100, 20);
        p.add(lblMeterNum);
        
//        Drop down/ choice to select meter num
        meterNumber = new Choice();
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");  // fetch data from customer table 
            while(rs.next()){
                meterNumber.add(rs.getString("meter_no"));  // adding meter num to the choice
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        meterNumber.setBounds(240, 80, 200, 120);
        p.add(meterNumber);
        
//        Adding Name label
        JLabel lblName = new JLabel("Name");
        lblName.setBounds(100, 120, 100, 20);
        p.add(lblName);
        
        labelName = new JLabel("");
        labelName.setBounds(240, 120, 100, 20);
        p.add(labelName);
        
//        Adding Address label
        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(100, 160, 100, 20);
        p.add(lblAddress);
        
//        Adding input field for Address
        labelAddress = new JLabel();
        labelAddress.setBounds(240, 160, 200, 20);
        p.add(labelAddress);
        

//        To change name and address after changing meter num
        meterNumber.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                //        To get name and address from database
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meterNumber.getSelectedItem()+"'");
                    while(rs.next()){
                        labelName.setText(rs.getString("name"));  // add name in labelName
                        labelAddress.setText(rs.getString("address")); // add address in labelAddress
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        
//        Adding unit consumed label
        JLabel lblUnitConsumed = new JLabel("Unit Consumed");
        lblUnitConsumed.setBounds(100, 200, 100, 20);
        p.add(lblUnitConsumed);
        
//        Adding input field for city
        tfUnit = new JTextField();
        tfUnit.setBounds(240, 200, 200, 20);
        p.add(tfUnit);
        
//        Adding Month label
        JLabel lblMonth = new JLabel("Month");
        lblMonth.setBounds(100, 240, 100, 20);
        p.add(lblMonth);
        
//        Adding choice/drop down for month
        cMonth = new Choice();
        cMonth.setBounds(240, 240, 200, 20);
        cMonth.add("January");
        cMonth.add("February");
        cMonth.add("March");
        cMonth.add("April");
        cMonth.add("May");
        cMonth.add("June");
        cMonth.add("July");
        cMonth.add("August");
        cMonth.add("September");
        cMonth.add("October");
        cMonth.add("November");
        cMonth.add("December");
        p.add(cMonth);
        
        
//        Submit Button
        submit = new JButton("Submit");
        submit.setBounds(120, 350, 100, 25);
        submit.setBackground(Color.BLUE);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        p.add(submit);
        
//        Cancel Button
        cancel = new JButton("Cancel");
        cancel.setBounds(250, 350, 100, 25);
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);
        
        setLayout(new BorderLayout()); // Setting border layout manually
        
        add(p, "Center");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");
        
        getContentPane().setBackground(Color.WHITE); // Background color of the panel
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == submit){
            String meter = meterNumber.getSelectedItem();
            String units = tfUnit.getText();
            String month = cMonth.getSelectedItem();
            
            int totalBill = 0;
            int unit_consumed = Integer.parseInt(units);
            
            String query = "select * from tax";
            
                    
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                
                while(rs.next()){
                    totalBill += unit_consumed * Integer.parseInt(rs.getString("cost_per_unit"));
                    totalBill += Integer.parseInt(rs.getString("meter_rent"));
                    totalBill += Integer.parseInt(rs.getString("service_charge"));
                    totalBill += Integer.parseInt(rs.getString("service_tax"));
                    totalBill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
                    totalBill += Integer.parseInt(rs.getString("fixed_tax"));
                }   
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
            String query2 = "insert into totalBill values('"+meter+"', '"+month+"', '"+units+"', '"+totalBill+"', 'Not Paid')";
            
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
                
                setVisible(false);
                
            }catch(Exception e){
                e.printStackTrace();
                
            }
            
        }
        else{  // If cancel button clicked
            setVisible(false);
        }
    }
    public static void main(String[] args){
        new CalculateBill();
    }
}
