package ua.skillsup.javacourse.paintinggallery.model.entity.painting;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ua.skillsup.javacourse.paintinggallery.model.entity.gallery.PaintingGallery;

import javax.persistence.*;

/**
 * Created by Shine on 12.03.2016.
 */

@Data
@EqualsAndHashCode(of ={"title", "artist"})
@ToString(exclude = {"artist", "image", "summary"})

@Entity
@Table(name = "Painting")
public class Painting {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "summary", length = 20000)
  private String summary;

  @Column(name = "dateMade")
  private String dateMade;

  @Column(name = "image")
  private String image;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "artist_id")
  private Artist artist;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "gallery_id")
  private PaintingGallery paintingGallery;

  //------------------------------------------------------------------------------------------------//

  public Painting() {}

  public Painting(String paintingTitle, String paintingDateMade, String paintingSummary) {
    this.title = paintingTitle;
    this.dateMade = paintingDateMade;
    this.summary = paintingSummary;
  }

  public Long getId() { return id;  }

  public Artist getArtist() { return artist; }

  public void setArtist(Artist artist) { this.artist = artist; }

  public String getTitle() { return title; }

  public void setTitle(String title) {this.title = title;}

  public String getDateMade() { return dateMade; }

  public void setDateMade(String dateMade) { this.dateMade = dateMade; }

  public String getSummary() { return summary; }

  public void setSummary(String summary) { this.summary = summary; }

  public String getImage() { return image; }

  public void setImage(String image) { this.image = image; }

  public PaintingGallery getPaintingGallery() { return paintingGallery; }

  public void setPaintingGallery(PaintingGallery paintingGallery) { this.paintingGallery = paintingGallery; }

}
