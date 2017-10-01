package com.lwj.security.demo.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

public @Data class User {
    public interface UserInfoView{}
    public interface UserDetailView extends  UserInfoView{}

    @JsonView(UserInfoView.class)
    private  Long id;
    @JsonView(UserInfoView.class)
    private String name;
    @JsonView(UserDetailView.class)
    private String password;
}
