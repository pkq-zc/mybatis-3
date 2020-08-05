/**
 *    Copyright 2009-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.reflection.factory;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.ibatis.reflection.ReflectionException;
import org.apache.ibatis.reflection.Reflector;

/**
 * Mybatis中默认的对象工厂
 * @author Clinton Begin
 */
public class DefaultObjectFactory implements ObjectFactory, Serializable {

  private static final long serialVersionUID = -8855120656740914948L;

  @Override
  public <T> T create(Class<T> type) {
    return create(type, null, null);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
    //解析类型
    Class<?> classToCreate = resolveInterface(type);
    // 通过类型来创建实例
    return (T) instantiateClass(classToCreate, constructorArgTypes, constructorArgs);
  }

  /**
   * 根据类型创建实例
   * @param type 实例的类型
   * @param constructorArgTypes 构造方法参数集合
   * @param constructorArgs 构造方法参数
   * @return
   */
  private  <T> T instantiateClass(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
    try {
      Constructor<T> constructor;
      if (constructorArgTypes == null || constructorArgs == null) {
        //如果构造参数类型和或者构造参数为空,则获取默认的无参构造方法
        constructor = type.getDeclaredConstructor();
        try {
          //创建实例
          return constructor.newInstance();
        } catch (IllegalAccessException e) {
          //如果构造方法私有,例如使用private修饰
          if (Reflector.canControlMemberAccessible()) {
            //设置它可以访问
            constructor.setAccessible(true);
            //再次创建实例
            return constructor.newInstance();
          } else {
            throw e;
          }
        }
      }
      //如果提供了构造参数类型和参数
      constructor = type.getDeclaredConstructor(constructorArgTypes.toArray(new Class[0]));
      try {
        //创建实例
        return constructor.newInstance(constructorArgs.toArray(new Object[0]));
      } catch (IllegalAccessException e) {
        //如果构造方法不可访问
        if (Reflector.canControlMemberAccessible()) {
          constructor.setAccessible(true);
          return constructor.newInstance(constructorArgs.toArray(new Object[0]));
        } else {
          throw e;
        }
      }
    } catch (Exception e) {
      //异常处理
      String argTypes = Optional.ofNullable(constructorArgTypes).orElseGet(Collections::emptyList)
          .stream().map(Class::getSimpleName).collect(Collectors.joining(","));
      String argValues = Optional.ofNullable(constructorArgs).orElseGet(Collections::emptyList)
          .stream().map(String::valueOf).collect(Collectors.joining(","));
      throw new ReflectionException("Error instantiating " + type + " with invalid types (" + argTypes + ") or values (" + argValues + "). Cause: " + e, e);
    }
  }

  //解析类型
  protected Class<?> resolveInterface(Class<?> type) {
    Class<?> classToCreate;
    if (type == List.class || type == Collection.class || type == Iterable.class) {
      //如果对象是集合,则创建一个ArrayList
      classToCreate = ArrayList.class;
    } else if (type == Map.class) {
      //如果对象是集合,则创建一个HashMap
      classToCreate = HashMap.class;
    } else if (type == SortedSet.class) { // issue #510 Collections Support
      //如果是SortedSet,则创建TreeSet
      classToCreate = TreeSet.class;
    } else if (type == Set.class) {
      //如果是Set.则创建HashSet
      classToCreate = HashSet.class;
    } else {
      classToCreate = type;
    }
    return classToCreate;
  }

  @Override
  public <T> boolean isCollection(Class<T> type) {
    return Collection.class.isAssignableFrom(type);
  }

}
