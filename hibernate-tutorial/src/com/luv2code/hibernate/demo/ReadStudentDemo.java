package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {
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
            Student tempStudent = new Student("Daffy","Duck","daffy@luv2code.com");

            //Start/begin transaction
            session.beginTransaction();

            //Save student object
            System.out.println("Saving the student...");
            System.out.println(tempStudent);
            session.save(tempStudent);

            //commint transaction
            session.getTransaction().commit();
            System.out.println("Commit done.");

            //Retrieving object from database

            //find out the student1s id: primary key
            System.out.println("Saved student. Generate id: " + tempStudent.getId());

            //now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrive student based on the id: primary key
            System.out.println("\nGetting student with id: " + tempStudent.getId());
            Student myStudent = session.get(Student.class, tempStudent.getId());
            System.out.println("Get complete: " + myStudent);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Commit done.");
            
        }finally {
            factory.close();
        }
    }
}
