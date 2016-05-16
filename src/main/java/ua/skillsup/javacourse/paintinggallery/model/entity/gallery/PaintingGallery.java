package ua.skillsup.javacourse.paintinggallery.model.entity.gallery;

import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Shine on 12.03.2016.
 */

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class PaintingGallery {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @Column(name = "owner")
  protected String owner;

  @Column(name = "type", insertable = false, updatable = false)
  protected String type;

  //--------------------------------------------------------------------------------------------------------//

  public PaintingGallery() {
  }

  public long getId() {
    return id;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getType() {
    return type;
  }
}
