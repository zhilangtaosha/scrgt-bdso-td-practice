package gov.dhs.uscis.bdso.celebrity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.dhs.uscis.bdso.celebrity.domain.Movies;

@Repository
public interface MoviesRepository extends CrudRepository<Movies, Integer> {
    
    Movies findByTitle (String title);
    
    @Query(value = "SELECT * FROM movies WHERE lower(title) like CONCAT('%', :title, '%')",
            nativeQuery = true)
    List<Movies> findMoviesByTitle (@Param("title") String title);
    
    @Query(value = "SELECT * FROM movies WHERE year = :year",
            nativeQuery = true)
    List<Movies> findByMovieYear (@Param("year") int year); 
    
    @Query(value = "SELECT * FROM movies WHERE year between :fromYear and :toYear",
            nativeQuery = true)
    List<Movies> findByMovieYearRange (@Param("fromYear") int fromYear, @Param("toYear") int toYear);
    
    Movies findByMovdbId (String id);
    
    Movies findByImdbId (String id);
    
    

}