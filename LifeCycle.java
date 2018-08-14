/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pss;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Ali SLAYMAN
 */
public class LifeCycle implements Serializable {
    String PSSName;
    ArrayList<LifeCycleStage> lifeCycleStage; 
    
    public LifeCycle() {
        System.out.println("LifeCycle is created by default");
        PSSName= "None";
        lifeCycleStage = new ArrayList<LifeCycleStage>();
    }
    public LifeCycle(String PSSName, ArrayList<LifeCycleStage> lifeCycleStage ) {
        this.PSSName=PSSName;
        this.lifeCycleStage=lifeCycleStage;
    }
    
    public void setPSSName(String PSSName) {
        this.PSSName=PSSName;
    }
    public void setLifeCycleStage(ArrayList<LifeCycleStage> lifeCycleStage) {
        this.lifeCycleStage=lifeCycleStage;
    }
    
    public String getPSSName() {
        return this.PSSName;
    }
    public ArrayList<LifeCycleStage> getLifeCycleStage() {
        return this.lifeCycleStage;
    }
    
    public LifeCycleStage searchStageName(Step step) {
        boolean flagFound=false;
        LifeCycleStage stageFound = new LifeCycleStage();
        outerloop:
        for(LifeCycleStage item : this.lifeCycleStage) {
                    ArrayList<Scenario> scenario = item.getScenarioList();
                    if(!scenario.isEmpty()) {
                        for(Scenario element: scenario) {
                            ArrayList<Step> stepList= element.getStepList();
                            for(Step indiv : stepList) {
                                if(indiv==step) {
                                    flagFound=true;
                                    stageFound=item;
                                    break outerloop;
                                }
                            }
                        }
                    }
        }
        return stageFound;
    }
    
    public boolean verifyLifeCycleStages() {
        boolean verified=false;
                
        outerloop:
        for(LifeCycleStage item : this.lifeCycleStage) {   
            //if(item.verifyScenarios())
               // System.out.println("Scenarios are trues in PSS "+ item.getName());
            
            if(item.getName().equals("pending")) {
                if(item.getScenarioList().isEmpty()) 
                    verified=true;
                else {
                    ArrayList<Scenario> scenarioList= item.getScenarioList();
                    for(Scenario scenario : scenarioList) {
                        ArrayList<Step> stepList = scenario.getStepList();
                        for(Step step : stepList) {
                            ArrayList<Step> stepNextList = step.getStepNext();
                            for(Step stepNext : stepNextList) {
                                LifeCycleStage stage=searchStageName(stepNext);
                                if(stage.getName().equals("operation") || stage.getName().equals("evolution")) {
                                    verified=true;
                                    break outerloop;
                                }
                            }
                        }
                    }
                }
            }   
        }
        if(verified==false)
            System.out.println("Warning! Any state in PSS Pending has a next step in PSS Evolution or Operation");
        System.out.println("");
        return verified;
    }

}
