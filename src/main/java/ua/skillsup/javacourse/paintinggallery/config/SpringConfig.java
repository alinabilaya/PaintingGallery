package ua.skillsup.javacourse.paintinggallery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.skillsup.javacourse.paintinggallery.application.GalleryEditService;
import ua.skillsup.javacourse.paintinggallery.application.GallerySearchService;
import ua.skillsup.javacourse.paintinggallery.application.impl.GalleryEditServiceImpl;
import ua.skillsup.javacourse.paintinggallery.application.impl.GallerySearchServiceImpl;

/**
 * Created by Shine on 18.03.16.
 */

@Configuration
@ComponentScan ( basePackages = {
         "ua.skillsup.javacourse.paintinggallery.model",
        "ua.skillsup.javacourse.paintinggallery.persistence",
        "ua.skillsup.javacourse.paintinggallery.application",
        }
)
@EnableTransactionManagement
public class SpringConfig {

//  @Autowired
//  SessionFactory sessionFactory;
//
//  @Bean
//  public DataSource dataSource() {
//    final HikariConfig hikariConfig = new HikariConfig();
//    hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
//    hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/hibernate");
//    hikariConfig.setUsername("root");
//    hikariConfig.setPassword("");
//    return new HikariDataSource(hikariConfig);
//  }


  @Bean
  GallerySearchService gallerySearchService() {
    return new GallerySearchServiceImpl();
  }

  @Bean
  GalleryEditService galleryEditService() {
    return new GalleryEditServiceImpl();
  }
}
