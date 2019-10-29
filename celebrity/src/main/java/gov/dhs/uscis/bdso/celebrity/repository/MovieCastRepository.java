package gov.dhs.uscis.bdso.celebrity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gov.dhs.uscis.bdso.celebrity.domain.MovieCast;

@Repository
public interface MovieCastRepository extends JpaRepository<MovieCast, Integer> {
    MovieCast findByActorIdAndMovieId(Integer actId, Integer movId);
}