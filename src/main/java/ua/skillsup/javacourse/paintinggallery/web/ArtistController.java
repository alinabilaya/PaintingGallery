package ua.skillsup.javacourse.paintinggallery.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;
import ua.skillsup.javacourse.paintinggallery.service.GalleryEditService;
import ua.skillsup.javacourse.paintinggallery.service.GallerySearchService;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * Created by Shine on 16.04.2016.
 */

@Controller
@RequestMapping(path = "/artists")
public class ArtistController {

  private static final Logger logger = LoggerFactory.getLogger(ArtistController.class);

  @Inject
  GallerySearchService gallerySearchService;

  @RequestMapping(method = RequestMethod.GET)
  public String getAllArtists(Map<String, Object> model) {
    final List<Artist> artistsList = gallerySearchService.getAllArtists();
    model.put("artists", artistsList);

    return "artists";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String findArtist(Map<String, Object> model, @RequestParam String nameLike) {
    final List<Artist> artistsList = gallerySearchService.getArtistByName(nameLike);
    model.put("artists", artistsList);

    return "artists";
  }

  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  public ModelAndView getArtist(Map<String, Object> model, @PathVariable("id") Long id ) {
    final Artist artist = gallerySearchService.getArtistById(id);
    final List<Painting> artistPaintings = gallerySearchService.getAllArtistPaintings(artist.getName());
    model.put("paintings", artistPaintings);

    return new ModelAndView ("artist_view", "artist", artist);
  }
}
