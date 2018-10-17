package com.ddfdesign.msusers.dao;

import com.ddfdesign.msusers.entity.Article;
import com.ddfdesign.msusers.entity.UserInfo;

import java.util.List;

public interface IUserInfoDAO {
    UserInfo getActiveUser(String username);
    List<Article> getAllUserArticles();
    boolean createUser(UserInfo userInfo);
}
