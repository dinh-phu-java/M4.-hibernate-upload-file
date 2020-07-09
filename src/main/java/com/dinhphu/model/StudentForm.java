package com.dinhphu.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;


public class StudentForm {

    private int id;

    private String name;

    private MultipartFile file;

    public StudentForm() {
    }

    public StudentForm(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public StudentForm(String name) {
        this.name = name;
    }


    public StudentForm(int id, String name, MultipartFile file) {
        this.id = id;
        this.name = name;
        this.file = file;
    }

    public StudentForm(String name, MultipartFile file) {
        this.name = name;
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", file=" + file +
                '}';
    }
}
