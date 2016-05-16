package ua.skillsup.javacourse.paintinggallery.model.entity.painting;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Shine on 12.03.2016.
 */

@Data
@EqualsAndHashCode(of ={"name","country"})
@ToString(exclude = {"paintings", "image", "info"})

@Entity
@Table(name = "Artist")
public class Artist {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "image")
  private String image;

  @Column(name = "info", length = 20000)
  private String info;

  @Column(name = "country", nullable = false)
  private String country;

  @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Painting> paintings;

  //-------------------------------------------------------------------------------------------//

  public Artist () {}

  public Artist(String name, String country) {
    this.name = name;
    this.country = country;
  }

  public Long getId() { return id; }

  public String getCountry() { return country; }

  public void setCountry(String country) {this.country = country;}

  public String getName() { return name; }

  public void setName(String name) {this.name = name;}

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public List<Painting> getPaintings() { return paintings; }

}
