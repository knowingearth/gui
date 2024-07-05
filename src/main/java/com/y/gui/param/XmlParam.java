package com.y.gui.param;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
// xml根节点
@XmlRootElement(name = "xml")
// xml映射的范围
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlParam {
    @XmlElement(name = "UserName")
    private String userName;

    @XmlElement(name = "Age")
    private Integer age;

    @XmlElement(name = "Address")
    private String address;
}
