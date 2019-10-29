package gov.dhs.uscis.bdso.celebrity.repository.elastic;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.WikiActorDocument;

@Repository
public interface WikiActorRepository extends ElasticsearchRepository<WikiActorDocument, String> {

	List<WikiActorDocument> findByName(String name);
	
}
