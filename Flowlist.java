/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pss;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class Flowlist implements Serializable {
     ArrayList<pss.Flow> flowList;
    private int size;
    pss.Flow flow;
    
    public Flowlist () {
        flowList= new ArrayList<Flow>();
       // spec=null;
        size=0;
        flow= new Flow();
    }
    
    public int getSize() {
       return this.size;
   }
   public void setSize(int size) {
       this.size=size;
   }
    
   public ArrayList<pss.Flow> getFlowList(){
       return this.flowList;
   }
   public void setFlowList(ArrayList<pss.Flow> flowList) {
       this.flowList=flowList;
   }
   
public void setFlow(pss.Flow flow) {
    
    this.flow=flow;
}
    
   public pss.Flow getFlow() {
       return this.flow;
   } 
   
   
   public void addFlow() {
if(!this.flowList.contains(this.flow)) {
flowList.add(this.size,(Flow)flow);
size++;
}
   }
   
   
public void save(String url) { 
    
 
        try { 
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            for(pss.Flow item : this.flowList) {
                String Name = item.getName();
                String Type= item.getType();
                int Id= item.getId();
                boolean IsIn = item.getIsIn();
                boolean IsOut = item.getIsOut();
                Array speci = conn.createArrayOf("text", item.getSpecifications().toArray());
                
                String sql = "INSERT INTO flows VALUES (? , ?, ?, ? , ?, ?)"; //insert 
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, Name);
                pstmt.setInt(2, Id);
                pstmt.setString(3, Type);
                pstmt.setArray(4, speci);
                pstmt.setBoolean(5, IsIn);
                pstmt.setBoolean(6, IsOut);
                pstmt.executeUpdate();
            }    
            
        }
               
            
         catch (SQLException ex) { 
            Logger.getLogger(ex.getMessage());
        }
        
}

    public void open(String url) {
        ArrayList<Flow> flowlist = new ArrayList<Flow>();
        try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
            String sql = "Select * From flows"; //insert //delet
            ResultSet res = st.executeQuery(sql);
            
            while(res.next()) {
                Flow f = new Flow();
                f.setId(res.getInt("id"));
                f.setIsIn(res.getBoolean("isIn"));
                f.setIsOut(res.getBoolean("isOut"));
                f.setName(res.getString("name"));
                f.setType(res.getString("type"));
               // java.sql.Array a = res.getArray("Specifications");
                Array k = res.getArray("Specifications");
                String[] s =(String[]) k.getArray();
                ArrayList<String> specifications = new ArrayList<String>();
                for(int i=0;i<s.length;i++) {
                    specifications.add(s[i]);
                }
                f.setSpecifications(specifications);
                flowlist.add(f);
            }
           
        } catch (SQLException ex) { 
            Logger.getLogger(ex.getMessage());
        }
        this.flowList = flowlist;
    }
    
    public int repeted(pss.Flow f) {
     int i=0;
     for(pss.Flow item : this.flowList) {
         if(f.getIsIn()==item.getIsIn() && f.getId()==item.getId())
             i++;
     }
     
     return i;
 }
 
 public void clean() {
     for(pss.Flow item : this.flowList) {
         for(int i=0; i< repeted(item)-1 ;i++) {
             this.flowList.remove(item);
         }
     }
 }
 
 
}

