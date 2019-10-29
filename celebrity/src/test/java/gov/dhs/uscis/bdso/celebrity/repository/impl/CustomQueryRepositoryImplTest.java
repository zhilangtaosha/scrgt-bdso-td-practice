package gov.dhs.uscis.bdso.celebrity.repository.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

 

@RunWith(MockitoJUnitRunner.class)
public class CustomQueryRepositoryImplTest {
	
	@Mock
	private EntityManager entityManager;
	
	@InjectMocks
	CustomQueryRepositoryImpl customQueryRepositoryImpl;
	

    @Before
    public void init() {
    	List<Object[]> results = new ArrayList<Object[]>();
		Query q = Mockito.mock(Query.class);
		when(q.getResultList()).thenReturn(results);
		when(entityManager.createNativeQuery(Mockito.anyString())).thenReturn(q);
    }
    
	@Test
	public void testQueryFilmsByGender() throws Exception{
		
		List<Object[]>  returnList = customQueryRepositoryImpl.queryFilmsByGender();
		assertNotNull(returnList);
	}
	
	@Test
	public void testQuerySalaryByGender() throws Exception{
		
		List<Object[]>  returnList = customQueryRepositoryImpl.querySalaryByGender();
		assertNotNull(returnList);
	}
	
	@Test
	public void testQueryAnnualBoxOfficeTotals() throws Exception{
		
		List<Object[]>  returnList = customQueryRepositoryImpl.queryAnnualBoxOfficeTotals();
		assertNotNull(returnList);
	}
	@Test
	public void testQueryFilmCounts() throws Exception{
		
		List<Object[]>  returnList = customQueryRepositoryImpl.queryFilmCounts();
		assertNotNull(returnList);
	}
	@Test
	public void testQuerySalaryCounts() throws Exception{
		
		List<Object[]>  returnList = customQueryRepositoryImpl.querySalaryCounts();
		assertNotNull(returnList);
	}
	@Test
	public void testQuerySummaryCounts() throws Exception{
		
		List<Object[]>  returnInt = customQueryRepositoryImpl.querySummaryCounts();
		assertNotNull(returnInt);
	}
	
	@Test
	public void testQueryMultipleActorCount() throws Exception{
		
		customQueryRepositoryImpl.queryMultipleActorCount();
		Mockito.verify(entityManager).createNativeQuery(Mockito.anyString());
	}
	
}
