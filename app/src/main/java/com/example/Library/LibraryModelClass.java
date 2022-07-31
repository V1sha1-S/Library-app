package com.example.Library;

public class LibraryModelClass {

    Integer id;
    String Book_title;
    String Book_author;
    String Pages;

    public LibraryModelClass(String Book_title, String Book_author, String Pages) {
        this.Book_title = Book_title;
        this.Book_author = Book_author;
        this.Pages = Pages;
    }


    public LibraryModelClass(Integer id, String Book_title, String Book_author, String Pages) {
        this.id = id;
        this.Book_title = Book_title;
        this.Book_author = Book_author;
        this.Pages = Pages;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBook_title() {
        return Book_title;
    }

    public void setBook_title(String book_title) {
        Book_title = book_title;
    }

    public String getBook_author() {
        return Book_author;
    }

    public void setBook_author(String book_author) {
        Book_author = book_author;
    }

    public String getPages() {
        return Pages;
    }
}
