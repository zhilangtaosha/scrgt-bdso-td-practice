package gov.dhs.uscis.bdso.celebrity.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity(name = "movie_production")
public class MovieProduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "mov_id", nullable = false)
    private Integer movId;

    @Column(name = "comp_id", nullable = false)
    private Integer compId;
    

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMovId() {
		return movId;
	}

	public void setMovId(Integer movId) {
		this.movId = movId;
	}
	
	public Integer getCompId() {
		return compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieProduction that = (MovieProduction) o;
        return movId == that.movId &&
                Objects.equals(compId, that.compId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movId, compId);
    }
}
