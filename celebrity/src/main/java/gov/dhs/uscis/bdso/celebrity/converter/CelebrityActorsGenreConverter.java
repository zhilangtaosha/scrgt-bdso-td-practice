package gov.dhs.uscis.bdso.celebrity.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.github.dozermapper.core.DozerConverter;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.MapperAware;
import gov.dhs.uscis.bdso.celebrity.domain.Actors;
import gov.dhs.uscis.bdso.celebrity.domain.MovieCast;
import gov.dhs.uscis.bdso.celebrity.domain.Movies;
import gov.dhs.uscis.bdso.celebrity.model.Celebrity;
import gov.dhs.uscis.bdso.celebrity.model.Genre;
import gov.dhs.uscis.bdso.celebrity.model.Movie;

public class CelebrityActorsGenreConverter extends DozerConverter<Actors, Celebrity> implements MapperAware {
    private Mapper mapper;

    public CelebrityActorsGenreConverter() {
        super(Actors.class, Celebrity.class);
    }

    @Override
    public Celebrity convertTo(Actors source, Celebrity destination) {
        List<Genre> genreList = new ArrayList<>();
        List<Movie> movieList = new ArrayList<>();

        if (source.getMovieCast() != null && !source.getMovieCast().isEmpty()) {
            for (MovieCast movieCast : source.getMovieCast()) {
                Movies movies = movieCast.getMovies();
                Movie movie = mapper.map(movies, Movie.class);
                movieList.add(movie);
                if (movie.getGenres() != null && movie.getGenres().size() > 0) {
                    genreList.addAll(movie.getGenres());
                }
            }
        }

        if (movieList.size() > 0 && destination.getTotalSalary() != null) {
            destination.setAverageSalary(new BigDecimal(destination.getTotalSalary().longValue() / movieList.size()));
        }

        destination.setGenres(genreList);
        return destination;
    }

    @Override
    public Actors convertFrom(Celebrity source, Actors destination) {
        return null;
    }

    @Override
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }
}
