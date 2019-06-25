package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyEmployee {
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
            //create 3 employee object
            System.out.println("Creating new employees...");
            Employee tempEmployee1 = new Employee("John","Doe","Perfopol");
            Employee tempEmployee2 = new Employee("Mary","Public","WAT");
            Employee tempEmployee3 = new Employee("Bonita","Applebum","TIMSI");

            //Start/begin transaction
            session.beginTransaction();

            //Save student object
            System.out.println("Saving the employees...");
            session.save(tempEmployee1);
            session.save(tempEmployee2);
            session.save(tempEmployee3);

            //commint transaction
            session.getTransaction().commit();
            System.out.println("Commit done.");

        }finally {
            factory.close();
        }
    }
}
