package ua.skillsup.javacourse.paintinggallery.model.repository;

import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;

import java.util.List;

/**
 * Created by Shine on 18.03.16.
 */
public interface ArtistRepo {

  Artist getById(Long id);
  List<Artist> getByName(String artistName);
  List<Painting> getAllArtistPaintings(String artistName);
  void add(Artist artist);
}
