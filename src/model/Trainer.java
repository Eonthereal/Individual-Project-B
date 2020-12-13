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
public class Trainer {
    private int trainerID;
    private String firstName;
    private String lastName;
    private String subject;
    private Course course; // or CourseID TBD...

    public Trainer() {
    }

    public Trainer(int trainerID, String firstName, String LastName, String Subject, Course course) {
        this.trainerID = trainerID;
        this.firstName = firstName;
        this.lastName = LastName;
        this.subject = Subject;
        this.course = course;
    }

    public int getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String LastName) {
        this.lastName = LastName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String Subject) {
        this.subject = Subject;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "TrainerID=" + trainerID + ", First Name=" + firstName + ", Last Name=" + lastName + ", Subject=" + subject + ", Course: [" + course + "]";
    }
    
    
    
    
}
