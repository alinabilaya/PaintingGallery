package ua.skillsup.javacourse.paintinggallery.service.impl;

import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.javacourse.paintinggallery.service.GallerySearchService;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PaintingGallery;
import ua.skillsup.javacourse.paintinggallery.model.repository.PaintingGalleryRepo;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.model.repository.ArtistRepo;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;
import ua.skillsup.javacourse.paintinggallery.model.repository.PaintingRepo;

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
  public List<Artist> getArtist(String artistName) {
    return artistRepo.getByName(artistName);
  }

  @Override
  public List<Painting> getAllArtistPaintings(String artistName) {
    return artistRepo.getAllArtistPaintings(artistName);
  }

  @Override
  public PaintingGallery getGalleryByPainting(String paintingTitle) {
    return paintingGalleryRepo.getGalleryByPainting(paintingTitle);
  }
}
