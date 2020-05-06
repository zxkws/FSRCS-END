package edu.wbu.fsrcs.controller;

import edu.wbu.fsrcs.entity.Result;
import edu.wbu.fsrcs.entity.ResultCode;
import edu.wbu.fsrcs.entity.User;
import edu.wbu.fsrcs.service.UserService;
import edu.wbu.fsrcs.utils.AesEncryptUtil;
import edu.wbu.fsrcs.utils.IdWorker;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fsrcs/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    IdWorker idWorker;
    @RequestMapping("/{userId}")
    public Result queryUserById(@PathVariable String userId){
        User user = userService.queryUserById(userId);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(user);
        return result;
    }
    @GetMapping("")
    public Result queryAllUser(){
        List<User> list = userService.queryAllUser();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(list);
        return result;
    }

    @PostMapping("/insert")
    public Result insertUser(@RequestBody User user) throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new Exception("用户名或者密码不能为空");
        }
        User user1 = userService.queryUserByUsername(username);
        if (username == user1.getUsername()) {
            throw new Exception("用户名已存在");
        }
        user.setUserId(idWorker.nextId());
        userService.addUser(user);
        return new Result(ResultCode.SUCCESS);
    }

    @PostMapping("/update")
    public Result updateUser(@RequestBody User user) throws Exception {
        userService.updateUser(user);
        return new Result(ResultCode.SUCCESS);
    }

    @PostMapping("/delete")
    public Result deleteUser(@RequestBody User user){
        userService.deleteUser(user);
        return new Result(ResultCode.SUCCESS);
    }
}
