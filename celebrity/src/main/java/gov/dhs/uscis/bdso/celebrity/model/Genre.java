package gov.dhs.uscis.bdso.celebrity.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets Genre
 */
public enum Genre {
  
  ACTION("Action"),
  
  ADVENTURE("Adventure"),
  
  ANIMATION("Animation"),
  
  BIOGRAPHY("Biography"),
  
  COMEDY("Comedy"),
  
  CRIME("Crime"),
  
  DOCUMENTARY("Documentary"),
  
  DRAMA("Drama"),
  
  FAMILY("Family"),
  
  FANTASY("Fantasy"),
  
  FILM_NOIR("Film Noir"),
  
  HISTORY("History"),
  
  HORROR("Horror"),
  
  MUSIC("Music"),
  
  MUSICAL("Musical"),
  
  MYSTERY("Mystery"),
  
  ROMANCE("Romance"),
  
  SCIENCE_FICTION("Science Fiction"),
  
  SHORT_FILM("Short Film"),
  
  SPORT("Sport"),
  
  SUPERHERO("Superhero"),
  
  TV_MOVIE("TV Movie"),
  
  THRILLER("Thriller"),
  
  WAR("War"),
  
  WESTERN("Western");

  private String value;

  Genre(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Genre fromValue(String value) {
    for (Genre b : Genre.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

