package gov.dhs.uscis.bdso.celebrity.converter;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.dozermapper.core.Mapper;

import gov.dhs.uscis.bdso.celebrity.domain.MovieCast;
import gov.dhs.uscis.bdso.celebrity.model.Cast;

@RunWith(MockitoJUnitRunner.class)
public class MovieCastConverterTest {
	
	@InjectMocks
	MovieCastConverter MovieCastConverter;
	
	Mapper mapper = Mockito.mock(Mapper.class);
	
	@Test
	public void testConvertTo() throws Exception{
		
		MovieCast movies = new MovieCast();
		movies.setId(100);
		Cast destination = new Cast();
	    Mockito.when(mapper.map(movies, Cast.class)).thenReturn(destination);
		Cast returnObject = MovieCastConverter.convertTo(movies, destination);
		assertNotNull(returnObject);
	 
		
	}

	@Test
	public void testConvertFrom() throws Exception{
		
		MovieCast movies = new MovieCast();
		movies.setId(100);
		Cast destination = new Cast();
	    Mockito.when(mapper.map(destination, MovieCast.class)).thenReturn(movies);
	    MovieCast returnObject = MovieCastConverter.convertFrom(destination,movies);
		assertNotNull(returnObject);
	 
		
	}
	
	
}
