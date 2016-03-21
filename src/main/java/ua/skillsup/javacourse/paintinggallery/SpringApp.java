package ua.skillsup.javacourse.paintinggallery;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Shine on 18.03.16.
 */
public class SpringApp {
  public static void main(String[] args) {
    final ApplicationContext appCtx =
        new AnnotationConfigApplicationContext(SpringConfig.class);
  }
}
