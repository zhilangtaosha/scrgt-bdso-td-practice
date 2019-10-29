package gov.dhs.uscis.bdso.celebrity.converter;

import org.apache.commons.lang3.StringUtils;
import com.github.dozermapper.core.DozerConverter;
import gov.dhs.uscis.bdso.celebrity.domain.MovieGenres;
import gov.dhs.uscis.bdso.celebrity.model.Genre;

public class GenreConverter extends DozerConverter<MovieGenres, Genre> {

    public GenreConverter() {
        super(MovieGenres.class, Genre.class);
    }

    @Override
    public Genre convertTo(MovieGenres source, Genre destination) {
        if (source != null && StringUtils.isNotEmpty(source.getGenre())) {
            return Genre.fromValue(source.getGenre());
        }
        return null;
    }

    @Override
    public MovieGenres convertFrom(Genre source, MovieGenres destination) {
        MovieGenres genre = new MovieGenres();
        genre.setGenre(source.toString());
        return genre;
    }

}
