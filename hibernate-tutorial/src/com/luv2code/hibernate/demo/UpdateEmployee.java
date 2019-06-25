package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateEmployee {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory = new Configuration()
                                 .configure("employee.cfg.xml")
                                 .addAnnotatedClass(Employee.class)
                                 .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try{
            int employeeId = 1;

            //Start/begin transaction
            session.beginTransaction();

            System.out.println("\nGetting student with id: " + employeeId);
            Employee myEmployee = session.get(Employee.class, employeeId);

            System.out.println("Updating employee...");
            myEmployee.setFirstName("Scooby");

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Commit done.");

            //
            session = factory.getCurrentSession();

            //Start/begin transaction
            session.beginTransaction();

            //update email for all students
            System.out.println("Update email for all students");
            session.createQuery("update Employee set company = 'Maria Company'")
                    .executeUpdate();

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Commit done.");

        }finally {
            factory.close();
        }
    }
}
