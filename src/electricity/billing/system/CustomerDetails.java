
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class CustomerDetails extends JFrame implements ActionListener {
    
    Choice meterNumber, cMonth;
    JTable table;
    JButton search, print;
    
    CustomerDetails(){
        super("Customer Details");
        
        setSize(1200, 600);
        setLocation(90, 70);
        
        
//        Adding table
        table = new JTable();
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");  // Getting Bill details from database
            
            table.setModel(DbUtils.resultSetToTableModel(rs));  // adding query to table
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
//        Adding scroll bar
        JScrollPane sp = new JScrollPane(table);
        add(sp);
        
        
//        Adding print button
        print = new JButton("Print");
        print.addActionListener(this);
        add(print, "South");
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        
        try{
            table.print(); // to print the table
                
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        new CustomerDetails();
    }
}
