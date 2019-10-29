package gov.dhs.uscis.bdso.celebrity.service.impl;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gov.dhs.uscis.bdso.celebrity.repository.CustomQueryRepository;
import gov.dhs.uscis.bdso.celebrity.service.QueryService;

@Service(value="dbQueryService")
public class DBQueryServiceImpl implements QueryService {

	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
    @Autowired
    private CustomQueryRepository customQueryRepository;
    
    private static final String MOVIECOUNT ="movieCount";
    private static final String YEAR ="year";
    private static final String GENDER ="gender";
    private static final String SALARY ="salary";
    private static final String ERROR = "Error retrieving data";
    
	@Override
	public JSONArray getCelebritySalaryByYear() {
		List<Object[]> results = customQueryRepository.querySalaryCounts();
		JSONArray data = new JSONArray();
		try {
			for (Object[] objArray : results) {
				JSONObject obj = new JSONObject();
				obj.put(YEAR, ((String)objArray[0]).trim());
				obj.put(SALARY, ((BigDecimal)objArray[1]).longValue());
				data.put(obj);
			}
		}
		catch(JSONException e) {
			LOG.error(ERROR, e);
		}
		
		return data;
	}

	@Override
	public JSONArray getFilmsByGender() {
		List<Object[]> results = customQueryRepository.queryFilmsByGender();
		JSONArray data = new JSONArray();
		try {
			for (Object[] objArray : results) {
				JSONObject obj = new JSONObject();
				obj.put(GENDER, ((String)objArray[0]).trim());
				obj.put(YEAR, ((String)objArray[1]).trim());
				obj.put(MOVIECOUNT, ((BigInteger)objArray[2]).longValue());
				data.put(obj);
			}
		}
		catch(JSONException e) {
			LOG.error(ERROR, e);
		}
		
		return data;
	}

	@Override
	public JSONArray getSalaryByGender() {
		List<Object[]> results = customQueryRepository.querySalaryByGender();
		JSONArray data = new JSONArray();
		try {
			for (Object[] objArray : results) {
				JSONObject obj = new JSONObject();
				obj.put(GENDER, ((String)objArray[0]).trim());
				obj.put(YEAR, ((String)objArray[1]).trim());
                obj.put(SALARY, ((BigDecimal) objArray[2]).longValue());
				data.put(obj);
			}
		}
		catch(JSONException e) {
			LOG.error(ERROR, e);
		}
		
		return data;
	}

	@Override
	public JSONArray getAnnualBoxOfficeTotals() {
		List<Object[]> results = customQueryRepository.queryAnnualBoxOfficeTotals();
		JSONArray data = new JSONArray();
		try {
			for (Object[] objArray : results) {
				JSONObject obj = new JSONObject();
				obj.put(YEAR, ((String)objArray[0]).trim());
				obj.put("boxOffice", ((BigDecimal)objArray[1]).longValue());
				obj.put(MOVIECOUNT, ((BigInteger)objArray[2]).longValue());
				data.put(obj);
			}
		}
		catch(JSONException e) {
			LOG.error(ERROR, e);
		}
		
		return data;
	}

	@Override
	public JSONArray getFilmCountsByYear() {
		List<Object[]> results = customQueryRepository.queryFilmCounts();
		JSONArray data = new JSONArray();
		try {
			for (Object[] objArray : results) {
				JSONObject obj = new JSONObject();
				obj.put(YEAR, ((String)objArray[0]).trim());
				obj.put(MOVIECOUNT, ((BigInteger)objArray[1]).longValue());
				data.put(obj);
			}
		}
		catch(JSONException e) {
			LOG.error(ERROR, e);
		}
		
		return data;
	}

	@Override
	public JSONObject getSummaryCounts() {
		List<Object[]> summaryResults = customQueryRepository.querySummaryCounts();
		BigInteger counts = customQueryRepository.queryMultipleActorCount();
		JSONObject data = new JSONObject();
		try {
			Object[] objArray = summaryResults.get(0);
			
			data.put(MOVIECOUNT, ((BigDecimal)objArray[0]).longValue());
			data.put("totalBoxOffice", ((BigDecimal)objArray[1]).longValue());
			data.put("multipleActorCount", counts.longValue());
		}
		catch(JSONException e) {
			LOG.error(ERROR, e);
		}
		
		return data;
	}

}
