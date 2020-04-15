package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class GetInstructorDeatailDemo {

    public static void main(String[] args){

        //Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        //Create session
        Session session = factory.getCurrentSession();

        try{
            //start a transaction
            session.beginTransaction();

            //get the instructor detail object
            int theId = 299;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);

            //print the instructor detail
            System.out.println("tempInstructorDetail: " + tempInstructorDetail);

            //print the associated instructor
            System.out.println("The associated instructor: " + tempInstructorDetail.getInstructor());

            //commit transaction
            session.getTransaction().commit();
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
