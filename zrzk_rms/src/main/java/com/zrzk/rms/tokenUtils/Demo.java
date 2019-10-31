package com.zrzk.rms.tokenUtils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class Demo {

    @Autowired
    public HttpServletRequest request;

    @Test
    public void demo() {
        TokenTools.createToken(request,"123");
        //xJRh2fOtzVytMv517+HUtQ==
        //DdwYslZKdvrxyPEiNpe11g==
    }
}
