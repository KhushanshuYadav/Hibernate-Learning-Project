package com.khushanshu;

//this is our class which will hold the data in java code
//Now we will store this data in database using hibernate

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//using entity annotation to mark this class as entity

@Entity
public class Student {

    @Id                                                     //marking a field as Id i.e primary key which is must in hibernate for unique identification
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // Auto-incremented ID
    private int sId;
    private String sName;
    private int sAge;

    public void setsId(int sId) {
        this.sId = sId;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public void setsAge(int sAge) {
        this.sAge = sAge;
    }

    public int getsId() {
        return sId;
    }

    public String getsName() {
        return sName;
    }

    public int getsAge() {
        return sAge;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sId=" + sId +
                ", sName='" + sName + '\'' +
                ", sAge=" + sAge +
                '}';
    }
}
