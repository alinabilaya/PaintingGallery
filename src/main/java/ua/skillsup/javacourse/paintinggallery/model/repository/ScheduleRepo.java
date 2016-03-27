package ua.skillsup.javacourse.paintinggallery.model.repository;

import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PublicGallery;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.Schedule;

import java.util.List;

/**
 * Created by Shine on 27.03.2016.
 */
public interface ScheduleRepo {

  Schedule getPublicGallerySchedule (PublicGallery publicGallery);
  void addSchedule(Schedule schedule);
  void updateSchedule(Schedule schedule);
  List<Schedule> getScheduleByGallery(String publicGalleryOwner);
}
