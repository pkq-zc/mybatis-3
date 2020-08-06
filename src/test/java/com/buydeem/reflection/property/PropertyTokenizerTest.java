package com.buydeem.reflection.property;

import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.junit.jupiter.api.Test;

/**
 * Created by zengchao on 2020/8/6.
 */
public class PropertyTokenizerTest {
  @Test
  public void test1(){
    // "order.goodsList[0].qty"
    // "data.orders[1].goodsList[1].qty"
    PropertyTokenizer tokenizer = new PropertyTokenizer("data.orders[1].goodsList[1].qty");
    PropertyTokenizer temp = tokenizer;
    while (temp.hasNext()){
      System.out.println("temp.getName() = " + temp.getName());
      System.out.println("temp.getIndex() = " + temp.getIndex());
      System.out.println("temp.getIndexedName() = " + temp.getIndexedName());
      System.out.println();
      temp = temp.next();
    }
  }
}
