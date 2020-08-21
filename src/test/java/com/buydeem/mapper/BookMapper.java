package com.buydeem.mapper;

import com.buydeem.model.Book;

import java.util.List;

/**
 * Created by zengchao on 2020/8/17.
 */
public interface BookMapper {

  List<Book> selectList();
}
