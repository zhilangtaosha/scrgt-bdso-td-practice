package gov.dhs.uscis.bdso.celebrity.api.impl;

import javax.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gov.dhs.uscis.bdso.celebrity.api.DatasourcesApi;
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.service.DataLoadService;

@RestController
@RequestMapping("${openapi.celebrity.base-path:/api/v1}")
public class DatasourceApiImpl implements DatasourcesApi {
    @Inject
    private DataLoadService service;

    @Override
    public ResponseEntity<String> loadDatasources() {
        try {
            service.triggerLoad();
            return ResponseEntity.ok("Success");
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Unable to load datasources");
        }
    }
}
