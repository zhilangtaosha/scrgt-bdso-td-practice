package gov.dhs.uscis.bdso.celebrity.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.repository.CustomQueryRepository;

@RunWith(MockitoJUnitRunner.class)
public class DBQueryServiceImplTest {
	
	@Mock
    private CustomQueryRepository customQueryRepository;
	
	@InjectMocks
	DBQueryServiceImpl dbQueryServiceImpl;
	
	   @Test
	    public void testGetFilmsByGender() throws BusinessException {
		   List<Object[]> results = new ArrayList<Object[]>();
		   Object[] obj = new Object[3];
		   String st1 = new String("male");
		   obj[0] = st1;
		   String st2 = new String("1980");
		   obj[1] = st2;
		   BigInteger st3 = BigInteger.valueOf(10);
		   obj[2] = st3;
		   results.add(obj);
		   when(customQueryRepository.queryFilmsByGender()).thenReturn(results);
		   JSONArray outputArray = dbQueryServiceImpl.getFilmsByGender();
		   assertNotNull(outputArray);
		   
	   }
	   
	   @Test
	    public void testGetSalaryByGender() throws BusinessException {
		   List<Object[]> results = new ArrayList<Object[]>();
		   Object[] obj = new Object[3];
		   String st1 = new String("male");
		   obj[0] = st1;
		   String st2 = new String("1980");
		   obj[1] = st2;
		   BigDecimal st3 = BigDecimal.valueOf(1000000);
		   obj[2] = st3;
		   results.add(obj);
		   when(customQueryRepository.querySalaryByGender()).thenReturn(results);
		   JSONArray outputArray = dbQueryServiceImpl.getSalaryByGender();
		   assertNotNull(outputArray);
		   
	   }
	   
	   @Test
	    public void testGetAnnualBoxOfficeTotals() throws BusinessException {
		   List<Object[]> results = new ArrayList<Object[]>();
		   Object[] obj = new Object[3];
		   String st1 = new String("1980");
		   obj[0] = st1;
		 
		   BigDecimal st2 = BigDecimal.valueOf(1000000);
		   obj[1] = st2;
		   
		   BigInteger st3 = BigInteger.valueOf(1000000);
		   obj[2] = st3;
		   results.add(obj);
		   when(customQueryRepository.queryAnnualBoxOfficeTotals()).thenReturn(results);
		   JSONArray outputArray = dbQueryServiceImpl.getAnnualBoxOfficeTotals();
		   assertNotNull(outputArray);
		   
	   }
	   
	   @Test
	    public void testGetFilmCountsByYear() throws BusinessException {
		   List<Object[]> results = new ArrayList<Object[]>();
		   Object[] obj = new Object[2];
		   String st1 = new String("1980");
		   obj[0] = st1;
		 
		   BigInteger st3 = BigInteger.valueOf(1000000);
		   obj[1] = st3;
		   results.add(obj);
		   when(customQueryRepository.queryFilmCounts()).thenReturn(results);
		   JSONArray outputArray = dbQueryServiceImpl.getFilmCountsByYear();
		   assertNotNull(outputArray);
		   
	   }
	   
	   @Test
	    public void testGetSalaryByYear() throws BusinessException {
		   List<Object[]> results = new ArrayList<Object[]>();
		   Object[] obj = new Object[2];
		   String st1 = new String("1980");
		   obj[0] = st1;
		 
		   BigDecimal st3 = BigDecimal.valueOf(1000000);
		   obj[1] = st3;
		   results.add(obj);
		   when(customQueryRepository.querySalaryCounts()).thenReturn(results);
		   JSONArray outputArray = dbQueryServiceImpl.getCelebritySalaryByYear();
		   assertNotNull(outputArray);
		   
	   }
	   
	   @Test
	    public void testGetSummaryCounts() throws BusinessException {
		   List<Object[]> results = new ArrayList<Object[]>();
		   Object[] obj = new Object[2];
		   BigDecimal st1 = BigDecimal.valueOf(1000000);
		   obj[0] = st1;
		   
		   BigDecimal st2 = BigDecimal.valueOf(1000000);
		   obj[1] = st2;
		   results.add(obj);
		   BigInteger counts = BigInteger.valueOf(10);
		   when(customQueryRepository.querySummaryCounts()).thenReturn(results);
		  when(customQueryRepository.queryMultipleActorCount()).thenReturn(counts);
		   JSONObject outputArray = dbQueryServiceImpl.getSummaryCounts();
		   assertNotNull(outputArray);
		   
	   }
	
	

}
