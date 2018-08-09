package com.jiang.springboot_shiro.shiro;

import com.jiang.springboot_shiro.entity.User;
import com.jiang.springboot_shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0){
        System.out.println("授权");
        Subject subject=SecurityUtils.getSubject();
        //System.out.println(subject.getPrincipal());
        User user=(User) subject.getPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission(user.getAuthority());
        return simpleAuthorizationInfo;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        // TODO Auto-generated method stub
        System.out.println("认证");
        //shiro判断逻辑
        UsernamePasswordToken user = (UsernamePasswordToken) arg0;
        //System.out.println(user);
        User realUser=userService.findUserByName(user.getUsername());
        if(realUser==null){
            throw new UnknownAccountException();
        }else if(!(user.getPassword()).equals(realUser.getPassword()) ){
            throw new IncorrectCredentialsException();
        }else{
            return new SimpleAuthenticationInfo(realUser,realUser.getPassword(),"");
        }
    }

}
