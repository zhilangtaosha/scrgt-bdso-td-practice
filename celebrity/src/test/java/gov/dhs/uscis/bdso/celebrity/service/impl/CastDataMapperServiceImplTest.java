package gov.dhs.uscis.bdso.celebrity.service.impl;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import gov.dhs.uscis.bdso.celebrity.domain.Actors;
import gov.dhs.uscis.bdso.celebrity.domain.GenderEnum;
import gov.dhs.uscis.bdso.celebrity.domain.Movies;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbActorCast;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbActorDocument;
import gov.dhs.uscis.bdso.celebrity.repository.ActorsRepository;
import gov.dhs.uscis.bdso.celebrity.repository.MovieCastRepository;
import gov.dhs.uscis.bdso.celebrity.repository.MoviesRepository;
import gov.dhs.uscis.bdso.celebrity.service.MoviedbService;

@RunWith(MockitoJUnitRunner.class)
public class CastDataMapperServiceImplTest {
	
	@InjectMocks
	CastDataMapperServiceImpl castDataMapperServiceImpl;
	@Mock
	private MoviedbService moviedbService;
	@Mock
	private ActorsRepository actorsRepository;
	@Mock
	private MoviesRepository moviesRepository;
	@Mock
	private MovieCastRepository movieCastRepository;
 
	
	@Test
	public void testMapCelebrityData() throws Exception{
		Set<MoviedbActorDocument> document = new HashSet<>();
		MoviedbActorDocument moviedActorDocument = new MoviedbActorDocument();
		moviedActorDocument.setId("1");
		moviedActorDocument.setActorId("100");
		List<MoviedbActorCast> listCast = new ArrayList<>();
		MoviedbActorCast moviedbActorCast = new MoviedbActorCast();
		moviedbActorCast.setId("1");
		listCast.add(moviedbActorCast);
		moviedActorDocument.setCast(listCast);
		document.add(moviedActorDocument);
		
		Actors dto = new Actors();
		dto.setGender(GenderEnum.FEMALE);
		dto.setFirstName("test");
		Movies movies = new Movies();
		movies.setId(1);
		
		when(moviedbService.getAllCelebrities()).thenReturn(document);
		
		castDataMapperServiceImpl.mapAllMovieCast();
		verify(moviedbService).getAllCelebrities();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testMapMovieCastData() throws Exception{
		 
		MoviedbActorDocument moviedActorDocument = new MoviedbActorDocument();
		moviedActorDocument.setId("1");
		moviedActorDocument.setActorId("100");
		List<MoviedbActorCast> listCast = new ArrayList<>();
		MoviedbActorCast moviedbActorCast = new MoviedbActorCast();
		moviedbActorCast.setId("1");
        moviedbActorCast.setReleaseDate(new Date());
		listCast.add(moviedbActorCast);
		moviedActorDocument.setCast(listCast);
		 
		
		Actors dto = new Actors();
		dto.setGender(GenderEnum.FEMALE);
		dto.setFirstName("test");
		dto.setId(1);
		Movies movies = new Movies();
		movies.setId(1);
		
		when(actorsRepository.findActorsByMovdbId(Mockito.anyString())).thenReturn(dto);
		when(moviesRepository.findByMovdbId(Mockito.anyString())).thenReturn(null);
		when(moviesRepository.save(Mockito.anyObject())).thenReturn(movies);
		when(movieCastRepository.findByActorIdAndMovieId(Mockito.anyInt(),Mockito.anyInt())).thenReturn(null);
		castDataMapperServiceImpl.mapMovieCastData(moviedActorDocument);
		verify(moviesRepository).findByMovdbId(Mockito.anyString());
		verify(actorsRepository).findActorsByMovdbId(Mockito.anyString());
	}
	
    @Test
    public void testMapMovieCastNoData() throws Exception {
        MoviedbActorDocument moviedActorDocument = new MoviedbActorDocument();
        moviedActorDocument.setId("1");
        moviedActorDocument.setActorId("100");

        castDataMapperServiceImpl.mapMovieCastData(moviedActorDocument);
        verify(actorsRepository, times(0)).findActorsByMovdbId(anyString());
    }
}