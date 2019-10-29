package gov.dhs.uscis.bdso.celebrity.repository.elastic;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbMovieDocument;

@Repository
public interface MoviedbMovieRepository extends ElasticsearchRepository<MoviedbMovieDocument, String> {

	List<MoviedbMovieDocument> findByTitle(String title);
	
}
