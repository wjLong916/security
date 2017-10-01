package com.lwj.security.demo.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public  void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void whenQueryAllSuccess(){
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/user/getAll").param("userName","Lwj")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenQuerySuccess(){
        try {
            String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/query").param("name","Lwj")
                    .param("password","123")
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn().getResponse().getContentAsString();

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenGenInfoSuccess(){
        try {
           String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Lwj"))
                    .andReturn().getResponse().getContentAsString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenGenInfoFail(){
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(MockMvcResultMatchers.status().is4xxClientError());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        Date birthday = new Date();
        String content = "{\"id\":1,\"name\":\"Lzy\",\"password\":\"12345\",\"birthday\":"+birthday.getTime()+"}";
//         String content = "{\"id\":1,\"name\":\"Lzy\",\"password\":null,\"birthday\":"+birthday.getTime()+"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user/createUser")
        .contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));

    }



    @Test
    public void whenDeleteSuccess() throws Exception {
        Date birthday = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        String content = "{\"id\":1,\"name\":\"Lzy\",\"password\":\"12345\",\"birthday\":"+birthday.getTime()+"}";
//         String content = "{\"id\":1,\"name\":\"Lzy\",\"password\":null,\"birthday\":"+birthday.getTime()+"}";
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
               ;

    }
}
