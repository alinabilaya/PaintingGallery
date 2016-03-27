package ua.skillsup.javacourse.paintinggallery.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;
import ua.skillsup.javacourse.paintinggallery.model.repository.PaintingRepo;

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
  public List<Painting> getPaintingByTitle(String paintingTitle) {
    return castList(sessionFactory.getCurrentSession()
        .createCriteria(Painting.class)
        .add(Restrictions.ilike("title", "%" + paintingTitle + "%"))
        .list());
  }

  @Override
  public void add(Painting painting){

    List<Painting> list = getPaintingByTitle(painting.getTitle());

    if (list.size() != 0){
      for (Painting foundPainting : list) {
        if (foundPainting.equals(painting)){
          System.out.println("Such painting already exists!");
          break;
        }
        sessionFactory.getCurrentSession().save(painting);
      }
    }
    else sessionFactory.getCurrentSession().save(painting);
  }
}
