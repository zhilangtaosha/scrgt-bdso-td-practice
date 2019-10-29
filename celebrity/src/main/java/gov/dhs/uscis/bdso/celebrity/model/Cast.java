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
 * Cast
 */

public class Cast   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("celebrityId")
  private Integer celebrityId;

  @JsonProperty("movieId")
  private Integer movieId;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("nickName")
  private String nickName;

  @JsonProperty("roleName")
  private String roleName;

  public Cast id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Cast celebrityId(Integer celebrityId) {
    this.celebrityId = celebrityId;
    return this;
  }

  /**
   * Get celebrityId
   * @return celebrityId
  */
  @ApiModelProperty(value = "")


  public Integer getCelebrityId() {
    return celebrityId;
  }

  public void setCelebrityId(Integer celebrityId) {
    this.celebrityId = celebrityId;
  }

  public Cast movieId(Integer movieId) {
    this.movieId = movieId;
    return this;
  }

  /**
   * Get movieId
   * @return movieId
  */
  @ApiModelProperty(value = "")


  public Integer getMovieId() {
    return movieId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }

  public Cast firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
  */
  @ApiModelProperty(value = "")


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Cast lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
  */
  @ApiModelProperty(value = "")


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Cast nickName(String nickName) {
    this.nickName = nickName;
    return this;
  }

  /**
   * Get nickName
   * @return nickName
  */
  @ApiModelProperty(value = "")


  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public Cast roleName(String roleName) {
    this.roleName = roleName;
    return this;
  }

  /**
   * Get roleName
   * @return roleName
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cast cast = (Cast) o;
    return Objects.equals(this.id, cast.id) &&
        Objects.equals(this.celebrityId, cast.celebrityId) &&
        Objects.equals(this.movieId, cast.movieId) &&
        Objects.equals(this.firstName, cast.firstName) &&
        Objects.equals(this.lastName, cast.lastName) &&
        Objects.equals(this.nickName, cast.nickName) &&
        Objects.equals(this.roleName, cast.roleName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, celebrityId, movieId, firstName, lastName, nickName, roleName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Cast {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    celebrityId: ").append(toIndentedString(celebrityId)).append("\n");
    sb.append("    movieId: ").append(toIndentedString(movieId)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    nickName: ").append(toIndentedString(nickName)).append("\n");
    sb.append("    roleName: ").append(toIndentedString(roleName)).append("\n");
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

