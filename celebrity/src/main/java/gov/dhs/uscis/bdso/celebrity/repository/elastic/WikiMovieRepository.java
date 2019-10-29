package gov.dhs.uscis.bdso.celebrity.repository.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.WikiMovieDocument;

@Repository
public interface WikiMovieRepository extends ElasticsearchRepository<WikiMovieDocument, String> {

	
	
}
