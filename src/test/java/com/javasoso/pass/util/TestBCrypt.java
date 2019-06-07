package com.javasoso.pass.util;

import jodd.util.BCrypt;

public class TestBCrypt {

    public static void main(String[] args) {
        String salt = BCrypt.gensalt(10);
        System.out.println(salt);
        String pwd = "abc";
        String hash = BCrypt.hashpw(pwd,salt);
        System.out.println(hash);
        System.out.println(BCrypt.checkpw("abc",hash));
    }
}
