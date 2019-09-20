package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    @Column(nullable = false)
    private String booktitle;

    private String isbn;

    private int copy;

    @ManyToMany(mappedBy = "bookList")
    @JsonIgnoreProperties(value = "bookList")
    @JoinTable(name = "wrote", joinColumns = {@JoinColumn(name = "bookid")}, inverseJoinColumns = {@JoinColumn(name = "authorid")})
    List<Authors> authorsList = new ArrayList<>();

    public Book()
    {
    }

    public Book(String booktitle, String isbn, int copy)
    {
        this.booktitle = booktitle;
        this.isbn = isbn;
        this.copy = copy;
    }

    public Book(String booktitle, String isbn, int copy, List<Authors> authorsList)
    {
        this.booktitle = booktitle;
        this.isbn = isbn;
        this.copy = copy;
        this.authorsList = authorsList;
    }

    public long getBookid()
    {
        return bookid;
    }

    public void setBookid(long bookid)
    {
        this.bookid = bookid;
    }

    public String getBooktitle()
    {
        return booktitle;
    }

    public void setBooktitle(String booktitle)
    {
        this.booktitle = booktitle;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public int getCopy()
    {
        return copy;
    }

    public void setCopy(int copy)
    {
        this.copy = copy;
    }

    public List<Authors> getAuthorsList()
    {
        return authorsList;
    }

    public void setAuthorsList(List<Authors> authorsList)
    {
        this.authorsList = authorsList;
    }
}
