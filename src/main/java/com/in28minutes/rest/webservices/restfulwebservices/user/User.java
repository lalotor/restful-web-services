package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

//public record User(Integer id, String name, LocalDate birthDate) {}

@Data
@AllArgsConstructor
public class User {
  private Integer id;
  @Size(min=2, message = "Name should have at least 2 characters")
  @JsonProperty("user_name")
  private String name;
  @Past(message = "Birth date should be in the past")
  @JsonProperty("birth_name")
  private LocalDate birthDate;
}
