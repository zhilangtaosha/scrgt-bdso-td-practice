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
@Document(indexName= "imdbactor", type= "Actor")
public class ImdbActorDocument {

	@Id
    @JsonProperty("_actorID")
	private String id;

	@JsonProperty("headshot")
	private String headshot;
	
	@JsonProperty("birthName")
	private String birthName;

	@JsonProperty("nickNames")
	private List<String> nickNames;

	@JsonProperty("height")
	private String height;
	
    @JsonProperty("miniBiography")
    private List<String> miniBiography;
	
	@JsonProperty("spouse")
	private List<String> spouse;
	
	@JsonProperty("tradeMark")
	private List<String> tradeMark;

	@JsonProperty("trivia")
	private List<String> trivia;
	
	@JsonProperty("quotes")
	private List<String> quotes;
	
	@JsonProperty("salaryHistory")
	private List<String> salaryHistory;
	
	@Field( type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd")
	@JsonProperty("birthDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date birthDate;
	
	@JsonProperty("birthNotes")
	private String birthNotes;
	
    @JsonProperty("actorID")
    private String actorID;
	
	@JsonProperty("source")
	private String source;
	
	@Field( type = FieldType.Date, format = DateFormat.custom, pattern = "MM/dd/yyyy, HH:mm:ss")
    @JsonProperty("createdUtcDt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy, HH:mm:ss")
	private Date createdUtcDt;

    public ImdbActorDocument() {
        super();
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHeadshot() {
		return headshot;
	}

	public void setHeadshot(String headshot) {
		this.headshot = headshot;
	}

	public String getBirthName() {
		return birthName;
	}

	public void setBirthName(String birthName) {
		this.birthName = birthName;
	}

	public List<String> getNickNames() {
		return nickNames;
	}

	public void setNickNames(List<String> nickNames) {
		this.nickNames = nickNames;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

    public List<String> getMiniBiography() {
		return miniBiography;
	}

    public void setMiniBiography(List<String> miniBiography) {
		this.miniBiography = miniBiography;
	}

	public List<String> getSpouse() {
		return spouse;
	}

	public void setSpouse(List<String> spouse) {
		this.spouse = spouse;
	}

	public List<String> getTradeMark() {
		return tradeMark;
	}

	public void setTradeMark(List<String> tradeMark) {
		this.tradeMark = tradeMark;
	}

	public List<String> getTrivia() {
		return trivia;
	}

	public void setTrivia(List<String> trivia) {
		this.trivia = trivia;
	}

	public List<String> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<String> quotes) {
		this.quotes = quotes;
	}

	public List<String> getSalaryHistory() {
		return salaryHistory;
	}

	public void setSalaryHistory(List<String> salaryHistory) {
		this.salaryHistory = salaryHistory;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthNotes() {
		return birthNotes;
	}

	public void setBirthNotes(String birthNotes) {
		this.birthNotes = birthNotes;
	}

    public String getActorID() {
        return actorID;
    }

    public void setActorID(String actorID) {
        this.actorID = actorID;
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
        ImdbActorDocument that = (ImdbActorDocument) o;
        return  Objects.equals(id, that.id) &&
                Objects.equals(birthName, that.birthName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, birthName);
    }
}
