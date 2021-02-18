package com.dk.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Deacription TODO
 * @Author ASUS
 * @Date 2020/12/7 14:02
 * @Version 1.0
 **/
@Configuration
public class ShiroConfig {
    /*创建ShiroFilterFactoryBean*/
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Autowired DefaultWebSecurityManager defaultWebSecurityManager ){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设备安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        /*Shiro内置过滤器,可以实现权限相关的拦截
            常用的过滤器:
                anon:无需认证(登录)可以访问
                authc:必须认证才可以访问
                perms:该资源必须得到资源权限才可以访问
                role:该资源必须得到角色权限才可以访问
                logout:退出登录
        * */
        /*按照顺序取出*/
        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/index","anon");
        filterMap.put("/login","anon");
        filterMap.put("/add","perms[user:add]");
        filterMap.put("/update","perms[user:update]");
        //退出登录
        filterMap.put("/auth/logout","logout");
        filterMap.put("/**","authc");
        //默认跳转login.jsp 修改
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //设置无权限页面 (type=Unauthorized, status=401 无权限).
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
    /*创建DefaultWebSecurityManager*/
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Autowired LoginRealm loginRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联realm
        defaultWebSecurityManager.setRealm(loginRealm);
        return defaultWebSecurityManager;
    }
    /*创建Realm*/
    @Bean
    public LoginRealm getLoginRealm(){
        LoginRealm loginRealm = new LoginRealm();
        //设置加密方式
        loginRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return loginRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式
        //散列算法:MD4、MD5、SHA-1、SHA-256、SHA-384、SHA-512等
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(3);//撒盐次数
//      //此处的设置，true加密使用的hex编码，false用的base64密码
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
