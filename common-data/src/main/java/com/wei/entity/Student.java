package com.wei.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable, Comparable<Student> {

    private Long id;

    private String name;

    private Integer age;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return this.getId().compareTo(o.getId());
    }
}

