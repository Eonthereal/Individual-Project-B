/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indivpro_zografos_part_b;


import model.Assignment;
import model.Student;
import dao.AssignmentDaoImpl;
import dao.AssignmentDaoInt;
import dao.StudentDaoImpl;
import dao.StudentDaoInt;
import java.util.List;


/**
 *
 * @author eon_A
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

       // indexPage();indexPage();
  
        StudentDaoInt sdi = new StudentDaoImpl();
        
        List <Student> result = sdi.getAllStudents();
        for (Student x : result){
            System.out.println(x);
        }
        
        System.out.println(" ");
        
        AssignmentDaoInt adi = new AssignmentDaoImpl();
        
        List <Assignment> assignmentresult= adi.getAllAssignments();
        for (Assignment x : assignmentresult){
            System.out.println(x);
        }
        }
    
}
