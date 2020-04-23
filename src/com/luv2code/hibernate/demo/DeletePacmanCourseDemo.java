package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeletePacmanCourseDemo {

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

            //get the student mary from database
            int courseId = 10;
            Course tempCourse = session.get(Course.class,courseId);
            System.out.println("\nLoaded course: " + tempCourse);

            System.out.println("Deleting course.. " + tempCourse);
            session.delete(tempCourse);
            System.out.println("\nCourse deleted");

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
