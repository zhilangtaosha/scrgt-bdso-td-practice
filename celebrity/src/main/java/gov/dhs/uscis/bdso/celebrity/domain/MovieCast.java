package gov.dhs.uscis.bdso.celebrity.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "movie_cast")
public class MovieCast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "mov_id", nullable = false)
    private Integer movieId;
    
    @Column(name = "act_id", nullable = false)
    private Integer actorId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mov_id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private Movies movies;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "act_id", nullable = false)
//    @JsonBackReference("actorReference")
//    private Actors actors;
    
    @Column(name = "role_name", nullable = false, length=200)
    private String roleName;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    // public Actors getActors() {
    // return actors;
    // }
    //
    // public void setActors(Actors actors) {
    // this.actors = actors;
    // }

    public String getRoleName() {
		return roleName;
	}

    public void setRoleName(String roleName) {
		this.roleName = roleName;
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
        MovieCast that = (MovieCast) o;
        return Objects.equals(id, that.id)
                || Objects.equals(movieId, that.movieId) && Objects.equals(actorId, that.actorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieId, actorId);
    }
}
