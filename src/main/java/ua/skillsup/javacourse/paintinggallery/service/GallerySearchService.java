package ua.skillsup.javacourse.paintinggallery.service;

import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PaintingGallery;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;

import java.util.List;

/**
 * Created by Shine on 16.03.2016.
 */
public interface GallerySearchService {

  List<Artist> getArtist(String artistName);
  PaintingGallery getGalleryByPainting(String paintingTitle);
  List<Painting> getAllArtistPaintings(String artistName);
}
