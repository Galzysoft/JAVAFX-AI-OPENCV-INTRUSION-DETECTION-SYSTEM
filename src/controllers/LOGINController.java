/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
/**
 *
 * @author EBUKA
 */
public class LOGINController implements Initializable {
        Connection con=null; Statement stmt=null;String user,pass11;int c=0;String faceai_xml="a",imagepath="a";String filew;
        int facee=0;
@FXML
    public JFXPasswordField retyupe1;
 @FXML
    private ImageView picture;
 @FXML
    private Text textyy;
  @FXML
    private Text te;
    @FXML
    public JFXTextField emai1;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private JFXPasswordField pass1;
  @FXML
    public JFXTextField username1;
    @FXML
    private JFXTextField username;
      @FXML
    private ImageView search;
@FXML
    private HBox threat;

private void audio (){
    final MediaPlayer oracleVid = new MediaPlayer( new Media(new File("C:\\Users/EBUKA\\Documents\\NetBeansProjects\\INTRUSIOON_DETECTIN_SYS\\sr\\aistuff\\ai.m4a").toURI().toString()));
  
//C:\Users\EBUKA\Documents\NetBeansProjects\INTRUSIOON_DETECTIN_SYS\src\aistuff\ai.m4a
// final MediaPlayer oracleVid = new MediaPlayer( new Media(" C:/Users/EBUKA/Documents/NetBeansProjects/GENDER_SCANNER/src/gender_scanner/image/videoplayback.mp4"));
  
    
//  mp.setMediaPlayer(oracleVid); 
    oracleVid.setMute(false);
    oracleVid.setRate(1);

    oracleVid.setCycleCount(MediaPlayer.INDEFINITE);

    oracleVid.play();}


 private void hackedcatched(){
      Calendar timer=Calendar.getInstance();
				 timer.getTime();
				 SimpleDateFormat tTime=	new SimpleDateFormat("HH:mm:ss");
				 tTime.format( timer.getTime());
				 SimpleDateFormat tDate=new SimpleDateFormat("yyyy");
				 tDate.format(timer.getTime());
				String datea =( tDate.format(timer.getTime()));
				String time =( tTime.format(timer.getTime()));
                String c=time+"_"+datea;
    
   String sql="INSERT INTO hacker11 (imagepath,date) VALUES (?,?) ";
   
          try { 
        PreparedStatement pst=con.prepareStatement(sql);
         pst.setString(1, filew);
          pst.setString(2,c);
      
        pst.executeUpdate();
              System.out.println("image captured saved sucessfuy");
      
    } catch (SQLException ex) {
   System.out.println("image captured not saved sucessfuy");
                   }
 
 
 }
  private void detect_cam() { 
            
       if(facee>0){System.out.println("camera already detected");}else{
        // here we  start upthe  camera
        VideoCapture camera= new VideoCapture(0);
        // here we declare the matcclasss to store the  camera capture into rhe matrix
        Mat cammat=new Mat();Timer timer=new Timer();
        if( !camera.isOpened()){System.out.println("Unable to start up the camera");}
        else{
       //let declare a Timer Task Class to schedule the timming display every 33m iliseconds
       TimerTask task=new TimerTask() {
           @Override
           public void run() {  
             //we read the camera input into the mat class
          camera.read(cammat);
      
         if( camera.read(cammat)){
             // LET PROCESS THE  image  in the frame  amd detect the uperbody
       // String file = "C:/opencv/build/etc/lbpcascades/lbpcascade_frontalface_improved.xml";
//   String file1 = "C:/opencv/build/etc/haarcascades/haarcascade_upperbody.xml";
//  String file = "C:/opencv/build/etc/haarcascades/haarcascade_frontalface_alt.xml";
    String file =faceai_xml;
  
//  C:\Users\EBUKA\Documents\NetBeansProjects\bodyrecognition\src\Resources\lbpcascades\lbpcascade_frontalface_improved.xml
// String file =  "aistuff/haarcascade_frontalface_alt.xml";
  
         CascadeClassifier classifier = new  CascadeClassifier(file);

         MatOfRect faceDetections = new MatOfRect();
         classifier.detectMultiScale(cammat, faceDetections);
         System.out.println(String.format("Detected %s facedetected",
            faceDetections.toArray().length));
         // Drawing boxes
         for (org.opencv.core.Rect rect : faceDetections.toArray()) 
         {
            Imgproc.rectangle(
               cammat,                                   //where to draw the box
               new Point(rect.x, rect.y),                            //bottom left
               new Point(rect.x + rect.width, rect.y + rect.height), //top right
               new Scalar(220, 11, 11),5                                 //RGB colour
            ); 
            
//            txtlength.setText("width= "+rect.width);
//            txtwidth.setText("Hieght= "+rect.height);
//            
            
          }
      if(facee>0){System.out.println("already captured");}
      else{
         if( faceDetections.toArray().length>0){
              BufferedImage image = new BufferedImage(cammat.width(), cammat.height(),
            BufferedImage.TYPE_3BYTE_BGR);
           timer   .cancel();
         WritableRaster raster = image.getRaster();
         DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
         byte[] data = dataBuffer.getData();
         cammat.get(0, 0, data);

          
           
         // Creating the Writable Image
     WritableImage    writableImage = SwingFXUtils.toFXImage(image, null);
//          picture.setImage(writableImage);
                  
            String gg= UUID.randomUUID().toString();
        System.out.println(c);
      
   // int c+=c;
    
//  String filew = imagepath+"\\hacked"+c.replace("\\","/")+".jpg";
 filew = imagepath+"\\hacked"+gg+".jpg".replace("\\","\\");
  
             System.out.println("ffffomega "+filew);

 

      // Instantiating the imagecodecs class
      Imgcodecs imageCodecs = new Imgcodecs();

      // Saving it again
      Imgcodecs.imwrite(filew, cammat);
        System.out.println("picture savaed");
        hackedcatched();
   facee+=1;
   te.setText("1");
            timer   .cancel();
            }}

            // Imgproc .cvtColor(cammat, cammat, 0);
           //  let declare a buffer  to enable us store our mat input
             MatOfByte byyyte=new MatOfByte();
             
               Imgcodecs.imencode(".png", cammat, byyyte);
                   Image tmagetoshow=new Image(new ByteArrayInputStream(byyyte.toArray()));
                   
                 
 
               
                  
                
      
                        Platform.runLater(() -> {
                            
                      picture.setImage(tmagetoshow);
                  
                        
                        
                        
                        });
         }      }
       };  timer.schedule(task, 0, 33);

       
        }
        
        }}
 
       @FXML
     private void signup(ActionEvent event) throws FileNotFoundException{

          if(username1.getText().length()==0||pass1.getText().length()==0||retyupe1.getText().length()==0||emai1.getText().length()==0 )
         
         { Alert loginerror1 = new Alert(Alert.AlertType.ERROR);
                loginerror1.setTitle("reg error");
                loginerror1.setHeaderText(null);
                loginerror1.setContentText( "One Or More Fields Are Empty");
                loginerror1.initModality(Modality.APPLICATION_MODAL);
                loginerror1.showAndWait();
        }
          else{search.setVisible(true);
           ScaleTransition transision=new ScaleTransition();
    textyy.setVisible(true);
        transision.setDuration(Duration.millis(300));
transision.setNode(textyy);
transision.setByX(0);
transision.setByY(1);
transision.setCycleCount(TranslateTransition.INDEFINITE);
transision.setAutoReverse(true);
transision.play();
            new SplashScreen().start();}}
     
     
     
     
     
     
     
     
  private void login() throws IOException{  //pattern to find if there is any special character in string
Pattern regex = Pattern.compile("[^A-Za-z0-9]");
//matcher to find if there is any special character in string
Matcher matcher = regex.matcher(username.getText());
Matcher matcher1 = regex.matcher(pass.getText());

if(matcher.find()||matcher1.find())
{
     
             ScaleTransition transision=new ScaleTransition();
             textyy.setVisible(false);
    threat.setVisible(true);
    
        transision.setDuration(Duration.millis(300));
transision.setNode(threat);
transision.setByX(1.0);
transision.setByY(1);

transision.setCycleCount(TranslateTransition.INDEFINITE);
transision.setAutoReverse(true);
transision.play();
detect_cam();}
else{
    try {
        stmt = con.createStatement();
             System.out.println("1");
        String sql="SELECT username,password FROM login1 where username='"+username.getText()+"' AND password='"+pass.getText()+"' ";
        ResultSet rst=stmt.executeQuery(sql);
         System.out.println("2");
        while(rst.next()){
            
             System.out.println("3");
            user=rst.getString(1);
             System.out.println("");
            pass11=rst.getString(2);
            
            
            
            System.out.println( "userr"+user);
            System.out.println( "passss"+pass11);
        }
        
        if (username.getText().equalsIgnoreCase(user)&&pass.getText().equalsIgnoreCase(pass11))
            
            
            
            
            
            
        {     Stage stage1 = (Stage) username.getScene().getWindow();
        
        Stage stage;
        
        //if(event.getSource()==btn){
        stage = new Stage();
        
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxmls/mainsys.fxml"));
        
         
        
        stage.setResizable(true);
        stage .setMinWidth(943);stage .setMinHeight(521);
        stage.setTitle("C.B.N Intrusion Detection System");
        JFXDecorator decorator =new JFXDecorator(stage, root);
        
        
        Scene scene = new Scene(decorator, 773, 504);
        
        
        stage.setScene(scene);
        
        stage.centerOnScreen();
        stage1.close();
        stage.show();
        
        
        }else{    textyy.setVisible(false);
            search.setVisible(false);Alert loginerror1 = new Alert(Alert.AlertType.ERROR);
        loginerror1.setTitle("login error");
        loginerror1.setHeaderText(null);
        loginerror1.setContentText( "wrong username and password");
        loginerror1.initModality(Modality.APPLICATION_MODAL);
        loginerror1.showAndWait();
        }
    } catch (SQLException ex) {    textyy.setVisible(false);         search.setVisible(false);
       Alert loginerror1 = new Alert(Alert.AlertType.ERROR);
                loginerror1.setTitle("login error");
                loginerror1.setHeaderText(null);
                loginerror1.setContentText( "errorbro  zz"+ex.getMessage());
                loginerror1.initModality(Modality.APPLICATION_MODAL);
                loginerror1.showAndWait();
                System.out.println( "errorbro  "+ex.getMessage());
    }
    }
               }
    @FXML
    private void gin(ActionEvent event) throws IOException   {search.setVisible(true);
    ScaleTransition transision=new ScaleTransition();
    textyy.setVisible(true);
        transision.setDuration(Duration.millis(300));
transision.setNode(textyy);
transision.setByX(0);
transision.setByY(1);
transision.setCycleCount(TranslateTransition.INDEFINITE);
transision.setAutoReverse(true);
transision.play();// 
        new SplashScreen1().start();
  }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

            // let load the  native opencv lubraary
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);    // TODO
   //System.loadLibrary(Core.); 
      textyy.setVisible(false);
            DB_manager db=new  DB_manager();
            try {
                con= db.connect();
                db.create_tables();
                System.out.println("connected");
            } catch (ClassNotFoundException ex) {
                System.out.println("table not connected");}
//            }
//                    audio();// TODO
            try {
                selectt_lastscore_name();
//
//            
//               try {
//
//            
//            stmt = con.createStatement();
//            
//            System.out.println("1");
//            String sql="SELECT username,password FROM login1 ";
//            ResultSet rst;
//            
//            rst = stmt.executeQuery(sql);
//            
//            System.out.println("2");
//            while(rst.next()){
//                
//                System.out.println("3");
//                user=rst.getString(1);
//                System.out.println("");
//                pass11=rst.getString(2);
//
//
//                
//                System.out.println( "userr "+user);
//                System.out.println( "passss "+pass11);
//            }
//
//
//
//
//            
//        } catch (SQLException ex) {
//                Logger.getLogger(LOGINController.class.getName()).log(Level.SEVERE, null, ex);
//        }    // TODO
//        
            } catch (SQLException ex) {
                Logger.getLogger(LOGINController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
        
    }    

    
    
//       new SplashScreen().start();
//  //new SplashScreen().st
//           System.out.println("student_project_management.Splash_screenController.initialize()");
//        
//          
//    
//  
//        
//    }    
      private void selectt_lastscore_name() throws SQLException   {
         Statement stmt=con.createStatement();
            String userr="ss";
//        nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn
  String sqll=("select * from paths" );   
       System.out.println("sqllllll");
        ResultSet rsst=stmt.executeQuery(sqll);
                         System.out.println("aa");
 if(rsst.next()){ 
         
         
           
                       imagepath= rsst.getString("objectpath");
                        faceai_xml= rsst.getString("vedeopath");
               System.out.println("imagepath == "+imagepath);  
                System.out.println("faceai_xml == "+faceai_xml);  
              
               
               
               
               
               
               
               
             }  else { 
         
         
           
                      
               
               
               
               
               
               
               
             }  

        
        
        
        
        
        
        
        
        
        
   
        
        
        
        
        
    }

           class SplashScreen extends Thread{
          @Override
          public void run(){
              try {
                  Thread.sleep(9000);
                  Platform.runLater(new Runnable() {
                      @Override
                      public void run() {

                  String hack_pattern= "[^A-Za-z0-9]";
        Pattern ppp=Pattern.compile(hack_pattern);
        Matcher m= ppp.matcher(username1.getText());
       
                  Matcher m2= ppp.matcher(pass1.getText());
           Matcher m3= ppp.matcher(retyupe1.getText());
        if(m.find()||m2.find()||m3.find())
        
        {
            
             ScaleTransition transision=new ScaleTransition();
               textyy.setVisible(false);
    threat.setVisible(true);
        transision.setDuration(Duration.millis(300));
transision.setNode(threat);
transision.setByX(1.0);
transision.setByY(1);
transision.setCycleCount(TranslateTransition.INDEFINITE);
transision.setAutoReverse(true);
transision.play();
detect_cam();
        Alert loginerror1 = new Alert(Alert.AlertType.ERROR);
                loginerror1.setTitle("Intrusion Warning");
                loginerror1.setHeaderText(null);
                loginerror1.setContentText( "Threat Detected !!!");
                loginerror1.initModality(Modality.APPLICATION_MODAL);
                loginerror1.showAndWait();
        
        
        
        
        }
              
        
        else
        {
                  //pattern to find if there is any special character in string
Pattern regex = Pattern.compile("[^A-Za-z0-9]");
//matcher to find if there is any special character in string
Matcher matcher = regex.matcher(username.getText());

if(matcher.find())
{
    System.out.println("errr"); 
//   hhhhhhhhhhhhhhhh
    detect_cam();
    textyy.setVisible(false);
     threat.setVisible(true);
         ScaleTransition transision=new ScaleTransition();
        transision.setDuration(Duration.millis(300));
transision.setNode(threat);
transision.setByX(1.0);
transision.setByY(1);
transision.setCycleCount(TranslateTransition.INDEFINITE);
transision.setAutoReverse(true);
transision.play();
   
 
    
    
//    fffffffffffffffffff
            search.setVisible(false);}
    
              
              if(pass1.getText().equalsIgnoreCase(retyupe1.getText())){
                    
       String reg_pattern= "^[a-zA-Z0-9]{1,20}@[a-zA-Z0-9]{1,20}.[a-zA-Z]{2,3}$";
        Pattern pppp=Pattern.compile(reg_pattern);
        Matcher mmm= pppp.matcher(emai1.getText());
        if(mmm.matches()){ 
         
        String sql="INSERT INTO login1 (username,email,password) VALUES (?,?,?) ";
   
          try { 
        PreparedStatement pst=con.prepareStatement(sql);
         pst.setString(1, username.getText());
          pst.setString(2, emai1.getText());
        pst.setString(3, pass1.getText()); 
        pst.executeUpdate();
        
                Alert loginerror1 = new Alert(Alert.AlertType.INFORMATION);
                loginerror1.setTitle("SignIn sucessfull");
                loginerror1.setHeaderText(null);
                loginerror1.setContentText("you just sucessfully signed up");
                loginerror1.initModality(Modality.APPLICATION_MODAL);
                loginerror1.showAndWait();
      
    } catch (SQLException ex) {
  search.setVisible(false);
                Alert loginerror1 = new Alert(Alert.AlertType.ERROR);
                loginerror1.setTitle("Signup Error");
                loginerror1.setHeaderText(null);
                loginerror1.setContentText( "unable to sign up"+ex.getMessage());
                loginerror1.initModality(Modality.APPLICATION_MODAL);
                loginerror1.showAndWait();
             }}else{ search.setVisible(false);
             Alert loginerror1 = new Alert(Alert.AlertType.ERROR);
                loginerror1.setTitle("WRONG EMAIL PATTERN");
                loginerror1.setHeaderText(null);
                loginerror1.setContentText(" ENTER THE CORRECT PATTERN OF EMAIL");
                loginerror1.initModality(Modality.APPLICATION_MODAL);
                loginerror1.showAndWait();
}
          } else{ search.setVisible(false);
                  Alert loginerror1 = new Alert(Alert.AlertType.ERROR);
                loginerror1.setTitle("Mismatched password error");
                loginerror1.setHeaderText(null);
                loginerror1.setContentText("Please use the same password in the password fields");
                loginerror1.initModality(Modality.APPLICATION_MODAL);
                loginerror1.showAndWait();}}
                      }
                  });
                  
              } catch (InterruptedException ex) {
                  }
    }
}







   class SplashScreen1 extends Thread{
          @Override
          public void run(){
              try {
                  Thread.sleep(9000);
                  Platform.runLater(() -> {
                      try {
                          login();
                      } catch (IOException ex) {
                          Logger.getLogger(LOGINController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      
                              });
                  
              } catch (InterruptedException ex) {
                  }
    }
}

              
}
