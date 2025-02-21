package com.khushanshu;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.jaxb.SourceType;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        System.out.println("Hello and welcome!");

        Scanner sc = new Scanner(System.in);

        Student s1= new Student();
        s1.setsId(1);
        s1.setsName("John Doe");
        s1.setsAge(18);


        Configuration config=new Configuration();

        config.addAnnotatedClass(com.khushanshu.Student.class);          //this will add the class to configuration

        config.configure("hibernate.cfg.xml");                  //this will configure our connection details i.e will load our config file details we can also pass parameter if config file is other than default

        SessionFactory sessionFactory= config.buildSessionFactory();

        Session session= sessionFactory.openSession();                  //it will open a new session for us i.e connection with databases

        session.persist(s1);                                            //saves data to database as per our mapping on object





    }
}