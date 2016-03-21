package ua.skillsup.javacourse.paintinggallery.model.gallery;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * Created by Shine on 12.03.2016.
 */
@Data
@EqualsAndHashCode(of = {"owner", "type"})

@Entity
public class PrivateGallery extends PaintingGallery {

  @Override
  public String toString() {
    return "Private gallery {" +
            "id= " + getId() +
            ", owner= " + getOwner() +
            "}";
  }
}
