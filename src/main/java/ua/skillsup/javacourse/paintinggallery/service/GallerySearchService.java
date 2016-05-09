package ua.skillsup.javacourse.paintinggallery.service;

import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PaintingGallery;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PublicGallery;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;

import java.util.List;

/**
 * Created by Shine on 16.03.2016.
 */
public interface GallerySearchService {

  Painting getPaintingById(Long id);
  List<Painting> getPaintingByTitle(String title);
  List<Artist> getArtistByName(String artistName);
  Artist getArtistById(Long id);
  PaintingGallery getGalleryByPainting(String paintingTitle);
  List<Painting> getAllArtistPaintings(String artistName);
  List<Painting> getAllPaintings();
  List<Artist> getAllArtists();
  List<PublicGallery> getAllPublicGalleries();
  List<PublicGallery> getGalleryByOwner(String owner);
}
