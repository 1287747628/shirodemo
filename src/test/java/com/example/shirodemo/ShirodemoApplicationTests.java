package com.example.shirodemo;

import com.example.shirodemo.config.CodeGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShirodemoApplicationTests {

	@Test
	public void contextLoads() {
		CodeGenerator gse = new CodeGenerator();
		//要给那些表生成
		gse.generateByTables(false,"user", "user_role","role_permission","role","permission");
	}

}
