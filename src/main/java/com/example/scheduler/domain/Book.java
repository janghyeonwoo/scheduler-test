package com.example.scheduler.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Book extends item{
    private String author;
    private String isbn;
}
