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
@Document(indexName = "moviedbactor", type = "Actor")
public class MoviedbActorDocument {

	@Id
	@JsonProperty("_actorID")
	private String id;

	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd")
	@JsonProperty("birthday")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date birthday;

	@JsonProperty("knownForDepartment")
	private String knownForDepartment;

	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd")
	@JsonProperty("deathday")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date deathDay;

	@JsonProperty("actorId")
	private String actorId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("alsoKnownAs")
	private List<String> aka;

	@JsonProperty("gender")
	private Integer gender;

	@JsonProperty("biography")
	private String biography;

	@JsonProperty("popularity")
	private Integer popularity;

	@JsonProperty("placeOfBirth")
	private String placeOfBirth;

	@JsonProperty("profilePath")
	private String profilePath;

	@JsonProperty("adult")
	private String adult;

	@JsonProperty("imdbId")
	private String imdbId;

	@JsonProperty("homepage")
	private String homepage;

	@JsonProperty("source")
	private String source;

	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "MM/dd/yyyy, HH:mm:ss")
	@JsonProperty("createdUtcDt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy, HH:mm:ss")
	private Date createdUtcDt;

	@JsonProperty("cast")
	private List<MoviedbActorCast> cast;

	@JsonProperty("images")
	private List<MoviedbActorImage> images;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getKnownForDepartment() {
		return knownForDepartment;
	}

	public void setKnownForDepartment(String knownForDepartment) {
		this.knownForDepartment = knownForDepartment;
	}

	public Date getDeathDay() {
		return deathDay;
	}

	public void setDeathDay(Date deathDay) {
		this.deathDay = deathDay;
	}

	public String getActorId() {
		return actorId;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAka() {
		return aka;
	}

	public void setAka(List<String> aka) {
		this.aka = aka;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	public String getAdult() {
		return adult;
	}

	public void setAdult(String adult) {
		this.adult = adult;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getCreatedUtcDt() {
		return createdUtcDt;
	}

	public void setCreatedUtcDt(Date createdUtcDt) {
		this.createdUtcDt = createdUtcDt;
	}

	public List<MoviedbActorCast> getCast() {
		return cast;
	}

	public void setCast(List<MoviedbActorCast> cast) {
		this.cast = cast;
	}

	public List<MoviedbActorImage> getImages() {
		return images;
	}

	public void setImages(List<MoviedbActorImage> images) {
		this.images = images;
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
		MoviedbActorDocument that = (MoviedbActorDocument) o;
		return Objects.equals(id, that.id) && Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
