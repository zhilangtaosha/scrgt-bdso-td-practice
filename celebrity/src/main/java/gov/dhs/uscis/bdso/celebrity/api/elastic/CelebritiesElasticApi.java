package gov.dhs.uscis.bdso.celebrity.api.elastic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.dhs.uscis.bdso.celebrity.domain.elastic.ImdbActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.MoviedbActorDocument;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.WikiActorDocument;
import gov.dhs.uscis.bdso.celebrity.service.ImdbService;
import gov.dhs.uscis.bdso.celebrity.service.MoviedbService;
import gov.dhs.uscis.bdso.celebrity.service.WikipediaService;

@RestController
@RequestMapping("${openapi.celebrity.base-path:/api/v1/celebrities/elastic}")
public class CelebritiesElasticApi {
	
    private final Logger logger = LoggerFactory.getLogger(CelebritiesElasticApi.class);

    @Autowired
    private ImdbService imdbService;
    
    @Autowired
    private MoviedbService moviedbActorService;
    
    @Autowired
    private WikipediaService wikipediaService;
    
    @GetMapping("/actor/imdb/{id}")
    public ResponseEntity<ImdbActorDocument> getImdbCelebrity(@PathVariable String id) {
    	HttpHeaders headers = new HttpHeaders();
        try {
        	ImdbActorDocument actor = imdbService.getActorInfo(id);
        	return new ResponseEntity<>(actor, headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/actor/imdb/add")
    public ResponseEntity<ImdbActorDocument> addCelebrity(@RequestBody ImdbActorDocument dto) {
    	HttpHeaders headers = new HttpHeaders();
        try {
			dto = imdbService.addActor(dto);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(dto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
		}
    }
    
    @GetMapping("/actor/moviedb/{id}")
    public ResponseEntity<MoviedbActorDocument> getMoviedbCelebrity(@PathVariable String id) {
    	HttpHeaders headers = new HttpHeaders();
        try {
        	MoviedbActorDocument actor = moviedbActorService.getActorInfo(id);
        	return new ResponseEntity<>(actor, headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/actor/moviedb/add")
    public ResponseEntity<MoviedbActorDocument> addCelebrity(@RequestBody MoviedbActorDocument dto) {
    	HttpHeaders headers = new HttpHeaders();
        try {
			dto = moviedbActorService.addActor(dto);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(dto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
		}
    }
    
    @GetMapping("/actor/wikipedia/{id}")
    public ResponseEntity<WikiActorDocument> getWikipediaCelebrity(@PathVariable String id) {
    	HttpHeaders headers = new HttpHeaders();
        try {
        	WikiActorDocument actor = wikipediaService.getActorInfo(id);
        	return new ResponseEntity<>(actor, headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/actor/wikipedia/add")
    public ResponseEntity<WikiActorDocument> addCelebrity(@RequestBody WikiActorDocument dto) {
    	HttpHeaders headers = new HttpHeaders();
        try {
			dto = wikipediaService.addActor(dto);
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(dto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
		}
    }
}
