package com.y.gui.dto;

import lombok.Data;

@Data
public class UserDTO {
    /**
     * 用户ID
     */
    private Integer id;

    private String name;

    private Integer age;

    private Integer gender;

    private String address;
}
