import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ua.skillsup.javacourse.paintinggallery.config.SpringConfig;
import ua.skillsup.javacourse.paintinggallery.config.TestDataConfig;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.*;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;
import ua.skillsup.javacourse.paintinggallery.model.entity.security.User;
import ua.skillsup.javacourse.paintinggallery.model.repository.PaintingGalleryRepo;
import ua.skillsup.javacourse.paintinggallery.model.repository.ScheduleRepo;
import ua.skillsup.javacourse.paintinggallery.model.repository.UserRepo;
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
@ContextConfiguration(classes = {SpringConfig.class, TestDataConfig.class})
//@ActiveProfiles(value = "test")
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

  @Inject
  private UserRepo userRepo;


  @Test
  public void addArtistTest() {
    //create new artist
    final Artist artist = new Artist("Artist", "Poland");
    //add artist
    galleryEditService.addArtist(
            artist.getName(),
            artist.getCountry());
    //get list of all artist by name and check that only one artist has been found
    final List<Artist> artistList = gallerySearchService.getArtistByName("Artist");
    assertTrue(artistList.size() == 1);
    //get this artist and check that it is the same created artist
    Artist artist1 = artistList.get(0);
    assertEquals(artist, artist1);
    //check that artist has no paintings
    final TransactionStatus transaction = txManager.getTransaction(new DefaultTransactionDefinition());
    sessionFactory.getCurrentSession().refresh(artist1);
    List artistPaintings = artist1.getPaintings();
    assertTrue(artistPaintings.size() == 0);
    txManager.commit(transaction);
    //check also gallerySearchService
    assertTrue(gallerySearchService.getAllArtistPaintings("Artist").size() == 0);
  }

  @Test
  @Transactional
  public void addPrivateGalleryTest() {
    //create new private gallery
    final PrivateGallery privateGallery = new PrivateGallery("ownerOfPrivateCollection");
    //add this gallery
    galleryEditService.addPrivateGallery(privateGallery.getOwner());
    //get list off all galleries by name and check that only one private gallery has been found
    List list = paintingGalleryRepo.getGalleryByOwner("ownerOfPrivateCollection");
    System.out.println("\n" + list.get(0));
    assertTrue(list.size() == 1);
  }

  @Test
  public void createPaintingTest() {
    //add artist and private gallery
    galleryEditService.addArtist("Michelangelo", "Italy");
    galleryEditService.addPrivateGallery("PrivateCollection");
    //get artist and private gallery by name
    Artist michelangelo = gallerySearchService.getArtistByName("Michelangelo").get(0);
    PrivateGallery privateGallery = (PrivateGallery) paintingGalleryRepo.getGalleryByOwner("PrivateCollection").get(0);
    //create new painting_view.jsp
    final Painting theMusicians = new Painting("The Musiciants", "1595", "Some summary");
    theMusicians.setArtist(michelangelo);
    theMusicians.setPaintingGallery(privateGallery);
    //add this painting_view.jsp
    galleryEditService.addPainting(
            theMusicians.getTitle(),
            theMusicians.getDateMade(),
            theMusicians.getSummary(),
            michelangelo.getName(),
            privateGallery.getOwner());
    //check that artist has one painting_view.jsp
    final TransactionStatus transaction = txManager.getTransaction(new DefaultTransactionDefinition());
    sessionFactory.getCurrentSession().refresh(michelangelo);
    final List<Painting> paintings = michelangelo.getPaintings();
    assertTrue(paintings.size() == 1);
    txManager.commit(transaction);
    //check also gallerySearchService
    List paintings2 = gallerySearchService.getAllArtistPaintings("Michelangelo");
    assertTrue(paintings2.size() == 1);
    //get painting_view.jsp by name and check that it is the same created painting_view.jsp
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
    final List<PublicGallery> galleriesList = gallerySearchService.searchPublicGalleryByOwner("Metropolitan Museum of Art");
    assertTrue(galleriesList.size() == 1);
    //get this gallery and check that it is the same created public gallery
    PublicGallery publicGallery2 = (PublicGallery) galleriesList.get(0);
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

    List<PublicGallery> list = gallerySearchService.getAllPublicGalleries();
    assertEquals(5, list.size());
}

  private final PasswordEncoder passwordEncoder =
          new StandardPasswordEncoder();

  @Test
  public void testFindUserByName() {
    final User admin = new User();
    admin.setUsername("testUser");
    admin.setPassword(passwordEncoder.encode("test123"));
    admin.setAdmin(true);
    admin.setEnabled(true);
    admin.setName("testAdmin");
    admin.setEmail("someEmail@mail.com");

    userRepo.add(admin);

    User foundUser = userRepo.getByName("testUser").get();

    assertEquals(admin, foundUser);
  }
}

