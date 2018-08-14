/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pss;
import java.beans.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.text.DateFormat;
import java.util.*;
import java.io.Serializable;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class Steplist implements Serializable {
    ArrayList<Step> stepList;
    
    public Steplist() {
     this.stepList= new ArrayList<pss.Step>();
 }
 public void setStepList(ArrayList<pss.Step> stepList) {
     this.stepList=stepList;
 }   
 public ArrayList<pss.Step> getStepList() {
     return this.stepList;
 }
 
 public boolean found(int id) {
     boolean foundd=false;
    for(pss.Step item : this.stepList) {
        if(item.getIdStep()== id)
        {foundd=true; break; }
            }
    return foundd;
 }
 
 public pss.Step search(int id) {
    pss.Step f = new pss.Step();
    for(pss.Step item : this.stepList) {
        if(item.getIdStep()== id)
        {f=item; break; }
            }
    return f;
}
  public void save(String url) { 
    
        try { 
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            for(pss.Step item : this.stepList) {
                ArrayList<Integer> StepNextIdList = new ArrayList<Integer>();
                ArrayList<Integer> functionIdList = new ArrayList<Integer>();
                for(pss.Step element : item.getStepNext()) {
                    if(element.getIdStep()!= -1) StepNextIdList.add(element.getIdStep());
                }
                
                Array stepNext = conn.createArrayOf("integer", StepNextIdList.toArray());
                for(pss.Function element : item.getFunctionList()) {
                    if(element.getIdFunction()!= -1) functionIdList.add(element.getIdFunction());
                }
                Array functionList = conn.createArrayOf("integer", functionIdList.toArray());
                String Name = item.getName();
                String requirement = item.getRequirement();
                String stakeholder = item.getStakeholder();
                int Id= item.getIdStep();
                String sql = "INSERT INTO steps VALUES (? , ?, ?, ?, ?, ?)"; //insert 
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(2, Name);
                pstmt.setInt(1, Id);
                pstmt.setString(3, requirement);
                pstmt.setString(4, stakeholder);
                pstmt.setArray(5, stepNext);
                pstmt.setArray(6, functionList);
                pstmt.executeUpdate();
            }    
            
        }
               
            
         catch (SQLException ex) { 
            Logger.getLogger(ex.getMessage());
        }
        
}
  
  
 public int repeted(pss.Step f) {
     int i=0;
     for(pss.Step item : this.stepList) {
         if(f.getIdStep()==item.getIdStep() && f.getName().equals(item.getName()))
             i++;
     }
     
     return i;
 }

 public void clean() {
     for(pss.Step item : this.stepList) {
         for(int i=0; i< repeted(item)-1 ;i++) {
             this.stepList.remove(item);
         }
     }
 }
 
}
