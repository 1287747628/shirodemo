package com.example.shirodemo.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.shirodemo.constants.StatusCode;
import com.example.shirodemo.entity.User;
import com.example.shirodemo.service.UserService;
import com.example.shirodemo.vo.ListResponse;
import com.example.shirodemo.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jocken
 * @since 2019-08-15
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @RequestMapping("/all")
    @ResponseBody
    public ListResponse<User> getAll() {
        ListResponse<User> response = new ListResponse<>(StatusCode.UI.UI_0);
        List<User> users = userService.selectList(new EntityWrapper<>());
        response.setList(users);
        return response;
    }

    @RequestMapping("/page/all")
    @ResponseBody
    public ListResponse<User> getAll(@RequestParam int pageNum, @RequestParam int pageSize) {
        ListResponse<User> response = new ListResponse<>(StatusCode.UI.UI_0);
        Page<User> users = userService.selectPage(new Page<>(pageNum, pageSize));
        response.setList(users.getRecords());
        response.setTotal(users.getRecords().size());
        return response;
    }

}

