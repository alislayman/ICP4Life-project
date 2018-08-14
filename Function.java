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
public class Function implements Serializable {
    private String name;
    private String[] type;
    private int idFunction;
    ArrayList<Flow> flowInList;
    ArrayList<Flow> flowOutList;
    String[] flowIn;
    String[] flowOut;
    String[] functionStep;
    
public Function() {
    System.out.println("Function created as default");
    type=new String[0];
    idFunction = -1;
    flowInList = new ArrayList<Flow>();
    flowOutList = new ArrayList<Flow>();
}
public Function (String[] type, int idFunction, ArrayList<Flow> flowInList, ArrayList<Flow> flowOutList) {
    this.type= type;
    this.idFunction = idFunction;
    this.flowInList=flowInList;
    this.flowOutList = flowOutList;
}

public boolean Type(String type){
    boolean t= false;
    if(this.type!=null)
    for(int i=0;i<this.type.length;i++)
        if(this.type[i].equals(type)) {
            t=true; break;
        }
    return t;
}
        
public void setFunctionStep(String[] functionStep) {
    this.functionStep=functionStep;
    }

public String[] getFunctionStep() {
    return this.functionStep;
    }
    
    
public void setFlowIn(String[] flowIn) {
    this.flowIn=flowIn;
}

public String[] getFlowIn() {
    return this.flowIn;
}
public void setFlowOut(String[] flowOut) {
    this.flowOut=flowOut;
}

public String[] getFlowOut() {
    return this.flowOut;
}


public void setType(String[] type) {
    this.type=type;
}
public void setIdFunction(int idFunction) {
    this.idFunction=idFunction;
}
public void setFlowInList(ArrayList<Flow> flowInList) {
    this.flowInList= flowInList;
}
public void setFlowOutList(ArrayList<Flow> flowOutList) {
    this.flowOutList=flowOutList;
}
public void setName(String name) {
    this.name=name;
}
public String getName() {
   return this.name;
}        
public String[] getType() {
   return this.type;
}        
public int getIdFunction() {
   return this.idFunction;
}   
public ArrayList<Flow> getFlowInList() {
   return this.flowInList;
}   
public ArrayList<Flow> getFlowOutList() {
   return this.flowOutList;
}   

public boolean verifyFlows() {
    int count=0;
    boolean verified = true;
        if(flowInList.isEmpty()){
            verified = false;
            System.out.println("Flow in is empty at function ID " +this.idFunction);
        }
        else
            while(flowInList.size() > count) {
                if( flowInList.get(count).getIsIn() != true) {
                    verified= false;
                    System.out.println(flowInList.get(count).getType() + " Flow in direction isn't valide at function ID " + this.idFunction);
                }
                count++;
            }
        
        count=0;
        
        if(flowOutList.isEmpty()) {
            verified = false;
            System.out.println("Flow out is empty at function ID " +this.idFunction);
        }
        else
            while(flowOutList.size() > count) {
                if( flowOutList.get(count).getIsIn() != false) {
                    verified = false;
                    System.out.println(flowOutList.get(count).getType() + "Flow in direction isn't valide at function ID " + this.idFunction);
                }
                count++;
            }
        System.out.println("");
        return verified;

         }

 public void removeFlow(int id) {
        for(Flow item : this.flowInList) {
            if(item.getId()==id) {
                this.flowInList.remove(item);
                break;
            }
        }
        for(Flow item : this.flowOutList) {
            if(item.getId()==id) {
                this.flowOutList.remove(item);
                break;
            }
        }
    }
 
 public boolean flowHere (Flow thisFlow, ArrayList<Flow> flow) {
     boolean v=false;
     for(Flow f : flow) {
         if(thisFlow.getId()==f.getId())
         {v=true; break;}
     }
     return v;
 }   
        
}

