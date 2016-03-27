package ua.skillsup.javacourse.paintinggallery.model.repository;

import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PaintingGallery;

import java.util.List;

/**
 * Created by Shine on 19.03.2016.
 */
public interface PaintingGalleryRepo {

  PaintingGallery getById(long id);
  List<PaintingGallery> getGalleryByOwner(String owner);
  PaintingGallery getGalleryByPainting(String paintingTitle);
  void addPaintingGallery(PaintingGallery paintingGallery);
}
