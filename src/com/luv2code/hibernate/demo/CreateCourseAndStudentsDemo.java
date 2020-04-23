package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCourseAndStudentsDemo {

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

            // create a course
            Course tempCourse = new Course("Pacman - How To Score One Million Points");
            Course tempCourse2 = new Course("Unity for Begginers - Making the first game");
            Course tempCourse3 = new Course("Arkanoid - History course");


            //save the course
            System.out.println("\nSaving the course...\n");
            session.save(tempCourse);
            session.save(tempCourse2);
            session.save(tempCourse3);
            System.out.println("Saved the course: " + tempCourse);

            //create the students
            Student tempStudent1 = new Student("Jan","Brzechwa","j_brzechwa@gmail.com");
            Student tempStudent2 = new Student("Stefan","Pompka","stefano@gmail.com");
            Student tempStudent3 = new Student("Gerald","Wither","the_gerald_killer@coders.com");

            // add students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            //save the students
            System.out.println("\nSaving students... ");
            session.save(tempStudent1);
            session.save(tempStudent2);
            System.out.println("Saved students " + tempCourse.getStudents());

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
