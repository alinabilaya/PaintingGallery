package ua.skillsup.javacourse.paintinggallery;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.skillsup.javacourse.paintinggallery.application.GalleryEditService;
import ua.skillsup.javacourse.paintinggallery.application.GallerySearchService;
import ua.skillsup.javacourse.paintinggallery.config.ProdDataConfig;
import ua.skillsup.javacourse.paintinggallery.config.SpringConfig;
import ua.skillsup.javacourse.paintinggallery.config.TestDataConfig;

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

    galleryEditService.createArtist("newArtist", "UK");
    gallerySearchService.findArtist("newArtist");
  }
}
