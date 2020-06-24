package com.wdn.practicalworks.models.pr1.elements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "group")
@XmlAccessorType(XmlAccessType.FIELD)
public class Group {
    private ArrayList<Student> students;

    public Group() {
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void recalc(){
        for (Student student:students) {
            student.reculc();
        }
    }
}
