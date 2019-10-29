package gov.dhs.uscis.bdso.celebrity.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * MovieCount
 */

public class MovieCount   {
  @JsonProperty("year")
  private Integer year;

  @JsonProperty("movieCount")
  private Integer movieCount;

  public MovieCount year(Integer year) {
    this.year = year;
    return this;
  }

  /**
   * Get year
   * @return year
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public MovieCount movieCount(Integer movieCount) {
    this.movieCount = movieCount;
    return this;
  }

  /**
   * Get movieCount
   * @return movieCount
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getMovieCount() {
    return movieCount;
  }

  public void setMovieCount(Integer movieCount) {
    this.movieCount = movieCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MovieCount movieCount = (MovieCount) o;
    return Objects.equals(this.year, movieCount.year) &&
        Objects.equals(this.movieCount, movieCount.movieCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(year, movieCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MovieCount {\n");
    
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    movieCount: ").append(toIndentedString(movieCount)).append("\n");
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

