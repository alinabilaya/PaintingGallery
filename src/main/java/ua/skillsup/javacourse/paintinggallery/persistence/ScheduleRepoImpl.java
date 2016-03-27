package ua.skillsup.javacourse.paintinggallery.persistence;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PublicGallery;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.Schedule;
import ua.skillsup.javacourse.paintinggallery.model.repository.ScheduleRepo;

import javax.inject.Inject;
import java.util.List;

import static ua.skillsup.javacourse.paintinggallery.persistence.Util.castList;

/**
 * Created by Shine on 27.03.2016.
 */

@Repository
@Transactional
public class ScheduleRepoImpl implements ScheduleRepo {

  @Inject
  private SessionFactory sessionFactory;

  @Override
  public Schedule getPublicGallerySchedule(PublicGallery publicGallery) {
    return (Schedule)sessionFactory.getCurrentSession().createQuery("FROM Schedule sc WHERE sc.publicGallery =:g")
            .setParameter("g", publicGallery).uniqueResult();
  }

  @Override
  public void addSchedule(Schedule schedule) {
    List<Schedule> list = getScheduleByGallery(schedule.getPublicGallery().getOwner());

    if (list.size() != 0){
          System.out.println("Such gallery has already schedule");
        }
    else  sessionFactory.getCurrentSession().save(schedule);
    }

  @Override
  public void updateSchedule(Schedule schedule) { sessionFactory.getCurrentSession().saveOrUpdate(schedule); }

  @Override
  public List<Schedule> getScheduleByGallery(String publicGalleryOwner) {
    return castList(sessionFactory.getCurrentSession().createQuery("FROM Schedule s WHERE s.publicGallery.owner =:owner")
    .setParameter("owner", publicGalleryOwner).list());
  }
}
