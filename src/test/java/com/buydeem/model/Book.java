package com.buydeem.model;

/**
 * @author zengchao
 * @version 1.0.0
 * @ClassName Book.java
 * @Description TODO
 * @createTime 2020年07月27日 20:19:00
 */
public class Book {
  private Integer id;
  private String bookName;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public Book() {
  }

  public Book(Integer id) {
    this.id = id;
  }

  public Book(String bookName) {
    this.bookName = bookName;
  }

  public Book(Integer id, String bookName) {
    this.id = id;
    this.bookName = bookName;
  }

  @Override
  public String toString() {
    return "Book{" +
      "id=" + id +
      ", bookName='" + bookName + '\'' +
      '}';
  }
}
