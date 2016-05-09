package ua.skillsup.javacourse.paintinggallery.model.repository;

import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;

import java.util.List;

/**
 * Created by skillsup on 18.03.16.
 */
public interface PaintingRepo {

  Painting getPaintingById(Long id);
  List<Painting> getPaintingByTitle(String paintingTitle);
  void add(Painting painting);
  List<Painting> getAllPaintings();
}
