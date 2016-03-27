package ua.skillsup.javacourse.paintinggallery.model.entity.gallery;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 * Created by Shine on 12.03.2016.
 */
@Data

@Embeddable
public class Address {

  private String country;

  private String city;

  private String street;

  //-----------------------------------------------------------------------------------------//

  public Address() {}

  public Address(String country, String city, String street) {
    this.country = country;
    this.city = city;
    this.street = street;
  }

  public String getCountry() { return country; }

  public String getCity() { return city; }

  public String getStreet() { return street; }

  @Override
  public String toString() {
    return " " + country +
            ", " + city +
            ", " + street +
            " ";
  }
}

