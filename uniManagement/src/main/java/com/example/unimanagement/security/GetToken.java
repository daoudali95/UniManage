package com.example.unimanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetToken {

    @Autowired
    private TokenWrapper tokenWrapper;

    public String geTToken(){
        return tokenWrapper.getToken();
    }
}
