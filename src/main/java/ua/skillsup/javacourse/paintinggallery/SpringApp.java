package ua.skillsup.javacourse.paintinggallery;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PublicGallery;
import ua.skillsup.javacourse.paintinggallery.service.GalleryEditService;
import ua.skillsup.javacourse.paintinggallery.service.GallerySearchService;
import ua.skillsup.javacourse.paintinggallery.config.ProdDataConfig;
import ua.skillsup.javacourse.paintinggallery.config.SpringConfig;

/**
 * Created by Shine on 18.03.16.
 */
public class SpringApp {
  public static void main(String[] args) {
    final AnnotationConfigApplicationContext appCtx =
        new AnnotationConfigApplicationContext();

    appCtx.getEnvironment().setActiveProfiles("prod");
    appCtx.register(SpringConfig.class, ProdDataConfig.class);
    appCtx.refresh();

    GallerySearchService gallerySearchService = appCtx.getBean(GallerySearchService.class);
    GalleryEditService galleryEditService = appCtx.getBean(GalleryEditService.class);

    galleryEditService.addArtist("Leonardo da Vinci", "Italy");
    gallerySearchService.getArtist("Leonardo da Vinci");
    galleryEditService.addPublicGallery("Nation Gallery", "UK", "London", "Trafalgar Square", "www.someSite");
    galleryEditService.addPainting("Bacchus", 1515, "This is an awesome painting!", "Leonardo da Vinci", "Nation Gallery");
    galleryEditService.addPublicGallerySchedule("Nation Gallery", "09:00 - 18:00", "09:00 - 21:00", "closed",
            "09:00 - 18:00", "09:00 - 18:00", "09:00 - 21:00","09:00 - 18:00");

    PublicGallery publicGallery = (PublicGallery) gallerySearchService.getGalleryByPainting("Bacchus");

    System.out.println(publicGallery);
  }
}
