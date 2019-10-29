package gov.dhs.uscis.bdso.celebrity.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import gov.dhs.uscis.bdso.celebrity.model.Movie;
import gov.dhs.uscis.bdso.celebrity.model.MovieCount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Filmography
 */

public class Filmography   {
  @JsonProperty("ranking")
  private Integer ranking;

  @JsonProperty("numberOfMovies")
  private Integer numberOfMovies;

  @JsonProperty("movies")
  @Valid
  private List<Movie> movies = null;

  @JsonProperty("listOfMoviesByYear")
  @Valid
  private List<MovieCount> listOfMoviesByYear = null;

  public Filmography ranking(Integer ranking) {
    this.ranking = ranking;
    return this;
  }

  /**
   * Get ranking
   * @return ranking
  */
  @ApiModelProperty(value = "")


  public Integer getRanking() {
    return ranking;
  }

  public void setRanking(Integer ranking) {
    this.ranking = ranking;
  }

  public Filmography numberOfMovies(Integer numberOfMovies) {
    this.numberOfMovies = numberOfMovies;
    return this;
  }

  /**
   * Get numberOfMovies
   * @return numberOfMovies
  */
  @ApiModelProperty(value = "")


  public Integer getNumberOfMovies() {
    return numberOfMovies;
  }

  public void setNumberOfMovies(Integer numberOfMovies) {
    this.numberOfMovies = numberOfMovies;
  }

  public Filmography movies(List<Movie> movies) {
    this.movies = movies;
    return this;
  }

  public Filmography addMoviesItem(Movie moviesItem) {
    if (this.movies == null) {
      this.movies = new ArrayList<>();
    }
    this.movies.add(moviesItem);
    return this;
  }

  /**
   * Get movies
   * @return movies
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Movie> getMovies() {
    return movies;
  }

  public void setMovies(List<Movie> movies) {
    this.movies = movies;
  }

  public Filmography listOfMoviesByYear(List<MovieCount> listOfMoviesByYear) {
    this.listOfMoviesByYear = listOfMoviesByYear;
    return this;
  }

  public Filmography addListOfMoviesByYearItem(MovieCount listOfMoviesByYearItem) {
    if (this.listOfMoviesByYear == null) {
      this.listOfMoviesByYear = new ArrayList<>();
    }
    this.listOfMoviesByYear.add(listOfMoviesByYearItem);
    return this;
  }

  /**
   * Get listOfMoviesByYear
   * @return listOfMoviesByYear
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<MovieCount> getListOfMoviesByYear() {
    return listOfMoviesByYear;
  }

  public void setListOfMoviesByYear(List<MovieCount> listOfMoviesByYear) {
    this.listOfMoviesByYear = listOfMoviesByYear;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Filmography filmography = (Filmography) o;
    return Objects.equals(this.ranking, filmography.ranking) &&
        Objects.equals(this.numberOfMovies, filmography.numberOfMovies) &&
        Objects.equals(this.movies, filmography.movies) &&
        Objects.equals(this.listOfMoviesByYear, filmography.listOfMoviesByYear);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ranking, numberOfMovies, movies, listOfMoviesByYear);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Filmography {\n");
    
    sb.append("    ranking: ").append(toIndentedString(ranking)).append("\n");
    sb.append("    numberOfMovies: ").append(toIndentedString(numberOfMovies)).append("\n");
    sb.append("    movies: ").append(toIndentedString(movies)).append("\n");
    sb.append("    listOfMoviesByYear: ").append(toIndentedString(listOfMoviesByYear)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

