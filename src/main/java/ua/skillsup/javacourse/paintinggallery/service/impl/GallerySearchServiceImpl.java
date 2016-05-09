package ua.skillsup.javacourse.paintinggallery.service.impl;

import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PaintingGallery;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PublicGallery;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;
import ua.skillsup.javacourse.paintinggallery.model.repository.ArtistRepo;
import ua.skillsup.javacourse.paintinggallery.model.repository.PaintingGalleryRepo;
import ua.skillsup.javacourse.paintinggallery.model.repository.PaintingRepo;
import ua.skillsup.javacourse.paintinggallery.service.GallerySearchService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Shine on 17.03.2016.
 */


@Transactional
public class GallerySearchServiceImpl implements GallerySearchService {

  @Inject
  private ArtistRepo artistRepo;

  @Inject
  private PaintingRepo paintingRepo;

  @Inject
  private PaintingGalleryRepo paintingGalleryRepo;

  @Override
  public Painting getPaintingById(Long id) {
    return paintingRepo.getPaintingById(id);
  }

  @Override
  public List<Painting> getPaintingByTitle(String paintingTitle) {
    return paintingRepo.getPaintingByTitle(paintingTitle);
  }

  @Override
  public List<Artist> getArtistByName(String artistName) {
    return artistRepo.getByName(artistName);
  }

  @Override
  public Artist getArtistById(Long id) {
    return artistRepo.getById(id);
  }

  @Override
  public List<Painting> getAllArtistPaintings(String artistName) {
    return artistRepo.getAllArtistPaintings(artistName);
  }

  @Override
  public PaintingGallery getGalleryByPainting(String paintingTitle) {
    return paintingGalleryRepo.getGalleryByPainting(paintingTitle);
  }

  @Override
  public List<Painting> getAllPaintings() {
    return paintingRepo.getAllPaintings();
  }

  @Override
  public List<Artist> getAllArtists() {
    return artistRepo.getAllArtists();
  }

  @Override
  public List<PublicGallery> getAllPublicGalleries() {
    return paintingGalleryRepo.getAllPublicGalleries();
  }

  @Override
  public List<PublicGallery> getGalleryByOwner(String owner) {
    return paintingGalleryRepo.getPublicGalleryByOwner(owner);
  }
}
