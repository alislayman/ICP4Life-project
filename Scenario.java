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
 * @author Ali SLAYMAN
 */
public class Scenario implements Serializable {
    private String name;
    private int idScenario;
    private boolean isNominal;
    private String objectif;
    private ArrayList<Step> stepList;
    String[] stepSelect;
    private String life;
    public Scenario() {
        name="None";
        life="None";
        idScenario=-1;
        isNominal=false;
        stepList = new ArrayList<Step>();
    }
    public Scenario(String name, int idScenario, boolean isNominal,ArrayList<Step> stepList) {
        this.name=name;
        this.idScenario=idScenario;
        this.isNominal=isNominal;
        this.stepList=stepList;
    }
    
    public void setStepSelect(String[] stepSelect) {
    this.stepSelect=stepSelect;
    }

    public String[] getStepSelect() {
    return this.stepSelect;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setLife(String life){
        this.life=life;
    }    
    public void setObjectif(String objectif){
        this.objectif=objectif;
    }
    public void setIdScenario(int idScenario){
        this.idScenario=idScenario;
    }
    public void setIsNominal(boolean isNominal){
        this.isNominal=isNominal;
    }
    public void setStepList(ArrayList<Step> stepList){
        this.stepList=stepList;
    }
 
    public String getName(){
        return this.name;
    }
    public String getLife(){
        return this.life;
    }
    public String getObjectif(){
        return this.objectif;
    }
    public int getIdScenario(){
        return this.idScenario;
    }
    public boolean getIsNominal(){
        return this.isNominal;
    }
    public ArrayList<Step> getStepList(){
        return this.stepList;
    } 
    
    public boolean verifySteps() {
        boolean verified=true;
        if(stepList.isEmpty()) {
            verified =false;
            System.out.println("A scenario must contain at least one step");
        }
        else {
            int count=0;
            while(stepList.size()>count) {
                if(stepList.get(count).verifyFunctions())
                    System.out.println("Functions are trues at " + stepList.get(count).getName());
                ArrayList<Step> nextStep= stepList.get(count).getStepNext();
                if(nextStep.isEmpty()) {
                    verified=false;
                    System.out.println("Warning! Step " + stepList.get(count).getName()+ " is not connected to any step" );
                }
                count++;
            }
        }
        System.out.println("");
      return verified;  
    }
    public void addStep(Step s){
        this.stepList.add(s);
    }
    public void removeStep(int id) {
        for(Step item : this.stepList) {
            if(item.getIdStep()==id) {
                this.stepList.remove(item);
                break;
            }
        }
    }
    
 
 public boolean stepHere (int s) {
     boolean v=false;
     for(pss.Step step : this.stepList) {
         if(step.getIdStep()==s)
         {v=true; break;}
     }
     return v;
 }   
    
    
}
