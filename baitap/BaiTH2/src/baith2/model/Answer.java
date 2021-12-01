/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baith2.model;

import java.io.Serializable;

/**
 *
 * @author a
 */
public class Answer implements Serializable {
    static final long serialVersionUID = 21L;
    private Student student;

    public Answer() {
    }

    public Answer(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
