package ua.skillsup.javacourse.paintinggallery.model.entity.gallery;

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

  public PrivateGallery() {}

  public PrivateGallery(String owner) {
    this.owner = owner;
  }

  @Override
  public String toString() {
    return "Private gallery {" +
            "id= " + getId() +
            ", owner= " + getOwner() +
            "}";
  }
}
