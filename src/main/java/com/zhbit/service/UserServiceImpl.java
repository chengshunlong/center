package com.zhbit.service;

import com.zhbit.bean.Users;
import com.zhbit.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public int addUser(Users users) {
        return userMapper.addUser(users);
    }

    public int deleteUser(int uid) {
        return userMapper.deleteUser(uid);
    }

    public int updateUser(Users users) {
        return userMapper.updateUser(users);
    }

    public Users queryUserByUid(int uid) {
        return userMapper.queryUserByUid(uid);
    }

    public List<Users> queryAllUser() {
        return userMapper.queryAllUser();
    }

    public List<String> queryAllTypes() {
        return userMapper.queryAllTypes();
    }

    public Users queryUserByUserName(String userName) {
        return userMapper.queryUserByUserName(userName);
    }

    public Users queryUserByUserId(String userId) {
        return userMapper.queryUserByUserId(userId);
    }

    public String queryUserPwdByUserId(String userId) {
        return userMapper.queryUserPwdByUserId(userId);
    }

    public List<Users> searchUser(String searchParam) {
        return userMapper.searchUser(searchParam);
    }

}
