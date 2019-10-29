package gov.dhs.uscis.bdso.celebrity.domain.elastic;

import java.util.Date;
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
@Document(indexName = "wikimovie", type = "Movie")
public class WikiMovieDocument {

	@Id
	@JsonProperty("movieID")
	private String movieID;

	@JsonProperty("source")
	private String source;

	@JsonProperty("summary")
	private String summary;

	@JsonProperty("plot")
	private String plot;
	
	@JsonProperty("cast")
	private String cast;
	
	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "MM/dd/yyyy, HH:mm:ss")
	@JsonProperty("createdUtcDt")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy, HH:mm:ss")
	private Date createdUtcDt;

	public String getMovieID() {
		return movieID;
	}

	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public Date getCreatedUtcDt() {
		return createdUtcDt;
	}

	public void setCreatedUtcDt(Date createdUtcDt) {
		this.createdUtcDt = createdUtcDt;
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
		WikiMovieDocument that = (WikiMovieDocument) o;
		return Objects.equals(movieID, that.movieID);
	}

	@Override
	public int hashCode() {
		return Objects.hash(movieID);
	}
}
