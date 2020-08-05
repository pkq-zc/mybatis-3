package com.buydeem.mapper;

import org.apache.ibatis.submitted.sptests.Book;

public interface BookMapper {
  Book findById(Integer id);
}
