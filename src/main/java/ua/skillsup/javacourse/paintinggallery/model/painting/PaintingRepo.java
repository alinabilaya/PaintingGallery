package ua.skillsup.javacourse.paintinggallery.model.painting;

import java.util.List;

/**
 * Created by skillsup on 18.03.16.
 */
public interface PaintingRepo {

  List<Painting> findPaintingByTitle(String paintingTitle);

  void add(Painting painting);
}
