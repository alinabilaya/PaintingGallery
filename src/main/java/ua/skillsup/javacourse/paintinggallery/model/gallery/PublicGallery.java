package ua.skillsup.javacourse.paintinggallery.model.gallery;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * Created by Shine on 12.03.2016.
 */

@Data
@EqualsAndHashCode(of = {"owner", "type"})
@ToString(exclude = "paintings")

@Entity
public class PublicGallery extends PaintingGallery {

  @Embedded
  private Address address;

  //---------------------------------------------------------------------------------------------//

  public Address getAddress() { return address; }

  public void setAddress(Address address) { this.address = address; }

  @Override
  public String toString() {
    return "Public gallery {" +
            "id= " + getId() +
            ", owner= " + getOwner() +
            ", address= " + address +
            "}";
  }
}
