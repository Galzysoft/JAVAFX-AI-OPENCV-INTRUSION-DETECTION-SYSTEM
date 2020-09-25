/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author EBUKA
 */
public class paths {
    
 private final StringProperty PATH;
  
 private final StringProperty DATE;
  
 
    public paths(String PATH,String DATE) {
              this.PATH=new SimpleStringProperty(PATH);
                this.DATE=new SimpleStringProperty(DATE);
             }
 
   
 public String getpath() {
        return PATH.get();
    }
  
  public String getDATE() {
        return DATE.get();
    }
  
  
    
     public void setpath(String fName) {
       PATH.set(fName);
    } 
        public void setDATE(String fName) {
       DATE.set(fName);
    } 
       
      public StringProperty PATHProperty()
    {return PATH;}
       public StringProperty DATEProperty()
    {return DATE;}
      
       
    
}
