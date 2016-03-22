package ua.skillsup.javacourse.paintinggallery;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.skillsup.javacourse.paintinggallery.application.GalleryEditService;
import ua.skillsup.javacourse.paintinggallery.application.GallerySearchService;

/**
 * Created by Shine on 18.03.16.
 */
public class SpringApp {
  public static void main(String[] args) {
    final ApplicationContext appCtx =
        new AnnotationConfigApplicationContext(SpringConfig.class);

    GallerySearchService gallerySearchService = appCtx.getBean(GallerySearchService.class);
    GalleryEditService galleryEditService = appCtx.getBean(GalleryEditService.class);

    galleryEditService.createArtist("newArtist", "UK");
    gallerySearchService.findArtist("newArtist");
  }
}
