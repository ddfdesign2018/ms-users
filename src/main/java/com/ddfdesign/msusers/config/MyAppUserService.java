package com.ddfdesign.msusers.config;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ddfdesign.msusers.entity.UserInfo;
import com.ddfdesign.msusers.dao.IUserInfoDAO;

@Service
public class MyAppUserService implements UserDetailsService {
    @Autowired
    private IUserInfoDAO userInfoDAO;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserInfo activeUserInfo = userInfoDAO.getActiveUser(username);
        GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole());
        System.out.println("MyAppUserService loadUserByName");
        UserDetails userDetails = (UserDetails)new User(activeUserInfo.getUsername(),
                activeUserInfo.getPassword(), Arrays.asList(authority));
        return userDetails;
    }
}
