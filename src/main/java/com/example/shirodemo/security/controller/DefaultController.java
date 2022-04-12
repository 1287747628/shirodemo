package com.example.shirodemo.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jocken
 * @date 2022/4/12
 */
@RestController
@RequestMapping("/default")
public class DefaultController {

    @RequestMapping("/list")
    public String login() {
        return "default list";
    }

}
