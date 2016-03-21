package ua.skillsup.javacourse.paintinggallery.application.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.javacourse.paintinggallery.application.GalleryEditService;
import ua.skillsup.javacourse.paintinggallery.model.gallery.*;
import ua.skillsup.javacourse.paintinggallery.model.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.model.painting.ArtistRepo;
import ua.skillsup.javacourse.paintinggallery.model.painting.Painting;
import ua.skillsup.javacourse.paintinggallery.model.painting.PaintingRepo;

import javax.inject.Inject;


/**
 * Created by Shine on 17.03.2016.
 */

@Service
@Transactional
public class GalleryEditServiceImpl implements GalleryEditService{

  @Inject
  private ArtistRepo artistRepo;

  @Inject
  private PaintingRepo paintingRepo;

  @Inject
  private PaintingGalleryRepo paintingGalleryRepo;

  @Override
  public Artist createArtist(String artistName, String artistCountry) {
    final Artist artist = new Artist();
    artist.setName(artistName);
    artist.setCountry(artistCountry);

    artistRepo.add(artist);

    return artist;
  }

  @Override
  public Painting createPainting(String paintingTitle, int paintingDateMade,
                                 String paintingSummary, long artistId, long galleryId) {

    final Artist artist = artistRepo.getById(artistId);
    final PaintingGallery gallery = paintingGalleryRepo.findById(galleryId);

    final Painting painting = new Painting();
    painting.setTitle(paintingTitle);
    painting.setDateMade(paintingDateMade);
    painting.setSummary(paintingSummary);
    painting.setArtist(artist);
    painting.setPaintingGallery(gallery);

    paintingRepo.add(painting);

    return painting;
  }

  @Override
  public PaintingGallery createPrivateGallery(String paintingGalleryOwner) {
    final PrivateGallery privateGallery = new PrivateGallery();

    privateGallery.setOwner(paintingGalleryOwner);

    paintingGalleryRepo.add(privateGallery);

    return privateGallery;
  }

  @Override
  public PublicGallery createPublicGallery(String paintingGalleryOwner, Address address) {
    final PublicGallery publicGallery = new PublicGallery();
    publicGallery.setOwner(paintingGalleryOwner);
    publicGallery.setAddress(address);

    paintingGalleryRepo.add(publicGallery);

    return publicGallery;
  }
}
