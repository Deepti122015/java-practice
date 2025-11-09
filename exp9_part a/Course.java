package com.nimbus.springdi;

public class Course {
    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public void displayCourse() {
        System.out.println("Enrolled Course: " + courseName);
    }
}
