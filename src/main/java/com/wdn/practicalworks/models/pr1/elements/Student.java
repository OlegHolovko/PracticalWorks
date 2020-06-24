package com.wdn.practicalworks.models.pr1.elements;

import javax.xml.bind.annotation.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
    @XmlAttribute
    private String firstname;

    @XmlAttribute
    private String lastname;

    @XmlAttribute
    private String groupnumber;

    private ArrayList<Subject> subjects;

    @XmlElement(name="Average")
    private float average;

    public Student() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGroupnumber() {
        return groupnumber;
    }

    public void setGroupnumber(String groupnumber) {
        this.groupnumber = groupnumber;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public void reculc(){
        float average = 0f;
        int subjectCount = 0;
        float marks = 0f;
        for (Subject subject:subjects) {
            subjectCount++;
            marks += subject.getMark();
        }
        if(subjectCount > 0){
            average = marks/subjectCount;
            if(Math.abs(average - this.getAverage()) > 0.1){
                NumberFormat formatter = NumberFormat.getInstance(Locale.ROOT);
                formatter.setMaximumFractionDigits(1);
                formatter.setMinimumFractionDigits(1);
                formatter.setRoundingMode(RoundingMode.HALF_UP);
                float newAverage = Float.parseFloat(formatter.format(average));
                this.setAverage(newAverage);
            }
        }
    }
}
