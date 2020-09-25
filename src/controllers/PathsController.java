 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import com.jfoenix.controls.JFXDecorator;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javax.imageio.ImageIO;
import com.jfoenix.controls.JFXTextField;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author EBUKA
 */
public class PathsController implements Initializable {
   Connection con=null; String    filepath , filepath1;File file=null;
    /**
     * Initializes the controller class.
     */
    
        @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField name1;
         
        
        @FXML
     private void exitt(ActionEvent event)  {
         name     .getScene().getWindow().hide();
     } 
        
          @FXML
    void update(ActionEvent event) throws FileNotFoundException, IOException {
    
        
 String sql="update paths set objectpath =?,vedeopath =?";
   
          try { 
              
        PreparedStatement pst=con.prepareStatement(sql);
         pst.setString(1, name.getText());
          pst.setString(2, name1.getText());
        
        pst.executeUpdate();
        
                Alert loginerror1 = new Alert(Alert.AlertType.INFORMATION);
                loginerror1.setTitle("paths sucessfull");
                loginerror1.setHeaderText(null);
                loginerror1.setContentText("Saved sucessfully ");
                loginerror1.initModality(Modality.APPLICATION_MODAL);
                loginerror1.showAndWait();
          
        Stage stage =new Stage();
        
        
     Parent root = FXMLLoader.load(getClass().getResource("/fxmls/LOGIN.fxml"));
        
       
        
        
        
     stage.setResizable(true);
  stage .setMinWidth(943);stage .setMinHeight(521);
                        stage.setTitle("C.B.N Intrusion Detection System");
                     JFXDecorator decorator =new JFXDecorator(stage, root);
 
 
Scene scene = new Scene(decorator, 943, 521);  

    
                  stage.setScene(scene);
             
              stage.centerOnScreen(); 
 
                    stage.show();
        
        
          name     .getScene().getWindow().hide();
    } catch (SQLException ex) {
  
                Alert loginerror1 = new Alert(Alert.AlertType.ERROR);
                loginerror1.setTitle("Update Error");
                loginerror1.setHeaderText(null);
                loginerror1.setContentText("unable to save"+ex.getMessage());
                loginerror1.initModality(Modality.APPLICATION_MODAL);
                loginerror1.showAndWait();
             }
    }
    
     @FXML
    void clear(ActionEvent event) {
name.setText(null);
    name1.setText(null);
 
               
}
    
    
    
    
@FXML
    private void uploadclick(ActionEvent event){
        
          DirectoryChooser dir=new DirectoryChooser();
  Stage  stage=(Stage)name.getScene().getWindow();
 File file = dir.showDialog(stage);
  if(file!=null){
      
                       filepath=file.getAbsolutePath();
                        name.setText(filepath);
  }
      
    }
        
    
@FXML
    private void uploadclickvp(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        
            FileChooser.ExtensionFilter extFilterJPG = new  FileChooser.ExtensionFilter("xml files (*.xml)","*.xml");
               fileChooser.getExtensionFilters().addAll(extFilterJPG);
          file = fileChooser.showOpenDialog(null);
    filepath1  =  file.getAbsolutePath();
    name1.setText(filepath1);
     
    }
   

private void selectuser() throws IOException {
    try {
        Statement stmt=(Statement)con.createStatement();
   String r="ball";
 String sql="SELECT * from paths";
        ResultSet rs=stmt.executeQuery(sql);
           while ( rs.next()) { 
           String a=rs.getString( "objectpath");
              String b=rs.getString( "vedeopath");
             
                    name.setText(b);
                 name1.setText(a);
                 
                 
                   
           }
   } catch (SQLException ex) {
     Alert loginerror1 = new Alert(Alert.AlertType.ERROR);
                loginerror1.setTitle("paths info  Error");
                loginerror1.setHeaderText(null);
                loginerror1.setContentText("unable to get path info");
                loginerror1.initModality(Modality.APPLICATION_MODAL);
                loginerror1.showAndWait();
            }
 }
  @FXML
     private void exitt(MouseEvent event)  {
         name     .getScene().getWindow().hide();
     }
        
        
        
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    DB_manager db=new  DB_manager();
        try {
           con= db.connect();
            System.out.println("DB created");
        } catch (ClassNotFoundException ex) {
            System.out.println("DB not created");
        } 
       try {
           selectuser();     // TODO
       } catch (IOException ex) {
           Logger.getLogger(PathsController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }    

}
