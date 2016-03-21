package ua.skillsup.javacourse.paintinggallery.application;

import ua.skillsup.javacourse.paintinggallery.model.gallery.Address;
import ua.skillsup.javacourse.paintinggallery.model.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.model.painting.Painting;
import ua.skillsup.javacourse.paintinggallery.model.gallery.PaintingGallery;
import ua.skillsup.javacourse.paintinggallery.model.gallery.PublicGallery;

/**
 * Created by Shine on 17.03.2016.
 */
public interface GalleryEditService {

  Artist createArtist(String artistName, String artistCountry);

  Painting createPainting(String paintingTitle, int paintingDateMade,
                          String paintingSummary, long artistId, long galleryId);

  PaintingGallery createPrivateGallery(String paintingGalleryOwner);

  PublicGallery createPublicGallery(String paintingGalleryOwner, Address address);
}
