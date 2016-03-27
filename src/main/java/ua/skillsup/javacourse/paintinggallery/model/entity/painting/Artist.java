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
@ToString(exclude = "paintings")

@Entity
@Table(name = "Artist")
public class Artist {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Basic(fetch = FetchType.EAGER, optional = false)
  @Column(name = "name", nullable = false)
  private String name;

  @Basic(fetch = FetchType.EAGER, optional = false)
  @Column(name = "country", nullable = false)
  private String country;

  @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Painting> paintings;

  @Version
  private Integer version;

  //-------------------------------------------------------------------------------------------//

  public Artist () {}

  public Artist(String name, String country) {
    this.name = name;
    this.country = country;
  }

  public Long getId() { return id; }

  public String getCountry() { return country; }

  public String getName() { return name; }

  public List<Painting> getPaintings() { return paintings; }

  public void setPaintings(List<Painting> paintings) { this.paintings = paintings; }

  public Integer getVersion() { return version; }

  public void setVersion(Integer version) { this.version = version; }

//  @Override
//  public String toString() {
//    return "Artist{" +
//        "id=" + id +
//        ", name=" + name +
//        ", country=" + country +
//        "}";
//  }
}
