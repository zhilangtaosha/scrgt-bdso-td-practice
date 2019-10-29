package gov.dhs.uscis.bdso.celebrity.converter;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import gov.dhs.uscis.bdso.celebrity.domain.MovieGenres;
import gov.dhs.uscis.bdso.celebrity.model.Genre;

@RunWith(MockitoJUnitRunner.class)
public class GenreListConverterTest {
	
	@InjectMocks
	GenreListConverter genreListConverter;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testConvertTo() throws Exception{
		
		MovieGenres movieGenres = new MovieGenres();
		movieGenres.setGenre("Action");
		Set<MovieGenres> set = new HashSet<MovieGenres>();
		set.add(movieGenres);
		List<Genre> returnList = genreListConverter.convertTo(set, new ArrayList<Object>());
		assertNotNull(returnList);

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testConvertFrom() throws Exception{
		
		Genre genre = Genre.ACTION;
	 
		List<Genre> list = new ArrayList<Genre>();
		list.add(genre);
		Set<MovieGenres> set = new HashSet<MovieGenres>();
		Set<MovieGenres> returnSet = genreListConverter.convertFrom(list, set);
		assertNotNull(returnSet);

	}
	
 

}
