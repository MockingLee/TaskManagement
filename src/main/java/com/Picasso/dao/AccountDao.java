package com.Picasso.dao;

import com.Picasso.entity.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountDao {
  @Select("SELECT * FROM account WHERE uid = #{uid}")
  Account findAccountById(@Param("uid") int uid);

  @Select("SELECT * FROM account WHERE username = #{username}")
  Account findAccountByName(@Param("username") String username);

  @Select("SELECT * FROM account WHERE username = #{username} and password = #{password}")
  Account findAccountByNamePass(@Param("username") String username, @Param("password") String password);

  @Select("SELECT * FROM account")
  List<Account> findAllAccount();

  @Select("SELECT * FROM account where weight = #{weight}")
  List<Account> findAllAccountByWeight(@Param("weight") int weight);

  @Insert("INSERT INTO account(username,password,weight) VALUES(#{username}, #{password}, #{weight})")
  void insertAccount(@Param("username") String username, @Param("password") String password,
      @Param("weight") int weight);

  @Update("UPDATE  account SET username = #{username},password = #{password},weight = #{weight} WHERE uid = #{uid}")
  void updateAccount(@Param("username") String username, @Param("password") String password,
      @Param("weight") int weight, @Param("uid") int uid);

  @Delete("DELETE from account WHERE uid = #{uid}")
  void deleteAccount(@Param("uid") int uid);
}
