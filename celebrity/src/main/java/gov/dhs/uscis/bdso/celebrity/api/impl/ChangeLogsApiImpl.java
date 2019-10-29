/**
 * 
 */
package gov.dhs.uscis.bdso.celebrity.api.impl;

import java.util.List;
import javax.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gov.dhs.uscis.bdso.celebrity.api.ChangelogsApi;
import gov.dhs.uscis.bdso.celebrity.model.ChangeLog;
import gov.dhs.uscis.bdso.celebrity.service.ChangeLogService;

@RestController
@RequestMapping("${openapi.celebrity.base-path:/api/v1}")
public class ChangeLogsApiImpl implements ChangelogsApi {

    @Inject
    private ChangeLogService changeLogService;

    @Override
    public ResponseEntity<List<ChangeLog>> getChangeLogs() {
        return ResponseEntity.ok(changeLogService.getChangeLogs());
    }

    @Override
    public ResponseEntity<List<ChangeLog>> findCelebrityChangelogsById(Integer celebrityId) {
        return ResponseEntity.ok(changeLogService.getChangeLogsById(Integer.toString(celebrityId)));
    }

}
