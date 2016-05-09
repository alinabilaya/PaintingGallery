package ua.skillsup.javacourse.paintinggallery.service.impl;

import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.*;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;
import ua.skillsup.javacourse.paintinggallery.model.repository.ArtistRepo;
import ua.skillsup.javacourse.paintinggallery.model.repository.PaintingGalleryRepo;
import ua.skillsup.javacourse.paintinggallery.model.repository.PaintingRepo;
import ua.skillsup.javacourse.paintinggallery.model.repository.ScheduleRepo;
import ua.skillsup.javacourse.paintinggallery.service.GalleryEditService;

import javax.inject.Inject;
import java.util.List;


/**
 * Created by Shine on 17.03.2016.
 */


@Transactional
public class GalleryEditServiceImpl implements GalleryEditService {

  @Inject
  private ArtistRepo artistRepo;

  @Inject
  private PaintingRepo paintingRepo;

  @Inject
  private PaintingGalleryRepo paintingGalleryRepo;

  @Inject
  ScheduleRepo scheduleRepo;

  @Override
  public Artist addArtist(String artistName, String artistCountry) {
    final Artist artist = new Artist(artistName, artistCountry);
    artistRepo.add(artist);

    return artist;
  }

  @Override
  public Painting addPainting(String paintingTitle, String paintingDateMade, String paintingSummary,
                              String artistName, String galleryOwner) {

    final Artist artist = artistRepo.getByName(artistName).get(0);
    final PaintingGallery gallery = paintingGalleryRepo.getGalleryByOwner(galleryOwner).get(0);
    final Painting painting = new Painting(paintingTitle, paintingDateMade, paintingSummary);
    painting.setArtist(artist);
    painting.setPaintingGallery(gallery);
    paintingRepo.add(painting);

    return painting;
  }

  @Override
  public PaintingGallery addPrivateGallery(String paintingGalleryOwner) {

    final PrivateGallery privateGallery = new PrivateGallery(paintingGalleryOwner);
    paintingGalleryRepo.addPaintingGallery(privateGallery);

    return privateGallery;
  }

  @Override
  public PublicGallery addPublicGallery(String paintingGalleryOwner, String country, String city,
                                        String street, String homePage) {

    final Address address = new Address(country, city, street);
    final PublicGallery publicGallery = new PublicGallery(paintingGalleryOwner, address, homePage);
    paintingGalleryRepo.addPaintingGallery(publicGallery);

    return publicGallery;
  }

  @Override
  public Schedule addPublicGallerySchedule(String publicGalleryOwner, String mondayTime,
                                           String tuesdayTime, String wednesdayTime, String thursdayTime,
                                           String fridayTime, String saturdayTime, String sundayTime) {


    List<Schedule> list = scheduleRepo.getScheduleByGallery(publicGalleryOwner);

    if (list.size() == 0) {
      final PublicGallery publicGallery = (PublicGallery) paintingGalleryRepo.getGalleryByOwner(publicGalleryOwner).get(0);
      final Schedule schedule = new Schedule(mondayTime, tuesdayTime, wednesdayTime, thursdayTime, fridayTime,
              saturdayTime, sundayTime);
      schedule.setPublicGallery(publicGallery);
      scheduleRepo.addSchedule(schedule);

      return schedule;
    } else return null;
  }

  @Override
  public Painting updatePainting(Painting painting) {
    Painting origPainting = paintingRepo.getPaintingById(painting.getId());

    origPainting.setTitle(painting.getTitle());
    origPainting.setDateMade(painting.getDateMade());
    origPainting.setSummary(painting.getSummary());
    origPainting.setImage(painting.getImage());

    return painting;
  }

  @Override
  public Artist updateArtist(Artist artist) {
    Artist originArtist = artistRepo.getById(artist.getId());

    originArtist.setName(artist.getName());
    originArtist.setCountry(artist.getCountry());
    originArtist.setInfo(artist.getInfo());
    originArtist.setImage(artist.getImage());

    return artist;
  }

  @Override
  public PublicGallery updateGallery(PublicGallery publicGallery) {
    PublicGallery originGallery = (PublicGallery) paintingGalleryRepo.getById(publicGallery.getId());

    originGallery.setOwner(publicGallery.getOwner());
    originGallery.setAddress(publicGallery.getAddress());
    originGallery.setHomePage(publicGallery.getHomePage());

    return publicGallery;
  }
}
