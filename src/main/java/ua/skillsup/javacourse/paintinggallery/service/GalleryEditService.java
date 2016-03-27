package ua.skillsup.javacourse.paintinggallery.service;

import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PaintingGallery;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PublicGallery;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.Schedule;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;

/**
 * Created by Shine on 17.03.2016.
 */
public interface GalleryEditService {

  Artist addArtist(String artistName, String artistCountry);

  Painting addPainting(String paintingTitle, int paintingDateMade,
                       String paintingSummary, String artistName, String galleryOwner);

  PaintingGallery addPrivateGallery(String paintingGalleryOwner);

  PublicGallery addPublicGallery(String paintingGalleryOwner, String country, String city,
                                 String street, String homePage);

  Schedule addPublicGallerySchedule(String publicGalleryOwner, String mondayTime,
                                    String tuesdayTime, String wednesdayTime, String thursdayTime,
                                    String fridayTime, String saturdayTime, String sundayTime);
}
