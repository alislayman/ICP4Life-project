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



public class Scenariolist  implements Serializable {
    
   private ArrayList<pss.Scenario> scenarioList;
    
    public Scenariolist() {
     this.scenarioList= new ArrayList<pss.Scenario>();
 }
 public void setScenarioList(ArrayList<pss.Scenario> scenarioList) {
     this.scenarioList=scenarioList;
 }   
 public ArrayList<pss.Scenario> getScenarioList() {
     return this.scenarioList;
 }
 
 public boolean found(int id) {
     boolean foundd=false;
    for(pss.Scenario item : this.scenarioList) {
        if(item.getIdScenario()== id)
        {foundd=true; break; }
            }
    return foundd;
 }
 
 public pss.Scenario search(int id) {
    pss.Scenario f = new pss.Scenario();
    for(pss.Scenario item : this.scenarioList) {
        if(item.getIdScenario()== id)
        {f=item; break; }
            }
    return f;
}
 
 
   public void save(String url ) { 
    
        try { 
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            for(pss.Scenario item : this.scenarioList) {
                ArrayList<Integer> StepIdList = new ArrayList<Integer>();
                for(pss.Step element : item.getStepList()) {
                    if(element.getIdStep()!= -1) StepIdList.add(element.getIdStep());
                }
                Array stepList = conn.createArrayOf("integer", StepIdList.toArray());
                boolean isNominal = item.getIsNominal();
                String objectif = item.getObjectif();
                String Name = item.getName();
                int Id= item.getIdScenario();
                String lifeCycleStage = item.getLife();
                                
                String sql = "INSERT INTO scenarios VALUES (? , ?, ?, ?, ?, ?)"; //insert 
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(2, Name);
                pstmt.setInt(1, Id);
                pstmt.setBoolean(3, isNominal);
                pstmt.setString(4, objectif);
                pstmt.setArray(5, stepList);
                pstmt.setString(6, lifeCycleStage);
                pstmt.executeUpdate();
            }    
            
        }
               
            
         catch (SQLException ex) { 
            Logger.getLogger(ex.getMessage());
        }
        
}
   
   
   public int repeted(pss.Scenario f) {
     int i=0;
     for(pss.Scenario item : this.scenarioList) {
         if(f.getIdScenario()==item.getIdScenario() && f.getName().equals(item.getName()) && f.getLife().equals(item.getLife()))
             i++;
     }
     
     return i;
 }
 
 public void clean() {
     ArrayList<Scenario> scenarios = this.scenarioList;
     scenarios = this.scenarioList;
     for(int i=0; i<scenarios.size();i++) {
         for(int j=0; j< repeted(scenarios.get(i))-1 ;j++) {
             scenarios.remove(i);
         }
     
     }
 }
 
}
