package gov.dhs.uscis.bdso.celebrity.repository;

import java.math.BigInteger;
import java.util.List;

public interface CustomQueryRepository {

	List<Object[]> queryFilmsByGender();

	List<Object[]> querySalaryByGender();
	
	List<Object[]> queryAnnualBoxOfficeTotals();
	
	List<Object[]> queryFilmCounts();
	
	List<Object[]> querySalaryCounts();
	
	List<Object[]> querySummaryCounts();
	
	BigInteger queryMultipleActorCount();
}
