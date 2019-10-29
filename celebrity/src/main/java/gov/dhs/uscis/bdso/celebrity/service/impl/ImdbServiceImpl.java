package gov.dhs.uscis.bdso.celebrity.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.ImdbActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.ImdbMovieDocument;
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.repository.elastic.ImdbActorRepository;
import gov.dhs.uscis.bdso.celebrity.repository.elastic.ImdbMovieRepository;
import gov.dhs.uscis.bdso.celebrity.service.ImdbService;

@Service
public class ImdbServiceImpl implements ImdbService {

	@Autowired
    private ImdbActorRepository imdbActorRepository;
	
	@Autowired
    private ImdbMovieRepository imdbMovieRepository;
	
	@Override
	public List<ImdbActorDocument> getActorInfoByName(String name) {
		return imdbActorRepository.findByBirthName(name);
	}

	@Override
	public ImdbActorDocument getActorInfo(String id) {
		Optional<ImdbActorDocument> instance = imdbActorRepository.findById(id);
		if (instance.isPresent() && instance.get().getId() != null) {
			return instance.get();
		}
		else {
			return null;
		}
	}
	
	@Override
	public Iterable<ImdbActorDocument> getAllCelebrities() {
		return imdbActorRepository.findAll();
	}

	@Override
	public ImdbActorDocument addActor(ImdbActorDocument dto) throws BusinessException {
		ImdbActorDocument instance = getActorInfo(dto.getId());
		if (instance != null) {
			imdbActorRepository.delete(instance);
		}
		instance = imdbActorRepository.save(dto);
		return instance;
	}

	@Override
	public void deleteActor(String id) {
		ImdbActorDocument instance = getActorInfo(id);
		if (instance != null) {
			imdbActorRepository.delete(instance);
		}
	}

	@Override
	public List<ImdbMovieDocument> getMovieInfoByTitle(String title) {
		return imdbMovieRepository.findByTitle(title);
	}

	@Override
	public ImdbMovieDocument getMovieInfo(String id) {
		Optional<ImdbMovieDocument> instance = imdbMovieRepository.findById(id);
		if (instance.isPresent() && instance.get().getId() != null) {
			return instance.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Iterable<ImdbMovieDocument> getAllMovies() {
		return imdbMovieRepository.findAll();
	}

	@Override
	public ImdbMovieDocument addMovie(ImdbMovieDocument dto) throws BusinessException {
		ImdbMovieDocument instance = getMovieInfo(dto.getId());
		if (instance != null) {
			imdbMovieRepository.delete(instance);
		}
		instance = imdbMovieRepository.save(dto);
		return instance;
	}

	@Override
	public void deleteMovie(String id) {
		ImdbMovieDocument instance = getMovieInfo(id);
		if (instance != null) {
			imdbMovieRepository.delete(instance);
		}
	}

}
