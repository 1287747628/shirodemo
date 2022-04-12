package com.example.shirodemo.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jocken
 * @date 2022/4/12
 */
@RestController
@RequestMapping("/device")
public class DeviceController {

    @RequestMapping("/list")
    public String list() {
        return "device list";
    }

}
