/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HiTesh KuMar
 */
import java.net.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.net.*;
import java.io.*;
import java.sql.PreparedStatement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import java.io.*;

public class javaNetworking extends Thread {
    
//    Connection conn = javaconnect.ConnecrDb();
//    ResultSet rs;
//    Statement pat;
    ClientData clientObj;
    Socket st;
    int acc;
//    static DataOutputStream dout;
    
    javaNetworking(Socket sc) {
        st = sc;
    }
    
    public void run() {
        try{
            
//            System.out.println("balance at server side : "+clientObj.getbal());
//            System.out.println("1");
            InputStream is = st.getInputStream();
//            System.out.println("2");
            DataInputStream dis = new DataInputStream(is);
//            System.out.println("3");
            acc = dis.readInt();
//            System.out.println("4");
            clientObj = new ClientData(acc);
//            System.out.println("5");
            System.out.println("from client "+acc);
//            System.out.println("6");
            ObjectOutputStream oos = new ObjectOutputStream(st.getOutputStream());
////            System.out.println("7");
            oos.writeObject(clientObj);
////            System.out.println("8");
            oos.flush();
//            System.out.println("9");
            boolean isvalid = false;
            while(true)
            {
               if(dis.readBoolean())
              {
                  isvalid = true;
                  DataOutputStream dos1  = new DataOutputStream(st.getOutputStream());
                  dos1.writeBoolean(isvalid);
//                  System.out.println("10");
                  Connection conn = javaconnect.ConnecrDb();
                  Statement pat = conn.createStatement();
//                  System.out.println("11");
                  int sendacc = Integer.parseInt(dis.readUTF());
//                  System.out.println("12");
                  System.out.println("sendacc : "+sendacc);
//                  System.out.println("13");
//                  pat.setInt(1, sendacc );
                  String strr = "select Balance from account where Acc="+sendacc+"";
//                  System.out.println("14");
                  ResultSet rs = pat.executeQuery(strr);
//                  System.out.println("15");
                  rs.next();
//                  System.out.println("16");
                  int initial = rs.getInt("Balance");
//                  System.out.println("17");
                  System.out.println("initial : "+initial);      
//                  System.out.println("18");//
                  int amount = Integer.parseInt(dis.readUTF());
//                  System.out.println("19");
                  System.out.println("amount : "+amount);
//                  System.out.println("20");
                  if(amount < Integer.parseInt( clientObj.getbal()))
                  {
                      System.out.println("inside if");
                      int updated = initial+amount;
                        System.out.println("updated : "+updated);
                        pat.close();
                        String sql = "update account set Balance="+updated+" where Acc="+sendacc+"";
                        Statement stm = conn.createStatement();
                        stm.execute(sql);
                        stm.close();
                        int reduce = Integer.parseInt(clientObj.getbal())-(amount);
                        System.out.println("reduce : "+reduce);
                        String str1 = "update account set Balance="+reduce+" where Acc="+acc+"";
                        Statement stm1 = conn.createStatement();
                        stm1.execute(str1);
                  }

              }
            }
//            pat = conn.createStatement();
//            rs = pat.executeQuery("select Balance from account where Acc=679");
//            rs.next();
//            int bal = rs.getInt("Balance");
//            System.out.println(bal);
//          
//       PrintStream ps = new PrintStream(st.getOutputStream());
//       BufferedReader br  = new BufferedReader(new InputStreamReader(rs.getInt("Balance")));
        }
        catch(Exception e){System.out.println(e);};
    }


    public static void main(String args[]) throws Exception {
        ServerSocket ss = new ServerSocket(2000);   
        javaNetworking jn;
        MyPage m = new MyPage();
//        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setVisible(true);
        int count = 0;
        do {
            Socket soc = ss.accept();
            count++;
            System.out.println("client connected" + count);
            jn = new javaNetworking(soc);
            jn.start();
        } while (true);

    }
    }
    
//    public static void main(String[] args){
//        javaNetworking jn;
//        MyPage m = new MyPage();
//        m.setVisible(true);
//        try{
//            ServerSocket ss = new ServerSocket(2000);
//            System.out.println("Waiting for request...");
//            
//            Socket s = ss.accept();
//            System.out.println("Request Accepted");
//            
//        }catch(Exception e){}
    

