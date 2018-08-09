package com.jiang.springboot_shiro.controller;

import com.jiang.springboot_shiro.entity.User;
import com.jiang.springboot_shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@ApiIgnore
@Controller
public class UserController {
    /*@RequestMapping("springboot_shiro")//项目访问URL
    public String index(){
        return "Hello springboot!";
    }*/
    @Autowired
    private UserService userService;
    @RequestMapping(value="/",method = RequestMethod.POST)
    public String getIndex(){
        return "login";
    }
    @RequestMapping(value="/all",method = RequestMethod.GET)
    public String getAll(){
        return "all";
    }
    @RequestMapping(value="/one",method = RequestMethod.POST)
    public String getOne(){
        return "one";
    }
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    @RequestMapping(value="/permission",method = RequestMethod.POST)
    public String permission(){
        return "permission";
    }
    /*@RequestMapping(value="/toLogin",method = RequestMethod.GET)
    public String toLogin(@Valid User user, Model model){
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken userToken=new UsernamePasswordToken(user.getName(),user.getPassword());
        try{
            subject.login(userToken);
            return "redirect:/all";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }*/
    @RequestMapping(value="/toLogin",method = RequestMethod.GET)
    public String toLogin(@Valid User user, BindingResult bindingResult){
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken userToken=new UsernamePasswordToken(user.getName(),user.getPassword());
        if(bindingResult.hasErrors()){
            return "login";
        }
        subject.login(userToken);
        return "redirect:all";

    }

}
