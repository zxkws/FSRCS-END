package edu.wbu.fsrcs.dao;

import edu.wbu.fsrcs.entity.User;
import java.util.List;

public interface UserDao {

    /**
     * 查询用户
     * @return
     */
    User queryUser(String username, String password);

    /**
     * 查询所有用户
     * @return
     */
    List<User> queryAllUser();

    /**
     * 通过Id查询用户
     * @param userId
     * @return
     */
    User queryUserById(String userId);

    /**
     * 通过username查询用户
     * @param username
     * @return
     */
    User queryUserByUsername(String username);
    /**
     * 新建用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 删除用户
     * @param user
     * @return
     */
    int deleteUser(User user);
}
