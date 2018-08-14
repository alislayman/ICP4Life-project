/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pss;

import java.util.ArrayList;

/**
 *
 * @author lenovo
 */
public class Module {
    private int id;
    private String name;
    private String type;
    private ArrayList<Function> functionList;
    private ArrayList<Port> portList;
    private ArrayList<Interface> interfaceList;
    private ArrayList<Element> elementList;
    private String[] functionAssigned;
    private String systemType;
    private String provisionType;
    private String interactionMode;
    private boolean isIntern;
    
    public Module() {
        functionList = new ArrayList<Function>();
        name="None";
        type="None";
        id=-1;
        portList = new ArrayList<Port>();
        elementList= new ArrayList<Element>();
        systemType="None";
        provisionType="None";
        isIntern=true;
        interactionMode="None";
        interfaceList = new ArrayList<Interface>();

    }
    public Module(int id, String name,String systemType,String provisionType, boolean isIntern, String interactionMode,
            ArrayList<Function> functionList, ArrayList<Interface> interfaceList, ArrayList<Element> elementList) {
        this.functionList=functionList;
        this.name=name;
        this.id=id;
        this.elementList=elementList;
        this.functionList=functionList;
        this.systemType=systemType;
        this.provisionType=provisionType;
        this.isIntern=isIntern;
        this.interactionMode=interactionMode;
        this.interfaceList=interfaceList;
    }
    
    public void setFunctionAssigned(String[] functionStep) {
    this.functionAssigned=functionStep;
    }

    public String[] getFunctionAssigned() {
    return this.functionAssigned;
    }
    
    
    public void setElementList(ArrayList<Element> elementList) {
    this.elementList=elementList;
    }

    public ArrayList<Element> getElementList() {
    return this.elementList;
    }
    public void setInterfaceList(ArrayList<Interface> interfaceList) {
    this.interfaceList=interfaceList;
    }

    public ArrayList<Interface> getInterfaceList() {
    return this.interfaceList;
    }
    
    public void setPortList(ArrayList<Port> portList) {
    this.portList=portList;
    }

    public ArrayList<Port> getPortList() {
    return this.portList;
    }
    
    public void setSystemType(String systemType) {
        this.systemType=systemType;
    }
    public String getSystemType(){
        return this.systemType;
    }
    public boolean getIsIntern() {
        return this.isIntern;
    }
    public void setIsIntern(boolean isIntern) {
        this.isIntern=isIntern;
    }
    public void setType(String type) {
        this.type=type;
    }
    
    public String getType(){
        return this.type;
    }
    public void setProvisionType(String provisionType) {
        this.provisionType=provisionType;
    }
    
    public String getProvisionType(){
        return this.provisionType;
    }
    public void setName(String name) {
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public void setId(int id) {
        this.id=id;
    }
    public int getId(){
        return this.id;
    }
    public void setFunctionList(ArrayList<Function> functionList) {
        this.functionList=functionList;
    }
    public ArrayList<Function> getFunctionList() {
        return this.functionList;
    }
    public String getInteractionMode() {
        return this.interactionMode;
    }
    public void setInteractionMode (String interactionMode) {
        this.interactionMode=interactionMode;
    }
    
    public void assignFunction(Function f) {
        this.functionList.add(f);
    }
    
    public boolean found(Function fct){
        boolean founded=false;
        for(Function f : this.functionList) {
            if(fct.getName().equals(f.getName()) && fct.getIdFunction()==f.getIdFunction())
                founded=true;
        }
        return founded;
    }
    
    public ArrayList<Flow> getFlowIn() {
        ArrayList<Flow> flows = new ArrayList<Flow>();
        for(pss.Function fct: this.functionList) {
            flows.addAll(fct.getFlowInList());
        }
        return flows;
    }
    public ArrayList<Flow> getFlowOut() {
        ArrayList<Flow> flows = new ArrayList<Flow>();
        for(pss.Function fct: this.functionList) {
            flows.addAll(fct.getFlowOutList());
        }
        return flows;
    }
    
   
    public ArrayList<Port> getAllPorts() {
        ArrayList<Port> allPort = new ArrayList<Port>();
        for(Element item : this.elementList)
            allPort.addAll(item.getPortList());
        return allPort;
    }
    public boolean portHere(Port p) {
        boolean here=false;
        for(Port item: this.portList) {
            if(item.getIdPort()==p.getIdPort() && item.getType().equals(p.getType()) &&  p.getIsIn()==item.getIsIn() && p.getIsOut()==item.getIsOut() ) {
                here=true; break;
            }
        }
        return here;
    }
 
    public int repeted(pss.Port f) {
     int i=0;
     for(pss.Port item : this.portList) {
         if(f.getName().equals(item.getName()) && f.getIsIn()==item.getIsIn() && f.getIsOut()==item.getIsOut())
             i++;
     }
     return i;
    }

    public void cleanPorts() {
     for(pss.Port item : this.portList) {
         for(int i=0; i< repeted(item)-1 ;i++) {
             this.portList.remove(item);
         }
     }
    }
    
    public void updatePort(int index, pss.Port port) {
        this.portList.set(index-1, port);
    }
    
    public void deleteElement(int id) {
     for(pss.Element item : this.elementList) {
         if(id==item.getId()) {
             this.elementList.remove(item); break;
         }
     }
    }
    
    public int[] getPortsId() {
        int[] ids = new int[this.portList.size()];
        if(!this.portList.isEmpty())
        for(int i=0;i<ids.length;i++) {
            ids[i]=this.portList.get(i).getIdPort();
        }
        return ids;
    }
    public boolean isHere(int id) {
        int[] ids = this.getPortsId();
        boolean here=false;
        for(int i=0;i<ids.length;i++) 
            if(id==ids[i]) {
                here = true; break;
            }
        return here;
    }
    
    public int nextPortId() {
        int id=1;
        while(!isHere(id))
            id++;
        return id;
    }
    public void addInterface(InterfaceList interfaces) {
       for(Interface i : interfaces.getInterfaceList()) {
           if((i.getFisrtId()==this.id || i.getSecondId()==this.id) && !this.interfaceList.contains(i))
               this.interfaceList.add(i);
       }
    }
    
    public void addElement(Element element) {
        this.elementList.add(element);
    }
     
    public void configurePorts() {
        this.portList.clear();
        Port port = new Port();
        int i=1;
        ArrayList<Flow> flows = this.getFlowIn();
        for(Flow f : flows) {
            port.setIsIn(true);
            port.setIsOut(false);
            port.setType(f.getType());
            port.setIdModule(this.id);
            //int id = this.nextPortId();
            port.setIdPort(i);
            this.portList.add(port);
            i++;
            port = new Port();
        }
        flows = this.getFlowOut();
        for(Flow f : flows) {
            port.setIsIn(false);
            port.setIsOut(true);
            port.setType(f.getType());
            //int id = this.nextPortId();
            port.setIdPort(i);
            this.portList.add(port);
            i++;
            port = new Port();
        }
    }
    
    public boolean verifyPort(int id) {
        boolean verified = true; 
        outerloop : 
        for (Port p : this.portList) {
                if(p.getIdPort()==id) 
                    if(p.getName().equals("None") || p.getContactType().equals("None")) {
                        verified=false;
                        break;
                    }
        }
        return verified;
    }

    public boolean portInElement(pss.Port port) {
        boolean verified = false; 
        outerloop : 
        for (Element item : this.elementList) {
            for(Port p : item.getPortList())
                if(p.getIdPort()==port.getIdPort()) {
                    verified=true;
                    break outerloop;
                }
        }
        return verified;
    }
    
    public ArrayList<pss.Port> getSamePorts(pss.Port p) {
        ArrayList<pss.Port> ports = new ArrayList<pss.Port>();
        for(pss.Port item : this.getPortList()) {
            if(item.getType().equals(p.getType()) && item.getContactType().equals(p.getContactType()))
                ports.add(item);
        }
        return ports;
    }
    
    
    
    
    
}
