package ua.skillsup.javacourse.paintinggallery.model.repository;

import ua.skillsup.javacourse.paintinggallery.model.entity.security.User;

import java.util.Optional;

/**
 * Created by Shine on 17.04.2016.
 */
public interface UserRepo {

  Optional<User> getByName (String userName);
  void add (User user);
  Boolean isLoginFree(String login);
}
