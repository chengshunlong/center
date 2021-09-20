package com.zhbit.service;

import com.zhbit.bean.Users;

import java.util.List;

public interface UserService {

    //添加用户
    int addUser(Users users);

    //删除用户
    int deleteUser(int uid);

    //更新用户
    int updateUser(Users users);

    //查询用户
    Users queryUserByUid(int uid);

    //查询全部用户
    List<Users> queryAllUser();

    //查询所有用户类型
    List<String> queryAllTypes();

    //根据用户姓名查询用户
    Users queryUserByUserName(String userName);

    //根据用户账号查询用户
    Users queryUserByUserId(String userId);

    //根据用户账号查询密码
    String queryUserPwdByUserId(String userId);

    //模糊查询
    List<Users> searchUser(String searchParam);
}
