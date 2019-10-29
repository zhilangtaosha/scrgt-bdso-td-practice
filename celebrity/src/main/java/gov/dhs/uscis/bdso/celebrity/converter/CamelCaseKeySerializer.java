package gov.dhs.uscis.bdso.celebrity.converter;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.google.common.collect.ImmutableList;

// @JsonComponent
public class CamelCaseKeySerializer extends KeyDeserializer {
    private static final ImmutableList<String> KEYS_TO_OMIT = ImmutableList.of("_actorID", "_id");

    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        if (KEYS_TO_OMIT.contains(key)) {
            return key;
        }

        String[] tokens = StringUtils.strip(key, "_").split("\\s|\\_");
        String retVal = null;

        if (tokens.length > 1) {
            retVal = Arrays.stream(StringUtils.strip(key, "_").split("\\s|\\_")).filter(StringUtils::isNotEmpty)
                    .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase())
                    .collect(Collectors.joining());
        } else {
            retVal = tokens[0];
        }

        retVal = Character.toLowerCase(retVal.charAt(0)) + retVal.substring(1);
        return retVal;
    }

}
