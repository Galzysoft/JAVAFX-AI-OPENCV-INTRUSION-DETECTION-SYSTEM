/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author EBUKA
 */
public class MainsysController implements Initializable {    private ObservableList<USERS> data;Connection con=null;
 private ObservableList<paths> data1;
@FXML
    private ImageView hackimage;

    @FXML
    private TableColumn<paths, String> CIMAGE;

    @FXML
    private TableColumn<paths, String> CHACK;

    @FXML
    private JFXTextField hackttime;

    @FXML
    private JFXTextField emai11;

    @FXML
    private TableColumn<USERS, String> Cusername;
    @FXML
    private TableView<USERS> typeuser;
    @FXML
    private JFXTextField username11;
 @FXML
    private TableView<paths> typehack;
    @FXML
    private TableColumn<USERS, String> cmai;
    /**
     * Initializes the controller class.
     */
      
     private void refreshtable()  {
         
           try{
    
        data=FXCollections.observableArrayList();
        ResultSet rs=con.createStatement().executeQuery("select * from login1");
               while (rs.next()){
                  data.add(new USERS(rs.getString(1),rs.getString(2)));
        } }catch(SQLException se){
//         JFXSnackbar bar=new JFXSnackbar(bbww);
//bar.getStylesheets().add(UsersController.class.getResource("fail.css").toExternalForm());
//                bar.show("No records  found with the name "+SEARCH.getText(), 5000);
//   
        
        }
//           public bookers(String MATCH,String VIP,String REGULAR,String MATCH_DATE,String FROM,String TO) {
//  DRIVERSNAME,String DEPATURE,String DESTINATION,String DATE) {
              
        Cusername.setCellValueFactory(new PropertyValueFactory<>("NAME"));
        cmai.setCellValueFactory(new PropertyValueFactory<>("EMAIL" ) );
           typeuser.setItems(null);
     typeuser.setItems(data);
      
         
         
         
         
         
         
         
   
   try{
    
        data1=FXCollections.observableArrayList();
        ResultSet rs=con.createStatement().executeQuery("select * from hacker11");
               while (rs.next()){
                  data1.add(new paths(rs.getString(1),rs.getString(2)));
        } }catch(SQLException se){
//         JFXSnackbar bar=new JFXSnackbar(bbww);
//bar.getStylesheets().add(UsersController.class.getResource("fail.css").toExternalForm());
//                bar.show("No records  found with the name "+SEARCH.getText(), 5000);
//   
        
        }
//           public bookers(String MATCH,String VIP,String REGULAR,String MATCH_DATE,String FROM,String TO) {
//  
        CIMAGE.setCellValueFactory(new PropertyValueFactory<>("PATH"));
        CHACK.setCellValueFactory(new PropertyValueFactory<>("DATE" ) );
                   
         typehack.setItems(null);
     typehack.setItems(data1);
      
       
   
   }
      
      
  
    
    
        @FXML
    private void selecteduser( MouseEvent event)     {


  TableView.TableViewSelectionModel<USERS> tvSelContact = 
                typeuser.getSelectionModel() 
          ;
  
    tvSelContact.selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
        
        
          public void changed(ObservableValue<? extends Number> changed, 
                    Number oldVal, Number newVal) {
                int index = (int)newVal;
                username11.setText(data.get(index).getNAME());
//                        matchh.getSelectionModel().select(data.get(index).getname()); 
                 emai11.setText(data.get(index).getEMAIL());
                  
                
                 
                 
                 
                }});
                
                
               }
  
       @FXML
    private void selectedhack( MouseEvent event)     {


  TableView.TableViewSelectionModel<paths> tvSelContact = 
                typehack.getSelectionModel() 
          ;
  
    tvSelContact.selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
        
        
          public void changed(ObservableValue<? extends Number> changed, 
                    Number oldVal, Number newVal) {
                int index = (int)newVal;
           
//                        matchh.getSelectionModel().select(data.get(index).getname()); 
                 hackttime.setText(data1.get(index).getDATE());
                  
                 
                 
                 File ff=new File(data1.get(index).getpath());
      try {
            BufferedImage bufferedImage = ImageIO.read(ff);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            hackimage.setImage(image);
        } catch (IOException ex) {
            }
                
                 
                 
                 
                }});
                
                
               }
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DB_manager db=new  DB_manager();
            try {
                con= db.connect();
                db.create_tables();
                System.out.println("connected");
            } catch (ClassNotFoundException ex) {
                System.out.println("table not connected");
            } 
            refreshtable();


// TODO
    }    
    
}
