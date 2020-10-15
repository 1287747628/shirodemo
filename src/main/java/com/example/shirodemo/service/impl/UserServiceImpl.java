package com.example.shirodemo.service.impl;

import com.example.shirodemo.entity.Role;
import com.example.shirodemo.entity.User;
import com.example.shirodemo.mapper.RoleMapper;
import com.example.shirodemo.mapper.UserMapper;
import com.example.shirodemo.service.RoleService;
import com.example.shirodemo.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类,可自定义方法，并添加事务
 * </p>
 *
 * @author jocken
 * @since 2019-08-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    RoleService roleService;


    @Override
    public void updateByUserId() {

    }

    @Override
    public List<User> selectByName(String name,Integer valid) {
        Role role = roleService.selectById(1);
        return baseMapper.selectByName(name,valid);
    }

    @Override
    public List<User> selectRestAll() {
        return baseMapper.selectRestAll();
    }
}
