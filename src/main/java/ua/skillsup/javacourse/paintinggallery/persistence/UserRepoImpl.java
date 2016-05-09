package ua.skillsup.javacourse.paintinggallery.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.javacourse.paintinggallery.model.entity.security.User;
import ua.skillsup.javacourse.paintinggallery.model.repository.UserRepo;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by Shine on 17.04.2016.
 */
@Repository
@Transactional
public class UserRepoImpl implements UserRepo{

  @Inject
  private SessionFactory sessionFactory;

  @Override
  public Optional<User> getByName (String userName) {
    return Optional.ofNullable(Util.cast(
            sessionFactory.getCurrentSession()
            .createCriteria(User.class)
            .add(Restrictions.eq("username", userName))
            .uniqueResult()
    ));
  }

  @Override
  public void add (User user) {
    sessionFactory.getCurrentSession().save(user);
  }

  @Override
  public Boolean isLoginFree(String username){
    User user = (User)sessionFactory.getCurrentSession()
            .createQuery("from  User u where u.username like :username")
            .setParameter("username", username)
            .uniqueResult();

    return (user != null) ? false : true;
  }
}
