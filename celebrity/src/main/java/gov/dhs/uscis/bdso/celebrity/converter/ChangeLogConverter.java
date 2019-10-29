package gov.dhs.uscis.bdso.celebrity.converter;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dozermapper.core.DozerConverter;
import gov.dhs.uscis.bdso.celebrity.model.Changes;

public class ChangeLogConverter extends DozerConverter<String, Changes> {
    private final Logger logger = LoggerFactory.getLogger(ChangeLogConverter.class);

    public ChangeLogConverter() {
        super(String.class, Changes.class);
    }

    @Override
    public Changes convertTo(String source, Changes destination) {
        try {
            if (source != null) {
                ObjectMapper mapper = new ObjectMapper();
                destination = mapper.reader().forType(Changes.class).readValue(source);
            }
        } catch (IOException e) {
        	logger.error("Unable to parse JSON string", e);
        }

        return destination;
    }

    @Override
    public String convertFrom(Changes source, String destination) {
    	logger.error("no need this method, this is one way mapping");
        // One way mapping
        return null;
    }
}
