package com.kayvanbree.rockstardb.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity()
public class Band {
    @Id()
    private long id;

    private String name;
}
