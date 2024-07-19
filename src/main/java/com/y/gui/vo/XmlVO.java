package com.y.gui.vo;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;


@Data
public class XmlVO implements Serializable {
    private static final long serialVersionUID = 8987912149522025050L;

    @XmlElement(name = "userName")
    private String userName;

    @XmlElement(name = "age")
    private Integer age;

    @XmlElement(name = "address")
    private String address;
}
