package pss;
import java.beans.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.text.DateFormat;
import java.util.*;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ali SLAYMAN
 */
public class Flow implements Serializable {
private String name;   
private String type;
private int id;
//private boolean isOut;
private boolean isIn;
private boolean isOut;
ArrayList<String> Specifications;
 private String spec;

public Flow() {
    System.out.println("Flow created as default");
    name="None";
    type= "No type";
    id=-1;
    //isOut = false;
    isIn=false;
    isOut=false;
    Specifications= new ArrayList<String>();
    spec="None";
}
public Flow (String name, String type, boolean isIn,boolean isOut, ArrayList<String> Specifications, int id) {
    this.type= type;
    this.name= name;
    this.id=id;
    //this.isOut=isOut;
    this.isIn=isIn;
    this.isOut=isOut;
    this.Specifications= Specifications;
}


   public String getSpec() {
       return this.spec;
   }
   public void setSpec(String spec) {
       this.spec=spec;
   }
   
   public void clearSpec() { 
       this.spec=null;
   }
   

public void setType(String type) {
   this.type=type;
}     
public void setName(String name) {
   this.name=name;
}     
public void setId(int id) {
    this.id=id;
}
/*public void setFlow_isOut(boolean isOut) {
   this.isOut=isOut;
}      */
public void setIsIn(boolean isIn) {
   this.isIn=isIn;
}   
public void setIsOut(boolean isOut) {
   this.isOut=isOut;
}   
public void setSpecifications(ArrayList<String> Specifications) {
   this.Specifications=Specifications;
}   
        
public String getName() {
   return this.name;
} 

public String getType() {
   return this.type;
} 
public int getId() {
    return this.id;
}
/*public boolean getFlow_isOut() {
   return this.isOut;
}      */ 
public boolean getIsIn() {
   return this.isIn;
}   
public boolean getIsOut() {
   return this.isOut;
}   
public ArrayList<String> getSpecifications() {
   return this.Specifications;
}   



}
