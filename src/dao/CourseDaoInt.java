/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Course;
import java.util.List;

/**
 *
 * @author eon_A
 */
public interface CourseDaoInt {
    public List<Course> getAllCourses();
    
    public Course getCourseById(int cid);
    
    public int maxCourseId();
    
    public void insertCourse(Course c);
    
}
