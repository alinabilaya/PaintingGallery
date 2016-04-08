package ua.skillsup.javacourse.paintinggallery.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;
import ua.skillsup.javacourse.paintinggallery.service.GallerySearchService;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * Created by Shine on 07.04.2016.
 */

@Controller
public class GalleryController {

  @Inject
  GallerySearchService gallerySearchService;

  @RequestMapping(path = "/gallery", method = RequestMethod.GET)
  public ModelAndView getAllPaintings(Map<String, Object> model) {

    final List<Painting> paintings = gallerySearchService.getAllPaintings();

    model.put("paintings", paintings);

    return new ModelAndView("gallery", "gallery", paintings);
  }

}
