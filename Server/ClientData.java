
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.*;
import java.sql.PreparedStatement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HiTesh KuMar
 */
public class ClientData implements Serializable{
        
        int balance;
        int pin;
        String Name;
        int accNo;
        ClientData(int acc)
        {
            try{
                 Connection conn;
              ResultSet rs;
            
            conn  = javaconnect.ConnecrDb();
            PreparedStatement pat = conn.prepareStatement("select * from account where Acc=?");
            pat.setInt(1,acc);
            rs = pat.executeQuery();
            rs.next();
            this.accNo = acc;
            this.balance = rs.getInt("Balance");
            this.Name = rs.getString("Name");
            this.pin = rs.getInt("Pin");
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        String getbal()
        {
            String s = balance+"";
            return s;
        }
        String getName()
        {
            return Name;
        }
        String getPin()
        {    
            return Integer.toString(pin);
        }
//        public static void main(String args[])
//        {
//              ClientData mydata = new ClientData(679);
//              System.out.println(mydata.getbal());
//        }
        
}
