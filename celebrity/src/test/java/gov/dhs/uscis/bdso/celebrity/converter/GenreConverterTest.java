package gov.dhs.uscis.bdso.celebrity.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import gov.dhs.uscis.bdso.celebrity.domain.MovieGenres;
import gov.dhs.uscis.bdso.celebrity.model.Genre;

@RunWith(MockitoJUnitRunner.class)
public class GenreConverterTest {
	
	@InjectMocks
	GenreConverter genreConverter;
	
	MovieGenres movieGenres = new MovieGenres();
	Genre genre = Genre.ACTION;
	
	 
	
	
	@Test
	public void testConvertTo() throws Exception{
		movieGenres.setGenre("Action");
		Genre returnObject = genreConverter.convertTo(movieGenres,genre);
		assertNotNull(returnObject);
		assertEquals(Genre.ACTION,returnObject);
		
	}
	
	@Test
	public void testConvertToNull() throws Exception{
		movieGenres.setGenre("Action");
		Genre returnObject = genreConverter.convertTo(null,genre);
		assertNull(returnObject);
	 
		
	}
	

	@Test
	public void testConvertFrom() throws Exception{
		movieGenres.setGenre("Action");
		MovieGenres returnObject = genreConverter.convertFrom(genre,movieGenres);
		assertNotNull(returnObject);
	
		
	}
	
	
}
