package com.khushanshu;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.jaxb.SourceType;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        System.out.println("Hello and welcome!");



        Student s1= new Student();
        //s1.setsId(1);                                                                                                 //as auto generated so detached so setting it will throw the error
        s1.setsName("John Doe");
        s1.setsAge(18);


        Configuration config=new Configuration();

        config.addAnnotatedClass(com.khushanshu.Student.class);                                                         //this will add the class to configuration

        config.configure("hibernate.cfg.xml");                                                                  //this will configure our connection details i.e will load our config file details we can also pass parameter if config file is other than default

        SessionFactory sessionFactory= config.buildSessionFactory();                                                    //SessionFactory is heavy so close or use try with resources i.e once per session

        Session session= sessionFactory.openSession();                                                                  //it will open a new session for us i.e connection with databases


        //CREATE Operation

        Transaction tx= session.beginTransaction();                                                                   //need to create a transaction before any persistence logic is commited i.e writing to database

        session.persist(s1);                                                                                          //saves data to database as per our mapping on object

        //so always write logic in a transaction so that can be rolled back

        tx.commit();                                                                                                  //commiting a transaction



        //READ Operation

        //two types => eager loading and lazy loading
        //we do not need transaction for read operation

        //Eager Loading

        Student s2=session.get(Student.class,5);                                                                     //class name and primary key function is of Hibernate
        System.out.println(s2);

        Student s3=session.find(Student.class,5);
        System.out.println(s3);                                                                                         //class name and primary key function is of JPA

        //caching is there so query will not be executed twice for same object


        //Lazy Loading
        //they return a proxy object with primary key field as set and query is executed when non-key field is accessed of object

        Student s4=session.getReference(Student.class,1);                                                            //class name and primary key function is of JPA
        System.out.println(s4.getsId()); //query not executed
        System.out.println(s4.getsName()); //query fired


        Student s5=session.load(Student.class,10);                                                                   //class name and primary key function is of Hibernate
        System.out.println(s5.getsId()); //query not executed
        System.out.println(s5.getsName()); //query fired

        Student s6=session.byId(Student.class).load(11);

        Student s7=session.byId(Student.class).getReference(11);
        System.out.println(s7);


        //UPDATE

        //we use merge, update and dirty checking(auto tracking changes) for updating a record

        //merge
        //works for

        Student s8= new Student();

        s8.setsName("Mary Jane");
        s8.setsAge(50);

        Transaction tx0= session.beginTransaction();
        session.persist(s8);
        tx0.commit();

        //say we update the data of Mary jane
        s8.setsAge(26);
        Transaction tx1= session.beginTransaction();
        session.merge(s8);                                                                                              //it updates the record and if not there so adds it
        tx1.commit();

        //update

        Student s9= new Student();

        //s8.setsId(101);
        s8.setsName("Carl Kent");
        s8.setsAge(60);

        Transaction tx2= session.beginTransaction();
        session.update(s8);                                                                                             //it updates the record and if not there in DB so throws EXCEPTION
        tx2.commit();


        //DELETE

        Transaction tx9= session.beginTransaction();
        session.remove(session.get(Student.class,19));                                                               //throws exception if record is not there and is Of JPA
        session.delete(session.get(Student.class,20));                                                               //is of Hibernate
        tx9.commit();




















        sessionFactory.close();





    }
}