package com.buydeem.jdbc;

import org.apache.ibatis.jdbc.SQL;
import org.junit.Test;

/**
 *
 * @author zengchao
 * @version 1.0.0
 * @ClassName SQLTest.java
 * @Description TODO
 * @createTime 2020年08月05日 19:50:00
 */
public class SQLTest {
  /**
   * 创建查询语句
   */
  @Test
  public void querySql(){
    String selectSql = new SQL()
      .SELECT("*")
      .FROM("t_book")
      .WHERE("book_name = '海燕'")
      .WHERE("price > 10")
      .OR()
      .WHERE("id < 100")
      .WHERE("id > 200")
      .toString();
    System.out.println(selectSql);
  }

  /**
   * 创建插入语句
   */
  @Test
  public void insertSql(){
    String insertSql = new SQL()
      .INSERT_INTO("t_book")
      .VALUES("id", "1")
      .VALUES("price", "2.8")
      .VALUES("book_name", "海燕")
      .toString();
    System.out.println(insertSql);
  }

  /**
   * 创建删除语句
   */
  @Test
  public void deleteSql(){
    String deleteSql = new SQL()
      .DELETE_FROM("t_book")
      .WHERE("id = 1")
      .toString();
    System.out.println(deleteSql);
  }

  /**
   * 创建更新语句
   */
  @Test
  public void updateSql(){
    String updateSql = new SQL()
      .UPDATE("t_book")
      .SET("price = 19.2", "book_name = '海燕2'")
      .WHERE("id = 1")
      .toString();
    System.out.println(updateSql);
  }

}

