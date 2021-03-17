package com.example.shirodemo.update;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.shirodemo.entity.User;
import com.example.shirodemo.service.UserService;
import com.example.shirodemo.utils.AESUtil;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * 用于OSS黑龙江联通现场密码加密升级
 */
@Service
public class HljUpdateUserPasswordImpl {

    @Resource
    private UserService userService;

    @PostConstruct
    public void initPassword() throws Exception {
//        List<User> users = userService.selectRestAll();
//        for (User user : users) {
//            String date = "2019-05-28 20:00:05";
//            String newPwd = AESUtil.encrypt(user.getPassword());
//            userService.updateForSet("password='" + newPwd + "',pwdUpdateTime='" + date + "'", new EntityWrapper<User>().where("id={0}", user.getId()));
//        }
    }

}
