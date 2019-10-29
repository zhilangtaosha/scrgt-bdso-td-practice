package gov.dhs.uscis.bdso.celebrity.domain.elastic;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "moviedbmovie", type = "Movie")
public class MoviedbMovieDocument {

	@Id
	@JsonProperty("id")
	private String id;

	@JsonProperty("source")
	private String source;

	@JsonProperty("title")
	private String title;

	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "MM/dd/yyyy, HH:mm:ss")
	@JsonProperty("createdUtcDt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy, HH:mm:ss")
	private Date createdUtcDt;

	@JsonProperty("adult")
	private boolean adult;

	@JsonProperty("backdropPath")
	private String backdropPath;

	@JsonProperty("productionCompanies")
	private List<MoviedbMovieProdCompany> productionCompanies;

	@JsonProperty("budget")
	private String budget;

	@JsonProperty("homepage")
	private String homepage;

	@JsonProperty("imdbId")
	private String imdbId;

	@JsonProperty("originalLanguage")
	private String originalLanguage;

	@JsonProperty("originalTitle")
	private String originalTitle;

	@JsonProperty("overview")
	private String overview;

	@JsonProperty("genres")
	private List<MoviedbMovieGenre> genres;

	@JsonProperty("popularity")
	private String popularity;

	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd")
	@JsonProperty("releaseDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date releaseDate;

	@JsonProperty("revenue")
	private String revenue;

	@JsonProperty("runtime")
	private String runtime;

	@JsonProperty("status")
	private String status;

	@JsonProperty("tagline")
	private String tagline;

	@JsonProperty("voteAverage")
	private String voteAverage;

	@JsonProperty("voteCount")
	private String voteCount;

	@JsonProperty("video")
	private boolean video;

	public MoviedbMovieDocument() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreatedUtcDt() {
		return createdUtcDt;
	}

	public void setCreatedUtcDt(Date createdUtcDt) {
		this.createdUtcDt = createdUtcDt;
	}

	public boolean isAdult() {
		return adult;
	}

	public void setAdult(boolean adult) {
		this.adult = adult;
	}

	public String getBackdropPath() {
		return backdropPath;
	}

	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public List<MoviedbMovieGenre> getGenres() {
		return genres;
	}

	public void setGenres(List<MoviedbMovieGenre> genres) {
		this.genres = genres;
	}

	public String getPopularity() {
		return popularity;
	}

	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public String getRevenue() {
		return revenue;
	}

	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(String voteAverage) {
		this.voteAverage = voteAverage;
	}

	public String getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(String voteCount) {
		this.voteCount = voteCount;
	}

	public boolean isVideo() {
		return video;
	}

	public void setVideo(boolean video) {
		this.video = video;
	}
	
	public List<MoviedbMovieProdCompany> getProductionCompanies() {
		return productionCompanies;
	}

	public void setProductionCompanies(List<MoviedbMovieProdCompany> productionCompanies) {
		this.productionCompanies = productionCompanies;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MoviedbMovieDocument that = (MoviedbMovieDocument) o;
		return Objects.equals(id, that.id) && Objects.equals(title, that.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title);
	}
}
