package gov.dhs.uscis.bdso.celebrity.service;

import org.json.JSONArray;
import org.json.JSONObject;

public interface QueryService {
	
	JSONArray getCelebritySalaryByYear();

	JSONArray getFilmsByGender();

	JSONArray getSalaryByGender();
	
	JSONArray getAnnualBoxOfficeTotals();
	
	JSONArray getFilmCountsByYear();
	
	JSONObject getSummaryCounts();

}
