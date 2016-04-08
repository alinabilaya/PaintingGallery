package ua.skillsup.javacourse.paintinggallery.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.javacourse.paintinggallery.model.repository.ArtistRepo;
import ua.skillsup.javacourse.paintinggallery.model.repository.PaintingRepo;

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

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {
    log.info(" --- app ctx started!");
    initDb();
  }

  private void initDb() {

  }
}
