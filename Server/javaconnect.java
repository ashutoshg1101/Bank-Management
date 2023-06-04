/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HiTesh KuMar
 */
import java.sql.*;
import javax.swing.JOptionPane;
public class javaconnect {
    Connection conn ;
    
    public static Connection ConnecrDb(){
        try{
            String user = "root";
            String password = "7974626401";
            String url = "jdbc:mysql://localhost:3306/banks";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
       return null;
    }
    
}
