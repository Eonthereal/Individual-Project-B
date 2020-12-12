/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author eon_A
 */
public class Assignment {
    private int assignmentID;
    private String title;
    private String description;

    public Assignment() {
    }

    public Assignment(int assignmentID, String title, String description) {
        this.assignmentID = assignmentID;
        this.title = title;
        this.description = description;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Assignment{" + "assignmentID=" + assignmentID + ", title=" + title + ", description=" + description + '}';
    }
    
    
    
}
