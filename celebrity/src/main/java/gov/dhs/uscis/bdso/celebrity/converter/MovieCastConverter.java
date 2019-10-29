package gov.dhs.uscis.bdso.celebrity.converter;

import com.github.dozermapper.core.DozerConverter;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.MapperAware;

import gov.dhs.uscis.bdso.celebrity.domain.MovieCast;
import gov.dhs.uscis.bdso.celebrity.model.Cast;

public class MovieCastConverter extends DozerConverter<MovieCast, Cast> implements MapperAware {
    private Mapper mapper;

    public MovieCastConverter() {
        super(MovieCast.class, Cast.class);
    }

    @Override
    public Cast convertTo(MovieCast source, Cast destination) {
        return convertToCast(mapper, source);
    }

    @Override
    public MovieCast convertFrom(Cast source, MovieCast destination) {
        return convertFromCast(mapper, source);
    }

    public static MovieCast convertFromCast(Mapper mapper, Cast source) {
        return mapper.map(source, MovieCast.class);

    }

   public static Cast convertToCast(Mapper mapper, MovieCast source) {
        return mapper.map(source, Cast.class);

    }

    @Override
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }
}
