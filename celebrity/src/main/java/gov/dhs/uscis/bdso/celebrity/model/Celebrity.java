package gov.dhs.uscis.bdso.celebrity.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import gov.dhs.uscis.bdso.celebrity.model.Filmography;
import gov.dhs.uscis.bdso.celebrity.model.Gender;
import gov.dhs.uscis.bdso.celebrity.model.Genre;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Celebrity
 */

public class Celebrity   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("imdbId")
  private String imdbId;

  @JsonProperty("movdbId")
  private String movdbId;

  @JsonProperty("birthName")
  private String birthName;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("nickName")
  private String nickName;

  @JsonProperty("dob")
  private LocalDate dob;

  @JsonProperty("birthPlace")
  private String birthPlace;

  @JsonProperty("gender")
  private Gender gender;

  @JsonProperty("height")
  private String height;

  @JsonProperty("popularity")
  private Integer popularity;

  @JsonProperty("totalBoxOffice")
  private BigDecimal totalBoxOffice;

  @JsonProperty("averageBoxOffice")
  private BigDecimal averageBoxOffice;

  @JsonProperty("biography")
  private String biography;

  @JsonProperty("imageUrl")
  private String imageUrl;

  @JsonProperty("image")
  private String image;

  @JsonProperty("age")
  private Integer age;

  @JsonProperty("totalSalary")
  private BigDecimal totalSalary;

  @JsonProperty("averageSalary")
  private BigDecimal averageSalary;

  @JsonProperty("genres")
  @Valid
  private List<Genre> genres = null;

  @JsonProperty("filmography")
  private Filmography filmography;

  public Celebrity id(Integer id) {
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

  public Celebrity imdbId(String imdbId) {
    this.imdbId = imdbId;
    return this;
  }

  /**
   * Get imdbId
   * @return imdbId
  */
  @ApiModelProperty(value = "")


  public String getImdbId() {
    return imdbId;
  }

  public void setImdbId(String imdbId) {
    this.imdbId = imdbId;
  }

  public Celebrity movdbId(String movdbId) {
    this.movdbId = movdbId;
    return this;
  }

  /**
   * Get movdbId
   * @return movdbId
  */
  @ApiModelProperty(value = "")


  public String getMovdbId() {
    return movdbId;
  }

  public void setMovdbId(String movdbId) {
    this.movdbId = movdbId;
  }

  public Celebrity birthName(String birthName) {
    this.birthName = birthName;
    return this;
  }

  /**
   * Get birthName
   * @return birthName
  */
  @ApiModelProperty(value = "")


  public String getBirthName() {
    return birthName;
  }

  public void setBirthName(String birthName) {
    this.birthName = birthName;
  }

  public Celebrity firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Celebrity lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Celebrity nickName(String nickName) {
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

  public Celebrity dob(LocalDate dob) {
    this.dob = dob;
    return this;
  }

  /**
   * Get dob
   * @return dob
  */
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }

  public Celebrity birthPlace(String birthPlace) {
    this.birthPlace = birthPlace;
    return this;
  }

  /**
   * Get birthPlace
   * @return birthPlace
  */
  @ApiModelProperty(value = "")


  public String getBirthPlace() {
    return birthPlace;
  }

  public void setBirthPlace(String birthPlace) {
    this.birthPlace = birthPlace;
  }

  public Celebrity gender(Gender gender) {
    this.gender = gender;
    return this;
  }

  /**
   * Get gender
   * @return gender
  */
  @ApiModelProperty(value = "")

  @Valid

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public Celebrity height(String height) {
    this.height = height;
    return this;
  }

  /**
   * Get height
   * @return height
  */
  @ApiModelProperty(value = "")


  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  public Celebrity popularity(Integer popularity) {
    this.popularity = popularity;
    return this;
  }

  /**
   * Get popularity
   * @return popularity
  */
  @ApiModelProperty(value = "")


  public Integer getPopularity() {
    return popularity;
  }

  public void setPopularity(Integer popularity) {
    this.popularity = popularity;
  }

  public Celebrity totalBoxOffice(BigDecimal totalBoxOffice) {
    this.totalBoxOffice = totalBoxOffice;
    return this;
  }

  /**
   * Get totalBoxOffice
   * @return totalBoxOffice
  */
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getTotalBoxOffice() {
    return totalBoxOffice;
  }

  public void setTotalBoxOffice(BigDecimal totalBoxOffice) {
    this.totalBoxOffice = totalBoxOffice;
  }

  public Celebrity averageBoxOffice(BigDecimal averageBoxOffice) {
    this.averageBoxOffice = averageBoxOffice;
    return this;
  }

  /**
   * Get averageBoxOffice
   * @return averageBoxOffice
  */
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getAverageBoxOffice() {
    return averageBoxOffice;
  }

  public void setAverageBoxOffice(BigDecimal averageBoxOffice) {
    this.averageBoxOffice = averageBoxOffice;
  }

  public Celebrity biography(String biography) {
    this.biography = biography;
    return this;
  }

  /**
   * Get biography
   * @return biography
  */
  @ApiModelProperty(value = "")


  public String getBiography() {
    return biography;
  }

  public void setBiography(String biography) {
    this.biography = biography;
  }

  public Celebrity imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  /**
   * Get imageUrl
   * @return imageUrl
  */
  @ApiModelProperty(value = "")


  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Celebrity image(String image) {
    this.image = image;
    return this;
  }

  /**
   * Get image
   * @return image
  */
  @ApiModelProperty(value = "")


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Celebrity age(Integer age) {
    this.age = age;
    return this;
  }

  /**
   * Get age
   * @return age
  */
  @ApiModelProperty(value = "")


  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Celebrity totalSalary(BigDecimal totalSalary) {
    this.totalSalary = totalSalary;
    return this;
  }

  /**
   * Get totalSalary
   * @return totalSalary
  */
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getTotalSalary() {
    return totalSalary;
  }

  public void setTotalSalary(BigDecimal totalSalary) {
    this.totalSalary = totalSalary;
  }

  public Celebrity averageSalary(BigDecimal averageSalary) {
    this.averageSalary = averageSalary;
    return this;
  }

  /**
   * Get averageSalary
   * @return averageSalary
  */
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getAverageSalary() {
    return averageSalary;
  }

  public void setAverageSalary(BigDecimal averageSalary) {
    this.averageSalary = averageSalary;
  }

  public Celebrity genres(List<Genre> genres) {
    this.genres = genres;
    return this;
  }

  public Celebrity addGenresItem(Genre genresItem) {
    if (this.genres == null) {
      this.genres = new ArrayList<>();
    }
    this.genres.add(genresItem);
    return this;
  }

  /**
   * Get genres
   * @return genres
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Genre> getGenres() {
    return genres;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }

  public Celebrity filmography(Filmography filmography) {
    this.filmography = filmography;
    return this;
  }

  /**
   * Get filmography
   * @return filmography
  */
  @ApiModelProperty(value = "")

  @Valid

  public Filmography getFilmography() {
    return filmography;
  }

  public void setFilmography(Filmography filmography) {
    this.filmography = filmography;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Celebrity celebrity = (Celebrity) o;
    return Objects.equals(this.id, celebrity.id) &&
        Objects.equals(this.imdbId, celebrity.imdbId) &&
        Objects.equals(this.movdbId, celebrity.movdbId) &&
        Objects.equals(this.birthName, celebrity.birthName) &&
        Objects.equals(this.firstName, celebrity.firstName) &&
        Objects.equals(this.lastName, celebrity.lastName) &&
        Objects.equals(this.nickName, celebrity.nickName) &&
        Objects.equals(this.dob, celebrity.dob) &&
        Objects.equals(this.birthPlace, celebrity.birthPlace) &&
        Objects.equals(this.gender, celebrity.gender) &&
        Objects.equals(this.height, celebrity.height) &&
        Objects.equals(this.popularity, celebrity.popularity) &&
        Objects.equals(this.totalBoxOffice, celebrity.totalBoxOffice) &&
        Objects.equals(this.averageBoxOffice, celebrity.averageBoxOffice) &&
        Objects.equals(this.biography, celebrity.biography) &&
        Objects.equals(this.imageUrl, celebrity.imageUrl) &&
        Objects.equals(this.image, celebrity.image) &&
        Objects.equals(this.age, celebrity.age) &&
        Objects.equals(this.totalSalary, celebrity.totalSalary) &&
        Objects.equals(this.averageSalary, celebrity.averageSalary) &&
        Objects.equals(this.genres, celebrity.genres) &&
        Objects.equals(this.filmography, celebrity.filmography);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, imdbId, movdbId, birthName, firstName, lastName, nickName, dob, birthPlace, gender, height, popularity, totalBoxOffice, averageBoxOffice, biography, imageUrl, image, age, totalSalary, averageSalary, genres, filmography);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Celebrity {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    imdbId: ").append(toIndentedString(imdbId)).append("\n");
    sb.append("    movdbId: ").append(toIndentedString(movdbId)).append("\n");
    sb.append("    birthName: ").append(toIndentedString(birthName)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    nickName: ").append(toIndentedString(nickName)).append("\n");
    sb.append("    dob: ").append(toIndentedString(dob)).append("\n");
    sb.append("    birthPlace: ").append(toIndentedString(birthPlace)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    popularity: ").append(toIndentedString(popularity)).append("\n");
    sb.append("    totalBoxOffice: ").append(toIndentedString(totalBoxOffice)).append("\n");
    sb.append("    averageBoxOffice: ").append(toIndentedString(averageBoxOffice)).append("\n");
    sb.append("    biography: ").append(toIndentedString(biography)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
    sb.append("    totalSalary: ").append(toIndentedString(totalSalary)).append("\n");
    sb.append("    averageSalary: ").append(toIndentedString(averageSalary)).append("\n");
    sb.append("    genres: ").append(toIndentedString(genres)).append("\n");
    sb.append("    filmography: ").append(toIndentedString(filmography)).append("\n");
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

