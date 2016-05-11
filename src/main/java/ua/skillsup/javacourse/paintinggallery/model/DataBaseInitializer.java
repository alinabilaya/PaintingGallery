package ua.skillsup.javacourse.paintinggallery.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.Address;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PublicGallery;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.Schedule;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;
import ua.skillsup.javacourse.paintinggallery.model.entity.security.User;
import ua.skillsup.javacourse.paintinggallery.model.repository.*;

import javax.inject.Inject;

/**
 * Created by Shine on 07.04.2016.
 */

@Component
public class DataBaseInitializer implements ApplicationListener<ContextRefreshedEvent> {

  private static final Logger log = LoggerFactory.getLogger(DataBaseInitializer.class);

  @Inject
  private ArtistRepo artistRepo;

  @Inject
  private PaintingRepo paintingRepo;

  @Inject
  private PaintingGalleryRepo paintingGalleryRepo;

  @Inject
  private ScheduleRepo scheduleRepo;

  @Inject
  private UserRepo userRepo;

  private final PasswordEncoder passwordEncoder =
          new StandardPasswordEncoder();

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {
    log.info(" --- app ctx started!");
    initDb();
    initUsers();
  }

  private void initDb() {
    /*creation of artists*/

    final Artist dali = new Artist("Salvador Dali", "Spain");
    dali.setImage("/resources/images/artists/Salvador_Dalí.jpg");
    dali.setInfo("Salvador Domingo Felipe Jacinto Dalí i Domènech, Marqués de Dalí de Pubol (11 May 1904 – 23 January 1989), known as Salvador Dalí (Catalan: [səɫβəˈðo ðəˈɫi]; Spanish: [salβaˈðoɾ ðaˈli]), was a prominent Spanish surrealist painter born in Figueres, Catalonia, Spain.\n" +
            "\n" +
            "Dalí was a skilled draftsman, best known for the striking and bizarre images in his surrealist work. His painterly skills are often attributed to the influence of Renaissance masters. His best-known work, The Persistence of Memory, was completed in August 1931. Dalí's expansive artistic repertoire included film, sculpture, and photography, in collaboration with a range of artists in a variety of media.\n" +
            "\n" +
            "Dalí attributed his \"love of everything that is gilded and excessive, my passion for luxury and my love of oriental clothes\" to an \"Arab lineage\", claiming that his ancestors were descended from the Moors.\n" +
            "\n" +
            "Dalí was highly imaginative, and also enjoyed indulging in unusual and grandiose behavior. His eccentric manner and attention-grabbing public actions sometimes drew more attention than his artwork, to the dismay of those who held his work in high esteem, and to the irritation of his critics.");

    final Artist repin = new Artist("Ilya Repin", "Russia");
    repin.setImage("/resources/images/artists/REPIN_portret_REPIN.jpg");
    repin.setInfo("Ilya Yefimovich Repin (Russian: Илья́ Ефи́мович Ре́пин; 5 August [O.S. 24 July] 1844 – 29 September 1930) was a Russian realist painter. He was the most renowned Russian artist of the 19th century, when his position in the world of art was comparable to that of Leo Tolstoy in literature. He played a major role in bringing Russian art into the mainstream of European culture. His major works include Barge Haulers on the Volga (1873), Religious Procession in Kursk Province (1883) and Reply of the Zaporozhian Cossacks (1880–91).\n" +
            "\n" +
            "Repin was born in Chuguyev, in the Kharkov Governorate (now Ukraine) of the Russian Empire into a military family. He entered military school in 1854 and in 1856 studied under Ivan Bunakov, a local icon painter. He began to paint around 1860. He met fellow artist Ivan Kramskoi and the critic Vladimir Stasov during the 1860s, and his wife, Vera Shevtsova in 1872 (they remained married for ten years). In 1874–1876 he showed at the Salon in Paris and at the exhibitions of the Itinerants' Society in Saint Petersburg. He was awarded the title of academician in 1876.\n" +
            "\n" +
            "In 1880 Repin traveled to Zaporozhia in Ukraine to gather material for the 1891 Reply of the Zaporozhian Cossacks. His Religious Procession in Kursk Province was exhibited in 1883, and Ivan the Terrible and His Son Ivan in 1885. In 1892 he published the Letters on Art collection of essays. He taught at the Higher Art School attached to the Academy of Arts from 1894. In 1898 he purchased an estate, the Penates, in Kuokkala, Finland (now Repino, Saint Petersburg).\n" +
            "\n" +
            "In 1901 he was awarded the Legion of Honour. In 1911 he traveled with his common-law wife Natalia Nordman to the World Exhibition in Italy, where his painting 17 October 1905 and his portraits were displayed in their own separate room. In 1916 Repin worked on his book of reminiscences, Far and Near, with the assistance of Korney Chukovsky. He welcomed the Russian Revolution of 1917. Celebrations were held in 1924 in Kuokkala to mark Repin's 80th birthday, followed by an exhibition of his works in Moscow. In 1925 a jubilee exhibition of his works was held in the Russian Museum in Leningrad. Repin died in 1930 and was buried at the Penates.");

    final Artist gogh = new Artist("Vincent van Gogh", "Netherlands");
    gogh.setImage("/resources/images/artists/Van_Gogh_self_portrait_as_an_artist.jpg");
    gogh.setInfo("Vincent Willem van Gogh Dutch: [ˈvɪnsɛnt ˈʋɪləm vɑn ˈɣɔx] ( listen); (30 March 1853 – 29 July 1890) was a Dutch post-Impressionist painter whose work had far-reaching influence on 20th-century art. His output includes portraits, self portraits, landscapes, still lifes, olive trees and cypresses, wheat fields and sunflowers. Critics largely ignored his work until after his presumed suicide in 1890. His short life, expressive and spontaneous use of vivid colours, broad oil brushstrokes and emotive subject matter, mean he is recognisable both in the modern public imagination as the quintessential misunderstood genius.\n" +
            "\n" +
            "Van Gogh was born to religious upper middle class parents. He was driven as an adult by a strong sense of purpose, but was also thoughtful and intellectual; he was equally aware of modernist currents in art, music and literature. He was well travelled and spent several years in his 20s working for a firm of art dealers in The Hague, London and Paris, after which he taught in England at Isleworth and Ramsgate. He drew as a child, but spent years drifting in ill health and solitude, and did not paint until his late twenties. Most of his best-known works were completed during the last two years of his life. Deeply religious as a younger man, he worked from 1879 as a missionary in a mining region in Belgium where he sketched people from the local community. His first major work was 1885's The Potato Eaters, from a time when his palette mainly consisted of sombre earth tones and showed no sign of the vivid colouration that distinguished his later paintings. In March 1886, he moved to Paris and discovered the French Impressionists. Later, he moved to the south of France and was inspired by the region's strong sunlight. His paintings grew brighter in colour, and he developed the unique and highly recognisable style that became fully realised during his stay in Arles in 1888. In just over a decade, he produced more than 2,100 artworks, including around 860 oil paintings. After years of anxiety and frequent bouts of mental illness he died aged 37 from a self-inflicted gunshot wound. The extent to which his mental health affected his painting has been widely debated.");

    artistRepo.add(dali);
    artistRepo.add(repin);
    artistRepo.add(gogh);

    /*creation of public galleries*/

    final Address address1 = new Address("Russia", "Moscow", "Lavrushinsky Lane, 10");
    final PublicGallery tretyakovGallery = new PublicGallery("The State Tretyakov Gallery", address1, "www.tretyakov.ru");
    tretyakovGallery.setImage("/resources/images/galleries/TretyakovGallery.jpg");
    tretyakovGallery.setDescription("The State Tretyakov Gallery (Russian: Государственная Третьяковская Галерея, Gosudarstvennaya Tretyâkovskaya Galereya; abbreviated ГТГ, GTG) is an art gallery in Moscow, Russia, the foremost depository of Russian fine art in the world.\n" +
            "\n" +
            "The gallery's history starts in 1856 when the Moscow merchant Pavel Mikhailovich Tretyakov acquired works by Russian artists of his day with the aim of creating a collection, which might later grow into a museum of national art. In 1892, Tretyakov presented his already famous collection of approximately 2,000 works (1,362 paintings, 526 drawings, and 9 sculptures) to the Russian nation.\n" +
            "\n" +
            "The façade of the gallery building was designed by the painter Viktor Vasnetsov in a peculiar Russian fairy-tale style. It was built in 1902–04 to the south from the Moscow Kremlin. During the 20th century, the gallery expanded to several neighboring buildings, including the 17th-century church of St. Nicholas in Tolmachi.\n" +
            "\n" +
            "The collection contains more than 130,000 exhibits, ranging from Theotokos of Vladimir and Andrei Rublev's Trinity to the monumental Composition VII by Wassily Kandinsky and the Black Square by Kazimir Malevich.\n" +
            "\n" +
            "In 1977 the Gallery kept a significant part of the George Costakis collection.\n" +
            "\n" +
            "In May 2012, the Tretyakov Art Gallery played host to the prestigious FIDE World Chess Championship between Viswanathan Anand and Boris Gelfand as the organizers felt the event would promote both chess and art at the same time.");
    paintingGalleryRepo.addPaintingGallery(tretyakovGallery);
    final Schedule schedule1 = new Schedule("Closed", "10:00 - 18:00", "10:00 - 18:00",
        "10:00 - 21:00", "10:00 - 21:00", "10:00 - 21:00", "10:00 - 18:00");
    schedule1.setPublicGallery(tretyakovGallery);
    scheduleRepo.addSchedule(schedule1);

    final Address address2 = new Address("USA", "New York", "11 West 53rd Street");
    final PublicGallery modernArtGallery = new PublicGallery("Museum of Modern Art", address2, "www.moma.org");
    modernArtGallery.setImage("/resources/images/galleries/MoMa_NY_USA.jpg");
    modernArtGallery.setDescription("The Museum of Modern Art (MoMA) is an art museum located in Midtown Manhattan in New York City, on 53rd Street between Fifth and Sixth Avenues. It has been important in developing and collecting modernist art, and is often identified as the most influential museum of modern art in the world. It is also one of the largest. The museum's collection offers an overview of modern and contemporary art, including works of architecture and design, drawing, painting, sculpture, photography, prints, illustrated books and artist's books, film and electronic media. The Library's holdings include approximately 300,000 books and exhibition catalogs, over 1,000 periodical titles, and over 40,000 files of ephemera about individual artists and groups. The archives holds primary source material related to the history of modern and contemporary art.");
    paintingGalleryRepo.addPaintingGallery(modernArtGallery);
    final Schedule schedule2 = new Schedule("09:00 - 17:30", "09:00 - 21:00", "Closed",
        "09:00 - 17:30", "09:00 - 21:00", "09:00 - 17:30", "09:00 - 17:30");
    schedule2.setPublicGallery(modernArtGallery);
    scheduleRepo.addSchedule(schedule2);

    final Address address3 = new Address("Russia", "Saint Petersburg", "Engineering Street, 4");
    final PublicGallery stateRussianMuseum = new PublicGallery("State Russian Museum", address3, "www.rusmuseum.ru");
    stateRussianMuseum.setImage("/resources/images/galleries/Russian_museam.jpg");
    stateRussianMuseum.setDescription("The State Russian Museum (formerly the Russian Museum of His Imperial Majesty Alexander III) is the largest depository of Russian fine art in Saint Petersburg. It is also one of the largest museums in the country.\n" +
            "\n" +
            "The museum was established on April 13, 1895, upon enthronement of Nicholas II to commemorate his father, Alexander III. Its original collection was composed of artworks taken from the Hermitage Museum, Alexander Palace, and the Imperial Academy of Arts. After the Russian Revolution of 1917, many private collections were nationalized and relocated to the Russian Museum. These included Kazimir Malevich's Black Square.\n" +
            "\n" +
            "The main building of the museum is the Mikhailovsky Palace, a splendid Neoclassical residence of Grand Duke Michael Pavlovich, erected in 1819-25 to a design by Carlo Rossi on Square of Arts in St Petersburg. Upon the death of the Grand Duke the residence was named after his wife as the Palace of the Grand Duchess Elena Pavlovna, and became famous for its many theatrical presentations and balls.\n" +
            "\n" +
            "Some of the halls of the palace retain the Italianate opulent interiors of the former imperial residence. Other buildings assigned to the Russian museum include the Summer Palace of Peter I (1710–14), the Marble Palace of Count Orlov (1768–85), St Michael's Castle of Emperor Paul (1797–1801), and the Rastrelliesque Stroganov Palace on the Nevsky Prospekt (1752–54).\n" +
            "The Russian Museum of Ethnography.\n" +
            "\n" +
            "The Ethnographic Department was originally set up in a building specially designed by Vladimir Svinyin in 1902. The museum soon housed gifts received by Emperor's family from representatives of peoples inhabiting various regions of the Russian Empire. Further exhibits were purchased by Nicholas II and other members of his family as State financing was not enough to purchase new exhibits. In 1934, the Ethnographic Department was given the status of an independent museum: the Russian Museum of Ethnography.");
    paintingGalleryRepo.addPaintingGallery(stateRussianMuseum);
    final Schedule schedule3 = new Schedule("10:00 - 18:00", "Closed", "10:00 - 18:00",
            "10:00 - 21:00", "10:00 - 18:00", "10:00 - 18:00", "10:00 - 18:00");
    schedule3.setPublicGallery(stateRussianMuseum);
    scheduleRepo.addSchedule(schedule3);

    final Address address4 = new Address("France", "Paris", "Rue de Lille 75343");
    final PublicGallery museumDOrsay = new PublicGallery("Musée d'Orsay", address4, "www.musee-orsay.fr");
    museumDOrsay.setImage("/resources/images/galleries/Musée_d'Orsay.jpg");
    museumDOrsay.setDescription("The Musée d'Orsay (French pronunciation: [myze dɔʁsɛ]) is a museum in Paris, France, on the left bank of the Seine. It is housed in the former Gare d'Orsay, a Beaux-Arts railway station built between 1898 and 1900. The museum holds mainly French art dating from 1848 to 1914, including paintings, sculptures, furniture, and photography. It houses the largest collection of impressionist and post-Impressionist masterpieces in the world, by painters including Monet, Manet, Degas, Renoir, Cézanne, Seurat, Sisley, Gauguin and Van Gogh. Many of these works were held at the Galerie nationale du Jeu de Paume prior to the museum's opening in 1986. It is one of the largest art museums in Europe.");
    paintingGalleryRepo.addPaintingGallery(museumDOrsay);
    final Schedule schedule4 = new Schedule("Closed", "09:30 - 18:00", "09:30 - 18:00",
            "09:30 - 21:30", "09:30 - 18:00", "09:30 - 18:00", "09:30 - 18:00");
    schedule4.setPublicGallery(museumDOrsay);
    scheduleRepo.addSchedule(schedule4);

    /*creation of paintings*/

    final Painting ivanTheTerriblePainting = new Painting("Ivan the Terrible and His Son Ivan", "1885", null);
    ivanTheTerriblePainting.setSummary("Although Repin strayed away from painting historical episodes, he completed Ivan the Terrible and His Son Ivan in the genre. This painting depicts the historical 16th century story of Ivan the Terrible mortally wounding his son in Ivan in a fit of rage. By far the most psychologically intense of Repin’s paintings, the Emperor’s face is fraught with terror, as his son lay quietly dying in his arms, blood dripping down the side of his face, a single tear on his cheek. Repin began thinking about painting this historical episode after the assassination of Alexander II. In an attempt to recall other bloody episodes of Russian history, he painted this piece as an expression of his rejection of violence and bloodshed.");
    ivanTheTerriblePainting.setArtist(artistRepo.getByName(repin.getName()).get(0));
    ivanTheTerriblePainting.setPaintingGallery(tretyakovGallery);
    ivanTheTerriblePainting.setImage("/resources/images/paintings/Ilja_Jefimowitsch_Repin_-_ivan_the_terrible_and_his_son_ivan.jpg");
    paintingRepo.add(ivanTheTerriblePainting);

    final Painting thePersistenceOfMemoryPainting = new Painting("The Persistence Of Memory", "1931", null);
    thePersistenceOfMemoryPainting.setSummary("The well-known surrealist piece introduced the image of the soft melting pocket watch. It epitomizes Dalí's theory of \"softness\" and \"hardness\", which was central to his thinking at the time. As Dawn Ades wrote, \"The soft watches are an unconscious symbol of the relativity of space and time, a Surrealist meditation on the collapse of our notions of a fixed cosmic order\". This interpretation suggests that Dalí was incorporating an understanding of the world introduced by Albert Einstein's Theory of Special Relativity. Asked by Ilya Prigogine whether this was in fact the case, Dalí replied that the soft watches were not inspired by the theory of relativity, but by the surrealist perception of a Camembert melting in the sun." +
            "External video Smarthistory - Dali's The Persistence of Memory" +
            "Salvador Dalí. The Persistence of Memory. 1931" +
            "\n" +
            "It is possible to recognize a human figure in the middle of the composition, in the strange \"monster\" that Dalí used in several contemporary pieces to represent himself – the abstract form becoming something of a self-portrait, reappearing frequently in his work. The figure can be read as a \"fading\" creature, one that often appears in dreams where the dreamer cannot pinpoint the creature's exact form and composition. One can observe that the creature has one closed eye with several eyelashes, suggesting that the creature is also in a dream state. The iconography may refer to a dream that Dalí himself had experienced, and the clocks may symbolize the passing of time as one experiences it in sleep or the persistence of time in the eyes of the dreamer." +
            "\n" +
            "The orange clock at the bottom left of the painting is covered in ants. Dalí often used ants in his paintings as a symbol of decay." +
            "\n" +
            "The Persistence of Memory employs \"the exactitude of realist painting techniques\" to depict imagery more likely to be found in dreams than in waking consciousness.");
    thePersistenceOfMemoryPainting.setArtist(artistRepo.getByName(dali.getName()).get(0));
    thePersistenceOfMemoryPainting.setPaintingGallery(modernArtGallery);
    thePersistenceOfMemoryPainting.setImage("/resources/images/paintings/the_persistence_of_memory.jpg");
    paintingRepo.add(thePersistenceOfMemoryPainting);

    final Painting replyOfTheCossacksPainting = new Painting("Reply of the Zaporozhian Cossacks", "1880-1891", null);
    replyOfTheCossacksPainting.setSummary("Reply of the Zaporozhian Cossacks to Sultan Mehmed IV of the Ottoman Empire, also known as Cossacks of Saporog Are Drafting a Manifesto (Russian: Запорожцы пишут письмо турецкому султану), is a painting by Russian artist Ilya Repin. The 2.03 m (6 foot 7 inch) by 3.58 m (11 foot 9 inch) canvas was started in 1880 and finished in 1891. Repin recorded the years of work along the lower edge of the canvas. Alexander III bought the painting for 35,000 rubles, at the time the greatest sum ever paid for a Russian painting. Since then, the canvas has been exhibited in the State Russian Museum in Saint Petersburg. Vladimir Gilyarovsky, a popular journalist, was one of the models who posed for Repin.");
    replyOfTheCossacksPainting.setArtist(artistRepo.getByName(repin.getName()).get(0));
    replyOfTheCossacksPainting.setPaintingGallery(stateRussianMuseum);
    replyOfTheCossacksPainting.setImage("/resources/images/paintings/Ilja_Jefimowitsch_Repin_-_Reply_of_the_Zaporozhian_Cossacks.jpg");
    paintingRepo.add(replyOfTheCossacksPainting);

    final Painting burlakiNaVolge = new Painting("Barge Haulers on the Volga", "1870-1873", null);
    burlakiNaVolge.setSummary("Barge Haulers on the Volga or Burlaki (Russian: Burlaki na Volge, Бурлаки на Волге) is an 1870–73 oil-on-canvas painting by the Russian realist painter and sculptor Ilya Repin. The work depicts 11 laboring men dragging a barge on the Volga River. The men seem to almost collapse forward in exhaustion under the burden of hauling a large boat upstream in heavy, hot weather." +
            "\n" +
            "The work is both a celebration of the men's dignity and fortitude, and a highly emotional condemnation of those who sanctioned such inhumane labor. Although they are presented as stoical and accepting, the men are largely defeated; only one stands out: in the center of both the row and canvas, a brightly colored youth fights against his leather binds and takes on a heroic poise." +
            "\n" +
            "Repin conceived the painting during his travels through Russia as a young man and depicts actual characters he encountered. It drew international praise for its realistic portrayal of the hardships of working men, and launched his career. Soon after its completion, the painting was purchased by Grand Duke Vladimir Alexandrovich and exhibited widely throughout Europe as a landmark of Russian realist painting. Barge Haulers on the Volga has been described as \"perhaps the most famous painting of the Peredvizhniki movement [for]....its unflinching portrayal of backbreaking labor\".");
    burlakiNaVolge.setArtist(artistRepo.getByName(repin.getName()).get(0));
    burlakiNaVolge.setPaintingGallery(stateRussianMuseum);
    burlakiNaVolge.setImage("/resources/images/paintings/Ilia_Efimovich_Repin_-_Burlaki_na_Volge.jpg");
    paintingRepo.add(burlakiNaVolge);

    final Painting starryNight = new Painting("The Starry Night", "1889", null);
    starryNight.setSummary("The Starry Night is an oil on canvas by the Dutch post-impressionist painter Vincent van Gogh. Painted in June 1889, it depicts the view from the east-facing window of his asylum room at Saint-Rémy-de-Provence, just before sunrise, with the addition of an idealized village. It has been in the permanent collection of the Museum of Modern Art in New York City since 1941, acquired through the Lillie P. Bliss Bequest. It is regarded as among Van Gogh's finest works,[4] and is one of the most recognized paintings in the history of Western culture.");
    starryNight.setArtist(artistRepo.getByName(gogh.getName()).get(0));
    starryNight.setPaintingGallery(modernArtGallery);
    starryNight.setImage("/resources/images/paintings/the_starry_night.jpg");
    paintingRepo.add(starryNight);

    final Painting theChurch = new Painting("The Church at Auvers", "1890", null);
    theChurch.setSummary("The Church at Auvers is an oil painting created by Dutch post-impressionist artist Vincent van Gogh in June 1890 which now hangs in the Musée d'Orsay in Paris, France.\n" +
            "\n" +
            "The actual church is in Place de l'Eglise, Auvers-sur-Oise, France, 27 km northwest of Paris.");
    theChurch.setArtist(gogh);
    theChurch.setPaintingGallery(museumDOrsay);
    theChurch.setImage("/resources/images/paintings/The_Church_in_Auvers-sur-Oise.jpg");
    paintingRepo.add(theChurch);
  }

  /*creation of users*/

  private void initUsers() {
    final User user = new User();
    user.setUsername("user");
    user.setPassword(passwordEncoder.encode("userpass123"));
    user.setName("userName");
    user.setEmail("userEmail@mail.com");
    user.setAdmin(false);
    user.setEnabled(true);

    userRepo.add(user);

    final User admin = new User();
    admin.setUsername("admin");
    admin.setPassword(passwordEncoder.encode("adminpass123"));
    admin.setName("adminName");
    admin.setEmail("adminEmail@mail.com");
    admin.setAdmin(true);
    admin.setEnabled(true);

    userRepo.add(admin);
  }
}
