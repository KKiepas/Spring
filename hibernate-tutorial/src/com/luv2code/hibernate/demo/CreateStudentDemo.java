package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {
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
            //create Student object
            System.out.println("Creating new student...");
            Student tempStudent = new Student("Paul","Wall","paul@luv2code.com");

            //Start/begin transaction
            session.beginTransaction();

            //Save student object
            System.out.println("Saving the student...");
            session.save(tempStudent);

            //commint transaction
            session.getTransaction().commit();
            System.out.println("Commit done.");

        }finally {
            factory.close();
        }
    }
}
