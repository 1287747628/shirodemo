package com.example.shirodemo.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jocken
 * @date 2022/4/12
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/list")
    public String list() {
        return "admin list";
    }

}
