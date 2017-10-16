package com.lwj.security.demo.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Lists;
import com.lwj.security.demo.dto.User;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/findUser")
    @JsonView(User.UserInfoView.class)
    public User findUser(String id){
       throw new RuntimeException("测试异常");
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

    @PostMapping("/createUser")
    public User createUser(@Valid @RequestBody User user){

        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
        return user;
    }

    @PutMapping("/update/{id:\\d+}")
    public User updateUser(@Valid @PathVariable String id , BindingResult errors){
        return functionUser(id,errors);
    }

    @DeleteMapping("/delete/{id:\\d+}")
    public User deleteUser(@PathVariable String id ){
       return functionUser(id,null);
    }

    private User functionUser(String id , BindingResult errors){
        if(null != errors && errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                String message = fieldError.getField() + " : "+error.getDefaultMessage();
                System.out.println(message);
            });
        }
        User user =  new User();
        user.setId(Long.valueOf(id));
        user.setName("Lwj");
        user.setPassword("123");
        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
        return user;
    }
}
