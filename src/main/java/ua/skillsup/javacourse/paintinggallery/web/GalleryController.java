package ua.skillsup.javacourse.paintinggallery.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PaintingGallery;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PublicGallery;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.service.GallerySearchService;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

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
  public String getAllPublicGalleries (Map<String, Object> model) {
    final List<PublicGallery> galleriesList = gallerySearchService.getAllPublicGalleries();
    model.put("galleries", galleriesList);

    return "galleries";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String findGallery(Map<String, Object> model, @RequestParam String ownerLike) {
    final List<PublicGallery> galleriesList = gallerySearchService.getGalleryByOwner(ownerLike);
    model.put("galleries", galleriesList);

    return "galleries";
  }

  @RequestMapping(path = "/{owner}", method = RequestMethod.GET)
  public ModelAndView getPublicGallery(@PathVariable("owner") String owner) {
    final PublicGallery gallery = (PublicGallery)gallerySearchService.getGalleryByOwner(owner).get(0);

    return new ModelAndView ("gallery_view", "gallery", gallery);
  }
}
