/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicahiccpmp.tecuit;
import com.unicahiccpmp.utilities.Layout;
import com.unicahiccpmp.utilities.Aplication;
import java.util.Scanner;

/**
 *
 * @author vmol2
 */
public class Main {
    
    public static void main (String [] args)
    {
        Scanner entry = new Scanner(System.in);
        Aplication apli = new Aplication();
        int opcion;
        
        Layout.printSeparator();
        System.out.println("\t\t\t\tTIENDA TECUIT");
        Layout.printSeparator();
        do
        {
            do
            {
               Layout.printMenu();
               opcion = entry.nextInt();
               apli.opcionMenu(opcion);
               if(opcion < 1 || opcion > 7)
                    {
                        System.out.println("ERROR.... Ingrese una opcion entre [1-8]");
                    }
             
            }while(opcion < 1 || opcion > 7);
            
        }while(opcion != 7);
        
    }
   
    
}
