package com.Picasso.dao;

import com.Picasso.entity.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDaoTest {

  /**
   * 通过名字查询用户信息
   */
  @Select("SELECT * FROM Account WHERE name = #{username}")
  Account findUserByName(@Param("username") String username);

  /**
   * 查询所有用户信息
   */
  @Select("SELECT * FROM Account")
  List<Account> findAllUser();

  /**
   * 插入用户信息
   */
  @Insert("INSERT INTO Account(password,username) VALUES(#{password}, #{username})")
  void insertUser(@Param("password") String password, @Param("username") String username);

  /**
   * 根据 id 更新用户信息
   */
  @Update("UPDATE  Account SET username = #{username},password = #{password} WHERE user_id = #{user_id}")
  void updateUser(@Param("username") String username, @Param("password") String password,
      @Param("user_id") int user_id);

  /**
   * 根据 id 删除用户信息
   */
  @Delete("DELETE from user WHERE user_id = #{user_id}")
  void deleteUser(@Param("user_id") int user_id);
}
