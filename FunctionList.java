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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lenovo
 */
public class FunctionList implements Serializable {
  private  ArrayList<pss.Function> functionList;
  
 public FunctionList() {
     this.functionList= new ArrayList<pss.Function>();
 }
 public void setFunctionList(ArrayList<pss.Function> functionList) {
     this.functionList=functionList;
 }   
 public ArrayList<pss.Function> getFunctionList() {
     return this.functionList;
 }
 
 public boolean found(int id) {
     boolean foundd=false;
    for(pss.Function item : this.functionList) {
        if(item.getIdFunction()== id)
        {foundd=true; break; }
            }
    return foundd;
 }
 
 public pss.Function search(int id) {
    pss.Function f = new pss.Function();
    for(pss.Function item : this.functionList) {
        if(item.getIdFunction()== id)
        {f=item; break; }
            }
    return f;
}
 
 
 public void save(String url ) { 
    
        
        try { 
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            for(pss.Function item : this.functionList) {
                ArrayList<Integer> flowIdList = new ArrayList<Integer>();
                for(pss.Flow element : item.flowInList) {
                    if(element.getId()!= -1) flowIdList.add(element.getId());
                }
                
                Array flowIn = conn.createArrayOf("integer", flowIdList.toArray());
                flowIdList.clear();
                flowIdList = new ArrayList<Integer>();
                for(pss.Flow element : item.flowOutList) {
                    if(element.getId()!= -1) flowIdList.add(element.getId());
                }
                Array flowOut = conn.createArrayOf("integer", flowIdList.toArray());
                String Name = item.getName();
                String type="";
                for(int i=0;i<item.getType().length;i++) {
                    type+=item.getType()[i]+", ";
                }
                int Id= item.getIdFunction();
                                
                String sql = "INSERT INTO functions VALUES (? , ?, ?, ? , ?)"; //insert 
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(2, Name);
                pstmt.setInt(1, Id);
                pstmt.setString(3, type);
                pstmt.setArray(4, flowIn);
                pstmt.setArray(5, flowOut);
                pstmt.executeUpdate();
            }    
            
        }
               
            
         catch (SQLException ex) { 
            Logger.getLogger(ex.getMessage());
        }
        
}
 
 public int repeted(pss.Function f) {
     int i=0;
     for(pss.Function item : this.functionList) {
         if(f.getIdFunction()==item.getIdFunction() && f.getType().equals(item.getType()) && f.getName().equals(item.getName()))
             i++;
     }
     
     return i;
 }
 
 public void clean() {
     for(pss.Function item : this.functionList) {
         for(int i=0; i< repeted(item)-1 ;i++) {
             this.functionList.remove(item);
         }
     }
 }
}
