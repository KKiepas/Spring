package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory = new Configuration()
                                 .configure("hibernate.cfg.xml")
                                 .addAnnotatedClass(Student.class)
                                 .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try{

            //Start/begin transaction
            session.beginTransaction();

            //query students
            List<Student> theStudents = session
                                        .createQuery("from Student").getResultList();

            //display the students
            displayTheStudents(theStudents);

            //query students: lastName = 'Doe'
            theStudents = session
                            .createQuery("from Student s where s.lastName = 'Doe'").getResultList();

            //display the students
            System.out.println("\n\nLast name: Doe");
            displayTheStudents(theStudents);

            //query students: lastName = 'Doe' or firstName = 'Daffy'
            theStudents = session
                            .createQuery("from Student s where s.lastName = 'Doe' or s.firstName = 'Daffy'").getResultList();

            //display the students
            System.out.println("\n\nLast name: Doe or first name: Daffy");
            displayTheStudents(theStudents);

            //query students: email like %@luv2code.com
            theStudents = session
                            .createQuery("from Student s where s.email like '%luv2code.com'").getResultList();

            //display the students
            System.out.println("\n\nEmail like %luv2code.com");
            displayTheStudents(theStudents);

            //commint transaction
            session.getTransaction().commit();

            System.out.println("Commit done.");

        }finally {
            factory.close();
        }
    }

    private static void displayTheStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }
}
