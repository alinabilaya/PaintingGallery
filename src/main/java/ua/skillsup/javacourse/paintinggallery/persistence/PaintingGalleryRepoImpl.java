package ua.skillsup.javacourse.paintinggallery.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PaintingGallery;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PublicGallery;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;
import ua.skillsup.javacourse.paintinggallery.model.repository.PaintingGalleryRepo;

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
  public PaintingGallery getById(long id) {
    return sessionFactory.getCurrentSession()
        .get(PaintingGallery.class, id);
  }

  @Override
  public List<PaintingGallery> getGalleryByOwner(String owner) {
    return castList (sessionFactory.getCurrentSession()
        .createQuery("FROM PaintingGallery p where p.owner =:n")
        .setParameter("n", owner).list());
  }

  public List<PublicGallery> getPublicGalleryByOwner(String owner) {
    return castList (sessionFactory.getCurrentSession()
            .createCriteria(PublicGallery.class)
            .add(Restrictions.ilike("owner", "%" + owner + "%"))
            .list());
  }

  @Override
  public PaintingGallery getGalleryByPainting(String paintingTitle){
    Painting painting = (Painting)sessionFactory.getCurrentSession()
        .createQuery("FROM Painting p where p.title =:t")
        .setParameter("t", paintingTitle).uniqueResult();

    return painting.getPaintingGallery();
  }

  @Override
  public void addPaintingGallery(PaintingGallery paintingGallery) {
    List<PaintingGallery> list = getGalleryByOwner(paintingGallery.getOwner());

    if (list.size() != 0){
      for (PaintingGallery foundGallery : list) {
        if (foundGallery.equals(paintingGallery)){
          System.out.println("Such gallery already exists!");
          break;
        }
        sessionFactory.getCurrentSession().save(paintingGallery);
      }
    }
    else sessionFactory.getCurrentSession().save(paintingGallery);
  }

  @Override
  public List<PublicGallery> getAllPublicGalleries() {
    return castList (sessionFactory.getCurrentSession()
            .createQuery("FROM PaintingGallery g where g.type != 'PrivateGallery'")
            .list());
  }
}
