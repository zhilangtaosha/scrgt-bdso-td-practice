package gov.dhs.uscis.bdso.celebrity.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Audited
@Entity(name = "actors")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Actors extends AuditedEntity<Actors> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 200)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 200)
    private String lastName;

    @Column(name = "birth_name", nullable = false, length = 400)
    private String birthName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column(name = "box_office")
    private Long boxOffice;
    
    @Column(name = "num_movies")
    private Integer numMovies;

    @Column(name = "avg_revenue")
    private Long avgRevenue;
    
    @Column(name = "imdb_id")
    private String imdbId;
    
    @Column(name = "movdb_id")
    private String movdbId;
    
    @Column(name = "height")
    private String height;

    @Column(name = "birth_place", length = 200)
    private String birthPlace;
    
    @Column(name = "dob")
    private LocalDate dob;
    
    @Column(name = "nick_name", length = 100)
    private String nickName;
    
    @Column(name = "popularity")
    private Integer popularity;
    
    @Column(name = "biography", length = 4000)
    private String biography;
    
    @Column(name = "ext_photo_id", length = 100)
    private String extPhotoId;
    
    @Column(name = "image_url", length = 1000)
    private String imageUrl;
    
    // @JsonIgnore
    // @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JoinTable(name = "movie_cast", joinColumns
    // = @JoinColumn(name = "act_id", referencedColumnName = "id"),
    // inverseJoinColumns = @JoinColumn(name = "mov_id", referencedColumnName = "id"))
    // private Set<Movies> movies = new HashSet<>();
    
    // @JsonManagedReference("actorReference")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "act_id", nullable = false, insertable = false, updatable = false)
    private Set<MovieCast> movieCast;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    public String getBirthName() {
        return birthName;
    }

    public void setBirthName(String birthName) {
        this.birthName = birthName;
    }

    public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public Long getBoxOffice() {
		return boxOffice;
	}

	public void setBoxOffice(Long boxOffice) {
		this.boxOffice = boxOffice;
	}

	public Integer getNumMovies() {
		return numMovies;
	}

	public void setNumMovies(Integer numMovies) {
		this.numMovies = numMovies;
	}

	public Long getAvgRevenue() {
		return avgRevenue;
	}

	public void setAvgRevenue(Long avgRevenue) {
		this.avgRevenue = avgRevenue;
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

    // public Set<Movies> getMovies() {
    // return movies;
    // }
    //
    // public void setMovies(Set<Movies> movies) {
    // this.movies = movies;
    // }

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getExtPhotoId() {
		return extPhotoId;
	}

	public void setExtPhotoId(String extPhotoId) {
		this.extPhotoId = extPhotoId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

    public Set<MovieCast> getMovieCast() {
        return movieCast;
    }

    public void setMovieCast(Set<MovieCast> movieCast) {
        this.movieCast = movieCast;
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
        Actors that = (Actors) o;
        return id != null && Objects.equals(id, that.id)
                || Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    Serializable getEntityId() {
        return id;
    }
}
