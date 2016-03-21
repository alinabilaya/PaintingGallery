package ua.skillsup.javacourse.paintinggallery.model.gallery;

import java.util.List;

/**
 * Created by Shine on 19.03.2016.
 */
public interface PaintingGalleryRepo {
  PaintingGallery findById(long id);
  List<PaintingGallery> findGalleryByOwner(String owner);
  PaintingGallery findGalleryForPainting (String paintingTitle);
  void add(PaintingGallery paintingGallery);
}
