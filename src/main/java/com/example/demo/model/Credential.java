package com.example.demo.model;

import lombok.Data;

@Data
public class Credential {
    private Integer credentialId;
    private String url;
    private String username;
    private String key;
    private String password;
    private Integer userId;
}
