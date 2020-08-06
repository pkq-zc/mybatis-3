package com.buydeem.reflection;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengchao on 2020/8/6.
 */
public class MetaObjectTest {
  @Test
  public void test1(){
    Test2 test2 = new Test2();
    MetaObject metaObject = MetaObject.forObject(test2, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());

    metaObject.setValue("userName","tom");
    System.out.println(test2);

    ArrayList<Test3> list = new ArrayList<>();
    list.add(new Test3("1"));
    metaObject.setValue("list",list);

    Object value = metaObject.getValue("list[0].data");
    System.out.println(value);

  }
}

class Test2{
  private String userName;
  private Integer age;
  private List<Test3> list;

  @Override
  public String toString() {
    return "Test2{" +
      "userName='" + userName + '\'' +
      ", age=" + age +
      ", list=" + list +
      '}';
  }
}

class Test3{
  private String data;

  public Test3(String data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "Test3{" +
      "data='" + data + '\'' +
      '}';
  }
}
