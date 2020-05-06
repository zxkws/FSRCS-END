package edu.wbu.fsrcs.service;

import edu.wbu.fsrcs.dao.UserDao;
import edu.wbu.fsrcs.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User queryUser(@Param("username") String username, @Param("password")String password) throws Exception {
        return userDao.queryUser(username, password);
    }

    public List<User> queryAllUser(){
        return userDao.queryAllUser();
    }

    public User queryUserById(@Param("userId") String userId){
        return userDao.queryUserById(userId);
    }

    public User queryUserByUsername(@Param("username") String username){
        return userDao.queryUserByUsername(username);
    }
    public int addUser(User user) throws Exception {
        return userDao.addUser(user);
    }
    public int updateUser(User user) throws Exception {
        return userDao.updateUser(user);
    }

    public int deleteUser(User user){
        return userDao.deleteUser(user);
    }

    /**
     * 获取用户权限
     */
}
