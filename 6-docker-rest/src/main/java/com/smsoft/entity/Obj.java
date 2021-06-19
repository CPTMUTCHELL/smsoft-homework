package com.smsoft.entity;

import javax.persistence.*;

@Entity
@Table(name = "obj_table")
public class Obj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
