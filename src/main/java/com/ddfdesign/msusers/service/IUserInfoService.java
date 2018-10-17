package com.ddfdesign.msusers.service;

import com.ddfdesign.msusers.entity.Article;
import com.ddfdesign.msusers.entity.UserInfo;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface IUserInfoService {
    @Secured({"ROLE_ADMIN"})
    List<Article> getAllUserArticles();
    boolean createUser(UserInfo userInfo);
}
