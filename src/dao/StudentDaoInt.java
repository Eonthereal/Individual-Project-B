/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Student;
import java.util.List;

/**
 *
 * @author eon_A
 */
public interface StudentDaoInt {
    
    public List<Student> getAllStudents();
    
    
    public List<Student> getStudentsByCourseId(int stid);
    
    public List<Student> getStudentsMoreThanOneCourses();
    
    public int maxStudentId ();
    
    public void insertStudent (Student s);
    
    public List<Student> printListOfStudentsWithoutCourse();
    
    public void addExistingStudentToCourse(int cid, int sid);
    
    
    
}
