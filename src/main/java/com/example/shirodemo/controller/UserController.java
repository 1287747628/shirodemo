package com.example.shirodemo.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.shirodemo.constants.ProjectConstant;
import com.example.shirodemo.constants.StatusCode;
import com.example.shirodemo.entity.User;
import com.example.shirodemo.service.RedisService;
import com.example.shirodemo.service.UserService;
import com.example.shirodemo.vo.ListResponse;
import com.example.shirodemo.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;
    @Resource
    private RedisService redisService;
    @Resource
    private ProjectConstant projectConstant;

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
        userService.insert(user);
        return Response.buildSuccess("success");
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(@RequestBody User user) {
        //userService.update(user, new EntityWrapper<User>().where("id={0}", user.getId()));
        userService.updateForSet("mob='" + user.getMob() + "'", new EntityWrapper<User>().where("id={0}", user.getId()));
        return Response.buildSuccess("success");
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Response delete(@RequestBody User user) {
        userService.deleteById(user.getId());
        return Response.buildSuccess("success");
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

    @RequestMapping(path = "/select/byName", method = RequestMethod.POST)
    @ResponseBody
    public ListResponse<User> selectByName(@RequestBody User user) {
        ListResponse<User> response = new ListResponse<>(StatusCode.UI.UI_0);
        List<User> users = userService.selectByName(user.getName(), user.getValid());
        response.setTotal(users.size());
        response.setList(users);
        return response;
    }

    @RequestMapping("/test/redis")
    @ResponseBody
    public Response testRest() {
        redisService.putMap("three", "threeKey", "threeValue", 500);
        String value = redisService.getMap("three", "threeKey");
        log.info(">>> " + JSON.toJSONString(value));
        return Response.buildSuccess(JSON.toJSONString(value));
    }

    @RequestMapping("/test/constant")
    @ResponseBody
    public Response testConstant() {
        return Response.buildSuccess(projectConstant.toString());
    }

}

