package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class AddCoursesForJanDemo {

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
            int studentId = 1;
            Student tempStudent = session.get(Student.class,studentId);
            System.out.println("\nLoaded student: " + tempStudent.getCourses());

            //create more courses
            Course tempCourse2 = new Course("Unity for Begginers - Making the first game");
            Course tempCourse3 = new Course("Arkanoid - History course");

            tempCourse2.addStudent(tempStudent);
            tempCourse3.addStudent(tempStudent);

            System.out.println("\nSaving the course...\n");
            session.save(tempCourse2);
            session.save(tempCourse3);
            System.out.println("Saved the course: " + tempCourse2 + tempCourse3);

            //add student to courses

            //save courses

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
