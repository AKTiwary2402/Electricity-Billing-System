
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener {
    
    String sUser, meter;
    
    Project(String sUser, String meter){
        this.sUser = sUser;
        this.meter = meter;
//        Maximize the size and location of the frame.
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
//        Adding image and scaling the image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        
//        Adding menubar in the frame
        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);
        
//        Adding menu to the menubar
        JMenu master = new JMenu("Master");
        master.setForeground(Color.RED);
        
        
//        Adding menu item in the master menu
        JMenuItem newCustomer = new JMenuItem("New Customer");
        newCustomer.setFont(new Font("monospaced", Font.PLAIN, 12));
        newCustomer.setBackground(Color.WHITE);
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon1.png"));
        Image image1 = icon1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        newCustomer.setIcon(new ImageIcon(image1));
        newCustomer.setMnemonic('N');
        newCustomer.addActionListener(this);
        newCustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        master.add(newCustomer);
        
//        Adding menu item in the master menu
        JMenuItem customerDetails = new JMenuItem("Customer Details");
        customerDetails.setFont(new Font("monospaced", Font.PLAIN, 12));
        customerDetails.setBackground(Color.WHITE);
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("icon/icon2.png"));
        Image image2 = icon2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        customerDetails.setIcon(new ImageIcon(image2));
        customerDetails.setMnemonic('C');
        customerDetails.addActionListener(this);
        customerDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        master.add(customerDetails);
        
//        Adding menu item in the master menu
        JMenuItem depositDetails = new JMenuItem("Deposit Details");
        depositDetails.setFont(new Font("monospaced", Font.PLAIN, 12));
        depositDetails.setBackground(Color.WHITE);
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("icon/icon3.png"));
        Image image3 = icon3.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        depositDetails.setIcon(new ImageIcon(image3));
        depositDetails.setMnemonic('D');
        depositDetails.addActionListener(this);
        depositDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        master.add(depositDetails);
        
//        Adding menu item in the master menu
        JMenuItem calculateBill = new JMenuItem("Calculate Bill");
        calculateBill.setFont(new Font("monospaced", Font.PLAIN, 12));
        calculateBill.setBackground(Color.WHITE);
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image image4 = icon4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculateBill.setIcon(new ImageIcon(image4));
        calculateBill.setMnemonic('B');
        calculateBill.addActionListener(this);
        calculateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        master.add(calculateBill);
        
//        Adding menu to the menubar
        JMenu info = new JMenu("Information");
        info.setForeground(Color.BLUE);
        
        
//        Adding menu item in the info menu
        JMenuItem updateInfo = new JMenuItem("Update Information");
        updateInfo.setFont(new Font("monospaced", Font.PLAIN, 12));
        updateInfo.setBackground(Color.WHITE);
        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("icon/icon5.png"));
        Image image5 = icon5.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        updateInfo.setIcon(new ImageIcon(image5));
        updateInfo.setMnemonic('U');
        updateInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        updateInfo.addActionListener(this);
        info.add(updateInfo);
        
//        Adding menu item in the info menu
        JMenuItem viewInfo = new JMenuItem("View Information");
        viewInfo.setFont(new Font("monospaced", Font.PLAIN, 12));
        viewInfo.setBackground(Color.WHITE);
        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        Image image6 = icon6.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        viewInfo.setIcon(new ImageIcon(image6));
        viewInfo.setMnemonic('V');
        viewInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        viewInfo.addActionListener(this);
        info.add(viewInfo);
        
//        Adding menu to the menubar
        JMenu user = new JMenu("User");
        user.setForeground(Color.gray);
        
        
//        Adding menu item in the user menu
        JMenuItem payBill = new JMenuItem("Pay Bill");
        payBill.setFont(new Font("monospaced", Font.PLAIN, 12));
        payBill.setBackground(Color.WHITE);
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("icon/icon7.png"));
        Image image7 = icon5.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        payBill.setIcon(new ImageIcon(image7));
        payBill.setMnemonic('P');
        payBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        payBill.addActionListener(this);
        user.add(payBill);
        
//        Adding menu item in the user menu
        JMenuItem billDetails = new JMenuItem("Bill Details");
        billDetails.setFont(new Font("monospaced", Font.PLAIN, 12));
        billDetails.setBackground(Color.WHITE);
        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("icon/icon8.png"));
        Image image8 = icon8.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        billDetails.setIcon(new ImageIcon(image8));
        billDetails.setMnemonic('I');
        billDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        billDetails.addActionListener(this);
        user.add(billDetails);
        
//        Adding menu to the menubar
        JMenu report = new JMenu("Report");
        report.setForeground(Color.RED);
        
        
//        Adding menu item in the report menu
        JMenuItem generateBill = new JMenuItem("Generate Bill");
        generateBill.setFont(new Font("monospaced", Font.PLAIN, 12));
        generateBill.setBackground(Color.WHITE);
        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("icon/icon9.png"));
        Image image9 = icon9.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        generateBill.setIcon(new ImageIcon(image9));
        generateBill.setMnemonic('G');
        generateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        generateBill.addActionListener(this);
        report.add(generateBill);
        
//        Adding menu to the menubar
        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.BLUE);
        
        
//        Adding menu item in the utility menu
        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setFont(new Font("monospaced", Font.PLAIN, 12));
        notepad.setBackground(Color.WHITE);
        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("icon/icon7.png"));
        Image image10 = icon10.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(image10));
        notepad.setMnemonic('T');
        notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        notepad.addActionListener(this);
        utility.add(notepad);
        
//        Adding menu item in the utility menu
        JMenuItem calculator = new JMenuItem("Calculator");
        calculator.setFont(new Font("monospaced", Font.PLAIN, 12));
        calculator.setBackground(Color.WHITE);
        ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("icon/icon9.png"));
        Image image11 = icon11.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculator.setIcon(new ImageIcon(image11));
        calculator.setMnemonic('A');
        calculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        calculator.addActionListener(this);
        utility.add(calculator);
        
//        Adding menu to the menubar
        JMenu exit = new JMenu("Exit");
        exit.setForeground(Color.RED);
        
        
//        Adding menu item in the exit menu
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(new Font("monospaced", Font.PLAIN, 12));
        exitItem.setBackground(Color.WHITE);
        ImageIcon icon12 = new ImageIcon(ClassLoader.getSystemResource("icon/icon11.png"));
        Image image12 = icon12.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        exitItem.setIcon(new ImageIcon(image12));
        exitItem.setMnemonic('W');
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        exitItem.addActionListener(this);
        exit.add(exitItem);
        
        
        if(sUser.equals("Admin")){ // master is visible for admin only
            mb.add(master);
        }
        else{ // Customer 
            mb.add(info);
            mb.add(user);
            mb.add(report);
        }
        mb.add(utility); // common(for both)
        mb.add(exit);
        
        
        setLayout(new FlowLayout());
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        String msg = ae.getActionCommand();
        if(msg.equals("New Customer")){
            new NewCustomer();
        }else if(msg.equals("Customer Details")){
            new CustomerDetails();
        }else if(msg.equals("Deposit Details")){
            new DepositDetails();
        }else if(msg.equals("Calculate Bill")){
            new CalculateBill();
        }else if(msg.equals("View Information")){
            new ViewInformation(meter);
        }else if(msg.equals("Update Information")){
            new UpdateInformation(meter);
        }else if(msg.equals("Bill Details")){
            new BillDetails(meter);
        }else if(msg.equals("Notepad")){
            try{
                
                Runtime.getRuntime().exec("notepad.exe");
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(msg.equals("Calculator")){
            try{
                
                Runtime.getRuntime().exec("Calc.exe");
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(msg.equals("Exit")){
            setVisible(false);
            new Login();
        }else if(msg.equals("Pay Bill")){
            new PayBill(meter);
        }else if(msg.equals("Generate Bill")){
            new GenerateBill(meter);
        }
 
    }
    public static void main(String[] args){
        new Project("", "");
    }
}
