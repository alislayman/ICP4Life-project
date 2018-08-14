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

import java.util.ArrayList;

/**
 *
 * @author lenovo
 */
public class Step implements Serializable {
    private String name;
    private int idStep;
    private String stakeholder;
    private String requirement;
    private ArrayList<Step> stepNext;
    private ArrayList<Function> functionList;
    String stepSelected[];
    String functionSelected[];
    String scenarioSelected[];
    Integer[] stepNextId;
    
public Step() {
    System.out.println("Step created as default");
    name= "None";
    idStep = -1;
    stepNext= new ArrayList<Step>();
    functionList= new ArrayList<Function>();
}


public void setStepNextId(Integer[] stepNextId) {
    this.stepNextId=stepNextId;
}
public Integer[] getStepNextId() {
    return this.stepNextId;
}



public void setRequirement(String requirement) {
    this.requirement=requirement;
}
public String getRequirement() {
    return this.requirement;
}
public void setStepSelected(String[] stepSelected) {
    this.stepSelected=stepSelected;
}
public void setFunctionSelected(String[] functionSelected) {
    this.functionSelected=functionSelected;
}
public String[] getStepSelected() {
    return this.stepSelected;
}
public String[] getFunctionSelected() {
    return this.functionSelected;
}
public void setScenarioSelected(String[] scenarioSelected) {
    this.scenarioSelected=scenarioSelected;
}
public String[] getScenarioSelected() {
    return this.scenarioSelected;
}

public Step (String name, int idStep, ArrayList<Step> stepNext, ArrayList<Function> functionList) {
    this.name= name;
    this.idStep = idStep; 
    this.stepNext = stepNext; 
    this.functionList=functionList;
}

public void setName (String name) {
    this.name=name;
}
public void setIdStep (int idStep) {
    this.idStep=idStep;
}
public void setStepNext (ArrayList<Step> stepNext) {
    this.stepNext=stepNext;
}
public void setFunctionList (ArrayList<Function> functionList) {
    this.functionList=functionList;
}

public String getName () {
    return this.name;
}
public String getStakeholder () {
    return this.stakeholder;
}
public void setStakeholder (String stakeholder) {
    this.stakeholder=stakeholder;
}
public int getIdStep () {
   return this.idStep;
}
public ArrayList<Step> getStepNext () {
   return this.stepNext;
}
public ArrayList<Function> getFunctionList () {
   return this.functionList;
}

public boolean verifyFunctions() {
    boolean verified=true;
    if(functionList.isEmpty()){
            verified = false;
            System.out.println("Step " + this.idStep + " not contain a function" );
        }
    else {
        int count=0;
        while(functionList.size()> count) {
            if(functionList.get(count).verifyFlows())
                System.out.println("Flow is true at function " + functionList.get(count).getIdFunction() + " in step " + this.name);
            if(functionList.get(count).getIdFunction()== -1) {
                System.out.println("Function identification is false at "+ this.name);
                verified=false;
            }
        count++;
        }
            
    }
    System.out.println("");
   return verified;
}


 public void removeFunction(int id) {
        for(Function item : this.functionList) {
            if(item.getIdFunction()==id) {
                this.functionList.remove(item);
                break;
            }
        }
    }
 
 public void removeNextStep(int s) {
                this.stepNext.remove(s); 
    }
 
  public void addFunction(Function f){
        this.functionList.add(f);
    }
  public boolean functionHere (int s) {
     boolean v=false;
     for(pss.Function fct : this.functionList) {
         if(fct.getIdFunction()==s)
         {v=true; break;}
     }
     return v;
 }

}


