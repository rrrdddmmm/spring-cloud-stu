package com.consume.service.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@ToString
public class User implements Serializable {
    @Getter @Setter private Long id;
    @Getter @Setter private String username;
    @Getter @Setter private Integer age;
}
