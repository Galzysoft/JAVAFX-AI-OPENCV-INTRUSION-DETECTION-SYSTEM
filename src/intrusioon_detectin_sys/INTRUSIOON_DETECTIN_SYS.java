/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intrusioon_detectin_sys;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXDecorator;
import controllers.DB_manager;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author EBUKA
 */
public class INTRUSIOON_DETECTIN_SYS extends Application {
          String    name; Connection con=null; 
    @Override
    public void start(Stage stage) throws Exception {
  
  try {
           con= connect();
            System.out.println("DB created");
        } catch (ClassNotFoundException ex) {
            System.out.println("DB not created");
        } 
        
      
        
 create_tables();
        System.out.println("tables  created");
        
        
        
             selectt_lastscore_name();
        if(name.equalsIgnoreCase("ss")){
                     // stage.setIconified(true);
                   Parent     root = FXMLLoader.load(getClass().getResource("/fxmls/paths.fxml"));
        
    
     stage .setMinWidth(647);stage .setMinHeight(298);
                        stage.setTitle("choose paths");
                     JFXDecorator decorator =new JFXDecorator(stage, root);
decorator.setCustomMaximize(true);

//decorator.setMinWidth(989);
//decorator.setMinHeight(1007);
// root.getStylesheets().add(BIZ_APP.class.getResource("mainoo.css").toExternalForm());

           
Scene  scene = new Scene(decorator, 647, 298);  
     
//    ?nnnnnnnnnnnnnnnnnnnnnnnnnnn
        
        
        
        stage.setScene(scene);
        stage.show();
               
               }else{ Parent root = FXMLLoader.load(getClass().getResource("/fxmls/LOGIN.fxml"));
        
       
        
        
        
     stage.setResizable(true);
  stage .setMinWidth(943);stage .setMinHeight(521);
                        stage.setTitle("C.B.N Intrusion Detection System");
                     JFXDecorator decorator =new JFXDecorator(stage, root);
 
 
Scene scene = new Scene(decorator, 943, 521);  

    
                  stage.setScene(scene);
             
              stage.centerOnScreen(); 
 
                    stage.show();
    
  }
               
                
 
        }   
             
    private void selectt_lastscore_name() throws SQLException   {
         Statement stmt=con.createStatement();
            String userr="ss";
//        nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn
  String sqll=("select * from paths where objectpath ='"+userr+"'" );   
       System.out.println("sqllllll");
        ResultSet rsst=stmt.executeQuery(sqll);
                         System.out.println("aa");
 if(rsst.next()){ 
         
         
           
                       name= rsst.getString("objectpath");
               System.out.println("name == "+name);  
               
               
               
               
               
               
               
               
             }  else { 
         
         
           
                       name= "o";
               System.out.println("name == "+name);  
               
               
               
               
               
               
               
               
             }  

        
        
        
        
        
        
        
        
        
        
   
        
        
        
        
        
    }
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
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
