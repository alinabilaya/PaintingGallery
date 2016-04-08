package ua.skillsup.javacourse.paintinggallery.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Shine on 06.04.2016.
 */


//@Controller //annotation defines the class as a Spring MVC controller
//@RequestMapping(path = "/") //annotation indicates that all handling methods on this controller are relative to the /hello path
//public class HelloController {
//
//  //RequestMapping annotation is used to declare the printHello() method as the controller's default service method to handle HTTP GET request.
//  //You can define another method to handle any POST request at the same URL.
//  @RequestMapping(method = RequestMethod.GET)
//  public String printHello() {return "hello"; }
//}


  //the same controller in other form:
  @Controller
  public class HelloController {

  private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String printHello() {return "hello"; }
  }
