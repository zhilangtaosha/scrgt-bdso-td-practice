package gov.dhs.uscis.bdso.celebrity.service.impl;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbMovieDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbMovieGenre;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbMovieProdCompany;
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.model.Company;
import gov.dhs.uscis.bdso.celebrity.model.Genre;
import gov.dhs.uscis.bdso.celebrity.model.Movie;
import gov.dhs.uscis.bdso.celebrity.service.MovieDataMapperService;
import gov.dhs.uscis.bdso.celebrity.service.MovieService;
import gov.dhs.uscis.bdso.celebrity.service.MoviedbService;

@Service
public class MovieDataMapperServiceImpl implements MovieDataMapperService {

	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	private MoviedbService moviedbService;
	
	@Autowired
	private MovieService movieService;
	
	@Override
	@Transactional
	public void mapAllMovies() throws BusinessException {
		Iterable<MoviedbMovieDocument> movieList = moviedbService.getAllMovies();
		movieList.forEach(movie -> {
			try {
				LOG.info("Mapping movie ID - {}", movie.getId());
				mapMovieData(movie);
			} catch (BusinessException e) {
				LOG.error("Error mapping Movie data to postgres for id - {}", movie.getId(), e);
			}
		});
	}
	
	private void mapMovieData(MoviedbMovieDocument movieDbInfo) throws BusinessException {
		
		Movie dto = movieService.getMovieInfoByMovdbId(movieDbInfo.getId());
		if (dto != null) return; //If movie already added then skip
		
		dto = new Movie();
		transformMoviedbMovieData(movieDbInfo, dto);
		movieService.addMovie(dto);
		
	}
	
	private void transformMoviedbMovieData(MoviedbMovieDocument movieDbInfo, Movie dto) {
		if (NumberUtils.isCreatable(movieDbInfo.getBudget()))
			dto.setBudget(new BigDecimal(movieDbInfo.getBudget()));
		dto.setMovdbId(movieDbInfo.getId());
		dto.setImdbId(movieDbInfo.getImdbId());
		dto.setLanguage(movieDbInfo.getOriginalLanguage());
		dto.setOriginalLanguage(movieDbInfo.getOriginalLanguage());
		dto.setOriginalTitle(movieDbInfo.getOriginalTitle());
		dto.setOverview(movieDbInfo.getOverview());
		dto.setPopularity(Double.valueOf(movieDbInfo.getPopularity()).intValue());
		dto.setReleaseDate(movieDbInfo.getReleaseDate().toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate());
		if (NumberUtils.isCreatable(movieDbInfo.getRevenue()))
			dto.setRevenue(new BigDecimal(movieDbInfo.getRevenue()));
		if (NumberUtils.isCreatable(movieDbInfo.getRuntime()))
			dto.setRuntime(Integer.valueOf(movieDbInfo.getRuntime()));
		dto.setSpokenLanguages(movieDbInfo.getOriginalLanguage());
		dto.setTagline(movieDbInfo.getTagline());
		dto.setStatus(movieDbInfo.getStatus());
		dto.setTitle(movieDbInfo.getTitle());

		setGenres(movieDbInfo.getGenres(), dto);
		setProductionCompanies(movieDbInfo.getProductionCompanies(), dto);
	}

	private void setGenres(List<MoviedbMovieGenre> genres, Movie dto) {
		List<Genre> genreNames = new ArrayList<>();
		if (!CollectionUtils.isEmpty(genres)) {
			genres.forEach(genre -> {
				Genre g = Genre.fromValue(genre.getName());
				if (g != null) genreNames.add(g);
			});
			dto.setGenres(genreNames);
		}
	}
	
	private void setProductionCompanies(List<MoviedbMovieProdCompany> prodCompanies, Movie dto) {
		List<Company> companies = new ArrayList<>();
		if (!CollectionUtils.isEmpty(prodCompanies)) {
			prodCompanies.forEach(prodCompany -> {
				Company company = new Company();
				company.setCompanyName(prodCompany.getName());
				companies.add(company);
			});
			dto.setCompanies(companies);
		}
	}

}
