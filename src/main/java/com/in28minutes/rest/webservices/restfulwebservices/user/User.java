package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//public record User(Integer id, String name, LocalDate birthDate) {}

@Entity(name = "user_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue
  private Integer id;
  @Size(min=2, message = "Name should have at least 2 characters")
//  @JsonProperty("user_name")
  private String name;
  @Past(message = "Birth date should be in the past")
//  @JsonProperty("birth_name")
  private LocalDate birthDate;
  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private List<Post> posts;

  public User(Integer id, String name, LocalDate birthDate) {
    this.id = id;
    this.name = name;
    this.birthDate = birthDate;
  }
}
