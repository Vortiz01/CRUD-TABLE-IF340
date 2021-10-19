/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicahiccpmp.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author vmol2
 */
public class Conection {
    
    protected static String stringConnection = "jdbc:sqlite:tecuitDB.db";
    private static Connection conec = null;
    
    private Conection()
    {
        
    }
    
    public static Connection getConection()
    {
        try
        {
            if(conec == null)
            {
                
                Class.forName("org.sqlite.JDBC");
                conec = DriverManager.getConnection("jdbc:sqlite:tecuitDB.db");
                //System.out.println("Conexion establecida");
                return conec;
            }
            else
                return conec;
            
        }
        catch(Exception ex)
        {
        System.out.println("ERROR DE CONEXION....."+ex);
            return null;
        }
    }
}
