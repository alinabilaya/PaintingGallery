package ua.skillsup.javacourse.paintinggallery.persistence;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.javacourse.paintinggallery.model.gallery.PaintingGallery;
import ua.skillsup.javacourse.paintinggallery.model.gallery.PaintingGalleryRepo;
import ua.skillsup.javacourse.paintinggallery.model.painting.Painting;

import javax.inject.Inject;
import java.util.List;

import static ua.skillsup.javacourse.paintinggallery.persistence.Util.castList;

/**
 * Created by Shine on 19.03.2016.
 */

@Repository
@Transactional
public class PaintingGalleryRepoImpl implements PaintingGalleryRepo {

  @Inject
  private SessionFactory sessionFactory;

  @Override
  public PaintingGallery findById(long id) {
    return sessionFactory.getCurrentSession()
        .get(PaintingGallery.class, id);
  }

  @Override
  public List<PaintingGallery> findGalleryByOwner(String owner) {
    return castList (sessionFactory.getCurrentSession()
        .createQuery("FROM PaintingGallery p where p.owner =:n")
        .setParameter("n", owner).list());
  }

  @Override
  public PaintingGallery findGalleryForPainting (String paintingTitle){
    Painting painting = (Painting)sessionFactory.getCurrentSession()
        .createQuery("FROM Painting p where p.title =:t")
        .setParameter("t", paintingTitle).uniqueResult();

    return painting.getPaintingGallery();
  }

  @Override
  public void add(PaintingGallery paintingGallery) {
    sessionFactory.getCurrentSession().save(paintingGallery);
  }
}
