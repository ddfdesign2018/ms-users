package com.ddfdesign.msusers.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ddfdesign.msusers.entity.Article;
import com.ddfdesign.msusers.entity.UserInfo;

@Repository
@Transactional
public class UserInfoDAO implements IUserInfoDAO{
    @PersistenceContext
    private EntityManager entityManager;

    public UserInfo getActiveUser(String username) {
        System.out.println("getActive user inicio");
        UserInfo activeUser = new UserInfo();
        short userStatus = 1;
        List<?> list = entityManager.createQuery("SELECT u FROM UserInfo u WHERE username= :username")
                .setParameter("username", username).getResultList();
        if(!list.isEmpty()) {
            activeUser = (UserInfo)list.get(0);
            System.out.println("Coincidencia ");
        }
        System.out.println("usuario activo: " + activeUser);
        return activeUser;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Article> getAllUserArticles() {
        String hql = "FROM Article as atcl ORDER BY atcl.articleId";
        return (List<Article>) entityManager.createQuery(hql).getResultList();
    }
    @Override
    public boolean createUser(UserInfo user){
        System.out.println("Createuser INICIO UserInfoDao");
        boolean check = false;
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        String name = user.getName();
        String surnames = user.getSurnames();

        List<?> list = entityManager.createQuery("SELECT u FROM UserInfo u WHERE username= :username")
                .setParameter("username", username).getResultList();
        if(!list.isEmpty()) {
           System.out.println("Usuario repetido ");
        } else{
            String sql = "INSERT INTO users (username, password, email, name, surnames, role) VALUES (:username, :password, :email, :name, :surnames, 'ROLE_ADMIN')";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("username", username)
                    .setParameter("password", password)
                    .setParameter("email", email)
                    .setParameter("name", name)
                    .setParameter("surnames", surnames)
                    .executeUpdate();
            System.out.println("El usuario no existe");
            check= true;
        }

        return check;
    }

}
