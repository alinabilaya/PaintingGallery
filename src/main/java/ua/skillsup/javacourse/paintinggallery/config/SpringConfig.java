package ua.skillsup.javacourse.paintinggallery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.skillsup.javacourse.paintinggallery.service.GalleryEditService;
import ua.skillsup.javacourse.paintinggallery.service.GallerySearchService;
import ua.skillsup.javacourse.paintinggallery.service.impl.GalleryEditServiceImpl;
import ua.skillsup.javacourse.paintinggallery.service.impl.GallerySearchServiceImpl;

/**
 * Created by Shine on 18.03.16.
 */

@Configuration
@ComponentScan
@EnableTransactionManagement
public class SpringConfig {

  @Bean
  GallerySearchService gallerySearchService() {
    return new GallerySearchServiceImpl();
  }

  @Bean
  GalleryEditService galleryEditService() {
    return new GalleryEditServiceImpl();
  }
}
