package ua.skillsup.javacourse.paintinggallery.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;
import ua.skillsup.javacourse.paintinggallery.service.GalleryEditService;
import ua.skillsup.javacourse.paintinggallery.service.GallerySearchService;

import javax.inject.Inject;

/**
 * Created by skillsup on 15.04.16.
 */

@Controller
@RequestMapping(path = "/edit")
public class AdminController {

    @Inject
    private GallerySearchService gallerySearchService;

    @Inject
    GalleryEditService galleryEditService;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping(path = "/paintings/{id}", method = RequestMethod.GET)
    public ModelAndView editPainting(@PathVariable("id") Long id) {
        final Painting painting = gallerySearchService.getPaintingById(id);

        return new ModelAndView ("painting_edit", "painting", painting);
    }

    @RequestMapping(path = "/paintings/{id}", method = RequestMethod.POST)
    public String updatePainting(@ModelAttribute Painting painting) {
        galleryEditService.updatePainting(painting);

        return "redirect:/paintings/{id}";
    }


    @RequestMapping(path = "/artists/{id}", method = RequestMethod.GET)
    public ModelAndView editArtist(@PathVariable("id") Long id) {

        final Artist artist = gallerySearchService.getArtistById(id);

        return new ModelAndView ("artist_edit", "artist", artist);
    }

    @RequestMapping(path = "/artists/{id}", method = RequestMethod.POST)
    public String updateArtist(@ModelAttribute Artist artist) {
        galleryEditService.updateArtist(artist);

        return "redirect:/artists/{id}";
    }
}
