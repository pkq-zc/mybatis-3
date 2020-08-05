package com.buydeem;

import com.buydeem.model.Book;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.BeanWrapper;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.MapWrapper;
import org.apache.ibatis.submitted.result_handler_type.ObjectFactory;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zengchao
 * @version 1.0.0
 * @ClassName App2.java
 * @Description TODO
 * @createTime 2020年08月02日 15:13:00
 */
public class App2 {
  @Test
  public void test1() throws NoSuchMethodException {
    Constructor<Book> constructor = Book.class.getDeclaredConstructor();
    System.out.println(constructor.toString());

    Constructor<Book> constructor1 = Book.class.getDeclaredConstructor(Integer.class,String.class);
    System.out.println(constructor1);
  }

  @Test
  public void test2(){
    DefaultReflectorFactory factory = new DefaultReflectorFactory();
    Reflector reflector = factory.findForClass(Book.class);
    //获取默认的构造方法
    reflector.getDefaultConstructor();
    //获取get方法对应的返回值类型
    Class<?> bookNameType = reflector.getGetterType("bookName");
    //判断是否存在set方法
    System.out.println(bookNameType.getName());
    boolean hasSetter = reflector.hasSetter("bookName");
    System.out.println(hasSetter);
  }

  @Test
  public void test3(){
    Order order = new Order();
    order.setOrderNo("No.1111");
    List<Goods> goodsList = new ArrayList<>();
    Goods goods = new Goods();
    goods.setGoodsName("鞋子");
    goodsList.add(goods);
    order.setGoodsList(goodsList);

    MetaObject metaObject = SystemMetaObject.forObject(order);
    //设置订单金额
    metaObject.setValue("amount",88.8);
    //设置第一个商品的个数
    metaObject.setValue("goodsList[0].count",5);
    //获取第一个商品的名称
    Object goodsName = metaObject.getValue("goodsList[0].goodsName");
    System.out.println(goodsName);

    System.out.println(order);
  }
}

class Order {
  private String orderNo;
  private Double amount;
  private List<Goods> goodsList;

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public List<Goods> getGoodsList() {
    return goodsList;
  }

  public void setGoodsList(List<Goods> goodsList) {
    this.goodsList = goodsList;
  }

  @Override
  public String toString() {
    return "Order{" +
      "orderNo='" + orderNo + '\'' +
      ", amount=" + amount +
      ", goodsList=" + goodsList +
      '}';
  }
}

class Goods{
  private String goodsName;
  private Integer count;

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  @Override
  public String toString() {
    return "Goods{" +
      "goodsName='" + goodsName + '\'' +
      ", count=" + count +
      '}';
  }
}
