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
@Document(indexName = "imdbmovie", type = "Movie")
public class ImdbMovieDocument {

	@Id
	@JsonProperty("_id")
	private String id;

	@JsonProperty("source")
	private String source;

	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "MM/dd/yyyy, HH:mm:ss")
	@JsonProperty("createdUtcDt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy, HH:mm:ss")
	private Date createdUtcDt;

	@JsonProperty("languageCodes")
	private String languageCodes;

	@JsonProperty("plotOutline")
	private String plotOutline;

	@JsonProperty("akas")
	private List<String> akas;

	@JsonProperty("countries")
	private List<String> countries;

	@JsonProperty("kind")
	private String kind;

	@JsonProperty("rating")
	private String rating;

	@JsonProperty("genres")
	private List<String> genres;

	@JsonProperty("year")
	private String year;

	@JsonProperty("countryCodes")
	private List<String> countryCodes;

	@JsonProperty("languages")
	private List<String> languages;

	@JsonProperty("colorInfo")
	private List<String> colorInfo;

	@JsonProperty("certificates")
	private List<String> certificates;

	@JsonProperty("title")
	private String title;

	@JsonProperty("runtimes")
	private List<String> runtimes;

	@JsonProperty("coverUrl")
	private String coverUrl;

	public ImdbMovieDocument() {
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

	public Date getCreatedUtcDt() {
		return createdUtcDt;
	}

	public void setCreatedUtcDt(Date createdUtcDt) {
		this.createdUtcDt = createdUtcDt;
	}

	public String getLanguageCodes() {
		return languageCodes;
	}

	public void setLanguageCodes(String languageCodes) {
		this.languageCodes = languageCodes;
	}

	public String getPlotOutline() {
		return plotOutline;
	}

	public void setPlotOutline(String plotOutline) {
		this.plotOutline = plotOutline;
	}

	public List<String> getAkas() {
		return akas;
	}

	public void setAkas(List<String> akas) {
		this.akas = akas;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<String> getCountryCodes() {
		return countryCodes;
	}

	public void setCountryCodes(List<String> countryCodes) {
		this.countryCodes = countryCodes;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public List<String> getColorInfo() {
		return colorInfo;
	}

	public void setColorInfo(List<String> colorInfo) {
		this.colorInfo = colorInfo;
	}

	public List<String> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<String> certificates) {
		this.certificates = certificates;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getRuntimes() {
		return runtimes;
	}

	public void setRuntimes(List<String> runtimes) {
		this.runtimes = runtimes;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
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
		ImdbMovieDocument that = (ImdbMovieDocument) o;
		return Objects.equals(id, that.id) && Objects.equals(title, that.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title);
	}
}
