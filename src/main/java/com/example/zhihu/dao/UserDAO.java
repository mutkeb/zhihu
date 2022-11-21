package com.example.zhihu.dao;

import com.example.zhihu.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDAO {

    /**
     * 增加一个用户
     */
    int add(UserDO userDO);

    /**
     *更新用户信息
     */
    int update(UserDO userDO);

    /**
     *删除用户
     */
    int delete(@Param("id") long id);

    /**
     * 根据用户姓名查找用户
     */
    UserDO findByUserName(@Param("userName") String userName);

    /**
     * 根据用户id查找用户
     */
    UserDO findByUserId(@Param("id") long id);

    /**
     *查找所有用户
     */
    List<UserDO> findAll();

    /**
     * 加入一连串用户，主要用于初始化数据
     */
    int batchAdd(@Param("list") List<UserDO> userDOS);

    /**
     * 根据邮箱寻找用户
     */
    UserDO findByEmail(@Param("email") String email);

    /**
     * 根据电话号码寻找用户
     */
    UserDO findByMobile(@Param("mobile") String mobile);
}
