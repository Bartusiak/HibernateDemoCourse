package com.luv2code.hibernate.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="instructor_detail")
public class InstructorDetail {

    //annotate the calss as ana entity and map to db table

    //define the fields

    //annotate the fields with db column names

    //create constructors

    //gen getter/setter

    //gen toString() method

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="youtube_channel")
    private String ytChannel;
    @Column(name="hobby")
    private String hobby;

    public InstructorDetail(){

    }

    public InstructorDetail(String ytChannel, String hobby){
        this.ytChannel=ytChannel;
        this.hobby=hobby;
    }

    public String getYtChannel() {
        return ytChannel;
    }

    public void setYtChannel(String ytChannel) {
        this.ytChannel = ytChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", ytChannel='" + ytChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
