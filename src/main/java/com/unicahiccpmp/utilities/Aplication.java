/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicahiccpmp.utilities;
import com.unicahiccpmp.dao.Conection;
import com.unicahiccpmp.dao.TecuitDB;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author vmol2
 */
public class Aplication {
    
    ArrayList array = new ArrayList<>();
    private TecuitDB _itemID;
    
    
    public void opcionMenu(int option)
    {
        TecuitDB tecuit = new TecuitDB();
        Conection.getConection();
        tecuit.createTable();
        switch(option)
        {
            case 1:
                    this.insertData();
                    break;
            case 2:
                    this.showData();
                    break;
            case 3:
                    this.showOneData();
                    break;
            case 4:
                    this.updateData();
                    break;
            case 5:
                    this.deleteData();
                    break;
            case 6:
                    this.deleteAllData();
                    break;
            
            case 7:
                    System.out.println("Gracias por usar nuestro sistema");
                    break;
            
            default:
                    
        }
    }
    
    private void insertData()
    {
        Scanner entry = new Scanner(System.in);
        TecuitDB tecuit = new TecuitDB();
        
        System.out.println("Ingrese el nombre del producto:");
        tecuit.setName(entry.nextLine());
        System.out.println("Ingrese la marca del producto:");
        tecuit.setBrand(entry.nextLine());
        System.out.println("Ingrese las unidades en stock:");
        tecuit.setUnit(entry.nextInt());
        System.out.println("Ingrese el precio del producto:");
        tecuit.setPrice(entry.nextDouble());
        tecuit.insertData();
    }
    
    private void showData()
    {
        TecuitDB tecuit = new TecuitDB();
        
        System.out.println("Codigo\t\t Nombre\t\t\t Marca\t\t Unidades\t Precio");
        tecuit.showData();
    }
    private void showOneData()
    {
        TecuitDB tecuit = new TecuitDB();
        Scanner entry = new Scanner(System.in);
        int cod,result;
        
        System.out.println("Ingrese el codigo del registro a consultar:");
        cod = entry.nextInt();
        result = tecuit.getItemId(cod);
        if(result == 0)
        {
            System.out.println("El registro no existe");
            return;
        }
        else
        {
            System.out.println("Codigo\t\t Nombre\t\t\t Marca\t\t Unidades\t Precio");
            tecuit.showOneData();
        }
        
        
    }
    
    public void updateData()
    {
        Scanner entry = new Scanner(System.in);
        TecuitDB tecuit = new TecuitDB();
        String itemID = null;
        int cod,result;
        
        System.out.println("Ingrese el codigo del registro a actualizar:");
        cod = entry.nextInt();
        result = tecuit.getItemId(cod);
        if(result == 0)
        {
            System.out.println("El registro no existe");
            return;
        }
        else
        {
            entry.nextLine();
            System.out.println("Ingrese el nombre del producto:");
            tecuit.setName(entry.nextLine());
            System.out.println("Ingrese la marca del producto:");
            tecuit.setBrand(entry.nextLine());
            System.out.println("Ingrese las unidades en stock:");
            tecuit.setUnit(entry.nextInt());
            System.out.println("Ingrese el precio del producto:");
            tecuit.setPrice(entry.nextDouble());
            tecuit.updateData();
        }
        
    }
    
    public void deleteData()
    {
        Scanner entry = new Scanner(System.in);
        TecuitDB tecuit = new TecuitDB();
        String itemID = null;
        String resp;
        int cod,result;
        
        System.out.println("Ingrese el codigo del registro a eliminar:");
        cod = entry.nextInt();
        result = tecuit.getItemId(cod);
        if(result == 0)
        {
            System.out.println("El registro no existe");
            return;
        }
        else
            entry.nextLine();
            do
            {
                System.out.println("Esta seguro de eliminar el registro [S - N]:");
                resp = entry.nextLine();
                resp = resp.toUpperCase();
                if(!"S".equals(resp) && !"N".equals(resp))
                {
                    System.out.println("ERROR...INGRESE UN CARACTER ENTRE [S - N]");
                    Layout.printSeparator();
                }
            }while(!"S".equals(resp) && !"N".equals(resp));
            if(resp.equals("S")== true)
            {
                tecuit.deleteData(cod);   
            }
            else
            {
                System.out.println("REGISTRO NO ELIMINADO....");
                Layout.printSeparator();
            }
    }
    
    public void deleteAllData()
    {
        Scanner entry = new Scanner(System.in);
        TecuitDB tecuit = new TecuitDB();
        String resp;
        
        do
        {
            System.out.println("Esta seguro de eliminar el registro [S - N]:");
            resp = entry.nextLine();
            resp = resp.toUpperCase();
            if(!"S".equals(resp) && !"N".equals(resp))
                {
                    System.out.println("ERROR...INGRESE UN CARACTER ENTRE [S - N]");
                    Layout.printSeparator();
                }
        }while(!"S".equals(resp) && !"N".equals(resp));
       
        if(resp.equals("S") == true)
        {
            tecuit.deleteAllData();
        }
        else
        {
            System.out.println("REGISTROS NO ELIMINADOS....");
            Layout.printSeparator();
        }
    }
}
