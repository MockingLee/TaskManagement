package com.Picasso.dao;

import com.Picasso.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserInfoDao {
    @Select("SELECT * FROM user_info WHERE uid = #{uid}")
    UserInfo findUserById(@Param("uid") int uid);

    @Select("SELECT * FROM user_info")
    List<UserInfo> findAllUser();

    @Insert("INSERT INTO user_info(uid, name, school, profession, phone, sex, birth) VALUES(#{uid}, #{name}, #{school}, #{profession}, #{phone}, #{sex}, #{birth})")
    void insertUserInfo(@Param("uid") int uid, @Param("name") String name, @Param("school") String school, @Param("profession") String profession, @Param("phone") String phone, @Param("sex") int sex, @Param("birth") Date birth);

    @Update("UPDATE  user_info SET name = #{name},school = #{school},profession = #{profession},phone = #{phone},sex = #{sex},birth = #{birth} WHERE uid = #{uid}")
    void updateUserInfo(@Param("name") String name, @Param("school") String school, @Param("profession") String profession, @Param("phone") String phone, @Param("sex") int sex, @Param("birth") Date birth, @Param("uid") int uid);

    @Delete("DELETE from user_info WHERE uid = #{uid}")
    void deleteUserInfo(@Param("uid") int uid);

}
