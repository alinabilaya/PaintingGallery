package ua.skillsup.javacourse.paintinggallery.application.impl;

import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.javacourse.paintinggallery.application.GallerySearchService;
import ua.skillsup.javacourse.paintinggallery.model.gallery.PaintingGallery;
import ua.skillsup.javacourse.paintinggallery.model.gallery.PaintingGalleryRepo;
import ua.skillsup.javacourse.paintinggallery.model.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.model.painting.ArtistRepo;
import ua.skillsup.javacourse.paintinggallery.model.painting.Painting;
import ua.skillsup.javacourse.paintinggallery.model.painting.PaintingRepo;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Shine on 17.03.2016.
 */


@Transactional
public class GallerySearchServiceImpl implements GallerySearchService{

  @Inject
  private ArtistRepo artistRepo;

  @Inject
  private PaintingRepo paintingRepo;

  @Inject
  private PaintingGalleryRepo paintingGalleryRepo;

  @Override
  public List<Artist> findArtist(String artistName) {
    return artistRepo.findByName(artistName);
  }

  @Override
  public List<Painting> findAllPaintingsOfArtist(String artistName) {
  return null;
  }

  @Override
  public PaintingGallery findGalleryByPainting(String paintingTitle) {
    return paintingGalleryRepo.findGalleryForPainting(paintingTitle);
  }
}
