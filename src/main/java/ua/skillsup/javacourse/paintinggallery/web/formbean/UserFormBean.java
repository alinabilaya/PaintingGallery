package ua.skillsup.javacourse.paintinggallery.web.formbean;

import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import ua.skillsup.javacourse.paintinggallery.model.entity.security.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.logging.Logger;

/**
 * Created by Shine on 01.05.2016.
 */
public class UserFormBean {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserFormBean.class);

    @NotNull
    @Valid
    private User user;

    @Size(max = 255)
    private String repeatedPassword;

    public User getUser() { return user; }

    public void setUser(User user) {
        this.user = user;
        user.setEnabled(true);
    }

    public String getRepeatedPassword() {return repeatedPassword;}

    public void setRepeatedPassword(String repeatedPassword) {this.repeatedPassword = repeatedPassword;}
}
