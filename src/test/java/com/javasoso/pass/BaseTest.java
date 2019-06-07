package com.javasoso.pass;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {
    @Before
    public void begin(){
        System.out.println("--开始单元测试--");
    }

    @After
    public void end(){
        System.out.println("--结束单元测试--");
    }
}
