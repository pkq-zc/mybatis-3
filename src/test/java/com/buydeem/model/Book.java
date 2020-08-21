package com.buydeem.model;

import java.math.BigDecimal;

/**
 * Created by zengchao on 2020/8/17.
 */
public class Book {
  private Integer id;
  private String bookName;
  private BigDecimal price;

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

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
