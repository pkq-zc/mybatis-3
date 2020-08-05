package com.buydeem;

import com.buydeem.mapper.BookMapper;
import com.buydeem.model.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author zengchao
 * @version 1.0.0
 * @ClassName App1.java
 * @Description TODO
 * @createTime 2020年07月21日 20:21:00
 */
public class App1 {
  private SqlSessionFactory factory;

  /**
   * 解析mybatis-config 文件并将内容存储到config对象 然后创建
   * @throws IOException
   */
//  @Before
//  public void setSqlSessionFactory() throws IOException {
//    Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
//    factory = new SqlSessionFactoryBuilder().build(reader,"dev");
//  }

  @Test
  public void queryBook(){
    SqlSession sqlSession = factory.openSession();
    BookMapper mapper = sqlSession.getMapper(BookMapper.class);
  }

  @Test
  public void testSql(){
    //查询
    String querySql = new SQL()
      .SELECT("user_name", "password")
      .FROM("t_user")
      .WHERE("user_name = 'tom'", "password = 'root'")
      .toString();
    System.out.println(querySql);
    //插入
    String insertSql = new SQL()
      .INSERT_INTO("t_user")
      .VALUES("user_name", "tom")
      .VALUES("password", "root")
      .toString();
    System.out.println(insertSql);
    //delete
    String deleteSql = new SQL()
      .DELETE_FROM("t_user")
      .WHERE("user_name = 'tom'")
      .toString();
    System.out.println(deleteSql);
    //连表查询
    String leftJoinQuery = new SQL()
      .SELECT("t1.user_name", "t2.phone")
      .FROM("t_user t1")
      .LEFT_OUTER_JOIN("t_user_info t2 ON t1.id = t2.user_id")
      .toString();
    System.out.println(leftJoinQuery);
  }

  @Test
  public void testScriptRunner() throws IOException, SQLException {
    //获取connection
    Connection connection = factory.openSession().getConnection();
    //创建runner
    ScriptRunner runner = new ScriptRunner(connection);
    //提示警告
    runner.setThrowWarning(true);
    //遇到异常停止
    runner.setStopOnError(true);
    //运行脚本
    InputStream in = this.getClass().getResourceAsStream("/test_script.sql");
    runner.runScript(new InputStreamReader(in));
    //关闭资源
    connection.close();
    in.close();
  }

  @Test
  public void sqlRunner() throws SQLException {
    //获取connection
    Connection connection = factory.openSession().getConnection();
    connection.setAutoCommit(true);
    //创建runner
    SqlRunner runner = new SqlRunner(connection);
    runner.setUseGeneratedKeySupport(true);
    //插入
    String createId = new SQL()
      .INSERT_INTO("t_book")
      .VALUES( "book_name","'三体'")
      .toString();
    System.out.println("create row id is :"+createId);
    //删除
    String deleteSql = new SQL()
      .DELETE_FROM("t_book")
      .WHERE("id = ?")
      .toString();
    int deleteRows = runner.delete(deleteSql, 21);
    System.out.println("delete row count is :"+ deleteRows);
    //查询
    String querySql = new SQL()
      .SELECT("*")
      .FROM("t_book")
      .toString();
    List<Map<String, Object>> list = runner.selectAll(querySql);
    for (Map<String, Object> temp : list) {
      System.out.println(temp);
    }
    //关闭连接
    connection.close();
  }

  @Test
  public void metaObject(){

  }

  @Test
  public void propertyTokenizer(){
    PropertyTokenizer tokenizer = new PropertyTokenizer("user[0].name");
    System.out.println(tokenizer);
  }

  @Test
  public void reflector() throws NoSuchMethodException {
    Reflector reflector = new Reflector(Book.class);
  }

}
