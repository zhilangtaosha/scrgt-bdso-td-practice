package gov.dhs.uscis.bdso.celebrity.converter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.github.dozermapper.core.DozerConverter;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.MapperAware;
import gov.dhs.uscis.bdso.celebrity.domain.Actors;
import gov.dhs.uscis.bdso.celebrity.domain.GenderEnum;
import gov.dhs.uscis.bdso.celebrity.domain.MovieCast;
import gov.dhs.uscis.bdso.celebrity.domain.Movies;
import gov.dhs.uscis.bdso.celebrity.model.Cast;
import gov.dhs.uscis.bdso.celebrity.model.Celebrity;
import gov.dhs.uscis.bdso.celebrity.model.Filmography;
import gov.dhs.uscis.bdso.celebrity.model.Gender;
import gov.dhs.uscis.bdso.celebrity.model.Movie;
import gov.dhs.uscis.bdso.celebrity.model.MovieCount;

public class CelebrityActorsConverter extends DozerConverter<Actors, Celebrity> implements MapperAware {
    private Mapper mapper;

    public CelebrityActorsConverter() {
        super(Actors.class, Celebrity.class);
    }

    @Override
    public Celebrity convertTo(Actors source, Celebrity destination) {
        if (source.getMovieCast() != null && !source.getMovieCast().isEmpty()) {
            List<Movie> movieList = new ArrayList<>();
            Map<String, Integer> listOfMoviesByYearMap = new HashMap<>();
            List<MovieCount>listOfMoviesByYear = new ArrayList<>();

            for (MovieCast movieCast : source.getMovieCast()) {
                Movies movies = movieCast.getMovies();
                Movie movie = mapper.map(movies, Movie.class);
                movie.setReleaseDate(movies.getReleaseDate());
                movieList.add(movie);

                LocalDate releaseDate = movie.getReleaseDate();
                
                if (releaseDate != null) {
                    String year = Integer.toString(releaseDate.getYear());
                    Integer movieCount = listOfMoviesByYearMap.get(year);

                    if (movieCount == null) {
                        movieCount = 0;
                        listOfMoviesByYearMap.put(year, movieCount);
                    }

                    movieCount = movieCount + 1;
                    listOfMoviesByYearMap.put(year, movieCount);
                }
            }
            
            listOfMoviesByYearMap.forEach(
                    (k, v) -> listOfMoviesByYear.add(new MovieCount().year(Integer.parseInt(k)).movieCount(v)));



            if (destination.getFilmography() == null) {
                destination.setFilmography(new Filmography());
            }
            destination.getFilmography().setListOfMoviesByYear(listOfMoviesByYear);
            destination.getFilmography().setMovies(movieList);
        }

        return destination;
    }

    @Override
    public Actors convertFrom(Celebrity source, Actors destination) {
        if (destination == null) {
            destination = new Actors();
        }

        if (source != null) {
            Filmography filmography = source.getFilmography();
            destination.setAvgRevenue(getLongValue(source.getAverageBoxOffice()));
            destination.setBiography(source.getBiography());
            destination.setBirthName(source.getBirthName());
            destination.setBirthPlace(source.getBirthPlace());
            destination.setBoxOffice(getLongValue(source.getTotalBoxOffice()));
            destination.setFirstName(source.getFirstName());

            Gender gender = source.getGender();

            if (gender != null) {
                if (Gender.MALE == gender) {
                    destination.setGender(GenderEnum.MALE);
                } else {
                    destination.setGender(GenderEnum.FEMALE);
                }
            }

            destination.setHeight(source.getHeight());
            destination.setImdbId(source.getImdbId());
            destination.setMovdbId(source.getMovdbId());
            destination.setLastName(source.getLastName());
            destination.setNickName(source.getNickName());
            destination.setNumMovies(filmography != null ? filmography.getNumberOfMovies() : null);
            destination.setPopularity(source.getPopularity());

            Set<MovieCast> movieCasts = destination.getMovieCast();
            if (movieCasts == null) {
                movieCasts = new HashSet<>();
                destination.setMovieCast(movieCasts);
            }

            mapFilmography(destination, filmography, movieCasts);
        }

        return destination;
    }

    @Override
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    private void mapFilmography(Actors destination, Filmography filmography, Set<MovieCast> movieCasts) {
        if (filmography != null) {
            List<Movie> movList = filmography.getMovies();

            if (movList != null) {
                for (Movie movie : movList) {
                    if (movie != null && movie.getCast() != null) {
                        for (Cast cast : movie.getCast()) {
                            if (destination.getId().equals(cast.getCelebrityId())) {
                                mapMovieCast(movieCasts, cast);
                            }
                        }
                    }
                }
            }
        }
    }

    private Long getLongValue(BigDecimal value) {
        if (value != null) {
           return value.longValue();
        }

        return null;
    }

    private void mapMovieCast(Set<MovieCast> movieCasts, Cast cast) {
        boolean found = false;

        if (movieCasts != null && cast != null) {
                for (MovieCast movieCast : movieCasts) {
                    if (isExisting(movieCast, cast)) {
                        movieCast.setId(cast.getId());
                        movieCast.setActorId(cast.getCelebrityId());
                        movieCast.setMovieId(cast.getMovieId());
                        found = true;
                    }
                }

                if (!found) {
                    MovieCast ct = new MovieCast();
                    ct.setId(cast.getId());
                    ct.setActorId(cast.getCelebrityId());
                    ct.setMovieId(cast.getMovieId());
                    movieCasts.add(ct);
                }
        }
    }

    private boolean isExisting(MovieCast movieCast, Cast cast) {
        return cast.getId() != null && cast.getId().equals(movieCast.getId())
                || cast.getCelebrityId() != null && cast.getCelebrityId().equals(movieCast.getActorId())
                        && cast.getMovieId() != null && cast.getMovieId().equals(movieCast.getMovieId());
    }
}
