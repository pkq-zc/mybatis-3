package com.buydeem.reflection;

import org.apache.ibatis.reflection.Reflector;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

/**
 * @author zengchao
 * @version 1.0.0
 * @ClassName Reflector.java
 * @Description TODO
 * @createTime 2020年08月05日 20:59:00
 */
public class ReflectorTest {
  @Test
  public void test1() throws InvocationTargetException, IllegalAccessException {
    Book book = new Book();
    Reflector reflector = new Reflector(book.getClass());
    reflector.getSetInvoker("price").invoke(book,new Object[]{new BigDecimal(10)});
    System.out.println(book);
  }
}

class Book{
  private String bookName;
  private BigDecimal price;
  private String author;
  private Boolean sale;

  public Book(String bookName, BigDecimal price, String author) {
    this.bookName = bookName;
    this.price = price;
    this.author = author;
  }

  public Book() {
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Boolean isSale() {
    return sale;
  }

  public void setSale(Boolean sale) {
    this.sale = sale;
  }

  @Override
  public String toString() {
    return "Book{" +
      "bookName='" + bookName + '\'' +
      ", price=" + price +
      ", author='" + author + '\'' +
      ", sale=" + sale +
      '}';
  }
}
