package com.hidiu.multids.controller;

import com.hidiu.multids.entity.Users;
import com.hidiu.multids.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("/user/save")
    @ResponseBody
    public String save(){
        Users user = new Users();
        user.setPassword("123");
        user.setUsername("admin");
        usersService.save(user);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/get/{id}")
    public String get(@PathVariable("id") Integer id){
        Users user = usersService.findById(id);
        return  "get----" + user.getUsername();
    }

}
