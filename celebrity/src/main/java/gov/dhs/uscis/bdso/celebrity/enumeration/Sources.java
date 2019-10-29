package gov.dhs.uscis.bdso.celebrity.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Sources {
    IMDB("imdb"),
    THE_MOVIE_DB("themoviedb"),
    WIKIPEDIA("wikipedia");

    private String value;

    Sources(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static Sources fromValue(String value) {
        for (Sources b : Sources.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
