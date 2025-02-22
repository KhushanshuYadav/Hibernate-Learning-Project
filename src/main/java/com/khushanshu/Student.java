package com.khushanshu;

//this is our class which will hold the data in java code
//Now we will store this data in database using hibernate

import jakarta.persistence.*;

//using entity annotation to mark this class as entity

//levels class->entity->table

@Entity
@Table(name="Students")
public class Student {

    @Id                                                     //marking a field as Id i.e primary key which is must in hibernate for unique identification
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // Auto-incremented ID
    @Column(name="Roll_Number")
    private int sId;                                        //use Integer instead of int to have avoid not null constraint i.e cols created by primitive datatypes always has not null constraints as in java also they cannot be null so can lead to exception on retrieval
    @Column(name="Name")
    private String sName;
    @Column(name="Age")
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
