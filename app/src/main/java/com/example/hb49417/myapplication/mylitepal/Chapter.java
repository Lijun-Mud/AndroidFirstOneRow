package com.example.hb49417.myapplication.mylitepal;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by LIJUN on 7/23/2017.
 */

public class Chapter extends DataSupport {
    @Column(nullable = false)
    private String title;
    private String comment;
    @Column(nullable = false)
    private Book book;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
