package com.r2system.app.SpringSecurityTest.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
