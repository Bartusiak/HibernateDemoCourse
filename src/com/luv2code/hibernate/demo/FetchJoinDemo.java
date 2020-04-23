package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.query.Query;


public class FetchJoinDemo {

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

            //option 2: Hibernate query with HQL

            //get the instructor detail object
            int theId = 1;

            Query<Instructor> query =  session.createQuery("select i from Instructor i "
                    + "JOIN FETCH i.courses "
                    + "where i.id=:theInstructorId",
                    Instructor.class);

            // set parameter on query
            query.setParameter("theInstructorId", theId);

            // execute query and get instructor
            Instructor tempInstructor = query.getSingleResult();

            System.out.println("DEBUG_CODE: Instructor: " + tempInstructor);

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
