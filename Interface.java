/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pss;

/**
 *
 * @author lenovo
 */
public class Interface {
    private int id;
    private String name;
    private String type;
    private String configuration;
    private Module firstModule;
    private Module secondModule;
    private int fisrtId;
    private int secondId;
    public Interface() {
        id=-1;
        name="None";
        type="None";
        firstModule = new Module();
        secondModule = new Module();
        fisrtId=-1;
        secondId=-1;
    }
    
    public Interface(int id, int firstId,  int secondId, String name, String configuration , String type , Module firstModule,Module secondModule ) {
        this.id=id;
        this.name=name;
        this.type=type;
        this.configuration=configuration;
        this.firstModule=firstModule;
        this.secondModule=secondModule;
        this.fisrtId=firstId;
        this.secondId=secondId;
    }
    
    public void setId(int id) {
        this.id=id;
    }
    public void setFisrtId(int firstId) {
        this.fisrtId=firstId;
    }
    public void setSecondId(int secondId) {
        this.secondId=secondId;
    }
    public void setName(String name) {
        this.name=name;
    }
    public void setFirstModule(Module firstModule ) {
        this.firstModule=firstModule;
    }
    public void setSecondModule(Module secondModule ) {
        this.secondModule=secondModule;
    }
    public void setType(String type) { 
        this.type=type;
    }
    public void setConfiguration(String configuration) {
        this.configuration=configuration;
    }
    
    
    public int getId() {
        return this.id;
    }
    public int getFisrtId() {
        return this.fisrtId;
    }
    public int getSecondId() {
        return this.secondId;
    }
    public Module getFisrtModule() {
        return this.firstModule;
    }
    public Module getSecondModule() {
        return this.secondModule;
    }
    public String getName() {
        return this.name;
    }
    public String getType () {
        return this.type;
    }
    public String getConfiguration () {
        return this.configuration;
    }
}
