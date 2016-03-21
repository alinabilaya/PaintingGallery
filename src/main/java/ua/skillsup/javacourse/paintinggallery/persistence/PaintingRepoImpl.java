package ua.skillsup.javacourse.paintinggallery.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ua.skillsup.javacourse.paintinggallery.model.painting.Painting;
import ua.skillsup.javacourse.paintinggallery.model.painting.PaintingRepo;

import javax.inject.Inject;
import java.util.List;

import static ua.skillsup.javacourse.paintinggallery.persistence.Util.castList;

/**
 * Created by Shine on 18.03.16.
 */

@Repository
public class PaintingRepoImpl implements PaintingRepo {

  @Inject
  private SessionFactory sessionFactory;

  @Override
  public List<Painting> findPaintingByTitle(String paintingTitle) {
    return castList(sessionFactory.getCurrentSession()
        .createCriteria(Painting.class)
        .add(Restrictions.ilike("title", "%" + paintingTitle + "%"))
        .list());
  }

  @Override
  public void add(Painting painting){
    sessionFactory.getCurrentSession().save(painting);
  }
}
