package gov.dhs.uscis.bdso.celebrity.repository.elastic;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbActorDocument;

@Repository
public interface MoviedbActorRepository extends ElasticsearchRepository<MoviedbActorDocument, String> {

	List<MoviedbActorDocument> findByName(String name);
	
}
