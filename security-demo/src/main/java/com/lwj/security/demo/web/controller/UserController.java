package com.lwj.security.demo.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Lists;
import com.lwj.security.demo.dto.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

//    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @GetMapping("/query")
    @JsonView(User.UserInfoView.class)
    public User getUser(User user){
        System.out.println("===================================="+user.getName());
        System.out.println("===================================="+user.getPassword());
       user.setId(1L);
       user.setName("Lwj");
       user.setPassword("123");
        return user;
    }

//    @RequestMapping(value = "/user/{id:\\d+}",method = RequestMethod.GET)
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getUserInfo(String id){
        System.out.println("===================================="+id);
        User user =  new User();
        user.setId(1L);
        user.setName("Lwj");
        user.setPassword("123");
        return user;
    }

//    @RequestMapping(value = "/allUser",method = RequestMethod.GET)
    @GetMapping("/getAll")
    public List<User> getAllUser(@RequestParam  String userName){
        System.out.println("===================================="+userName);
        List<User> userList = Lists.newArrayList();
        for(int i = 0 ; i<3; i++){
            User user =  new User();
            user.setId(1L+i);
            user.setName("Lwj"+i);
            user.setPassword("123");
            userList.add(user);
        }
        return userList;
    }
}
