package gov.dhs.uscis.bdso.celebrity.domain.elastic;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MoviedbActorCast {

	@JsonProperty("character")
	private String character;

	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd")
	@JsonProperty("releaseDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date releaseDate;

	@JsonProperty("voteCount")
	private String voteCount;

	@JsonProperty("video")
	private boolean video;

	@JsonProperty("adult")
	private boolean adult;

	@JsonProperty("voteAverage")
	private String voteAverage;

	@JsonProperty("title")
	private String title;

	@JsonProperty("genreIds")
	private List<Integer> genreIds;

	@JsonProperty("originalLanguage")
	private String originalLanguage;

	@JsonProperty("originalTitle")
	private String originalTitle;

	@JsonProperty("popularity")
	private String popularity;

	@JsonProperty("id")
	private String id;

	@JsonProperty("overview")
	private String overview;

	public MoviedbActorCast() {
		super();
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
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

	public boolean isAdult() {
		return adult;
	}

	public void setAdult(boolean adult) {
		this.adult = adult;
	}

	public String getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(String voteAverage) {
		this.voteAverage = voteAverage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Integer> getGenreIds() {
		return genreIds;
	}

	public void setGenreIds(List<Integer> genreIds) {
		this.genreIds = genreIds;
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

	public String getPopularity() {
		return popularity;
	}

	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
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
		MoviedbActorCast that = (MoviedbActorCast) o;
		return Objects.equals(id, that.id) && Objects.equals(title, that.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title);
	}
}
