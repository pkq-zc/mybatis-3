package com.buydeem.reflection;

import org.apache.ibatis.reflection.TypeParameterResolver;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by zengchao on 2020/8/6.
 */
public class TypeParameterResolverTest {

  @Test
  public void test1() throws NoSuchFieldException {
    Test1<String> test1 = new Test1<>();
    Field[] fields = test1.getClass().getDeclaredFields();

    for (Field field : fields) {
      Type type = TypeParameterResolver.resolveFieldType(field, test1.getClass());
      System.out.println(type);
    }
  }

}

class Test1<T>{
  private String age;
  private Map<String,Object> map;
  private T data;
}
