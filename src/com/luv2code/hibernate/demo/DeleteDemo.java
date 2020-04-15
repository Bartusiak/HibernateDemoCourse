package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteDemo {

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

            //get instructor by primary key / id
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class,theId);

            System.out.println("Found instructor: " + tempInstructor);
            //delete the instructor
            if (tempInstructor!=null){
                System.out.println("Deleting... " + tempInstructor);
                // CascadeType.ALL so delete too associated "details" object
                session.delete(tempInstructor);
            }
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
