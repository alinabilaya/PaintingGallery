import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ua.skillsup.javacourse.paintinggallery.application.GalleryEditService;
import ua.skillsup.javacourse.paintinggallery.application.GallerySearchService;
import ua.skillsup.javacourse.paintinggallery.config.SpringConfig;
import ua.skillsup.javacourse.paintinggallery.config.TestDataConfig;
import ua.skillsup.javacourse.paintinggallery.model.gallery.*;
import ua.skillsup.javacourse.paintinggallery.model.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.model.painting.Painting;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Created by Shine on 19.03.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, TestDataConfig.class})
@ActiveProfiles("test")
public class GalleryEditServiceTest {

  @Inject
  private PlatformTransactionManager txManager;

  @Inject
  private GalleryEditService galleryEditService;

  @Inject
  private GallerySearchService gallerySearchService;

  @Inject
  private PaintingGalleryRepo paintingGalleryRepo;

  @Inject
  private SessionFactory sessionFactory;

  @Test
  public void createArtistAndPainting() {
    final Artist michelangelo = new Artist();
    michelangelo.setName("Michelangelo");
    michelangelo.setCountry("Italy");

    galleryEditService.createArtist(
        michelangelo.getName(),
        michelangelo.getCountry());

    final List<Artist> list = gallerySearchService.findArtist("Michelangelo");
    assertEquals(1, list.size());
    Artist michelangelo2 = list.get(0);
    assertEquals(michelangelo, michelangelo2);

    final PrivateGallery privateGallery = new PrivateGallery();
    privateGallery.setOwner("ownerOfPrivateCollection");
    galleryEditService.createPrivateGallery(privateGallery.getOwner());

    final Painting theMusicians = new Painting();
    theMusicians.setTitle("The Musiciants");
    theMusicians.setDateMade(1595);
    theMusicians.setSummary("Some summary");
    theMusicians.setArtist(michelangelo2);
    theMusicians.setPaintingGallery(privateGallery);

    galleryEditService.createPainting(theMusicians.getTitle(), theMusicians.getDateMade(),
        theMusicians.getSummary(), 1, 1);

    final TransactionStatus transaction = txManager.getTransaction(new DefaultTransactionDefinition());
    sessionFactory.getCurrentSession().refresh(michelangelo2);
    final List<Painting> paintings = michelangelo2.getPaintings();

    assertEquals(1, paintings.size());
    txManager.commit(transaction);

    final PaintingGallery privateGallery2 = gallerySearchService.findGalleryByPainting("The Musiciants");
    assertEquals(privateGallery, privateGallery2);
  }

  @Test
  public void createPaintingGallery() {

    final PublicGallery publicGallery = new PublicGallery();
    final Address address = new Address();
    address.setCountry("New York");
    publicGallery.setOwner("Metropolitan Museum of Art");
    publicGallery.setAddress(address);

    galleryEditService.createPublicGallery(publicGallery.getOwner(), address);

    final List<PaintingGallery> galleriesList = paintingGalleryRepo.findGalleryByOwner("Metropolitan Museum of Art");

    assertEquals(1, galleriesList.size());
    PaintingGallery publicGallery2 = galleriesList.get(0);
    assertEquals(publicGallery, publicGallery2);
  }
}
