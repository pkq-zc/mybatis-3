package com.buydeem.binding;

import com.buydeem.mapper.BookMapper;
import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zengchao on 2020/8/21.
 */
public class MethodHandlerTest {

  @Test
  public void test1() throws Throwable {
    MethodHandles.Lookup lookup = MethodHandles.lookup();

    MethodType type = MethodType.methodType(void.class,String.class);

    MethodHandle handle = lookup.findStatic(Person.class, "say", type);

    Object result = handle.invoke("hello");

    System.out.println(result);
  }

  @Test
  public void test2(){
    Proxy.newProxyInstance(MethodHandlerTest.class.getClassLoader(), new Class[]{BookMapper.class}, new InvocationHandler() {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
      }
    });
  }

}
