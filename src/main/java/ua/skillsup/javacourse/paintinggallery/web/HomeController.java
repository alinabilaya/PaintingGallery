package ua.skillsup.javacourse.paintinggallery.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;
import ua.skillsup.javacourse.paintinggallery.service.GallerySearchService;

import javax.inject.Inject;
import java.util.*;

/**
 * Created by Shine on 06.04.2016.
 */


//@Controller //annotation defines the class as a Spring MVC controller
//@RequestMapping(path = "/") //annotation indicates that all handling methods on this controller are relative to the /hello path
//public class HomeController {
//
//  //RequestMapping annotation is used to declare the printHello() method as the controller's default service method to handle HTTP GET request.
//  //You can define another method to handle any POST request at the same URL.
//  @RequestMapping(method = RequestMethod.GET)
//  public String printHello() {return "hello"; }
//}


  //the same controller in other form:
@Controller
public class HomeController {

  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

  @Inject
  GallerySearchService gallerySearchService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String showHome(Map<String, Object> model, Locale locale) {

      logger.info("Welcome home! The client locale is {}.", locale);

      final List<Painting> paintingsList = gallerySearchService.getAllPaintings();

      final List<Painting> paintings = new ArrayList<>();

      Random rand = new Random();
      for (int i = 0; i < 8; i++) {
        int n = rand.nextInt(paintingsList.size());
        paintings.add(paintingsList.get(n));
      }

      model.put("paintings", paintings);

      return "home"; }
  }
