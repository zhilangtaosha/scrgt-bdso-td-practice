package gov.dhs.uscis.bdso.celebrity.api.impl;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gov.dhs.uscis.bdso.celebrity.api.CelebritiesApi;
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.model.Celebrity;
import gov.dhs.uscis.bdso.celebrity.service.CelebrityService;

@RestController
@RequestMapping("${openapi.celebrity.base-path:/api/v1}")
public class CelebritiesApiImpl implements CelebritiesApi {
	
	@Autowired
	private CelebrityService celebrityService;

    @Override
    public ResponseEntity<Celebrity> updateCelebrity(@Valid Celebrity celebrity) {
        try {
            Celebrity response = celebrityService.updateCelebrity(celebrity);
            return ResponseEntity.ok(response);
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<Celebrity>> getCelebrities() {
        return ResponseEntity.ok(celebrityService.getAllCelebrities());
    }

    @Override
    public ResponseEntity<Celebrity> findCelebrityById(Integer celebrityId) {
        return ResponseEntity.ok(celebrityService.getCelebrityInfo(celebrityId));
    }
}
