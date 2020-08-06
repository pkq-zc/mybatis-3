package com.buydeem.reflection;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.junit.jupiter.api.Test;

/**
 * Created by zengchao on 2020/8/6.
 */
public class MetaClassTest {

  @Test
  public void test1(){
    MetaClass metaClass = MetaClass.forClass(Test4.class, new DefaultReflectorFactory());
    MetaClass data = metaClass.metaClassForProperty("test5");
    System.out.println("data = " + data);

    String name = metaClass.findProperty("test5.data2");
    System.out.println(name);
  }
}

class Test4{
  private String name;
  private Test5 test5;
}

class Test5{
  private String data;
}
