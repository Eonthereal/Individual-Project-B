/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import static menu.WriteMenu.staticDate;

/**
 *
 * @author eon_A
 */
public class SupportMethods {

    public static int validatePositive(int x) {
        Scanner input = new Scanner(System.in);
        if (x <= 0) {
            System.err.println("Please, insert positive number");
            return validatePositive(input.nextInt());
        } else {
            return x;
        }
    }

    public static void valDate() {
        Scanner input = new Scanner(System.in);
        try {
            LocalDate dateObj = LocalDate.parse(staticDate);
        } catch (DateTimeParseException ex) {
            System.err.println("Invalid date, please insert a valid date with pattern yyyy-MM-dd");
            staticDate = input.next();
            valDate();
        }
    }
    
    public static String valPartFullTime(String x) { 
        Scanner input = new Scanner(System.in);
        if (x.equalsIgnoreCase("Full Time") || x.equalsIgnoreCase("Part Time")) {
            return x;
        } else {
            System.err.println("You should type \"Full Time\" or \"Part Time\":");
            return valPartFullTime(input.nextLine());
        }

    }

    

}
