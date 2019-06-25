package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteEmployee {
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

            //retrive student with id = 1
            System.out.println("\nGetting employee with id: " + employeeId);
            Employee myEmployee = session.get(Employee.class, employeeId);

            //delete the student
            System.out.println("Deleting employee:" + myEmployee);
            session.delete(myEmployee);

            //delete student with id = 2
            System.out.println("Deleting employee with id = 2");
            session.createQuery("delete from Employee where id = 2").executeUpdate();

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Commit done.");
        }finally {
            factory.close();
        }
    }
}
