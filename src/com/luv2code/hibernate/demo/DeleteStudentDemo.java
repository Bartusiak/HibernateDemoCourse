package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteStudentDemo {

    public static void main(String[] args){

        //Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //Create session
        Session session = factory.getCurrentSession();

        try{

            //start a transaction
            session.beginTransaction();

            int studentId = 1;
            Student tempStudent = session.get(Student.class,studentId);
            System.out.println("Student loaded: " + tempStudent);
            System.out.println("\nCourses: " + tempStudent.getCourses());

            //delete student
            System.out.println("\nStudent is deleting... " + tempStudent);
            session.delete(tempStudent);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //add clean up code
            session.close();
            factory.close();
        }
    }

}
