package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateDemo {

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
            //create the objects
            Instructor tempInstructor = new Instructor("Ernest","High","ernhigh@gmail.com");

            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com/ernhigh","Flying");

            //associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            //start a transaction
            session.beginTransaction();

            //save the instructor
            //
            //Save too the details object, because of CascadeType.ALL
            //
            System.out.println("Saving instructor: "+tempInstructor);
            session.save(tempInstructor);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            factory.close();
        }
    }

}
