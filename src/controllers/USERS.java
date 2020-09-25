/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author EBUKA
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author EBUKA
 */
public class USERS {
     private final StringProperty NAME;
  
 private final StringProperty EMAIL; 
  
  
 
  
    public USERS(String NAME,String EMAIL) {
              
                this.NAME=new SimpleStringProperty(NAME);
                 this.EMAIL=new SimpleStringProperty(EMAIL);
                    
                      
                
             }
 
   
    public String getNAME() {
        return NAME.get();
    }
  
  public String getEMAIL() {
        return EMAIL.get();
    } 
   
       public StringProperty NAMEProperty()
    {return NAME;}
       public StringProperty EMAILProperty()
    {return EMAIL;}
       
      
         
    
}
