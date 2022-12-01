
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class MeterInfo extends JFrame implements ActionListener {
    
    JTextField tfName, tfAddress, tfCity, tfState, tfEmail, tfPhone;
    JButton submit;
    JLabel lblMeter;
    Choice meterLocation, meterType, phaseCode, billType;
    String meterNumber;
    
    MeterInfo(String meterNumber){
        
        this.meterNumber = meterNumber;
        
//        Frame size and location
        setSize(700, 500);
        setLocation(400, 200);
        
//        Adding panel in the frame
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        add(p);
        
//        Meter Info headind added to the panel
        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 10, 200, 25);
        heading.setFont(new Font("Tohoma", Font.PLAIN, 24));
        p.add(heading);
        
//        Adding Meter Num label
        JLabel lblname = new JLabel("Meter Number");
        lblname.setBounds(100, 80, 100, 20);
        p.add(lblname);
        
        JLabel lblMeterNum = new JLabel(meterNumber);
        lblMeterNum.setBounds(240, 80, 100, 20);
        p.add(lblMeterNum);
        
//        Adding Meter Location label
        JLabel lblMeterLocation = new JLabel("Meter Location");
        lblMeterLocation.setBounds(100, 120, 100, 20);
        p.add(lblMeterLocation);
        
//        For choice/drop down option for meter location
        meterLocation = new Choice();
        meterLocation.add("Outside");
        meterLocation.add("Inside");
        meterLocation.setBounds(240, 120, 200, 20);
        p.add(meterLocation);
        
//        Adding Meter Type label
        JLabel lblMeterType = new JLabel("Meter Type");
        lblMeterType.setBounds(100, 160, 100, 20);
        p.add(lblMeterType);
        
//        For choice/drop down for meter type
        meterType = new Choice();
        meterType.add("Electric Meter");
        meterType.add("Solar Meter");
        meterType.add("Smart Meter");
        meterType.setBounds(240, 160, 200, 20);
        p.add(meterType);
        
        
//        Adding phase code label
        JLabel lblPhaseCode = new JLabel("Phase Code");
        lblPhaseCode.setBounds(100, 200, 100, 20);
        p.add(lblPhaseCode);
        
//        For choice/drop down for phaseCode
        phaseCode = new Choice();
        phaseCode.add("011");
        phaseCode.add("022");
        phaseCode.add("033");
        phaseCode.add("044");
        phaseCode.add("055");
        phaseCode.add("066");
        phaseCode.add("077");
        phaseCode.add("088");
        phaseCode.add("099");
        phaseCode.setBounds(240, 200, 200, 20);
        p.add(phaseCode);
        
//        Adding Bill Type label
        JLabel lblBillType = new JLabel("Bill Type");
        lblBillType.setBounds(100, 240, 100, 20);
        p.add(lblBillType);
        
//        For choice/drop down for bill type
        billType = new Choice();
        billType.add("Normal");
        billType.add("Industrial");
        billType.setBounds(240, 240, 200, 20);
        p.add(billType);
        
//        Adding Days label
        JLabel lblDays = new JLabel("Days");
        lblDays.setBounds(100, 280, 100, 20);
        p.add(lblDays);
        
//        Adding 30Days label
        JLabel lbl30Days = new JLabel("30 Days");
        lbl30Days.setBounds(240, 280, 100, 20);
        p.add(lbl30Days);
        
//        Adding Note label
        JLabel lblNote = new JLabel("Note");
        lblNote.setBounds(100, 320, 100, 20);
        p.add(lblNote);
        
//        Adding Notes label
        JLabel lblNotes = new JLabel("By Default Bill is calculated for 30 days only");
        lblNotes.setBounds(240, 320, 500, 20);
        p.add(lblNotes);

        
//        Submit Button
        submit = new JButton("Submit");
        submit.setBounds(240, 390, 100, 25);
        submit.setBackground(Color.BLUE);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        p.add(submit);
        
        
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
        if(ae.getSource() == submit){
            String meter = meterNumber;
            String location = meterLocation.getSelectedItem();
            String type = meterType.getSelectedItem();
            String pCode = phaseCode.getSelectedItem();
            String typeBill = billType.getSelectedItem();
            String days = "30";
            
            String query = "insert into meter_info values('"+meter+"', '"+location+"', '"+type+"', '"+pCode+"', '"+typeBill+"', '"+days+"')";
            
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query);
                
//                To show pop up message
                JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");
                
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
        new MeterInfo("");
    }
}
