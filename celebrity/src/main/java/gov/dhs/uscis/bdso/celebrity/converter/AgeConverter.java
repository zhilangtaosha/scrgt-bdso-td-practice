package gov.dhs.uscis.bdso.celebrity.converter;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import com.github.dozermapper.core.DozerConverter;

public class AgeConverter extends DozerConverter<LocalDate, Integer> {
    private static final ZoneId ZONE_ID = ZoneId.of("America/New_York");

    public AgeConverter() {
        super(LocalDate.class, Integer.class);
    }

    @Override
    public Integer convertTo(LocalDate source, Integer destination) {
        if (source != null) {
            return Period.between(source, LocalDate.now(ZONE_ID)).getYears();
        }

        return null;
    }

    @Override
    public LocalDate convertFrom(Integer source, LocalDate destination) {
        if (source != null) {
            return LocalDate.now(ZONE_ID).minus(source, ChronoUnit.YEARS);
        }

        return null;
    }
}
