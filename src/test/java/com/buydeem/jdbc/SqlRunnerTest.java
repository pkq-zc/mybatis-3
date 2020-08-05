package com.buydeem.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.jdbc.SqlRunner;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author zengchao
 * @version 1.0.0
 * @ClassName SqlRunnerTest.java
 * @Description TODO
 * @createTime 2020年08月05日 20:30:00
 */
public class SqlRunnerTest {

  @Test
  public void sqlRunnerTest() throws SQLException {
    HikariConfig config = new HikariConfig();
    config.setUsername("root");
    config.setPassword("pieceofbake");
    config.setDriverClassName("com.mysql.cj.jdbc.Driver");
    config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8&autoReconnect=true&useSSL=false");
    HikariDataSource dataSource = new HikariDataSource(config);
    Connection connection = dataSource.getConnection();
    //创建SqlRunner对象
    SqlRunner runner = new SqlRunner(connection);
    //创建sql语句
    String sql = new SQL()
      .SELECT("*")
      .FROM("t_user")
      .toString();
    //查询
    List<Map<String, Object>> maps = runner.selectAll(sql);
    System.out.println(maps);

  }
}
