package gov.dhs.uscis.bdso.celebrity.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import gov.dhs.uscis.bdso.celebrity.model.Cast;
import gov.dhs.uscis.bdso.celebrity.model.Company;
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
 * Movie
 */

public class Movie   {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("title")
  private String title;

  @JsonProperty("imdbId")
  private String imdbId;

  @JsonProperty("movdbId")
  private String movdbId;

  @JsonProperty("budget")
  private BigDecimal budget;

  @JsonProperty("homePage")
  private String homePage;

  @JsonProperty("language")
  private String language;

  @JsonProperty("overview")
  private String overview;

  @JsonProperty("popularity")
  private Integer popularity;

  @JsonProperty("releaseDate")
  private LocalDate releaseDate;

  @JsonProperty("revenue")
  private BigDecimal revenue;

  @JsonProperty("runtime")
  private Integer runtime;

  @JsonProperty("originalLanguage")
  private String originalLanguage;

  @JsonProperty("originalTitle")
  private String originalTitle;

  @JsonProperty("spokenLanguages")
  private String spokenLanguages;

  @JsonProperty("status")
  private String status;

  @JsonProperty("tagline")
  private String tagline;

  @JsonProperty("ranking")
  private Integer ranking;

  @JsonProperty("genres")
  @Valid
  private List<Genre> genres = null;

  @JsonProperty("cast")
  @Valid
  private List<Cast> cast = null;

  @JsonProperty("companies")
  @Valid
  private List<Company> companies = null;

  public Movie id(Integer id) {
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

  public Movie title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Movie imdbId(String imdbId) {
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

  public Movie movdbId(String movdbId) {
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

  public Movie budget(BigDecimal budget) {
    this.budget = budget;
    return this;
  }

  /**
   * Get budget
   * @return budget
  */
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getBudget() {
    return budget;
  }

  public void setBudget(BigDecimal budget) {
    this.budget = budget;
  }

  public Movie homePage(String homePage) {
    this.homePage = homePage;
    return this;
  }

  /**
   * Get homePage
   * @return homePage
  */
  @ApiModelProperty(value = "")


  public String getHomePage() {
    return homePage;
  }

  public void setHomePage(String homePage) {
    this.homePage = homePage;
  }

  public Movie language(String language) {
    this.language = language;
    return this;
  }

  /**
   * Get language
   * @return language
  */
  @ApiModelProperty(value = "")


  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Movie overview(String overview) {
    this.overview = overview;
    return this;
  }

  /**
   * Get overview
   * @return overview
  */
  @ApiModelProperty(value = "")


  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public Movie popularity(Integer popularity) {
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

  public Movie releaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
    return this;
  }

  /**
   * Get releaseDate
   * @return releaseDate
  */
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public Movie revenue(BigDecimal revenue) {
    this.revenue = revenue;
    return this;
  }

  /**
   * Get revenue
   * @return revenue
  */
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getRevenue() {
    return revenue;
  }

  public void setRevenue(BigDecimal revenue) {
    this.revenue = revenue;
  }

  public Movie runtime(Integer runtime) {
    this.runtime = runtime;
    return this;
  }

  /**
   * Get runtime
   * @return runtime
  */
  @ApiModelProperty(value = "")


  public Integer getRuntime() {
    return runtime;
  }

  public void setRuntime(Integer runtime) {
    this.runtime = runtime;
  }

  public Movie originalLanguage(String originalLanguage) {
    this.originalLanguage = originalLanguage;
    return this;
  }

  /**
   * Get originalLanguage
   * @return originalLanguage
  */
  @ApiModelProperty(value = "")


  public String getOriginalLanguage() {
    return originalLanguage;
  }

  public void setOriginalLanguage(String originalLanguage) {
    this.originalLanguage = originalLanguage;
  }

  public Movie originalTitle(String originalTitle) {
    this.originalTitle = originalTitle;
    return this;
  }

  /**
   * Get originalTitle
   * @return originalTitle
  */
  @ApiModelProperty(value = "")


  public String getOriginalTitle() {
    return originalTitle;
  }

  public void setOriginalTitle(String originalTitle) {
    this.originalTitle = originalTitle;
  }

  public Movie spokenLanguages(String spokenLanguages) {
    this.spokenLanguages = spokenLanguages;
    return this;
  }

  /**
   * Get spokenLanguages
   * @return spokenLanguages
  */
  @ApiModelProperty(value = "")


  public String getSpokenLanguages() {
    return spokenLanguages;
  }

  public void setSpokenLanguages(String spokenLanguages) {
    this.spokenLanguages = spokenLanguages;
  }

  public Movie status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @ApiModelProperty(value = "")


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Movie tagline(String tagline) {
    this.tagline = tagline;
    return this;
  }

  /**
   * Get tagline
   * @return tagline
  */
  @ApiModelProperty(value = "")


  public String getTagline() {
    return tagline;
  }

  public void setTagline(String tagline) {
    this.tagline = tagline;
  }

  public Movie ranking(Integer ranking) {
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

  public Movie genres(List<Genre> genres) {
    this.genres = genres;
    return this;
  }

  public Movie addGenresItem(Genre genresItem) {
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

  public Movie cast(List<Cast> cast) {
    this.cast = cast;
    return this;
  }

  public Movie addCastItem(Cast castItem) {
    if (this.cast == null) {
      this.cast = new ArrayList<>();
    }
    this.cast.add(castItem);
    return this;
  }

  /**
   * Get cast
   * @return cast
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Cast> getCast() {
    return cast;
  }

  public void setCast(List<Cast> cast) {
    this.cast = cast;
  }

  public Movie companies(List<Company> companies) {
    this.companies = companies;
    return this;
  }

  public Movie addCompaniesItem(Company companiesItem) {
    if (this.companies == null) {
      this.companies = new ArrayList<>();
    }
    this.companies.add(companiesItem);
    return this;
  }

  /**
   * Get companies
   * @return companies
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Company> getCompanies() {
    return companies;
  }

  public void setCompanies(List<Company> companies) {
    this.companies = companies;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Movie movie = (Movie) o;
    return Objects.equals(this.id, movie.id) &&
        Objects.equals(this.title, movie.title) &&
        Objects.equals(this.imdbId, movie.imdbId) &&
        Objects.equals(this.movdbId, movie.movdbId) &&
        Objects.equals(this.budget, movie.budget) &&
        Objects.equals(this.homePage, movie.homePage) &&
        Objects.equals(this.language, movie.language) &&
        Objects.equals(this.overview, movie.overview) &&
        Objects.equals(this.popularity, movie.popularity) &&
        Objects.equals(this.releaseDate, movie.releaseDate) &&
        Objects.equals(this.revenue, movie.revenue) &&
        Objects.equals(this.runtime, movie.runtime) &&
        Objects.equals(this.originalLanguage, movie.originalLanguage) &&
        Objects.equals(this.originalTitle, movie.originalTitle) &&
        Objects.equals(this.spokenLanguages, movie.spokenLanguages) &&
        Objects.equals(this.status, movie.status) &&
        Objects.equals(this.tagline, movie.tagline) &&
        Objects.equals(this.ranking, movie.ranking) &&
        Objects.equals(this.genres, movie.genres) &&
        Objects.equals(this.cast, movie.cast) &&
        Objects.equals(this.companies, movie.companies);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, imdbId, movdbId, budget, homePage, language, overview, popularity, releaseDate, revenue, runtime, originalLanguage, originalTitle, spokenLanguages, status, tagline, ranking, genres, cast, companies);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Movie {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    imdbId: ").append(toIndentedString(imdbId)).append("\n");
    sb.append("    movdbId: ").append(toIndentedString(movdbId)).append("\n");
    sb.append("    budget: ").append(toIndentedString(budget)).append("\n");
    sb.append("    homePage: ").append(toIndentedString(homePage)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("    overview: ").append(toIndentedString(overview)).append("\n");
    sb.append("    popularity: ").append(toIndentedString(popularity)).append("\n");
    sb.append("    releaseDate: ").append(toIndentedString(releaseDate)).append("\n");
    sb.append("    revenue: ").append(toIndentedString(revenue)).append("\n");
    sb.append("    runtime: ").append(toIndentedString(runtime)).append("\n");
    sb.append("    originalLanguage: ").append(toIndentedString(originalLanguage)).append("\n");
    sb.append("    originalTitle: ").append(toIndentedString(originalTitle)).append("\n");
    sb.append("    spokenLanguages: ").append(toIndentedString(spokenLanguages)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    tagline: ").append(toIndentedString(tagline)).append("\n");
    sb.append("    ranking: ").append(toIndentedString(ranking)).append("\n");
    sb.append("    genres: ").append(toIndentedString(genres)).append("\n");
    sb.append("    cast: ").append(toIndentedString(cast)).append("\n");
    sb.append("    companies: ").append(toIndentedString(companies)).append("\n");
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

