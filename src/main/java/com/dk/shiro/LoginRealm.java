package com.dk.shiro;

import com.dk.pojo.TPerson;
import com.dk.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Deacription TODO
 * @Author ASUS
 * @Date 2020/12/7 11:26
 * @Version 1.0
 **/
public class LoginRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权认证");
        //获取登录用户
        Subject subject = SecurityUtils.getSubject();
        TPerson tPerson = (TPerson) subject.getPrincipal();
        System.out.println("当前登录用户是："+tPerson.getPname());
        String perms = "user:add";
        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
        List<String> permsList = new ArrayList<>();
        permsList.add(perms);
        auth.addStringPermissions(permsList);
        return auth;
    }
    /*认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //填写shiro判断逻辑 , 判断用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        TPerson tPerson = userService.findByUsername(token.getUsername());
        // 判断密码是否正确 1、如果用户认证成功将对象放入session  2、数据库查询出来的密码 3、数据库查询出来的盐 4、用户的真实名字
        return tPerson==null? null : new SimpleAuthenticationInfo(tPerson,tPerson.getPwd(), ByteSource.Util.bytes(tPerson.getSalt()),tPerson.getPname());
    }
}
