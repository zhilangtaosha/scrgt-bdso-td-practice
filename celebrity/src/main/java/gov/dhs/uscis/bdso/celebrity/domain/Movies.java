package gov.dhs.uscis.bdso.celebrity.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity(name = "movies")
public class Movies extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "budget")
    private Integer budget;

    @Column(name = "home_page", length = 2000)
    private String homePage;
    
    @Column(name = "orig_language", length = 200)
    private String origLanguage;

    @Column(name = "orig_title", length = 500)
    private String origTitle;

    @Column(name = "overview", length = 500)
    private String overview;
    
    @Column(name = "popularity")
    private Integer popularity;

    @Column(name = "poster_img_id", length = 50)
    private String posterImgId;
 
    @Column(name = "release_date")
    private LocalDate releaseDate;
    
    @Column(name = "revenue")
    private Integer revenue;

    @Column(name = "runtime")
    private Integer runtime;
    
    @Column(name = "spoken_languages", length = 500)
    private String spokenLanguages;

    @Column(name = "status", length = 200)
    private String status;

    @Column(name = "tagline", length = 2000)
    private String tagline;
    
    @Column(name = "ranking")
    private Integer ranking;
    
    @Column(name = "imdb_id")
    private String imdbId;
    
    @Column(name = "movdb_id")
    private String movdbId;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "movie_production", joinColumns
            = @JoinColumn(name = "mov_id", referencedColumnName = "id"),
            			inverseJoinColumns = @JoinColumn(name = "comp_id", referencedColumnName = "id"))
    private List<Companies> companies = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "movies")
    private Set<MovieGenres> genres = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mov_id", nullable = false, insertable = false, updatable = false)
    private Set<MovieCast> cast = new HashSet<>();
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getBudget() {
		return budget;
	}

	public void setBudget(Integer budget) {
		this.budget = budget;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getOrigLanguage() {
		return origLanguage;
	}

	public void setOrigLanguage(String origLanguage) {
		this.origLanguage = origLanguage;
	}

	public String getOrigTitle() {
		return origTitle;
	}

	public void setOrigTitle(String origTitle) {
		this.origTitle = origTitle;
	}

    public String getOverview() {
		return overview;
	}

    public void setOverview(String overview) {
		this.overview = overview;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

	public String getPosterImgId() {
		return posterImgId;
	}

	public void setPosterImgId(String posterImgId) {
		this.posterImgId = posterImgId;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Integer getRevenue() {
		return revenue;
	}

	public void setRevenue(Integer revenue) {
		this.revenue = revenue;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	public String getSpokenLanguages() {
		return spokenLanguages;
	}

	public void setSpokenLanguages(String spokenLanguages) {
		this.spokenLanguages = spokenLanguages;
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

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getMovdbId() {
		return movdbId;
	}

	public void setMovdbId(String movdbId) {
		this.movdbId = movdbId;
	}
	
	public List<Companies> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Companies> companies) {
		this.companies = companies;
	}

    public Set<MovieGenres> getGenres() {
		return genres;
	}

    public void setGenres(Set<MovieGenres> genres) {
		this.genres = genres;
	}

    public Set<MovieCast> getCast() {
		return cast;
	}

    public void setCast(Set<MovieCast> cast) {
		this.cast = cast;
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
        Movies that = (Movies) o;
        return id != null && Objects.equals(id, that.id) || Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
