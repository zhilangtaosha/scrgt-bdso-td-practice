package gov.dhs.uscis.bdso.celebrity.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbMovieDocument;
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.repository.elastic.MoviedbActorRepository;
import gov.dhs.uscis.bdso.celebrity.repository.elastic.MoviedbMovieRepository;
import gov.dhs.uscis.bdso.celebrity.service.MoviedbService;

@Service
public class MoviedbServiceImpl implements MoviedbService {

	@Autowired
    private MoviedbActorRepository moviedbActorRepository;
	
	@Autowired
    private MoviedbMovieRepository moviedbMovieRepository;
	
	@Override
	public List<MoviedbActorDocument> getActorInfoByName(String name) {
		return moviedbActorRepository.findByName(name);
	}

	@Override
	public MoviedbActorDocument getActorInfo(String id) {
		Optional<MoviedbActorDocument> instance = moviedbActorRepository.findById(id);
		if (instance.isPresent() && instance.get().getId() != null) {
			return instance.get();
		}
		else {
			return null;
		}
	}
	
	@Override
	public Iterable<MoviedbActorDocument> getAllCelebrities() {
		return moviedbActorRepository.findAll();
	}

	@Override
	public MoviedbActorDocument addActor(MoviedbActorDocument dto) throws BusinessException {
		MoviedbActorDocument instance = getActorInfo(dto.getId());
		if (instance != null) {
			moviedbActorRepository.delete(instance);
		}
		instance = moviedbActorRepository.save(dto);
		return instance;
	}	

	@Override
	public void deleteActor(String id) {
		MoviedbActorDocument instance = getActorInfo(id);
		if (instance != null) {
			moviedbActorRepository.delete(instance);
		}
	}

	@Override
	public List<MoviedbMovieDocument> getMovieInfoByTitle(String title) {
		return moviedbMovieRepository.findByTitle(title);
	}

	@Override
	public MoviedbMovieDocument getMovieInfo(String id) {
		Optional<MoviedbMovieDocument> instance = moviedbMovieRepository.findById(id);
		if (instance.isPresent() && instance.get().getId() != null) {
			return instance.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Iterable<MoviedbMovieDocument> getAllMovies() {
		return moviedbMovieRepository.findAll();
	}

	@Override
	public MoviedbMovieDocument addMovie(MoviedbMovieDocument dto) throws BusinessException {
		MoviedbMovieDocument instance = getMovieInfo(dto.getId());
		if (instance != null) {
			moviedbMovieRepository.delete(instance);
		}
		instance = moviedbMovieRepository.save(dto);
		return instance;
	}

	@Override
	public void deleteMovie(String id) {
		MoviedbMovieDocument instance = getMovieInfo(id);
		if (instance != null) {
			moviedbMovieRepository.delete(instance);
		}
	}

}
