package ua.skillsup.javacourse.paintinggallery.persistence;

import java.util.List;

/**
 * Created by Shine on 18.03.16.
 */
public final class Util {

  private Util() {
  }

  @SuppressWarnings("unchecked")
  public static <T> List<T> castList(List list) {
    return list;
  }
}
