package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args){
        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try{
            //use the session object to save Java object
            //create 3 students object
            System.out.println("Creating new student...");
            Student tempStudent1 = new Student("John","Doe","john@luv2code.com");
            Student tempStudent2 = new Student("Mary","Public","mary@luv2code.com");
            Student tempStudent3 = new Student("Bonita","Applebum","bonita@luv2code.com");

            //Start/begin transaction
            session.beginTransaction();

            //Save student object
            System.out.println("Saving the students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            //commint transaction
            session.getTransaction().commit();
            System.out.println("Commit done.");

        }finally {
            factory.close();
        }
    }
}
