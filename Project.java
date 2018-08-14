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
import java.util.logging.*;

/**
 *
 * @author Ali SLAYMAN
 */
public class Project implements Serializable {
    private String name;
    private LifeCycle lifeCycle;
    private String type;
    private String client;
    private String objectif;
    private String service;
    private String product;
    
    public Project() {
        name = null;
        type = "None";
        client = "None";
        objectif = "None";
        service="None";
        product="None";
        lifeCycle= new LifeCycle();
    }
    public Project(String name, String type, String objectif, String client,String service, String product, LifeCycle lifeCycle) {
        this.name=name;
        this.type=type;
        this.objectif=objectif;
        this.client=client;
        this.product=product;
        this.service=service;
        this.lifeCycle=lifeCycle;
    }
     public String getService(){
       return this.service;
    }
    
    public void setService(String service) {
        this.service=service;
    }
    
    
    public String getProduct(){
       return this.product;
    }
    
    public void setProduct(String product) {
        this.product=product;
    }
    
    
    
    public void setName(String name) {
        this.name=name;
    }
    public void setLifeCycle(LifeCycle lifeCycle) {
        this.lifeCycle=lifeCycle;
    }
    
    public String getName(){
       return this.name;
    }
    
    public void setType(String type) {
        this.type=type;
    }
    
    public String getType(){
       return this.type;
    }
    
        public void setClient(String client) {
        this.client=client;
    }
    
    public String getClient(){
       return this.client;
    }
    
        public void setObjectif(String objectif) {
        this.objectif=objectif;
    }
    
    public String getObjectif(){
       return this.objectif;
    }
    
    
    
    public LifeCycle getProject_lifeCycle() {
        return this.lifeCycle;
    }
    
    public boolean verifyProject() {
        boolean verified= this.lifeCycle.verifyLifeCycleStages();
        if(verified==false) 
            System.out.println("Sorry, your Project has errors");
        else
            System.out.println("Your Project is verified");
        return verified;
    }
    
    public ArrayList<LifeCycleStage> getLifeCycleStageList() {
        return lifeCycle.getLifeCycleStage();
    }
    public ArrayList<Scenario> getScenarioList() {
        ArrayList<LifeCycleStage> lifeCycleStage= getLifeCycleStageList();
        ArrayList<Scenario> scenarioList = new ArrayList<Scenario>();
        for(LifeCycleStage item: lifeCycleStage) {
            scenarioList.addAll(item.getScenarioList());
        }
        return scenarioList;
    }
    public ArrayList<Step> getStepList() {
        ArrayList<Scenario> scenarioList= getScenarioList();
        ArrayList<Step> stepList = new ArrayList<Step>();
        for(Scenario item: scenarioList) {
            stepList.addAll(item.getStepList());
        }
        return stepList;
    }
    public ArrayList<Function> getFunctionList() {
        ArrayList<Step> stepList= getStepList();
        ArrayList<Function> FunctionList = new ArrayList<Function>();
        for(Step item: stepList) {
            FunctionList.addAll(item.getFunctionList());
        }
        return FunctionList;
    }
    public ArrayList<Flow> getFlowList() {
        ArrayList<Function> functionList= getFunctionList();
        ArrayList<Flow> flowList = new ArrayList<Flow>();
        for(Function item: functionList) {
            flowList.addAll(item.getFlowInList());
            flowList.addAll(item.getFlowOutList());
        }
        return flowList;
    }
    
    public void save(String url) { 
    
        try { 
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            
              
               
                String objectif = this.objectif;
                String Name = this.name;
                String clients =this.client;
                String type = this.type;
                String service = this.service;
                String product = this.product;
                String sql = "INSERT INTO project VALUES (? , ?, ?, ?, ?, ?)"; //insert 
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, Name);
                pstmt.setString(2, type);
                pstmt.setString(3, clients);
                pstmt.setString(4, service);
                pstmt.setString(5, product);
                pstmt.setString(6, objectif);
                pstmt.executeUpdate();
            
            
        }
               
            
         catch (SQLException ex) { 
            Logger.getLogger(ex.getMessage());
        }
        
}
    
    public void open(String url) {
        try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
            String sql = "Select * From project"; //insert //delet
            ResultSet res = st.executeQuery(sql);
            while(res.next()) {
                this.name=res.getString("Name");
                this.type=res.getString("type");
                this.client=res.getString("clients");
                this.service=res.getString("service");
                this.product=res.getString("product");
                this.objectif=res.getString("objectif");

               

            }
            System.out.println("yess");
        } catch (SQLException ex) { 
            Logger.getLogger(ex.getMessage());
        }
    }
       
}
