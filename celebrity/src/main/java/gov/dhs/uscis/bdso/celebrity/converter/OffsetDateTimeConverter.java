package gov.dhs.uscis.bdso.celebrity.converter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import com.github.dozermapper.core.DozerConverter;

public class OffsetDateTimeConverter extends DozerConverter<LocalDateTime, OffsetDateTime> {
    private static final ZoneId ZONE_ID = ZoneId.of("America/New_York");

    public OffsetDateTimeConverter() {
        super(LocalDateTime.class, OffsetDateTime.class);
    }

    @Override
    public OffsetDateTime convertTo(LocalDateTime source, OffsetDateTime destination) {
        if (source != null) {
            return source.atZone(ZONE_ID).toOffsetDateTime();
        }

        return null;
    }

    @Override
    public LocalDateTime convertFrom(OffsetDateTime source, LocalDateTime destination) {
        // One way mapping
        return null;
    }
}
