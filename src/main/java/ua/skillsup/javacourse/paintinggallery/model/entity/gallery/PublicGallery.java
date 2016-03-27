package ua.skillsup.javacourse.paintinggallery.model.entity.gallery;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

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

  @Column(name = "homePage")
  private String homePage;

  @OneToOne(mappedBy = "publicGallery", cascade = CascadeType.ALL)
  private Schedule schedule;

  //---------------------------------------------------------------------------------------------//

  public PublicGallery() {}

  public PublicGallery(String owner, Address address, String homePage) {
    this.owner = owner;
    this.address = address;
    this.homePage = homePage;
  }

  public Address getAddress() { return address; }

  public String getHomePage() { return homePage; }

  public Schedule getSchedule() { return schedule; }

  public void setSchedule(Schedule schedule) { this.schedule = schedule; }

  @Override
  public String toString() {
    return "Public gallery {" +
            "id= " + getId() +
            ", owner= " + getOwner() +
            ", address= " + address +
            ", homePage= " + homePage +
            "}";
  }
}
