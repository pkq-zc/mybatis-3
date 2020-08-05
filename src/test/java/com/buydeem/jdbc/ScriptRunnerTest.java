package com.buydeem.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

/** 测试执行脚本类
 * @author zengchao
 * @version 1.0.0
 * @ClassName ScriptRunnerTest.java
 * @Description TODO
 * @createTime 2020年08月05日 20:13:00
 */
public class ScriptRunnerTest {
  /**
   * 运行SQL脚本
   * @throws SQLException
   */
  @Test
  public void runTest() throws SQLException {
    //获取连接
    HikariConfig config = new HikariConfig();
    config.setUsername("root");
    config.setPassword("pieceofbake");
    config.setDriverClassName("com.mysql.cj.jdbc.Driver");
    config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8&autoReconnect=true&useSSL=false");
    HikariDataSource dataSource = new HikariDataSource(config);
    Connection connection = dataSource.getConnection();
    connection.setAutoCommit(false);
    //创建ScriptRunner
    ScriptRunner runner = new ScriptRunner(connection);
    //执行
    InputStream in = this.getClass().getResourceAsStream("/test_script.sql");
    runner.runScript(new InputStreamReader(in));
    connection.commit();
  }
}
