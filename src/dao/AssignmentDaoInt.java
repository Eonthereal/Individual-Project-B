/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.StudentAssignmentDto;
import model.Assignment;
import java.util.List;

/**
 *
 * @author eon_A
 */
public interface AssignmentDaoInt {
    
    public List<Assignment> getAllAssignments();
     
    public List<Assignment> getAssignmentsByCourseId(int cid);
    
    public List<StudentAssignmentDto> getAssignmentsByStudentId(int sid);
    
    public int maxAssignmentId();
    
    public void insertAssingnment(Assignment a);
    
    
}
