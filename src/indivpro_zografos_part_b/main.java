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
import dao.CourseDaoImpl;
import dao.CourseDaoInt;
import dao.StudentDaoImpl;
import dao.StudentDaoInt;
import dao.TrainerDaoImpl;
import dao.TrainerDaoInt;
import java.util.List;
import model.Course;
import model.Trainer;


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
  
        System.out.println("SELECT * FROM student;");
        StudentDaoInt sdi = new StudentDaoImpl();
        
        List <Student> result = sdi.getAllStudents();
        for (Student x : result){
            System.out.println(x);
        }
        
        System.out.println(" ");
        
        System.out.println("SELECT * FROM assignment;");
        AssignmentDaoInt adi = new AssignmentDaoImpl();
        
        List <Assignment> assignmentresult= adi.getAllAssignments();
        for (Assignment x : assignmentresult){
            System.out.println(x);
        }
        
        
        System.out.println(" ");
        
        System.out.println("SELECT * FROM course;");
        CourseDaoInt cdi = new CourseDaoImpl();
        
        List <Course> courseResult = cdi.getAllCourses();
        for (Course x : courseResult){
            System.out.println(x);
        }
        
        System.out.println("");
        
        
        System.out.println("SELECT * FROM trainer");
        
        TrainerDaoInt tdi = new TrainerDaoImpl();
        
        List<Trainer> trainerResult = tdi.getAllTrainers();
        for (Trainer x : trainerResult){
            System.out.println(x);
        }
        



        }
    
    
    
    
}
