/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author eon_A
 */
public class Student {
    private int stundentID;
    private String firstName;
    private String lastName;
    private LocalDate DateOfBirth;
    private int TuitionFees;

    public Student() {
    }

    public Student(int stundentID, String firstName, String lastName, LocalDate DateOfBirth, int TuitionFees) {
        this.stundentID = stundentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DateOfBirth = DateOfBirth;
        this.TuitionFees = TuitionFees;
    }

    public int getStundentID() {
        return stundentID;
    }

    public void setStundentID(int stundentID) {
        this.stundentID = stundentID;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(LocalDate DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    public int getTuitionFees() {
        return TuitionFees;
    }

    public void setTuitionFees(int TuitionFees) {
        this.TuitionFees = TuitionFees;
    }

    @Override
    public String toString() {
        return "StundentID=" + stundentID + ", First Name=" + firstName + ", Last Name=" + lastName + ", Date Of Birth=" + DateOfBirth + ", Tuition Fees=" + TuitionFees;
    }
    
    
    
    
}
