package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


//@JsonIgnoreProperties({"field1","field2"})
@JsonFilter("SomeBeanFilter")
@AllArgsConstructor
@Getter
@ToString
public class SomeBean {
  private String field1;
  private String field2;
  //@JsonIgnore
  private String field3;
}
