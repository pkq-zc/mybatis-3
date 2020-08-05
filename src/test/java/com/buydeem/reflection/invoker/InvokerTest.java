package com.buydeem.reflection.invoker;

import org.apache.ibatis.reflection.invoker.GetFieldInvoker;
import org.apache.ibatis.reflection.invoker.MethodInvoker;
import org.apache.ibatis.reflection.invoker.SetFieldInvoker;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zengchao
 * @version 1.0.0
 * @ClassName InvokerTest.java
 * @Description TODO
 * @createTime 2020年08月05日 21:23:00
 */
public class InvokerTest {
  @Test
  public void getOrSetInvoker() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
    User user = new User();
    Field age = User.class.getDeclaredField("age");
    //
    GetFieldInvoker getFieldInvoker = new GetFieldInvoker(age);
    Object getAge = getFieldInvoker.invoke(user, new Object[0]);
    System.out.println(getAge);
    //
    SetFieldInvoker setFieldInvoker = new SetFieldInvoker(age);
    setFieldInvoker.invoke(user,new Object[]{20});
    System.out.println(user);
  }

  @Test
  public void methodInvoker() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    User user = new User();
    Method method = User.class.getDeclaredMethod("setUserName", String.class);
    MethodInvoker methodInvoker = new MethodInvoker(method);
    methodInvoker.invoke(user, new Object[]{"jack"});
    System.out.println(user);
  }

}

class User{
  private String userName;
  private Integer age = 18;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Override
  public String toString() {
    return "User{" +
      "userName='" + userName + '\'' +
      ", age=" + age +
      '}';
  }
}
