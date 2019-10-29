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
@Document(indexName= "wikiactor", type= "Actor")
public class WikiActorDocument {

	@Id
	@JsonProperty("_id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("source")
	private String source;
	
	@JsonProperty("biography")
	private String biography;
	
	@JsonProperty("earlyLifeAndFamily")
	private String earlyLifeAndFamily;
	
	@JsonProperty("career")
	private String career;
	
	@JsonProperty("otherVentures")
	private String otherVentures;
	
	@JsonProperty("personalLife")
	private String personalLife;
	
	@JsonProperty("filmographyAndAwards")
	private String filmographyAndAwards;
	
	@JsonProperty("discography")
	private String discography;
	
	@JsonProperty("references")
	private String references;
	
	@JsonProperty("externalLinks")
	private String externalLinks	;
	
	@Field( type = FieldType.Date, format = DateFormat.custom, pattern = "MM/dd/yyyy, HH:mm:ss")
    @JsonProperty("createdUtcDt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy, HH:mm:ss")
	private Date createdUtcDt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getEarlyLifeAndFamily() {
		return earlyLifeAndFamily;
	}

	public void setEarlyLifeAndFamily(String earlyLifeAndFamily) {
		this.earlyLifeAndFamily = earlyLifeAndFamily;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getOtherVentures() {
		return otherVentures;
	}

	public void setOtherVentures(String otherVentures) {
		this.otherVentures = otherVentures;
	}

	public String getPersonalLife() {
		return personalLife;
	}

	public void setPersonalLife(String personalLife) {
		this.personalLife = personalLife;
	}

	public String getFilmographyAndAwards() {
		return filmographyAndAwards;
	}

	public void setFilmographyAndAwards(String filmographyAndAwards) {
		this.filmographyAndAwards = filmographyAndAwards;
	}

	public String getDiscography() {
		return discography;
	}

	public void setDiscography(String discography) {
		this.discography = discography;
	}

	public String getReferences() {
		return references;
	}

	public void setReferences(String references) {
		this.references = references;
	}

	public String getExternalLinks() {
		return externalLinks;
	}

	public void setExternalLinks(String externalLinks) {
		this.externalLinks = externalLinks;
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
        WikiActorDocument that = (WikiActorDocument) o;
        return  Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
