package com.tiy;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by localdom on 5/20/2016.
 */
@Entity
@Table(name = "gaming_formats")
public class GamingFormat {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;

    public int getId() {
        return id;
    }

    public GamingFormat() {
    }

    public GamingFormat(String name) {
        this.name = name;
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

}
