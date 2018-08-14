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
public class moduleList implements Serializable {
    ArrayList<Module> moduleList;
    
    public moduleList() {
     this.moduleList= new ArrayList<pss.Module>();
 }
 public void setModuleList(ArrayList<pss.Module> moduleList) {
     this.moduleList=moduleList;
 }   
 public ArrayList<pss.Module> getModuleList() {
     return this.moduleList;
 }
 
 public int repeted(pss.Module f) {
     int i=0;
     for(pss.Module item : this.moduleList) {
         if(f.getId()==item.getId() && f.getName().equals(item.getName()))
             i++;
     }
     
     return i;
 }

 public void clean() {
     for(pss.Module item : this.moduleList) {
         item.cleanPorts();
         for(int i=0; i< repeted(item)-1 ;i++) {
             this.moduleList.remove(item);
         }
     }
 }
 
 public void deleteModule(int id) {
     for(pss.Module item : this.moduleList) {
         if(id==item.getId()) {
             this.moduleList.remove(item); break;
         }
     }
 }
 
 public ArrayList<Port> getAllPorts() {
     ArrayList<Port> allPorts = new ArrayList<Port>();
     for(Module m : this.moduleList) {
         allPorts.addAll(m.getPortList());
     }
     return allPorts;
 }
 
 public ArrayList<Module> getSystemsOfInterest() {
     ArrayList<Module> SystemsOfInterest = new ArrayList<Module>();
     for(Module module: this.moduleList) {
         if(module.getSystemType().equals("System Of Interest")) 
             SystemsOfInterest.add(module);
     }
     return SystemsOfInterest;
 }
 
 public ArrayList<Module> getEnablingSystems() {
     ArrayList<Module> EnablingSystems = new ArrayList<Module>();
     for(Module module: this.moduleList) {
         if(module.getSystemType().equals("Enabling System")) 
             EnablingSystems.add(module);
     }
     return EnablingSystems;
 }
 
 
 
 public void save(String url) { 
    try { 
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            for(pss.Module item : this.moduleList) {
                ArrayList<Integer> FunctionIdList = new ArrayList<Integer>();
                ArrayList<Integer> PortIdList = new ArrayList<Integer>();
                ArrayList<Integer> ElementIdList = new ArrayList<Integer>();
                
                for(pss.Function element : item.getFunctionList()) {
                    if(element.getIdFunction()!= -1) FunctionIdList.add(element.getIdFunction());
                }
                
                for(pss.Port element : item.getPortList()) {
                    if(element.getIdPort()!= -1) PortIdList.add(element.getIdPort());
                }
                
                for(pss.Element element : item.getElementList()) {
                    if(element.getId()!= -1) ElementIdList.add(element.getId());
                }
                
                
                
                Array functionList = conn.createArrayOf("integer", FunctionIdList.toArray());
                Array portList = conn.createArrayOf("integer", PortIdList.toArray());
                Array elementList = conn.createArrayOf("integer", ElementIdList.toArray());
                String type = item.getType();
                String Name = item.getName();
                int Id= item.getId();
                boolean isInternal = item.getIsIntern();
                String provisionType = item.getProvisionType();
                
                String sql = "INSERT INTO modules VALUES (?, ?, ?, ?, ?, ?, ? ,?)"; //insert 
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(2, Name);
                pstmt.setInt(1, Id);
                pstmt.setString(3, type);
                pstmt.setArray(4, functionList);
                pstmt.setArray(5, portList);
                pstmt.setArray(6, elementList);
                pstmt.setBoolean(7, isInternal);
                pstmt.setString(8, provisionType);
                pstmt.executeUpdate();
            }    
            
        }
         catch (SQLException ex) { 
            Logger.getLogger(ex.getMessage());
        }
}
 
 public ArrayList<pss.Module> modulesFunction(pss.Function f) {
     ArrayList<pss.Module> m = new ArrayList<pss.Module>();
     for(pss.Module item : this.moduleList) {
         for(Function element : item.getFunctionList()) {
             if(element.getIdFunction()==f.getIdFunction()) {
                 m.add(item);
                 break;
             }
         }
     }
     return m;
 }
 
 
 public void configureInterfaces(InterfaceList interfaces) {
     for(Module m : this.moduleList) {
         m.addInterface(interfaces);
     }
 }
 
}