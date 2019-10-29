package gov.dhs.uscis.bdso.celebrity.converter;

import com.github.dozermapper.core.DozerConverter;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.MapperAware;
import gov.dhs.uscis.bdso.celebrity.domain.Actors;
import gov.dhs.uscis.bdso.celebrity.domain.MovieCast;
import gov.dhs.uscis.bdso.celebrity.domain.MovieGenres;
import gov.dhs.uscis.bdso.celebrity.domain.Movies;
import gov.dhs.uscis.bdso.celebrity.model.Movie;

public class MovieConverter extends DozerConverter<Movies, Movie> implements MapperAware {
    private Mapper mapper;

    public MovieConverter() {
        super(Movies.class, Movie.class);
    }

    @Override
    public Movie convertTo(Movies source, Movie destination) {
        return convertToMovie(mapper, source);
        
    }

    @Override
    public Movies convertFrom(Movie source, Movies destination) {
        return convertFromMovies(mapper, source, null);
    }

    public static Movies convertFromMovies(Mapper mapper, Movie source, Actors actors) {
        Movies movie = mapper.map(source, Movies.class);

        if (movie != null) {

            for (MovieGenres movieGenres : movie.getGenres()) {
                movieGenres.setMovies(movie);
            }

            for (MovieCast movieCast : movie.getCast()) {
                movieCast.setMovieId(movie.getId());
                movieCast.setActorId(actors.getId());
            }

            movie.setGenres(movie.getGenres());

            if (actors != null) {
                actors.setMovieCast(movie.getCast());
            }
        }

        return movie;
    }

   public static Movie convertToMovie(Mapper mapper, Movies source) {
        return mapper.map(source, Movie.class);

    }

    @Override
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }
}
