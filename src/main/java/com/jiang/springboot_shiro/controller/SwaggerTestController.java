package com.jiang.springboot_shiro.controller;

import com.jiang.springboot_shiro.entity.User;
import com.jiang.springboot_shiro.result.Result;
import com.jiang.springboot_shiro.result.ResultGenerator;
import com.jiang.springboot_shiro.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}
