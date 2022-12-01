
package electricity.billing.system;

import java.sql.*;

public class Conn {
    Connection c;
    Statement s;
    Conn(){
        try{
//            Creating the connection
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "root", "Jaibhole2402@#");
//            Creating the statement
            s = c.createStatement();
            
        }catch(Exception e){
            
            e.printStackTrace();
        }
    }
}
