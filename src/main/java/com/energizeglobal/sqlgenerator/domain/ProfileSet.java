package com.energizeglobal.sqlgenerator.domain;

import javax.persistence.*;


@Entity
@Table(name = "profile")
public class ProfileSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
