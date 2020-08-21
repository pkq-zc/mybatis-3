package com.buydeem.mapper;

import com.buydeem.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zengchao on 2020/8/21.
 */
public interface UserMapper {

  User getById(@Param("id") Integer id);
}
