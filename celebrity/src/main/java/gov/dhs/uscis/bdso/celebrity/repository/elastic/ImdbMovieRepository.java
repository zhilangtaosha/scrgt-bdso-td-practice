package gov.dhs.uscis.bdso.celebrity.repository.elastic;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.ImdbMovieDocument;

@Repository
public interface ImdbMovieRepository extends ElasticsearchRepository<ImdbMovieDocument, String> {

	List<ImdbMovieDocument> findByTitle(String title);
	
}
