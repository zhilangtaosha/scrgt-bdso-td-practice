package gov.dhs.uscis.bdso.celebrity.service.impl;

import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbMovieDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbMovieGenre;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbMovieProdCompany;
import gov.dhs.uscis.bdso.celebrity.service.MovieService;
import gov.dhs.uscis.bdso.celebrity.service.MoviedbService;

@RunWith(MockitoJUnitRunner.class)
public class MovieDataMapperServiceImplTest {
	
	@Mock
	private MoviedbService moviedbService;
	
	@Mock
	private MovieService movieService;
	
	@InjectMocks
	MovieDataMapperServiceImpl mapperServiceImpl;
	
	@Test
	public void testGetActorInfoByName() throws Exception{
		Set<MoviedbMovieDocument> list = new HashSet<MoviedbMovieDocument>();
		MoviedbMovieDocument moviedbMovieDocument = new MoviedbMovieDocument();
		moviedbMovieDocument.setId("100");
		moviedbMovieDocument.setBudget("100000000");
		moviedbMovieDocument.setRevenue("100000000");
		moviedbMovieDocument.setRuntime("180");
		moviedbMovieDocument.setPopularity("5.8");
		List<MoviedbMovieGenre> genres = new ArrayList<MoviedbMovieGenre>();
		MoviedbMovieGenre moviedbMovieGenre = new MoviedbMovieGenre();
		moviedbMovieGenre.setId("1");
		moviedbMovieGenre.setName("Action");
		genres.add(moviedbMovieGenre);
		moviedbMovieDocument.setGenres(genres);
		
		List<MoviedbMovieProdCompany> productionCompanies = new ArrayList<MoviedbMovieProdCompany>();
		MoviedbMovieProdCompany moviedbMovieProdCompany = new MoviedbMovieProdCompany();
		productionCompanies.add(moviedbMovieProdCompany);
		moviedbMovieDocument.setProductionCompanies(productionCompanies);
		
		String sDate1="31/12/1998";
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		moviedbMovieDocument.setReleaseDate(date1);
		list.add(moviedbMovieDocument);
		when(moviedbService.getAllMovies()).thenReturn(list);
		mapperServiceImpl.mapAllMovies();
		Mockito.verify(moviedbService, Mockito.times(1)).getAllMovies();
		
		
	}
	
	

}
