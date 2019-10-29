/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.1.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package gov.dhs.uscis.bdso.celebrity.api;

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
@Api(value = "datasources", description = "the datasources API")
public interface DatasourcesApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "Loads celebrities from datasources.", nickname = "loadDatasources", notes = "Loads celebrities from a list of datasources", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Datasources successfully loaded.", response = String.class),
        @ApiResponse(code = 500, message = "An unexpected error occured."),
        @ApiResponse(code = 400, message = "Unable to load datasources") })
    @RequestMapping(value = "/datasources/load",
        produces = { "text/plain" }, 
        method = RequestMethod.POST)
    default ResponseEntity<String> loadDatasources() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
