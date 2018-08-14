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
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author Ali SLAYMAN
 */
public class LifeCycleStage implements Serializable {
    private String name;
    private int idStage;
    private ArrayList<Scenario> scenarioList;
    
public LifeCycleStage() {
    System.out.println("Life cycle stage is created as default");
    name= "None";
    idStage = -1;
    scenarioList= new ArrayList<Scenario>();
    }

public LifeCycleStage(String name, int idStage, ArrayList<Scenario> scenarioList) {
    this.name= name;
    this.idStage = idStage;
    this.scenarioList=scenarioList;
}

public void setName(String name){
    this.name=name;
}
public void setIdStage(int idStage){
    this.idStage=idStage;
}
public void setScenarioList(ArrayList<Scenario> scenarioList){
    this.scenarioList=scenarioList;
}

public String getName(){
    return this.name;
}
public int getIdStage(){
    return this.idStage;
}
public ArrayList<Scenario> getScenarioList(){
    return this.scenarioList;
}
public void addScenario(Scenario s) {
   if(!this.scenarioList.contains(s)) 
    this.scenarioList.add(s);
}
public void removeScenario(Scenario s) {
   if(this.scenarioList.contains(s)) 
    this.scenarioList.remove(s);
}
public boolean verifyScenarios() {
    boolean verified=true;

    if((this.name).equals("operation")) {
        if(scenarioList.isEmpty()) {
        verified=false;
        System.out.println("It's necessery when PSS operation stage has at least one scenario");
        }
        else {verified=false;
            int count=0;
            while(scenarioList.size()>count){
                //if(scenarioList.get(count).verifySteps())
                    //System.out.println("Steps are trues at scenario " + scenarioList.get(count).getName());
                 if( scenarioList.get(count).getIsNominal())
                 {verified=true; break;}
                 count++;
            }
            //if(verified==false)
               // System.out.println("PSS operation stage must contain a nominal scenario");
           // else
                //System.out.println("PSS operation stage is true");
        }  
    }
    else {
        if(this.scenarioList.isEmpty()) {
                    System.out.println("Warning, you don't identify a scenario in PSS "+ this.name);
                    verified=false;    
        }
    }
    System.out.println("");
    return verified;
    
}

public HashMap<String, Boolean> color() {
    boolean verified = false;
    HashMap<String, Boolean> color = new HashMap<String, Boolean>();
    color.put("flow", true);
    color.put("function", true);
    color.put("step", true);
    color.put("scenario", true);
    ArrayList<pss.Scenario> scenarioList = this.getScenarioList();
    ArrayList<pss.Step> stepList = new ArrayList<pss.Step>();
        for(Scenario item: scenarioList) {
            stepList.addAll(item.getStepList());
        }
    ArrayList<pss.Function> functionList= new ArrayList<pss.Function>();
        for(Step item: stepList) {
            functionList.addAll(item.getFunctionList());
        }
    ArrayList<pss.Flow> flowList = new ArrayList<pss.Flow>();
    for(Function item: functionList) {
            flowList.addAll(item.getFlowInList());
            flowList.addAll(item.getFlowOutList());
        }
    
    
    if(scenarioList.isEmpty())
        color.put("scenario", false);
    if(stepList.isEmpty())
        color.put("step", false);
    if(functionList.isEmpty())
        color.put("function", false);
    if(flowList.isEmpty())
        color.put("flow", false);
    
    
    for(pss.Function item : functionList) {
        if(!item.verifyFlows())
        {color.put("flow", false); break;}
    }
    
    for(pss.Step item : stepList) {
        if(!item.verifyFunctions()) 
        {color.put("function", false); break; }
    }
    
    for(pss.Scenario item: scenarioList) {
        if(!item.verifySteps()) {
            color.put("step", false); break; 
        }
    }
    color.put("scenario", this.verifyScenarios());
    
    return color;
}

public void save(String url, String sql) { 
    
        try { 
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            
                ArrayList<Integer> ScenarioIdList = new ArrayList<Integer>();
                for(pss.Scenario element : this.scenarioList) {
                    if(element.getIdScenario()!= -1) ScenarioIdList.add(element.getIdScenario());
                }
                Array scenarioList = conn.createArrayOf("integer", ScenarioIdList.toArray());
                
                                
                //insert 
                PreparedStatement pstmt = conn.prepareStatement(sql);
               
                pstmt.setArray(1, scenarioList);
              
                pstmt.executeUpdate();
            }    
            
        
               
            
         catch (SQLException ex) { 
            Logger.getLogger(ex.getMessage());
        }
        
}


    public Integer[] open(String sql) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Integer[] scenarioIdList={-1};
        try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
            //String sql = "Select * From pss_operation"; //insert //delet
            ResultSet res = st.executeQuery(sql);
            while(res.next()) {
               // java.sql.Array a = res.getArray("Specifications");
                Array k = res.getArray("scenarioList");
                scenarioIdList= (Integer[])k.getArray();
            }
            for (int i=0; i<scenarioIdList.length; i++) {
                System.out.println(scenarioIdList[i]);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
        return scenarioIdList;
    }

}
