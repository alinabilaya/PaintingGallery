package ua.skillsup.javacourse.paintinggallery.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(path = "/paintings")
public class PaintingController {

  private static final Logger logger = LoggerFactory.getLogger(PaintingController.class);

  @Inject
  GallerySearchService gallerySearchService;

  @RequestMapping(method = RequestMethod.GET)
  public String getAllPaintings(Map<String, Object> model, String q) {
    if(q!=null) {
      final List<Painting> paintingsList = gallerySearchService.getPaintingByTitle(q);
      model.put("paintings", paintingsList);
      model.put("q", q);

      return "paintings";
    }
    else{
      final List<Painting> paintingsList = gallerySearchService.getAllPaintings();
      model.put("paintings", paintingsList);

      return "paintings";
    }
  }

  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  public ModelAndView getPainting(@PathVariable("id") Long id) {
    final Painting painting = gallerySearchService.getPaintingById(id);

    return new ModelAndView ("painting_view", "painting", painting);
  }
}
