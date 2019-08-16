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
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response insert(@RequestBody User user) {
        Response response = new Response(StatusCode.UI.UI_0);
        userService.insert(user);
        return response;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Response delete(@RequestBody User user) {
        Response response = new Response(StatusCode.UI.UI_0);
        userService.deleteById(user.getId());
        return response;
    }

    @RequestMapping(path = "/select", method = RequestMethod.POST)
    @ResponseBody
    public ListResponse<User> select(@RequestBody User user) {
        ListResponse<User> response = new ListResponse<>(StatusCode.UI.UI_0);
        EntityWrapper<User> ewUser = new EntityWrapper<>();
        ewUser.where("valid={0}", user.getValid()).or("id={0}", user.getId());
//        if (user.getId() != null) {
//            ewUser.eq("id", user.getId());
//        }
//        if (user.getEmail() != null) {
//            ewUser.eq("email", user.getEmail());
//        }
//        if (user.getName() != null) {
//            ewUser.eq("name", user.getName());
//        }
//        if (user.getValid() != null) {
//            ewUser.eq("valid", user.getValid());
//        }
        //
        Page<User> users = userService.selectPage(new Page<>(0, Integer.MAX_VALUE), ewUser);
        response.setTotal(users.getRecords().size());
        response.setList(users.getRecords());
        return response;
    }

}

