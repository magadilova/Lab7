package repository;


import model.User;
import org.hibernate.Session;


public class UserRepository extends RepositoryBase<Long, User> {
    public UserRepository(Session session) {
        super(User.class, session);
    }

    public User findByLogin(String login){
        return getSession().createQuery("select user from User user where user.login=:login", User.class)
                .setParameter("login", login).getSingleResult();
    }


}
