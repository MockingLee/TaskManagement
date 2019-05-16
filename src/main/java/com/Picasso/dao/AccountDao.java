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

    @Select("SELECT * FROM account WHERE username = #{username} and passwd = #{passwd}")
    Account findAccountByNamePass(@Param("username") String username, @Param("passwd") String passwd);

    @Select("SELECT * FROM account")
    List<Account> findAllAccount();

    @Select("SELECT * FROM account where weight = #{weight}")
    List<Account> findAllAccountByWeight(@Param("weight") int weight);

    @Insert("INSERT INTO account(username,passwd,weight) VALUES(#{username}, #{passwd}, #{weight})")
    void insertAccount(@Param("username") String username, @Param("passwd") String passwd, @Param("weight") int weight);

    @Update("UPDATE  account SET username = #{username},passwd = #{passwd},weight = #{weight} WHERE uid = #{uid}")
    void updateAccount(@Param("username") String username, @Param("passwd") String passwd, @Param("weight") int weight, @Param("uid") int uid);

    @Delete("DELETE from account WHERE uid = #{uid}")
    void deleteAccount(@Param("uid") int uid);
}
