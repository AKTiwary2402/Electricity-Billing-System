
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class DepositDetails extends JFrame implements ActionListener {
    
    Choice meterNumber, cMonth;
    JTable table;
    JButton search, print;
    
    DepositDetails(){
        super("Deposit Details");
        
        setSize(700, 700);
        setLocation(400, 100);
        
        setLayout(null);
        
        getContentPane().setBackground(Color.WHITE);
        
//        Search By Meter Number label
        JLabel lblMeterNumber = new JLabel("Search by Meter Number");
        lblMeterNumber.setBounds(20, 20, 150, 20);
        add(lblMeterNumber);
        
//        Adding choice in search by meter number
        meterNumber = new Choice();
        meterNumber.setBounds(180, 20, 150, 20);
        add(meterNumber);
        
//        Adding meter number from mysql database
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()){
                meterNumber.add(rs.getString("meter_no"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
//        Search By Month label
        JLabel lblMonth = new JLabel("Search by Month");
        lblMonth.setBounds(400, 20, 100, 20);
        add(lblMonth);
        
//        Adding choice in search by meter number
        cMonth = new Choice();
        cMonth.setBounds(520, 20, 150, 20);
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
        
//        Adding table
        table = new JTable();
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from totalBill");  // Getting Bill details from database
            
            table.setModel(DbUtils.resultSetToTableModel(rs));  // adding query to table
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
//        Adding scroll bar
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 100, 700, 600);
        add(sp);
        
//        Adding search button
        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);
        
//        Adding print button
        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == search){
            String query = "select * from totalBill where meter_no = '"+meterNumber.getSelectedItem()+"' and month = '"+cMonth.getSelectedItem()+"'";
            
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{ // if print button is clicked
            try{
                table.print(); // to print the table
                
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        new DepositDetails();
    }
}
