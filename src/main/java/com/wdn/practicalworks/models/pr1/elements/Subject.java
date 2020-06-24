package com.wdn.practicalworks.models.pr1.elements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "subject")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subject {
    @XmlAttribute
    private String title;

    @XmlAttribute
    private int mark;

    public Subject() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
