package gov.dhs.uscis.bdso.celebrity.repository.elastic;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.ImdbActorDocument;

@Repository
public interface ImdbActorRepository extends ElasticsearchRepository<ImdbActorDocument, String> {

	List<ImdbActorDocument> findByBirthName(String name);
	
}
