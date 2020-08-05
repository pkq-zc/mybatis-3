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
package org.apache.ibatis.reflection.wrapper;

import java.util.List;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;

/**
 * @author Clinton Begin
 */
public interface ObjectWrapper {

  /**
   * 根据属性分词器获取属性的值
   * @param prop
   * @return
   */
  Object get(PropertyTokenizer prop);

  /**
   * 根据属性分词器设置属性的值
   * @param prop
   * @param value
   */
  void set(PropertyTokenizer prop, Object value);

  /**
   * 查询表达式指定的属性
   * @param name
   * @param useCamelCaseMapping 是否开启驼峰映射
   * @return
   */
  String findProperty(String name, boolean useCamelCaseMapping);

  /**
   * 获取对象可读属性的数组
   * @return
   */
  String[] getGetterNames();

  /**
   * 获取对象可写属性的数组
   * @return
   */
  String[] getSetterNames();

  /**
   * 根据属性表达式获取对应set方法的参数类型
   * @param name
   * @return
   */
  Class<?> getSetterType(String name);

  /**
   * 根据属性表达式获取对应get方法的参数类型
   * @param name
   * @return
   */
  Class<?> getGetterType(String name);

  /**
   * 根据属性表达式获取是否含有set方法
   * @param name
   * @return
   */
  boolean hasSetter(String name);

  /**
   * 根据属性表达式获取是否含有get方法
   * @param name
   * @return
   */
  boolean hasGetter(String name);

  /**
   * 根据属性表达式实例化对象，并set到当前对象
   * @param name 表达式
   * @param prop
   * @param objectFactory
   * @return
   */
  MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory);

  /**
   * 是否是集合
   * @return
   */
  boolean isCollection();

  /**
   * 添加元素到集合
   * @param element
   */
  void add(Object element);

  /**
   * 批量添加元素到集合
   * @param element
   * @param <E>
   */
  <E> void addAll(List<E> element);

}
