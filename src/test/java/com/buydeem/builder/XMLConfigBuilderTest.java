package com.buydeem.builder;

import com.buydeem.mapper.BookMapper;
import com.buydeem.model.Book;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.net.URL;
import java.util.List;

/**
 * XMLConfigBuilder
 * Created by zengchao on 2020/8/17.
 */
public class XMLConfigBuilderTest {
  @Test
  public void xmlConfiguration(){
    InputStream in = this.getClass().getClassLoader().getResourceAsStream("mybatis-config.xml");
    XMLConfigBuilder builder = new XMLConfigBuilder(in);
    Configuration configuration = builder.parse();
    //创建SqlSessionFactory 并获取session
//    DefaultSqlSessionFactory factory = new DefaultSqlSessionFactory(configuration);
//    SqlSession session = factory.openSession();
//    //获取mapper
//    BookMapper mapper = session.getMapper(BookMapper.class);
//
//    List<Book> books = mapper.selectList();

  }

}
