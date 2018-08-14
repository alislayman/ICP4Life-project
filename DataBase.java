/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class DataBase {
    private String name;
    
    public DataBase() {
        name=null;
    }
    public DataBase(String name) {
        this.name=name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public String getName() {
        return this.name;
    }
    
    public void createDB(String s) {
        String url = "jdbc:postgresql://localhost:5432/";
      String  sql= "CREATE DATABASE "+s;
       
       try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
             System.out.println("yes");
            st.executeQuery(sql);
            
       } catch (SQLException ex) {  System.out.println(ex.getMessage());
            Logger.getLogger(ex.getMessage());
        }
//create table:       
       url="jdbc:postgresql://localhost:5432/"+s;
       sql= "CREATE TABLE public.flows\n" +
"(\n" +
"    name text COLLATE pg_catalog.\"default\",\n" +
"    id integer NOT NULL,\n" +
"    type text COLLATE pg_catalog.\"default\",\n" +
"    \"Specifications\" text[] COLLATE pg_catalog.\"default\" NOT NULL,\n" +
"    \"isIn\" boolean,\n" +
"    \"isOut\" boolean,\n" +
"    CONSTRAINT \"ID\" PRIMARY KEY (id)\n" +
")\n" +
"WITH (\n" +
"    OIDS = FALSE\n" +
")\n" +
"TABLESPACE pg_default;";
      try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
             System.out.println("yes");
            st.executeQuery(sql);
            
       } catch (SQLException ex) {  System.out.println(ex.getMessage());
            Logger.getLogger(ex.getMessage());
        }
      
      
      sql="CREATE TABLE public.functions\n" +
"(\n" +
"    id integer,\n" +
"    name text COLLATE pg_catalog.\"default\",\n" +
"    type text COLLATE pg_catalog.\"default\",\n" +
"    \"flowInList\" integer[],\n" +
"    \"flowOutList\" integer[]\n" +
")";
      try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
             System.out.println("yes");
            st.executeQuery(sql);
            
       } catch (SQLException ex) {  System.out.println(ex.getMessage());
            Logger.getLogger(ex.getMessage());
        }
      
      
      sql="CREATE TABLE public.project\n" +
"(\n" +
"    \"Name\" text COLLATE pg_catalog.\"default\" NOT NULL,\n" +
"    type text COLLATE pg_catalog.\"default\" NOT NULL,\n" +
"    clients text COLLATE pg_catalog.\"default\" NOT NULL,\n" +
"    service text COLLATE pg_catalog.\"default\" NOT NULL,\n" +
"    product text COLLATE pg_catalog.\"default\" NOT NULL,\n" +
"    objectif text COLLATE pg_catalog.\"default\" NOT NULL\n" +
")\n" +
"WITH (\n" +
"    OIDS = FALSE\n" +
")\n" +
"TABLESPACE pg_default;";
      try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
             System.out.println("yes");
            st.executeQuery(sql);
            
       } catch (SQLException ex) {  System.out.println(ex.getMessage());
            Logger.getLogger(ex.getMessage());
        }
      
      
      sql="CREATE TABLE public.pss_end_of_life\n" +
"(\n" +
"    \"scenarioList\" integer[]\n" +
")\n" +
"WITH (\n" +
"    OIDS = FALSE\n" +
")\n" +
"TABLESPACE pg_default;";
      try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
             System.out.println("yes");
            st.executeQuery(sql);
            
       } catch (SQLException ex) {  System.out.println(ex.getMessage());
            Logger.getLogger(ex.getMessage());
        }
      
      sql="CREATE TABLE public.pss_operation\n" +
"(\n" +
"    \"scenarioList\" integer[]\n" +
")\n" +
"WITH (\n" +
"    OIDS = FALSE\n" +
")\n" +
"TABLESPACE pg_default;";
      try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
             System.out.println("yes");
            st.executeQuery(sql);
            
       } catch (SQLException ex) {  System.out.println(ex.getMessage());
            Logger.getLogger(ex.getMessage());
        }
      
      sql="CREATE TABLE public.pss_pending\n" +
"(\n" +
"    \"scenarioList\" integer[]\n" +
")\n" +
"WITH (\n" +
"    OIDS = FALSE\n" +
")\n" +
"TABLESPACE pg_default;";
      try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
             System.out.println("yes");
            st.executeQuery(sql);
            
       } catch (SQLException ex) {  System.out.println(ex.getMessage());
            Logger.getLogger(ex.getMessage());
        }
      
      
      sql="CREATE TABLE public.pss_evolution\n" +
"(\n" +
"    \"scenarioList\" integer[]\n" +
")\n" +
"WITH (\n" +
"    OIDS = FALSE\n" +
")\n" +
"TABLESPACE pg_default;";
      try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
             System.out.println("yes");
            st.executeQuery(sql);
            
       } catch (SQLException ex) {  System.out.println(ex.getMessage());
            Logger.getLogger(ex.getMessage());
        }
      
      
      
      sql="CREATE TABLE public.scenarios\n" +
"(\n" +
"    id integer,\n" +
"    name text COLLATE pg_catalog.\"default\",\n" +
"    \"isNominal\" boolean,\n" +
"    objectif text COLLATE pg_catalog.\"default\",\n" +
"    \"stepList\" integer[],\n" +
"    \"lifeCycleStage\" text COLLATE pg_catalog.\"default\"\n" +
")\n" +
"WITH (\n" +
"    OIDS = FALSE\n" +
")\n" +
"TABLESPACE pg_default;";
      try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
             System.out.println("yes");
            st.executeQuery(sql);
            
       } catch (SQLException ex) {  System.out.println(ex.getMessage());
            Logger.getLogger(ex.getMessage());
        }
      
      sql="CREATE TABLE public.steps\n" +
"(\n" +
"    id integer,\n" +
"    name text COLLATE pg_catalog.\"default\",\n" +
"    requirement text COLLATE pg_catalog.\"default\",\n" +
"    stakeholder text COLLATE pg_catalog.\"default\",\n" +
"    \"stepNext\" integer[],\n" +
"    \"functionList\" integer[]\n" +
")\n" +
"WITH (\n" +
"    OIDS = FALSE\n" +
")\n" +
"TABLESPACE pg_default;";
      try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
             System.out.println("yes");
            st.executeQuery(sql);
            
       } catch (SQLException ex) {  System.out.println(ex.getMessage());
            Logger.getLogger(ex.getMessage());
        }
      
      
     sql="CREATE TABLE public.modules\n" +
"(\n" +
"    id integer,\n" +
"    name text COLLATE pg_catalog.\"default\",\n" +
"    type text COLLATE pg_catalog.\"default\",\n" +
"    \"functionList\" integer[],\n" +
"    \"portList\" integer[],\n" +
"    \"elementList\" integer[],\n" +
"    \"isInternal\" boolean,\n" +
"    \"provisionType\" text COLLATE pg_catalog.\"default\"\n" +
")";
      try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
             System.out.println("yes");
            st.executeQuery(sql);
            
       } catch (SQLException ex) {  System.out.println(ex.getMessage());
            Logger.getLogger(ex.getMessage());
        }
      
      
      //interfaces : 
      sql = "CREATE TABLE public.interfaces\n" +
"(\n" +
"    id integer,\n" +
"    name text COLLATE pg_catalog.\"default\",\n" +
"    type text COLLATE pg_catalog.\"default\",\n" +
"    configuration text COLLATE pg_catalog.\"default\",\n" +
"    \"firstModule\" integer,\n" +
"    \"secondModule\" integer\n" +
")";
      try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
             System.out.println("yes");
            st.executeQuery(sql);
            
       } catch (SQLException ex) {  System.out.println(ex.getMessage());
            Logger.getLogger(ex.getMessage());
        }
     
      
      
      //elements
      
      sql="CREATE TABLE public.elements\n" +
"(\n" +
"    id integer,\n" +
"    name text COLLATE pg_catalog.\"default\",\n" +
"    \"portList\" integer[],\n" +
"    \"moduleID\" integer\n" +
")\n" +
"WITH (\n" +
"    OIDS = FALSE\n" +
")\n" +
"TABLESPACE pg_default;";
      try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
             System.out.println("yes");
            st.executeQuery(sql);
            
       } catch (SQLException ex) {  System.out.println(ex.getMessage());
            Logger.getLogger(ex.getMessage());
        }
      
      
      //ports
      
      sql="CREATE TABLE public.ports\n" +
"(\n" +
"    id integer,\n" +
"    name text COLLATE pg_catalog.\"default\",\n" +
"    type text COLLATE pg_catalog.\"default\",\n" +
"    \"contactType\" text COLLATE pg_catalog.\"default\",\n" +
"    \"isIn\" boolean,\n" +
"    \"isOut\" boolean,\n" +
"    \"moduleID\" integer\n" +
")\n" +
"WITH (\n" +
"    OIDS = FALSE\n" +
")\n" +
"TABLESPACE pg_default;";
      try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
             System.out.println("yes");
            st.executeQuery(sql);
            
       } catch (SQLException ex) {  System.out.println(ex.getMessage());
            Logger.getLogger(ex.getMessage());
        }
      
      
    }
    
    
    public void deleteDB(String s) {
        String url = "jdbc:postgresql://localhost:5432/";
      String  sql= "update pg_database set datallowconn = 'false' where datname ='" + s + "'";
       
          
       try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
             System.out.println("yes");
            st.executeQuery(sql);
            
       } catch (SQLException ex) {  System.out.println(ex.getMessage());
            Logger.getLogger(ex.getMessage());
        }
   
   sql="SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname ='" + s + "'";
 try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
             System.out.println("yes");
            st.executeQuery(sql);
            
       } catch (SQLException ex) {  System.out.println(ex.getMessage());
            Logger.getLogger(ex.getMessage());
        }



sql="DROP DATABASE "+s;
try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
             System.out.println("yes");
            st.executeQuery(sql);
            
       } catch (SQLException ex) {  System.out.println(ex.getMessage());
            Logger.getLogger(ex.getMessage());
        } 
    }
    
    
    public ArrayList<String> getDataBases() {
         String url = "jdbc:postgresql://localhost:5432/";
    String sql="SELECT datname FROM pg_database WHERE datistemplate = false;";       
          ArrayList<String> names = new ArrayList<String>();
          
       try {
            Connection conn = DriverManager.getConnection(url, "postgres", "0000");
            Statement st = conn.createStatement();
             System.out.println("yes");
            ResultSet res = st.executeQuery(sql);
            while(res.next()) {
                String name= res.getString("datname");
                names.add(name);
            }
       } catch (SQLException ex) {  System.out.println(ex.getMessage());
            Logger.getLogger(ex.getMessage());
        }
       return names;
    }
    
}
