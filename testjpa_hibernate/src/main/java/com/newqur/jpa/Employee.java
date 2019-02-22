package com.newqur.jpa;

import javax.persistence.*;

@Entity
@Table(name = "emplyee", indexes = {})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "eid")
    private int id;

    @Column(name = "ename")
    private String name;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
