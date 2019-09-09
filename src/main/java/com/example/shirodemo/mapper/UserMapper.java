package com.example.shirodemo.mapper;

import com.example.shirodemo.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jocken
 * @since 2019-08-15
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT u.name,u.email FROM user u where name=#{name} and valid=#{valid}")
    List<User> selectByName(@Param("name") String name,@Param("valid") Integer valid);

    @Select("SELECT u.id,u.name,u.password,u.pwdUpdateTime FROM user u")
    List<User> selectRestAll();

}
