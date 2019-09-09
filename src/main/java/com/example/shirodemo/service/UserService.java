package com.example.shirodemo.service;

import com.example.shirodemo.entity.User;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jocken
 * @since 2019-08-15
 */
public interface UserService extends IService<User> {

    void updateByUserId();

    List<User> selectByName(String name,Integer valid);

    List<User> selectRestAll();

}
