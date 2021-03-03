package com.example.shirodemo;

import com.alibaba.fastjson.JSON;
import com.example.shirodemo.vo.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShirodemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerAdapterTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testOne() {
        String resp = testRestTemplate.getForObject("/shirodemo/rest/user/test/constant", String.class);
        System.out.println(">>>" + JSON.toJSONString(resp));
    }
}
