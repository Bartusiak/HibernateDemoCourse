package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class EagerLazyDemo {

    public static void main(String[] args){

        //Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
        //Create session
        Session session = factory.getCurrentSession();

        try{
            //start a transaction
            session.beginTransaction();

            //get the instructor detail object
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            System.out.println("DEBUG_CODE: Instructor: " + tempInstructor);

            System.out.println("Courses: " + tempInstructor.getCourses());
            //commit transaction
            session.getTransaction().commit();

            //close the session
            session.close();

            System.out.println("\nCourseCode: The session is now closed !\n");
            // option 1: call getter method while session is open

            //get courses for the instructor
            System.out.println("Courses: " + tempInstructor.getCourses());


            System.out.println("Done!");
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //handle connection leak issue
            session.close();
            factory.close();
        }
    }

}
