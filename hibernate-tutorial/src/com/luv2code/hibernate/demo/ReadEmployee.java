package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadEmployee {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory = new Configuration()
                                 .configure("employee.cfg.xml")
                                 .addAnnotatedClass(Employee.class)
                                 .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try{
            //use the session object to save Java object
            //create Student object
            System.out.println("Creating new student...");
            Employee tempEmployee = new Employee("Daffy","Duck","PWPW");

            //Start/begin transaction
            session.beginTransaction();

            //Save student object
            System.out.println("Saving the employee...");
            System.out.println(tempEmployee);
            session.save(tempEmployee);

            //commint transaction
            session.getTransaction().commit();
            System.out.println("Commit done.");

            // -- Retrieving object from database --

            //find out the student1s id: primary key
            System.out.println("Saved employee. Generate id: " + tempEmployee.getId());   //Hibernate automaticly update the id after saving object in the table

            //now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrive student based on the id: primary key
            System.out.println("\nGetting employee with id: " + tempEmployee.getId());
            Employee myEmployee = session.get(Employee.class, tempEmployee.getId());
            System.out.println("Get complete: " + myEmployee);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Commit done.");
            
        }finally {
            factory.close();
        }
    }
}
