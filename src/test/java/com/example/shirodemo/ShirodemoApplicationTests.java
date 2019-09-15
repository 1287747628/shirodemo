package com.example.shirodemo;

import com.example.shirodemo.config.CodeGenerator;
import com.example.shirodemo.constants.ProjectConstant;
import com.example.shirodemo.entity.User;
import com.example.shirodemo.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShirodemoApplicationTests {

    @Autowired
    private ProjectConstant projectConstant;
    @Autowired
    private RedisService redisService;

    @Test
    public void contextLoads() {
        // CodeGenerator gse = new CodeGenerator();
        // 要给那些表生成
        // gse.generateByTables(false,"user", "user_role","role_permission","role","permission");
    }

    @Test
    public void testOne() {
        User user = new User();
        user.setId(3L);
        user.setName("lili");
        user.setMob("135");
        redisService.put("four", user, 300);
        System.out.println(">>> " + projectConstant);
    }

}
