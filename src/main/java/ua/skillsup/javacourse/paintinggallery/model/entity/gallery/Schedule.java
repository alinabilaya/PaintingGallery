package ua.skillsup.javacourse.paintinggallery.model.entity.gallery;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Shine on 25.03.2016.
 */

@Data
@EqualsAndHashCode(of = {"mondayTime", "tuesdayTime", "wednesdayTime", "thursdayTime", "fridayTime",
"saturdayTime", "sundayTime"})
@ToString(exclude = {"publicGallery", "id"})

@Entity
@Table(name = "Schedule")
public class Schedule {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column (name = "mondayTime")
  private String mondayTime;

  @Column (name = "tuesdayTime")
  private String tuesdayTime;

  @Column (name = "wednesdayTime")
  private String wednesdayTime;

  @Column (name = "thursdayTime")
  private String thursdayTime;

  @Column (name = "fridayTime")
  private String fridayTime;

  @Column (name = "saturdayTime")
  private String saturdayTime;

  @Column (name = "sundayTime")
  private String sundayTime;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "gallery_id", nullable = false)
  private PublicGallery publicGallery;

  //---------------------------------------------------------------------------------------------//

  public Schedule() {}

  public Schedule(String mondayTime, String tuesdayTime, String wednesdayTime,
                   String thursdayTime, String fridayTime, String saturdayTime, String sundayTime) {
    this.mondayTime = mondayTime;
    this.tuesdayTime = tuesdayTime;
    this.wednesdayTime = wednesdayTime;
    this.thursdayTime = thursdayTime;
    this.fridayTime = fridayTime;
    this.saturdayTime = saturdayTime;
    this.sundayTime = sundayTime;
  }

  public String getMondayTime() {  return mondayTime; }

  public String getTuesdayTime() { return tuesdayTime; }

  public String getWednesdayTime() { return wednesdayTime; }

  public String getThursdayTime() { return thursdayTime; }

  public String getFridayTime() { return fridayTime; }

  public String getSaturdayTime() { return saturdayTime; }

  public String getSundayTime() { return sundayTime; }

  public Long getId() { return id; }

  public PublicGallery getPublicGallery() {return publicGallery;}

  public void setPublicGallery(PublicGallery publicGallery) { this.publicGallery = publicGallery; }
}
