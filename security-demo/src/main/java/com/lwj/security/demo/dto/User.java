package com.lwj.security.demo.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.lwj.security.demo.validator.MyConstraint;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

public @Data class User {
    public interface UserInfoView{}
    public interface UserDetailView extends  UserInfoView{}

    @JsonView(UserInfoView.class)
    private  Long id;

    @JsonView(UserInfoView.class)
    @MyConstraint(message = "这是一个测试消息")
    private String name;

    @JsonView(UserDetailView.class)
    @NotBlank(message = "密码不能为空")/*Hibernate Validator*/
    private String password;

    @JsonView(UserDetailView.class)
    @Past(message = "生日必须是过去的时间")
    private Date birthday;
}
