package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class GetCourseAndReviewsDemo {

    public static void main(String[] args){

        //Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        //Create session
        Session session = factory.getCurrentSession();

        try{

            //start a transaction
            session.beginTransaction();

            // get the course
            int getId = 10;
            Course tempCourse = session.get(Course.class, getId);

            // print the course
            System.out.println(tempCourse);

            // print the course reviews
            System.out.println(tempCourse.getReviews());

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
