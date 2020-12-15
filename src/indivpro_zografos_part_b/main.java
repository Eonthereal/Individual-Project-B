/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indivpro_zografos_part_b;

import java.util.Scanner;
import menu.ReadMenu;
import menu.WriteMenu;


/**
 *
 * @author eon_A
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;
        System.out.println("~~~~~~~~~~~~~~~~~~~PROJECT B~~~~~~~~~~~~~~~~~~~");
        System.out.println(" ");
        System.out.println(" ");

        System.out.println("Type 1 to go to the Read Menu or Type 2 to go to the Write Menu \nType anything else to Shut Down!");
        choice= input.nextInt();
        
        switch(choice){
            case 1:
                ReadMenu.readMenu();
                break;
            case 2:
                WriteMenu.writeMenu();
                break;
            default:
                System.out.println("~~~~~~~~~~~~~~~~~~~Shutting Down~~~~~~~~~~~~~~~~~~~");
        }
   


}
    
    
    
    

    
    
    
}
