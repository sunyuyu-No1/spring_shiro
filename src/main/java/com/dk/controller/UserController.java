package com.dk.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Deacription TODO
 * @Author ASUS
 * @Date 2020/12/7 16:11
 * @Version 1.0
 **/
@Controller
public class UserController {
    @RequestMapping("index")
    public String index(){
        return "index";
    }
    @RequestMapping("add")
    public String add(){
        return "add";
    }
    @RequestMapping("get")
    public String get(){
        return "list";
    }
    @RequestMapping("update")
    public String update(){
        return "update";
    }
    @RequestMapping("delete")
    public String delete(){
        return "delete";
    }
    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("login")
    public String login(String pname, String pwd, Model model){
        /*使用Shiro编写认证操作
        * */
        //获取Subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(pname,pwd);
        //执行登录方法
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            //登录失败 用户名不存在
            model.addAttribute("msg","用户不存在");
            return "login";
        } catch ( IncorrectCredentialsException e){
            //登录失败 密码错误
            model.addAttribute("msg","密码不正确");
            return "login";
        }
        return "redirect:/index";
    }
    @RequestMapping("unauthorized")
    public String  unauthorized(){
        return "unauthorized";
    }
}
