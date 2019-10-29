package gov.dhs.uscis.bdso.celebrity.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import gov.dhs.uscis.bdso.celebrity.domain.Actors;

@Repository
public interface ActorsRepository extends JpaRepository<Actors, Integer> {
    
    @Query(value = "SELECT * FROM actors WHERE lower(first_name) like CONCAT('%', :name, '%') "
    		+ "OR lower(last_name) like CONCAT('%', :name, '%')",
            nativeQuery = true)
    List<Actors> findActorsByName (@Param("name") String name);
    
    Actors findActorsByImdbId(String id);
    
    Actors findActorsByMovdbId(String id);
    
    @Query(value = "select * from actors a " + 
    		"where exists (select 1 from movie_cast mc, movies m " + 
    		"				where mc.mov_id = m.id " + 
    		"				and m.movdb_id in :movieIds " + 
    		"				and a.id = mc.act_id);",
            nativeQuery = true)
    List<Actors> findActorsByMovieIds(@Param("movieIds") List<String> movieIds);

}