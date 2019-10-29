package gov.dhs.uscis.bdso.celebrity.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.WikiActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.WikiMovieDocument;
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.repository.elastic.WikiActorRepository;
import gov.dhs.uscis.bdso.celebrity.repository.elastic.WikiMovieRepository;
import gov.dhs.uscis.bdso.celebrity.service.WikipediaService;

@Service
public class WikipediaServiceImpl implements WikipediaService {

	@Autowired
    private WikiActorRepository wikiActorRepository;
	
	@Autowired
    private WikiMovieRepository wikiMovieRepository;
	
	@Override
	public List<WikiActorDocument> getActorInfoByName(String name) {
		return wikiActorRepository.findByName(name);
	}

	@Override
	public WikiActorDocument getActorInfo(String id) {
		Optional<WikiActorDocument> instance = wikiActorRepository.findById(id);
		if (instance.isPresent() && instance.get().getId() != null) {
			return instance.get();
		}
		else {
			return null;
		}
	}
	
	@Override
	public Iterable<WikiActorDocument> getAllCelebrities() {
		return wikiActorRepository.findAll();
	}

	@Override
	public WikiActorDocument addActor(WikiActorDocument dto) throws BusinessException {
		WikiActorDocument instance = getActorInfo(dto.getId());
		if (instance != null) {
			wikiActorRepository.delete(instance);
		}
		instance = wikiActorRepository.save(dto);
		return instance;
	}	

	@Override
	public void deleteActor(String id) {
		WikiActorDocument instance = getActorInfo(id);
		if (instance != null) {
			wikiActorRepository.delete(instance);
		}
	}

	@Override
	public WikiMovieDocument getMovieInfo(String id) {
		Optional<WikiMovieDocument> instance = wikiMovieRepository.findById(id);
		if (instance.isPresent() && instance.get().getMovieID() != null) {
			return instance.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Iterable<WikiMovieDocument> getAllMovies() {
		return wikiMovieRepository.findAll();
	}

	@Override
	public WikiMovieDocument addMovie(WikiMovieDocument dto) throws BusinessException {
		WikiMovieDocument instance = getMovieInfo(dto.getMovieID());
		if (instance != null) {
			wikiMovieRepository.delete(instance);
		}
		instance = wikiMovieRepository.save(dto);
		return instance;
	}

	@Override
	public void deleteMovie(String id) {
		WikiMovieDocument instance = getMovieInfo(id);
		if (instance != null) {
			wikiMovieRepository.delete(instance);
		}		
	}

}
