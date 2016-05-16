package ua.skillsup.javacourse.paintinggallery.model.entity.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static java.util.Collections.unmodifiableSet;

/**
 * Created by Shine on 17.04.2016.
 */

@Data
@EqualsAndHashCode(of = "username")
@ToString(exclude = "password")

@Entity
public class User {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "name")
  private String name;

  @Size(max = 255)
  @Pattern(regexp = "^.+@.+\\..+$" )
  @Column(name = "email")
  private String email;

  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @NotNull
  @Size(min = 5, max = 255)
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "enabled", nullable = false)
  private boolean enabled;

  @Column(name = "admin", nullable = false)
  private boolean admin;

  public Set<Role> getRoles() {
    return admin ? Role.ADMIN : Role.REGULAR_USER;
  }

  public enum Role {
    ROLE_USER, ROLE_ADMIN;

    public static final Set<Role> REGULAR_USER = singleton(ROLE_USER);
    public static final Set<Role> ADMIN = unmodifiableSet(new HashSet<>(asList(ROLE_USER,
            ROLE_ADMIN)));
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() { return email; }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setAdmin(boolean admin) { this.admin = admin; }
}
