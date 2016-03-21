package ua.skillsup.javacourse.paintinggallery.application;

import ua.skillsup.javacourse.paintinggallery.model.gallery.PaintingGallery;
import ua.skillsup.javacourse.paintinggallery.model.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.model.painting.Painting;

import java.util.List;

/**
 * Created by Shine on 16.03.2016.
 */
public interface GallerySearchService {

  List<Artist> findArtist(String artistName);
  PaintingGallery findGalleryByPainting(String paintingTitle);
  List<Painting> findAllPaintingsOfArtist(String artistName);
}
