
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class PayBill extends JFrame implements ActionListener {
    
    Choice cMonth;
    JButton pay, back;
    String meter;
    
    PayBill(String meter){
        
        this.meter = meter;
        setLayout(null);
        setBounds(250, 100, 900, 600);
        
//        Electricity Bill heading label
        JLabel heading = new JLabel("Electricity Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(120, 5, 400, 30);
        add(heading);
        
//        Meter Number label
        JLabel lblMeterNumber = new JLabel("Meter Number");
        lblMeterNumber.setBounds(35, 80, 200, 20);
        add(lblMeterNumber);
        
        JLabel meterNumber = new JLabel("");
        meterNumber.setBounds(300, 80, 200, 20);
        add(meterNumber);
        
//        Name label
        JLabel lblName = new JLabel("Name");
        lblName.setBounds(35, 140, 200, 20);
        add(lblName);
        
        JLabel name = new JLabel("");
        name.setBounds(300, 140, 200, 20);
        add(name);
        
//        Month label
        JLabel lblMonth = new JLabel("Month");
        lblMonth.setBounds(35, 200, 200, 20);
        add(lblMonth);
        
//        Drop down/choice for month
        cMonth = new Choice();
        cMonth.setBounds(300, 200, 200, 20);
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
        add(cMonth);
        
//        Units label
        JLabel lblUnits = new JLabel("Units");
        lblUnits.setBounds(35, 260, 200, 20);
        add(lblUnits);
        
        JLabel units = new JLabel("");
        units.setBounds(300, 260, 200, 20);
        add(units);
        
//        Total Bill label
        JLabel lblTotalBill = new JLabel("Total Bill");
        lblTotalBill.setBounds(35, 320, 200, 20);
        add(lblTotalBill);
        
        JLabel totalBill = new JLabel("");
        totalBill.setBounds(300, 320, 200, 20);
        add(totalBill);
        
//        Status label
        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(35, 380, 200, 20);
        add(lblStatus);
        
        JLabel status = new JLabel("");
        status.setBounds(300, 380, 200, 20);
        status.setForeground(Color.RED);
        add(status);
        
//        To set name and meter number form database
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
            
            while(rs.next()){
                meterNumber.setText(meter);
                name.setText(rs.getString("name"));
            }
            
            rs = c.s.executeQuery("select * from totalBill where meter_no = '"+meter+"' and month = '"+cMonth.getSelectedItem()+"'");
            
            while(rs.next()){
                units.setText(rs.getString("units"));
                totalBill.setText(rs.getString("totalbill"));
                status.setText(rs.getString("status"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
//        To set units total bill and status accordingly to month
        cMonth.addItemListener(new ItemListener(){
            
            public void itemStateChanged(ItemEvent ae){
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from totalBill where meter_no = '"+meter+"' and month = '"+cMonth.getSelectedItem()+"'");

                    while(rs.next()){
                        units.setText(rs.getString("units"));
                        totalBill.setText(rs.getString("totalbill"));
                        status.setText(rs.getString("status"));
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        
//        Pay button
        pay = new JButton("Pay");
        pay.setBackground(Color.BLUE);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100, 460, 100, 25);
        pay.addActionListener(this);
        add(pay);
        
//        Back button
        back = new JButton("Back");
        back.setBounds(230, 460, 100, 25);
        back.addActionListener(this);
        add(back);
        
//        Background color white
        getContentPane().setBackground(Color.WHITE);
        
//        Adding image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 120, 600, 300);
        add(image);
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == pay){
            try{
                Conn c = new Conn();
                c.s.executeUpdate("update totalBill set status = 'Paid' where meter_no = '"+meter+"' and month = '"+cMonth.getSelectedItem()+"'");
            }catch(Exception e){
                e.printStackTrace();
            }
            setVisible(false);
            new Paytm(meter);  // new class
        }
        else{ // if back button clicked
            setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new PayBill("");
    }
    
}
