/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.1.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package gov.dhs.uscis.bdso.celebrity.api;

import gov.dhs.uscis.bdso.celebrity.model.ChangeLog;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Validated
@Api(value = "changelogs", description = "the changelogs API")
public interface ChangelogsApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "Get change logs for celebrity", nickname = "findCelebrityChangelogsById", notes = "Returns a celebrity's changelogs by id.", response = ChangeLog.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns celebrity change log information", response = ChangeLog.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "An unexpected error occured.") })
    @RequestMapping(value = "/changelogs/{celebrityId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<ChangeLog>> findCelebrityChangelogsById(@ApiParam(value = "Celebrity Id",required=true) @PathVariable("celebrityId") Integer celebrityId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "{  \"fieldName\" : \"fieldName\",  \"changedBy\" : \"changedBy\",  \"changeDate\" : \"2000-01-23T04:56:07.000+00:00\",  \"changes\" : {    \"newValue\" : \"newValue\",    \"oldValue\" : \"oldValue\"  },  \"tableId\" : \"tableId\",  \"tableName\" : \"tableName\"}");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Retrieve all changelogs.", nickname = "getChangeLogs", notes = "Retrieve all data changelogs.", response = ChangeLog.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of data changelogs.", response = ChangeLog.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "Resource was not found"),
        @ApiResponse(code = 500, message = "An unexpected error occured.") })
    @RequestMapping(value = "/changelogs",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<ChangeLog>> getChangeLogs() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "{  \"fieldName\" : \"fieldName\",  \"changedBy\" : \"changedBy\",  \"changeDate\" : \"2000-01-23T04:56:07.000+00:00\",  \"changes\" : {    \"newValue\" : \"newValue\",    \"oldValue\" : \"oldValue\"  },  \"tableId\" : \"tableId\",  \"tableName\" : \"tableName\"}");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
