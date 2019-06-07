package com.javasoso.pass.service.impl;

import com.javasoso.pass.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public String sayHello(String name) {
        return String.format("hello %s",name);
    }
}
