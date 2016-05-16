package ua.skillsup.javacourse.paintinggallery.model.entity.gallery;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ua.skillsup.javacourse.paintinggallery.model.entity.painting.Painting;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Shine on 12.03.2016.
 */

@Data
@EqualsAndHashCode(of = {"owner", "address"})
@ToString(exclude = {"paintings", "image", "description"})

@Entity
public class PublicGallery extends PaintingGallery {

  @Embedded
  private Address address;

  @Column(name = "homePage")
  private String homePage;

  @Column(name = "image")
  private String image;

  @Column(name = "description", length = 20000)
  private String description;

  @OneToOne(mappedBy = "publicGallery", cascade = CascadeType.ALL)
  private Schedule schedule;

  @OneToMany(mappedBy = "paintingGallery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  protected Set<Painting> paintings;

  //---------------------------------------------------------------------------------------------//

  public PublicGallery() {
  }

  public PublicGallery(String owner, Address address, String homePage) {
    this.owner = owner;
    this.address = address;
    this.homePage = homePage;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getHomePage() {
    return homePage;
  }

  public void setHomePage(String homePage) {
    this.homePage = homePage;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Schedule getSchedule() {
    return schedule;
  }

  public void setSchedule(Schedule schedule) {
    this.schedule = schedule;
  }

  public Set<Painting> getPaintings() {
    return paintings;
  }
}
