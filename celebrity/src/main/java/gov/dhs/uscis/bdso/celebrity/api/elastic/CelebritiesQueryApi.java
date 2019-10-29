package gov.dhs.uscis.bdso.celebrity.api.elastic;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gov.dhs.uscis.bdso.celebrity.model.Celebrity;
import gov.dhs.uscis.bdso.celebrity.service.CelebrityService;
import gov.dhs.uscis.bdso.celebrity.service.QueryService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("${openapi.celebrity.base-path:/api/v1/celebrities/query}")
public class CelebritiesQueryApi {
	
    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private QueryService dbQueryService;
    
    @Autowired
    private CelebrityService celebrityService;
    
    @GetMapping("/filmsByGender")
    public ResponseEntity<String> getFilmsByGenderData() {
    	HttpHeaders headers = new HttpHeaders();
        try {
        	JSONArray data = dbQueryService.getFilmsByGender();
        	return new ResponseEntity<>(data.toString(), headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/salaryByGender")
    public ResponseEntity<String> getSalaryByGenderData() {
    	HttpHeaders headers = new HttpHeaders();
        try {
        	JSONArray data = dbQueryService.getSalaryByGender();
        	return new ResponseEntity<>(data.toString(), headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/annualTotals")
    public ResponseEntity<String> getAnnualBoxOfficeTotals() {
    	HttpHeaders headers = new HttpHeaders();
        try {
        	JSONArray data = dbQueryService.getAnnualBoxOfficeTotals();
        	return new ResponseEntity<>(data.toString(), headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/filmsByYear")
    public ResponseEntity<String> getFilmCountsByYear() {
    	HttpHeaders headers = new HttpHeaders();
        try {
        	JSONArray data = dbQueryService.getFilmCountsByYear();
        	return new ResponseEntity<>(data.toString(), headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/salaryByYear")
    public ResponseEntity<String> getSalaryCountsByYear() {
    	HttpHeaders headers = new HttpHeaders();
        try {
        	JSONArray data = dbQueryService.getCelebritySalaryByYear();
        	return new ResponseEntity<>(data.toString(), headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/summaryCounts")
    public ResponseEntity<String> getSummaryCounts() {
    	HttpHeaders headers = new HttpHeaders();
        try {
        	JSONObject data = dbQueryService.getSummaryCounts();
        	return new ResponseEntity<>(data.toString(), headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/byActorIds")
    public ResponseEntity<List<Celebrity>> getCelebrities(@RequestParam(value="actors") String[] actorIdArray) {
    	HttpHeaders headers = new HttpHeaders();
        try {
        	List<Celebrity> data = celebrityService.getCelebritiesByActorIds(actorIdArray);
        	return new ResponseEntity<>(data, headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/byIds")
    public ResponseEntity<String> getCelebrities(
    			@RequestParam(value="actors") String[] actorIdArray,
    			@RequestParam(value="movies") String[] movieIdArray) {
    	HttpHeaders headers = new HttpHeaders();
        try {
        	JSONArray data = celebrityService.getCelebritiesByActorsAndMovies(actorIdArray, movieIdArray);
        	return new ResponseEntity<>(data.toString(), headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }
 
}
