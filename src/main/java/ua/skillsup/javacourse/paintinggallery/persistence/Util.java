package ua.skillsup.javacourse.paintinggallery.persistence;

import java.util.List;

/**
 * Created by Shine on 18.03.16.
 */
public final class Util {

  private Util() {
  }

  @SuppressWarnings("unchecked")
  static <T> List<T> castList(List list) {
    return list;
  }

  @SuppressWarnings("unchecked")
  static <T> T cast(Object o) {
    return (T) o;
  }
}
