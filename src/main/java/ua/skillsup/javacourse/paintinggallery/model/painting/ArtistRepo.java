package ua.skillsup.javacourse.paintinggallery.model.painting;

import java.util.List;

/**
 * Created by Shine on 18.03.16.
 */
public interface ArtistRepo {
  Artist getById(Long id);

  List<Artist> findByName(String artistName);

  List<Painting> findAllPaintingsForArtist(String artistName);

  void add(Artist artist);

}
