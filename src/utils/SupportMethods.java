/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dao.StudentDaoImpl;
import dao.StudentDaoInt;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import static menu.ReadMenu.printListOfCourses;
import static menu.ReadMenu.printListOfStudents;
import static menu.WriteMenu.staticDate;
import model.Student;

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

    public static void studentswithoutCourses() {
        Scanner input = new Scanner(System.in);
        String choice = null;
        String choice2 = null;
        int choiceInt;
        int choiceInt2;
        StudentDaoInt sdi = new StudentDaoImpl();

        try {
            List<Student> listOfStudents = sdi.printListOfStudentsWithoutCourse();
            if (listOfStudents.size() == 0) {
                
                System.err.println("There are no Students with zero Courses Assigned");
                System.out.println("");

            } else {
                System.out.println("~~~~~~~~~Please select a **StudentID** to add them to a Course~~~~~~~~~");
                System.out.println(" ");
                for (Student x : listOfStudents) {
                    System.out.println(x);
                    System.out.println("");
                }
                choice = input.nextLine();
                choiceInt = Integer.parseInt(choice);
                System.out.println("~~~~~~~~~Please select the **CourseID** of the Course~~~~~~~~~");
                System.out.println(" ");
                printListOfCourses();
                choice2 = input.nextLine();
                choiceInt2 = Integer.parseInt(choice2);
                sdi.addExistingStudentToCourse(choiceInt2, choiceInt);

            }
        } catch (NumberFormatException e) {
            System.err.println("Please select with number");
        }

    }

    public static void studentsWithCourses() {
        Scanner input = new Scanner(System.in);
        String choice = null;
        String choice2 = null;
        int choiceInt;
        int choiceInt2;
        StudentDaoInt sdi = new StudentDaoImpl();

        try {
            System.out.println("~~~~~~~~~Please select a StudentID to add them to a Course~~~~~~~~~");
            printListOfStudents();
            choice = input.nextLine();
            choiceInt = Integer.parseInt(choice);

            System.out.println("~~~~~~~~~Please select the CourseID of the Course~~~~~~~~~");
            printListOfCourses();
            choice2 = input.nextLine();
            choiceInt2 = Integer.parseInt(choice2);

            sdi.addExistingStudentToCourse(choiceInt2, choiceInt);

        } catch (NumberFormatException e) {
            System.err.println("Please select with number");
        }

    }

}
