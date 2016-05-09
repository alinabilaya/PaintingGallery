package ua.skillsup.javacourse.paintinggallery.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ua.skillsup.javacourse.paintinggallery.model.entity.security.User;
import ua.skillsup.javacourse.paintinggallery.model.repository.UserRepo;
import ua.skillsup.javacourse.paintinggallery.web.formbean.UserFormBean;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by Shine on 30.04.2016.
 */

@Controller
@RequestMapping("/auth")
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Inject
    private MessageSource messageSource;

    @Inject
    UserRepo userRepo;

    private final PasswordEncoder passwordEncoder =  new StandardPasswordEncoder();

    @RequestMapping("/isLoginFree")// can be used with ajax jquery validation plugin
    @ResponseBody
    public String checkIsFree(@RequestParam String login4test){
        return userRepo.isLoginFree(login4test)?"true":"false";
    }

    @RequestMapping (path = "/singUp", method = RequestMethod.GET)
    public String createNewAccount (Map<String, Object> model)  {
        UserFormBean userFormBean = new UserFormBean();
        userFormBean.setUser(new User());
        model.put("userFormBean", userFormBean);
        model.put("userFormAction", "/auth/saveUser");

        return "/auth/singup";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(HttpServletRequest request, Map<String, Object> model, @Valid @ModelAttribute UserFormBean userFormBean, BindingResult result){

        String usernameError = null;
        String nameError = null;
        String emailError = null;
        String passwordError = null;
        String passwordConfirmError = null;

        if (request.getParameterMap().containsKey("cancel")) {
            model.put("message","Sing up canceled");
            return "redirect:/";
        }

        if (userFormBean.getUser().getName().length()==0) {
            nameError = "Name can't be empty";
        }

        if(userFormBean.getUser().getUsername().length()==0){
            usernameError = "Login can't be empty";
        }
        else if(!userRepo.isLoginFree(userFormBean.getUser().getUsername())) {
            usernameError = "This login is already in use";
        }

        if(userFormBean.getUser().getPassword().length()==0) {
            passwordError = "Password can't be empty";
        }
        else if (userFormBean.getUser().getPassword().length() < 5) {
            passwordError = "Password is too short";
        }
        else if (!userFormBean.getUser().getPassword().equals(userFormBean.getRepeatedPassword())) {
            passwordConfirmError = "Passwords do not match";
        }

        if(userFormBean.getUser().getEmail().length()==0) {
            emailError = "Email can't be empty";
        }

        if (result.hasErrors())
        {
            model.put("usernameError", usernameError);
            model.put("passwordConfirmError", passwordConfirmError);
            model.put("passwordError", passwordError);
            model.put("nameError", nameError);
            model.put("emailError", emailError);

            return "/auth/singup";
        }

        if(userFormBean.getUser().getPassword().equals(userFormBean.getRepeatedPassword())) {
            User newUser = userFormBean.getUser();
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            userRepo.add(newUser);
            return "/auth/welcome";
        }
        else  {
            return "redirect:/auth/singUp";
        }
    }
}

