package com.example.shirodemo.service.impl;

import com.example.shirodemo.entity.User;
import com.example.shirodemo.mapper.UserMapper;
import com.example.shirodemo.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jocken
 * @since 2019-08-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
