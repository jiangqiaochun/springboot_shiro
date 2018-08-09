package com.jiang.springboot_shiro.controller;

import com.jiang.springboot_shiro.entity.User;
import com.jiang.springboot_shiro.result.Result;
import com.jiang.springboot_shiro.result.ResultGenerator;
import com.jiang.springboot_shiro.service.UserService;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Api(value = "/test",tags = "swagger测试")
@RestController
@RequestMapping("/test")
public class SwaggerTestController {
    @Autowired
    private UserService userService;
    @ApiOperation("查询所有")
    @ApiResponses(value={@ApiResponse(code=123, message="OK")})
    @RequestMapping(value = "", method = RequestMethod.GET)
    private Result<List<User>> queryAll() {
        return ResultGenerator.genSuccessResult(userService.qureyAll());

    }

    @ApiOperation(value = "根据name查询用户信息", notes = "查询数据库中某个用户的信息")
    @ApiImplicitParam(name = "name", value = "用户name", paramType = "path", required = true, dataType = "String")
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public User getUser(@PathVariable String name) {
        return userService.findUserByName(name);
    }
    @ApiOperation(value = "根据name删除用户信息", notes = "删除数据库中某个用户的信息")
    @ApiImplicitParam(name = "name", value = "用户name", paramType = "path", required = true, dataType = "String")
    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public Result deleteUser(@PathVariable String name) {
        if(userService.deleteUserByName(name)){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult("删除失败");
        }

    }
    @ApiOperation("添加")
    @RequestMapping(value = "", method = RequestMethod.POST)
    private Result add(@RequestBody User user) {
        Integer row = userService.add(user);

        if (row > 0) {
          return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult("添加失败");
        }

    }
    @ApiOperation(value = "根据name更新用户信息", notes = "更新数据库中某个用户的信息")
    @RequestMapping(value = "/{name}", method = RequestMethod.PUT)
    public Result update(@RequestBody User user, @PathVariable String name) {
        if(userService.update(user)){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult("更新失败");
        }

    }
    @ApiOperation(value = "登录接口", notes = "使用用户名和密码登录")
    @RequestMapping(value="/user/{name}",method = RequestMethod.POST)
    public Result toLogin( @RequestBody User user, BindingResult bindingResult){
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken userToken=new UsernamePasswordToken(user.getName(),user.getPassword());
        if(bindingResult.hasErrors()){
            List<ObjectError> errorList = bindingResult.getAllErrors();
            String errorMsg="";
            for(ObjectError error : errorList){
                errorMsg +=error;
            }
            return ResultGenerator.genFailResult(errorMsg);
        }
        try{
            subject.login(userToken);
        }catch (UnknownAccountException e){
            return ResultGenerator.genFailResult("账号不存在");
        }catch (IncorrectCredentialsException e){
            return ResultGenerator.genFailResult("密码错误");
        }
        return ResultGenerator.genSuccessResult();

    }


}
