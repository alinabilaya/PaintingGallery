package ua.skillsup.javacourse.paintinggallery.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Artist;
import ua.skillsup.javacourse.paintinggallery.model.repository.ArtistRepo;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;

import javax.inject.Inject;
import java.util.List;

import static ua.skillsup.javacourse.paintinggallery.persistence.Util.castList;

/**
 * Created by Shine on 18.03.16.
 */

@Repository
public class ArtistRepoImpl implements ArtistRepo{

  @Inject
  private SessionFactory sessionFactory;

  @Override
  public List<Artist> getByName(String artistName) {
    return castList (sessionFactory.getCurrentSession()
        .createCriteria(Artist.class)
        .add(Restrictions.ilike("name", "%" + artistName + "%"))
        .list());
  }

  @Override
  public Artist getById(Long id) {
    return sessionFactory.getCurrentSession()
        .get(Artist.class, id);
  }

  @Override
  public List<Painting> getAllArtistPaintings(String artistName) {
    return  castList (sessionFactory.getCurrentSession()
        .createQuery("FROM Painting p where p.artist.name =:name")
        .setParameter("name", artistName).list());
  }

  @Override
  public void add (Artist artist) {
    List<Artist> list = getByName(artist.getName());

    if(list.size() != 0) {
      for(Artist foundArtist: list)  {
        if(foundArtist.equals(artist))   {
          System.out.println("Such artist already exists!");
          break;
        }
        sessionFactory.getCurrentSession().save(artist);
      }
    }
    else sessionFactory.getCurrentSession().save(artist);
  }
}
