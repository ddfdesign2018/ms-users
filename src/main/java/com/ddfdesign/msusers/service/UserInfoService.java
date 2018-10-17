package com.ddfdesign.msusers.service;

import com.ddfdesign.msusers.dao.IUserInfoDAO;
import com.ddfdesign.msusers.entity.Article;
import com.ddfdesign.msusers.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService implements IUserInfoService {
    @Autowired
    private IUserInfoDAO userInfoDAO;
    @Override
    public List<Article> getAllUserArticles(){
        return userInfoDAO.getAllUserArticles();
    }
    @Override
    public boolean createUser(UserInfo userInfo) {
        System.out.println("userinfosevise create user");
        return userInfoDAO.createUser(userInfo);}
}
