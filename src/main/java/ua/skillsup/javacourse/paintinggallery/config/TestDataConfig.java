package ua.skillsup.javacourse.paintinggallery.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by Shine on 25.03.2016.
 */

@Configuration
@Profile("test")
@ComponentScan( basePackages = {
        "ua.skillsup.javacourse.paintinggallery.model",
        "ua.skillsup.javacourse.paintinggallery.persistence",
        "ua.skillsup.javacourse.paintinggallery.application",
}
)
@EnableTransactionManagement
public class TestDataConfig {

  @Bean
  public DataSource dataSource() {
    final HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl("jdbc:h2:mem:books_db;DB_CLOSE_DELAY=-1");
    return new HikariDataSource(hikariConfig);
  }

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan("ua.skillsup.javacourse.paintinggallery.model");
    sessionFactory.setConfigLocation(new ClassPathResource("hibernate_spring.cfg.xml"));

    return sessionFactory;
  }

  @Bean
  public PlatformTransactionManager txManager() {
    return new HibernateTransactionManager(sessionFactory().getObject());
  }
}
