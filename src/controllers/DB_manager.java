/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.stage.Modality;

/**
 *
 * @author ONYEKA
 */
public class DB_manager {
//     
    Connection con=null; 
    DB_manager(){}
    public Connection connect() throws ClassNotFoundException{
  
         
    try {
        //   jdbc:mysql://localhost:3306/project_manager?zeroDateTimeBehavior=convertToNull [root on Default schema]
        
        Class.forName("org.h2.Driver");
             
       
        
             con = (Connection)DriverManager.getConnection("jdbc:h2:~/intrusion1","test","test");
  System.out.println("connected successfull"); 
 /* 
          con = (Connection)DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2243538", "sql2243538","vZ6%dN2*");
  System.out.println("connected successfull"); 
 
      */ 
        //   String url = "jdbc:mysql://localhost/project_manager";
      } catch (SQLException ex) {
      System.out.println("connectedooo not"+ex.getMessage());
     System.out.println(" Server Timed Out OR UnReachable"); 
      
      
      }
        return con;
    
    
    }
    public void create_tables()  {
        
         try {
            Statement stmt=con.createStatement();
            stmt.execute("CREATE TABLE login1"
                    + "(username VARCHAR(2000),"
                    + "email VARCHAR(2000),"
                   + "password VARCHAR(20))"
                   
                  
                    
            );
            
                   System.out.println(" login created " ); 
        } catch (SQLException ex) {
                        System.out.println("login not created "+ex.getMessage());           }
                try {
            Statement stmt=con.createStatement();
            stmt.execute("CREATE TABLE paths"
                    + "(objectpath VARCHAR(2000),"
                    + "vedeopath VARCHAR(2000))"
                  
                    
            );
            String adu="ss"; String adp="ss";
            String  insert = "INSERT INTO paths VALUES('"+adu+"','"+adp+"')";
            
            stmt.execute( insert);
                    System.out.println("path   created");   
        } catch (SQLException ex) {
                          System.out.println("path not created");       }  
        
    
        
        
        
        
        
        
        
        
        try {
            Statement stmt=con.createStatement();
            stmt.execute("CREATE TABLE hacker11  "
                    + "(imagepath VARCHAR(500),"
                    + "date VARCHAR(20))"
                         
                 
            );   System.out.println("hacker   created");
        } catch (SQLException ex) {
         System.out.println("hacker not created "+ex.getMessage());           }
            
  
    }
    
    }
  
 