/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicahiccpmp.dao;

import com.unicahiccpmp.utilities.Layout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author vmol2
 */
public class TecuitDB {

    /**
     * @return the _id
     */
    public int getId() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void setId(int _id) {
        this._id = _id;
    }

    /**
     * @return the _name
     */
    public String getName() {
        return _name;
    }

    /**
     * @param _name the _name to set
     */
    public void setName(String _name) {
        this._name = _name;
    }

    /**
     * @return the _brand
     */
    public String getBrand() {
        return _brand;
    }

    /**
     * @param _brand the _brand to set
     */
    public void setBrand(String _brand) {
        this._brand = _brand;
    }

    /**
     * @return the _unit
     */
    public int getUnit() {
        return _unit;
    }

    /**
     * @param _unit the _unit to set
     */
    public void setUnit(int _unit) {
        while(_unit < 0)
        {
            System.out.println("ERROR...INGRESE UNA UNIDAD MAYOR A [-1]");
            Scanner entry = new Scanner(System.in);
            System.out.println("Ingrese las unidades en stock:");
            _unit = entry.nextInt();
        }
        this._unit = _unit;
    }

    /**
     * @return the _price
     */
    public double getPrice() {
        return _price;
    }

    /**
     * @param _price the _price to set
     */
    public void setPrice(double _price) {
        while(_price <= 0)
        {
            System.out.println("ERROR...INGRESE UN PRECIO MAYOR A [0]");
            Scanner entry = new Scanner(System.in);
            System.out.println("Ingrese el precio del producto:");
            _price = entry.nextDouble();
        }
        this._price = _price;
    }
    
    private int _id;
    private String _name;
    private String _brand;
    private int _unit;
    private double _price;
    
    public TecuitDB()
    {
        this._id = 0;
        this._name = "";
        this._brand = "";
        this._unit = 0;
        this._price = 0;
    }       
    
    
    public int getItemId(int id){
        try {
            String SQLGetItemID = "SELECT * FROM Products WHERE ProductID = ?;";
            PreparedStatement comand = Conection.getConection().prepareStatement(SQLGetItemID);
            comand.setInt(1, id);
            ResultSet misRegistro = comand.executeQuery();
            while (misRegistro.next()) {
                setId(misRegistro.getInt("ProductID"));
                setName(misRegistro.getString("Prod_Name"));
                setBrand(misRegistro.getString("Prod_Brand"));
                setUnit(misRegistro.getInt("UnitsInStock"));
                setPrice(misRegistro.getDouble("Prod_Price"));
                break;
            }
            comand.close();

            return this._id;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
            return 0;
        }   
    }
    
    public void createTable()
    {
        int result;
        String SqlCreate = "Create Table IF NOT EXISTS Products"
                + "(ProductID INTEGER primary key AUTOINCREMENT NOT NULL,"
                + "Prod_Name TEXT NOT NULL,"
                + "Prod_Brand TEXT NOT NULL,"
                + "UnitsInStock INTEGER NOT NULL,"
                + "Prod_Price REAL NOT NULL)";
        try
        {
            Statement comand  = Conection.getConection().createStatement();
            result = comand.executeUpdate(SqlCreate);
            comand.close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR, No se pudo crear la tabla....."+ex);
        }               
    }
    
    public void insertData()
    { 
        try
        {
            System.out.println("\t\t\t\tInsertando registro....");
            Layout.printSeparator();
            String SQLInsert = "INSERT INTO Products "
                    + "(Prod_Name,Prod_Brand,UnitsInStock,Prod_Price) VALUES"
                    + "(?, ?, ?, ?);";
            PreparedStatement comand = Conection.getConection().prepareStatement(SQLInsert);
            comand.setString(1, this._name);
            comand.setString(2, this._brand);
            comand.setInt(3, this._unit);
            comand.setDouble(4, this._price);
            
            int add = comand.executeUpdate();
            comand.close();
                    
        }
        catch(Exception ex)
        {
            System.out.println("ERROR, No se pudo insertar el registro...."+ex);
        }
    }
    
    public void showData()
    {
        try
        {
            String SQLShow = "Select ProductID, Prod_Name, Prod_Brand, UnitsInStock, Prod_Price From Products";
            Statement comand = Conection.getConection().createStatement();
            ResultSet rs = comand.executeQuery(SQLShow);
            while(rs.next())
            {
                System.out.println(rs.getInt("ProductID") + "\t\t "
                                + rs.getString("Prod_Name") + "\t\t "
                                + rs.getString("Prod_Brand") + "\t\t "
                                + rs.getInt("UnitsInStock") + "\t\t  "
                                + rs.getDouble("Prod_Price") + "\t");
            }
            comand.close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR,No se pueden mostrar los datos...."+ex);
        }
    }
    
    public void showOneData()
    {
         try
        {
            String SQLShow = "Select ProductID, Prod_Name, Prod_Brand, UnitsInStock, Prod_Price From Products where ProductID=?";
            PreparedStatement comand = Conection.getConection().prepareStatement(SQLShow);
            
            comand.setInt(1, this._id);
            
            ResultSet rs = comand.executeQuery();          
            while(rs.next())
            {
                System.out.println(rs.getInt("ProductID") + "\t\t "
                                + rs.getString("Prod_Name") + "\t\t "
                                + rs.getString("Prod_Brand") + "\t\t "
                                + rs.getInt("UnitsInStock") + "\t\t  "
                                + rs.getDouble("Prod_Price") + "\t");
                break;
            }
            comand.close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR,No se pueden mostrar los datos...."+ex);
        }
    }
    
    
    public void updateData()
    { 
        try {
            System.out.println("\t\t\t\tActualizando registro....");
            Layout.printSeparator();
            String SQLUpdate = "Update Products set Prod_Name=?, Prod_Brand=?, "
                + "UnitsInStock=?, Prod_Price=? where ProductID=?";
            PreparedStatement comand = Conection.getConection().prepareStatement(SQLUpdate);
            
            comand.setString(1,this._name);
            comand.setString(2, this._brand);
            comand.setInt(3, this._unit);
            comand.setDouble(4, this._price);
            comand.setInt(5, this._id);
            
            comand.executeUpdate();
            comand.close();
        } 
        catch (Exception ex) 
        {
            System.out.println("ERROR, No se pudo actualizar el registro..."+ex);
        }
        
        System.out.println("");
    }
    
    public void deleteData(int id)
    {
        try {
            System.out.println("Eliminando registro....");
            Layout.printSeparator();
            String SQLDelete = "DELETE FROM Products WHERE ProductID = ?;";
            PreparedStatement comand = Conection.getConection().prepareStatement(SQLDelete);
            
            comand.setInt(1, id);
            
            int registrAfectado = comand.executeUpdate();
            comand.close();
            
        } catch( Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
            
    public void deleteAllData()
    {
        try
        {
            System.out.println("Eliminando registro....");
            Layout.printSeparator();
            String SQLDeleteAll = "DROP TABLE Products";
            PreparedStatement comand = Conection.getConection().prepareStatement(SQLDeleteAll);
            
            comand.executeUpdate();
            createTable();
            comand.close();
        }
        catch(Exception ex)
        {
            System.out.println("ERROR,No se pudieron eliminar los registros..."+ex);
        }
    }
}
