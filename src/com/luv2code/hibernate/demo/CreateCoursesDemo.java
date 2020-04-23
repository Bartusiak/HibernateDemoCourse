package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCoursesDemo {

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
            //create the objects

            //associate the objects

            //start a transaction
            session.beginTransaction();

            //get the instructor from db
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            //create some courses
            Course tempCourse1 = new Course("Air Guitor - for begginers");
            Course tempCourse2 = new Course("The Pinball");

            //add courses to instructors
            tempInstructor.add(tempCourse1);
            tempInstructor.add(tempCourse2);
            //save the courses
            session.save(tempCourse1);
            session.save(tempCourse2);

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
