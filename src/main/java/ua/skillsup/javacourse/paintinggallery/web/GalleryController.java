package ua.skillsup.javacourse.paintinggallery.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PaintingGallery;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PublicGallery;
import ua.skillsup.javacourse.paintinggallery.service.GallerySearchService;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Shine on 16.04.2016.
 */

@Controller
@RequestMapping(path = "/galleries")
public class GalleryController {

  private static final Logger logger = LoggerFactory.getLogger(GalleryController.class);

  @Inject
  GallerySearchService gallerySearchService;

  @RequestMapping(method = RequestMethod.GET)
  public String getAllPublicGalleries (Map<String, Object> model, String q) {
    if(q!=null) {
      final List<PublicGallery> galleriesList = gallerySearchService.searchPublicGalleryByOwner(q);
      model.put("galleries", galleriesList);
      model.put("q", q);

      return "galleries";
    }
    else {
      final List<PublicGallery> galleriesList = gallerySearchService.getAllPublicGalleries();
      model.put("galleries", galleriesList);

      return "galleries";
    }
  }

  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  public ModelAndView getPublicGallery(@PathVariable("id") Long id) {
    final PublicGallery gallery = (PublicGallery)gallerySearchService.getGalleryById(id);

    return new ModelAndView ("gallery_view", "gallery", gallery);
  }
}