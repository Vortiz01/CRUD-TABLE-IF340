/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicahiccpmp.utilities;


/**
 *
 * @author vmol2
 */
public class Layout {
    public static void printSeparator()
    {
        System.out.println("================================================================================");
    }
    
    public static void printMenu()
    {   
        printSeparator();
        System.out.println("\t\t\t\tMENU PRINCIPAL");
        printSeparator();
        System.out.println("1 - Agregar Registro\n2 - Mostrar Registros\n3 - Consultar Registro\n4 - Actualizar Registro\n5 - Eliminar Registro\n6 - Eliminar todos los registros\n7 - Salir");
        System.out.println("Ingrese su opcion:");        
    }
}
