package gov.dhs.uscis.bdso.celebrity.service.impl;

import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.ImdbActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbActorImage;
import gov.dhs.uscis.bdso.celebrity.service.CelebrityService;
import gov.dhs.uscis.bdso.celebrity.service.ImdbService;
import gov.dhs.uscis.bdso.celebrity.service.MoviedbService;
import gov.dhs.uscis.bdso.celebrity.service.S3Service;
import gov.dhs.uscis.bdso.celebrity.service.WikipediaService;

@RunWith(MockitoJUnitRunner.class)
public class CelebrityDataMapperServiceImplTest {
	
	@Mock
	private ImdbService imdbService;
	
	@Mock
	private MoviedbService moviedbService;
	
	@Mock
	private WikipediaService wikipediaService;
	
	@Mock
	private CelebrityService celebrityService;
	
	@Mock
	private S3Service s3Service;
	
	@InjectMocks
	private CelebrityDataMapperServiceImpl dataMapperServiceImpl;
	
	
	@Test
	public void testMapCelebrityData() throws Exception {
		ImdbActorDocument imdbInfo = createImdbActor();
		MoviedbActorDocument movieDbInfo = createMoviedbActor();
		
		
		when(imdbService.getActorInfo(Mockito.anyString())).thenReturn(imdbInfo);
		when(moviedbService.getActorInfo(Mockito.anyString())).thenReturn(movieDbInfo);
		
		dataMapperServiceImpl.mapCelebrityData("1");
		
		Set<ImdbActorDocument> document = new HashSet<ImdbActorDocument>();
		ImdbActorDocument imdbActorDocument = new ImdbActorDocument();
		imdbActorDocument.setId("1");
		imdbActorDocument.setActorID("10");
		document.add(imdbActorDocument);
		
		when(imdbService.getAllCelebrities()).thenReturn(document);
		dataMapperServiceImpl.mapAllCelebrities();
		Mockito.verify(imdbService, Mockito.times(2)).getActorInfo(Mockito.anyString());
		Mockito.verify(moviedbService, Mockito.times(2)).getActorInfo(Mockito.anyString());
	}
	
	private ImdbActorDocument createImdbActor() throws ParseException {
		ImdbActorDocument imdbInfo = new ImdbActorDocument();
		imdbInfo.setActorID("100");
		imdbInfo.setId("101");
		imdbInfo.setBirthName("firstName lastName");
		imdbInfo.setBirthNotes("india");
		String sDate1="1980-01-01";  
		java.util.Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);  
		imdbInfo.setBirthDate(date1);
		imdbInfo.setHeight("150");
	 	List<String> nickNames = new ArrayList<String>();
		nickNames.add("test");
		imdbInfo.setNickNames(nickNames);
		List<String> salaryHistory = new ArrayList<String>();
		salaryHistory.add("$100000::$100000");
		imdbInfo.setSalaryHistory(salaryHistory);
		
		return imdbInfo;
	}
	
	private MoviedbActorDocument createMoviedbActor() {
		MoviedbActorDocument movieDbInfo = new MoviedbActorDocument();
		movieDbInfo.setGender(1);
		movieDbInfo.setBiography("test");
		movieDbInfo.setActorId("1");
		movieDbInfo.setName("test last");
		MoviedbActorImage image = new MoviedbActorImage();
		image.setFile_path("test");
		movieDbInfo.setImages(Collections.singletonList(image));
		
		return movieDbInfo;
	}

}
