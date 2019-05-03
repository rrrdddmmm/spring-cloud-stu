package com.hystrix.consume.entity;


import lombok.*;

import java.io.Serializable;


@ToString
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class User implements Serializable {
    @Getter @Setter private Long id;
    @Getter @Setter private String username;
    @Getter @Setter private Integer age;
}
