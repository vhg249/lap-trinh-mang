/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author a
 */
public class Answer {
    static final long serialVersionUID = 21L;
    private Student s;

    public Answer() {
    }

    public Answer(Student s) {
        this.s = s;
    }

    public Student getS() {
        return s;
    }

    public void setS(Student s) {
        this.s = s;
    }
    
    
}
