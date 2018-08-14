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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class InterfaceList {
    private ArrayList<Interface> interfaceList;
    private boolean edited;
    public InterfaceList(){
        interfaceList = new ArrayList<Interface>();
        edited=false;
    }
    public InterfaceList(ArrayList<Interface> interfaceList) {
        this.interfaceList=interfaceList;
    }
    public void setInterfaceList(ArrayList<Interface> interfaceList) {
        this.interfaceList=interfaceList;
    }
  
    public ArrayList<Interface> getInterfaceList() {
        return this.interfaceList;
    }
    public boolean getEdited() {
        return this.edited;
    }
    public void setEdited(boolean edited) {
        this.edited=edited;
    }
    public void addAll(ArrayList<Interface> interfaceList) {
        this.interfaceList.addAll(interfaceList);
    }
    public void addOne(Interface onterface) {
        this.interfaceList.add(onterface);
    }
    
    public Function flowFunction2(int id, boolean In ,int idFunction, pss.FunctionList functionlist) {
    Function function = new Function();
    
    for(pss.Function fct : functionlist.getFunctionList()) {
        if(fct.getIdFunction()!=idFunction) {
        if(In==true) {
            for(pss.Flow flwIn : fct.getFlowInList()) 
                if(flwIn.getId()==id)
                    {function.setName(fct.getName());
                    function.setIdFunction(fct.getIdFunction());
                    function.setType(fct.getType());
                    function.setFlowInList(fct.getFlowInList());
                    function.setFlowOutList(fct.getFlowOutList());
                    break;}   
        }
        else {
            for(pss.Flow flwOut : fct.getFlowOutList()) 
                if(flwOut.getId()==id)
                    {function.setName(fct.getName());
                    function.setIdFunction(fct.getIdFunction());
                    function.setType(fct.getType());
                    function.setFlowInList(fct.getFlowInList());
                    function.setFlowOutList(fct.getFlowOutList());
                    break;} 
        }
        } 
    }
    return function;
}
     public Function flowFunction(int id, boolean In ,int idFunction, pss.FunctionList functionlist) {
    
    for(pss.Function fct : functionlist.getFunctionList()) {
        if(fct.getIdFunction()!=idFunction) {
        if(In==true) {
            for(pss.Flow flwIn : fct.getFlowInList()) 
                if(flwIn.getId()==id)
                    {return fct;}   
        }
        else {
            for(pss.Flow flwOut : fct.getFlowOutList()) 
                if(flwOut.getId()==id)
                    {return fct;} 
        }
        } 
    }
    return null;
}
    public String reverseName(String name) {
  
       char content[] = new char[name.length()];
        name.getChars(0, name.length(), content, 0);
        char other[]= new char[name.length()];
        int c=0;
        
        int i =(content.length-1);
        int numb=-1;
        while(i>=0) {
            int j =0;
            while(content[i--]!='_' && i>=0)
                j++;
           if(j>0) numb++;
           if(numb<2){
            for(int k=i+2; k<=i+j+1; k++)
                other[c++] = content[k];
            other[c++] =content[i+1];
           }
        }
        i=0;
        while(content[i]!='_') {
            other[c++]=content[i];
            i++;
        }
        String finall;
        finall=String.valueOf(other);
        return finall;
    }
    
    public boolean isHere(Interface f) {
        boolean here = false;
        for(Interface item : this.interfaceList) {
            if(f.getType().equals(item.getType()) && ( f.getName().equals(item.getName()) || f.getName().equals(this.reverseName(item.getName()))) ) {
                here=true;
                break;
            }
        }
        return here;
    }
    public void refreshInterfaces() {
        int id= 1;
        ArrayList<Interface> interfaces = new ArrayList<Interface>();
        interfaces.addAll(this.interfaceList);
        for(Interface interf : interfaces) {
            if(interf.getName().equals("None"))
                this.interfaceList.remove(interf);
        }
        for(Interface interf : interfaceList) {
            interf.setId(id);
            id++;
        }
    }
        
        
    public void createInterfaces(moduleList modulesList, FunctionList functionsList) { 
        this.interfaceList.clear();
        this.interfaceList = new ArrayList<Interface>();
        int id=1;
        for(Function function1 : functionsList.getFunctionList()) {
            ArrayList<Module> modules1 = new ArrayList<Module>();
            HashMap<Flow, Function> flowout_Function = new HashMap<Flow, Function>();
            HashMap<Flow, Function> flowin_Function = new HashMap<Flow, Function>();
            HashMap<Function, ArrayList<Module>> functionOut_modules = new HashMap<Function, ArrayList<Module>>();
            HashMap<Function, ArrayList<Module>> functionIn_modules = new HashMap<Function, ArrayList<Module>>();
            
            modules1 = modulesList.modulesFunction(function1);
            
            for(pss.Flow flowout : function1.getFlowOutList()) {
                Function function2 = new Function();
                Flow flow = new Flow();
                function2= flowFunction(flowout.getId(),true , function1.getIdFunction() , functionsList );
                flow.setId(flowout.getId());
                flow.setIsIn(flowout.getIsIn());
                flow.setName(flowout.getName());
                flow.setType(flowout.getType());
                flow.setSpecifications(flowout.getSpecifications());
                flow.setIsOut(flowout.getIsOut());
                if(function2!=null){
                    flowout_Function.put(flow, function2);
                    functionOut_modules.put(function2, modulesList.modulesFunction(function2));
                }
                else {
                    flowout_Function.put(flow, null);
                }

                
                    
            }
            
            for(pss.Flow flowin : function1.getFlowInList()) {
                Function function2 = new Function();
                Flow flow = new Flow();
                function2= flowFunction(flowin.getId(),false ,function1.getIdFunction() , functionsList );
                flow.setId(flowin.getId());
                flow.setIsIn(flowin.getIsIn());
                flow.setName(flowin.getName());
                flow.setType(flowin.getType());
                flow.setSpecifications(flowin.getSpecifications());
                flow.setIsOut(flowin.getIsOut());
                if(function2!=null){
                    flowin_Function.put(flow, function2);
                    functionIn_modules.put(function2, modulesList.modulesFunction(function2));
                }
                else {
                    flowout_Function.put(flow, null);
                }
            }
            
            
            
            
            
            
            
            Set set = flowin_Function.entrySet();
      Iterator iterator = set.iterator();
      while(iterator.hasNext()) {
         Map.Entry mentry = (Map.Entry)iterator.next();
         
         Flow flow = (Flow) mentry.getKey();
         Function function = (Function) mentry.getValue();
         if(function==null) {
             for(Module module1 : modules1) {
                   Interface intrfce = new Interface();
                   intrfce.setFisrtId(module1.getId());
                   intrfce.setFirstModule(module1);
                   intrfce.setId(id);
                   intrfce.setName(module1.getName()+"_"+flow.getName()+"_Human");
                   intrfce.setSecondId(0);
                   intrfce.setSecondModule(null);
                   String config=" ";
                   if(module1.getSystemType().equals("Enabling System"))
                       config += "EC_";
                   else if(module1.getType().equals("Electronical") || 
                          module1.getType().equals("Mechanical") || 
                           module1.getType().equals("Cybernetic") )
                           config += "PC_";
                   else 
                       config += "SC_";
                   config+="U";
                   intrfce.setConfiguration(config);
                   intrfce.setType("Hum_Mach");
                   if(!this.isHere(intrfce)) {
                       id++;
                       this.interfaceList.add(intrfce);
                      }
             }
         }
         else {
            ArrayList<Module> modules2 = (ArrayList<Module>) functionIn_modules.get(function);
            for(Module module1 : modules1) {
                for(Module module2 : modules2) {
                   Interface intrfce = new Interface();
                   intrfce.setFisrtId(module1.getId());
                   intrfce.setFirstModule(module1);
                   intrfce.setId(id);
                   intrfce.setName(module1.getName()+"_"+flow.getName()+"_"+module2.getName());
                   intrfce.setSecondId(module2.getId());
                   intrfce.setSecondModule(module2);

                   if(flow.getType().equals("data")) 
                       intrfce.setType("cyber");
                   else
                       intrfce.setType("physical");

                   if(module2.getType().equals("Organizational capabilities"))
                       intrfce.setType("organizational");
                   else
                       //if(!module2.getIsIntern() && module2.getProvisionType().equals("Available"))
                       if(!module2.getInteractionMode().equals("None"))
                           intrfce.setType("transactional");


                   String config=" ";
                   if(module1.getSystemType().equals("Enabling System"))
                       config += "EC_";
                   else if(module1.getType().equals("Electronical") || 
                          module1.getType().equals("Mechanical") || 
                           module1.getType().equals("Cybernetic") )
                           config += "PC_";
                   else 
                       config += "SC_";

                   if(module2.getSystemType().equals("Enabling System"))
                       config += "EC";
                   else if(module2.getType().equals("Electronical") || 
                          module2.getType().equals("Mechanical") || 
                           module2.getType().equals("Cybernetic"))
                           config += "PC";
                   else 
                       config += "SC";

                   intrfce.setConfiguration(config);


                      if(!this.isHere(intrfce)) {
                       id++;
                       this.interfaceList.add(intrfce);
                      }

                   if(modules2.size() > 1 && !module2.getType().equals("Organizational capabilities")) {
                       Interface intrf = new Interface();
                       intrf.setFisrtId(module1.getId());
                       intrf.setFirstModule(module1);
                       intrf.setId(id);
                       intrf.setName(module1.getName()+"_organizational_"+module2.getName());
                       intrf.setSecondId(module2.getId());
                       intrf.setSecondModule(module2);
                       intrf.setConfiguration(config);
                       intrf.setType("organizational");
                       if(!this.isHere(intrf)) {
                           this.interfaceList.add(intrf);
                           id++;
                       }
                   }

                }

            }
         }
      }
      
      
      
      
      
      
      
      
      set = flowout_Function.entrySet();
      iterator = set.iterator();
      while(iterator.hasNext()) {
         Map.Entry mentry = (Map.Entry)iterator.next();
         
         Flow flow = (Flow) mentry.getKey();
         Function function = (Function) mentry.getValue();
         if(function==null) {
             for(Module module1 : modules1) {
                   Interface intrfce = new Interface();
                   intrfce.setFisrtId(module1.getId());
                   intrfce.setFirstModule(module1);
                   intrfce.setId(id);
                   intrfce.setName(module1.getName()+"_"+flow.getName()+"_Human");
                   intrfce.setSecondId(0);
                   intrfce.setSecondModule(null);
                   String config=" ";
                   if(module1.getSystemType().equals("Enabling System"))
                       config += "EC_";
                   else if(module1.getType().equals("Electronical") || 
                          module1.getType().equals("Mechanical") || 
                           module1.getType().equals("Cybernetic") )
                           config += "PC_";
                   else 
                       config += "SC_";
                   config+="U";
                   intrfce.setConfiguration(config);
                   intrfce.setType("Hum_Mach");
                   if(!this.isHere(intrfce)) {
                       id++;
                       this.interfaceList.add(intrfce);
                      }
             }
         }
         else {
            ArrayList<Module> modules2 = (ArrayList<Module>) functionOut_modules.get(function);
            for(Module module1 : modules1) {
                for(Module module2 : modules2) {
                   Interface intrfce = new Interface();
                   intrfce.setFisrtId(module1.getId());
                   intrfce.setFirstModule(module1);
                   intrfce.setId(id);
                   intrfce.setName(module1.getName()+"_"+flow.getName()+"_"+module2.getName());
                   intrfce.setSecondId(module2.getId());
                   intrfce.setSecondModule(module2);

                   if(flow.getType().equals("data")) 
                       intrfce.setType("cyber");
                   else
                       intrfce.setType("physical");

                   if(module2.getType().equals("Organizational capabilities"))
                       intrfce.setType("organizational");
                   else
                       //if(!module2.getIsIntern() && module2.getProvisionType().equals("Available"))
                       if(!module2.getInteractionMode().equals("None"))
                           intrfce.setType("transactional");


                   String config=" ";
                   if(module1.getSystemType().equals("Enabling System"))
                       config += "EC_";
                   else if(module1.getType().equals("Electronical") || 
                          module1.getType().equals("Mechanical") || 
                           module1.getType().equals("Cybernetic") )
                           config += "PC_";
                   else 
                       config += "SC_";

                   if(module2.getSystemType().equals("Enabling System"))
                       config += "EC";
                   else if(module2.getType().equals("Electronical") || 
                          module2.getType().equals("Mechanical") || 
                           module2.getType().equals("Cybernetic"))
                           config += "PC";
                   else 
                       config += "SC";

                   intrfce.setConfiguration(config);


                      if(!this.isHere(intrfce)) {
                       id++;
                       this.interfaceList.add(intrfce);
                      }

                   if(modules2.size() > 1 && !module2.getType().equals("Organizational capabilities")) {
                       Interface intrf = new Interface();
                       intrf.setFisrtId(module1.getId());
                       intrf.setFirstModule(module1);
                       intrf.setId(id);
                       intrf.setName(module1.getName()+"_organizational_"+module2.getName());
                       intrf.setSecondId(module2.getId());
                       intrf.setSecondModule(module2);
                       intrf.setConfiguration(config);
                       intrf.setType("organizational");
                       if(!this.isHere(intrf)) {
                           this.interfaceList.add(intrf);
                           id++;
                       }
                   }

                }

            }
         }
      }
            
            
            
            
        }
    }
    
    
    public void save(String url) { 
    
 
        try { 
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            for(pss.Interface item : this.interfaceList) {
                String Name = item.getName();
                String Type= item.getType();
                int Id= item.getId();
                int firstModule= item.getFisrtId();
                int secondModule = item.getSecondId();
                String configuration= item.getConfiguration();
                
                
                String sql = "INSERT INTO interfaces VALUES (? , ?, ?, ? , ?, ?)"; //insert 
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(2, Name);
                pstmt.setInt(1, Id);
                pstmt.setString(3, Type);
                pstmt.setString(4, configuration);
                pstmt.setInt(5, firstModule);
                pstmt.setInt(6, secondModule);
                pstmt.executeUpdate();
            }    
            
        }
               
            
         catch (SQLException ex) { 
            Logger.getLogger(ex.getMessage());
        }
        
}

    public void open(String url, moduleList modules) {
        ArrayList<Interface> interfacelist = new ArrayList<Interface>();
        try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
            String sql = "Select * From interfaces"; //insert 
            ResultSet res = st.executeQuery(sql);
            
            while(res.next()) {
                Interface f = new Interface();
                f.setId(res.getInt("id"));
                f.setFisrtId(res.getInt("firstModule"));
                f.setSecondId(res.getInt("secondModule"));
                f.setName(res.getString("name"));
                f.setType(res.getString("type"));
                f.setConfiguration(res.getString("configuration"));
                if(f.getFisrtId()!=0)
                    for(pss.Module m : modules.getModuleList()) {
                        if(m.getId()==f.getFisrtId())
                            f.setFirstModule(m);
                     }
                else {
                     f.setFisrtId(0);
                     f.setFirstModule(null);
                }

                if(f.getSecondId()!=0)
                    for(pss.Module m : modules.getModuleList()) {
                        if(m.getId()==f.getSecondId())
                                f.setSecondModule(m);
                    }
                else {
                    f.setSecondId(0);
                    f.setSecondModule(null);
                } 
                interfacelist.add(f);
                
            }
           
        } catch (SQLException ex) { 
            Logger.getLogger(ex.getMessage());
        }
        this.interfaceList = interfacelist;
    }
    
}
