import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ua.skillsup.javacourse.paintinggallery.config.SpringConfig;
import ua.skillsup.javacourse.paintinggallery.config.TestDataConfig;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.*;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;
import ua.skillsup.javacourse.paintinggallery.model.repository.PaintingGalleryRepo;
import ua.skillsup.javacourse.paintinggallery.model.repository.ScheduleRepo;
import ua.skillsup.javacourse.paintinggallery.service.GalleryEditService;
import ua.skillsup.javacourse.paintinggallery.service.GallerySearchService;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by Shine on 19.03.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles(value = "test")
@ContextConfiguration(classes = {SpringConfig.class, TestDataConfig.class})
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
  private ScheduleRepo scheduleRepo;

  @Inject
  private SessionFactory sessionFactory;

  @Test
  public void addArtistTest() {
    //create new artist
    final Artist vanGogh = new Artist("Vincent van Gogh", "Holland");
    //add artist
    galleryEditService.addArtist(
            vanGogh.getName(),
            vanGogh.getCountry());
    //get list of all artist by name and check that only one artist has been found
    final List<Artist> list = gallerySearchService.getArtist("Vincent van Gogh");
    assertTrue(list.size()==1);
    //get this artist and check that it is the same created artist
    Artist vanGogh2 = list.get(0);
    assertEquals(vanGogh, vanGogh2);
    //check that artist has no paintings
    final TransactionStatus transaction = txManager.getTransaction(new DefaultTransactionDefinition());
    sessionFactory.getCurrentSession().refresh(vanGogh2);
    List artistPaintings = vanGogh2.getPaintings();
    assertTrue(artistPaintings.size() == 0);
    txManager.commit(transaction);
    //check also gallerySearchService
    assertTrue(gallerySearchService.getAllArtistPaintings("Vincent van Gogh").size() == 0);
  }

  @Test
  public void addPrivateGalleryTest() {
    //create new private gallery
    final PrivateGallery privateGallery = new PrivateGallery("ownerOfPrivateCollection");
    //add this gallery
    galleryEditService.addPrivateGallery(
            privateGallery.getOwner());
    //get list off all galleries by name and check that only one private gallery has been found
    List list = paintingGalleryRepo.getGalleryByOwner("ownerOfPrivateCollection");
    assertTrue(list.size() == 1);
    //get this gallery and check that it is the same created private gallery
    PrivateGallery privateGallery2 = (PrivateGallery) list.get(0);
    assertEquals(privateGallery, privateGallery2);
  }

  @Test
  public void createPaintingTest() {
    //add artist and private gallery
    galleryEditService.addArtist("Michelangelo", "Italy");
    galleryEditService.addPrivateGallery("PrivateCollection");
    //get artist and private gallery by name
    Artist michelangelo = gallerySearchService.getArtist("Michelangelo").get(0);
    PrivateGallery privateGallery = (PrivateGallery)paintingGalleryRepo.getGalleryByOwner("PrivateCollection").get(0);
    //create new painting
    final Painting theMusicians = new Painting("The Musiciants", 1595, "Some summary");
    theMusicians.setArtist(michelangelo);
    theMusicians.setPaintingGallery(privateGallery);
    //add this painting
    galleryEditService.addPainting(
            theMusicians.getTitle(),
            theMusicians.getDateMade(),
            theMusicians.getSummary(),
            michelangelo.getName(),
            privateGallery.getOwner());
    //check that artist has one painting
    final TransactionStatus transaction = txManager.getTransaction(new DefaultTransactionDefinition());
    sessionFactory.getCurrentSession().refresh(michelangelo);
    final List<Painting> paintings = michelangelo.getPaintings();
    assertTrue(paintings.size() == 1);
    txManager.commit(transaction);
    //check also gallerySearchService
    List paintings2 = gallerySearchService.getAllArtistPaintings("Michelangelo");
    assertTrue(paintings2.size() == 1);
    //get painting by name and check that it is the same created painting
    final PaintingGallery privateGallery2 = gallerySearchService.getGalleryByPainting("The Musiciants");
    assertEquals(privateGallery, privateGallery2);
  }

  @Test
  public void createPublicGalleryAndScheduleTest() {
    //create public gallery
    final Address address = new Address("USA", "New York", null);
    final PublicGallery publicGallery = new PublicGallery("Metropolitan Museum of Art", address, "http://somePage");
    //add this gallery
    galleryEditService.addPublicGallery(publicGallery.getOwner(), address.getCountry(), address.getCity(), null, publicGallery.getHomePage());
    //find list of all galleries by name and check that only one gallery has been found
    final List<PaintingGallery> galleriesList = paintingGalleryRepo.getGalleryByOwner("Metropolitan Museum of Art");
    assertTrue(galleriesList.size() == 1);
    //get this gallery and check that it is the same created public gallery
    PublicGallery publicGallery2 = (PublicGallery)galleriesList.get(0);
    assertEquals(publicGallery, publicGallery2);
    //create new schedule
    final Schedule gallerySchedule = new Schedule("09:00 - 17:30", "09:00 - 21:00", "Closed",
            "09:00 - 17:30", "09:00 - 21:00", "09:00 - 17:30", "09:00 - 17:30");
    //add this schedule
    galleryEditService.addPublicGallerySchedule("Metropolitan Museum of Art", gallerySchedule.getMondayTime(),
            gallerySchedule.getTuesdayTime(), gallerySchedule.getWednesdayTime(), gallerySchedule.getThursdayTime(),
            gallerySchedule.getFridayTime(), gallerySchedule.getSaturdayTime(), gallerySchedule.getSundayTime());
    //get schedule and check that it is the same created schedule
    final TransactionStatus transaction = txManager.getTransaction(new DefaultTransactionDefinition());
    sessionFactory.getCurrentSession().refresh(publicGallery2);
    Schedule gallerySchedule2 = scheduleRepo.getPublicGallerySchedule(publicGallery2);
    assertEquals(gallerySchedule, gallerySchedule2);
    txManager.commit(transaction);
  }

  @Test
  public void testFindingAllPaintings() {
    final List<Painting> paintings = gallerySearchService.getAllPaintings();
    for(int i = 0; i < paintings.size(); i++) {
    System.out.println(paintings.get(i));
    }
  }
}
