package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

  @GetMapping("/filtering")
  public MappingJacksonValue filtering() {
    return getMappingJacksonValue(new SomeBean("value1", "value2", "value3"), "field1", "field2");
  }

  @GetMapping("/filtering-list")
  public MappingJacksonValue filteringList() {
    List<SomeBean> list = List.of(new SomeBean("value1", "value2", "value3"),
        new SomeBean("value4", "value5", "value6"));

    return getMappingJacksonValue(list, "field2", "field3");
  }
  private MappingJacksonValue getMappingJacksonValue(Object value, String... fields) {
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(value);
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
    FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
    mappingJacksonValue.setFilters(filters);
    return mappingJacksonValue;
  }
}
