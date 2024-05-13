package com.example.restapi.api.models;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private int created_by;

    @Column
    private int updated_by;

    @Column
    private Date updated_at;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getcreated_by() {
        return created_by;
    }

    public void setcreated_by(int created_by) {
        this.created_by = created_by;
    }

    public int getupdated_by() {
        return updated_by;
    }

    public void setupdated_by(int updated_by) {
        this.updated_by = updated_by;
    }

    public Date getupdated_at() {
        return updated_at;
    }

    public void setupdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
