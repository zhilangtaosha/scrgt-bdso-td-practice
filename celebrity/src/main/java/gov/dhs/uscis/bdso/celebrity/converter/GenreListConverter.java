package gov.dhs.uscis.bdso.celebrity.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.github.dozermapper.core.DozerConverter;
import gov.dhs.uscis.bdso.celebrity.domain.MovieGenres;
import gov.dhs.uscis.bdso.celebrity.model.Genre;

public class GenreListConverter extends DozerConverter<Set, List> {

    public GenreListConverter() {
        super(Set.class, List.class);
    }

    @Override
    public List convertTo(Set source, List destination) {
        List<Genre> genres = new ArrayList<>();

        for (Object gen : source) {
            if (gen != null) {
                MovieGenres movieGenre = (MovieGenres) gen;
                genres.add(Genre.fromValue(movieGenre.getGenre()));
            }
        }

        return genres;
    }

    @Override
    public Set convertFrom(List source, Set destination) {
        Set<MovieGenres> movieGenres = new HashSet();

        if (source == null) return movieGenres;
        
        for (Object gen : source) {
            if (gen != null) {
                Genre genreObj = (Genre) gen;
                MovieGenres genre = new MovieGenres();
                genre.setGenre(genreObj.toString());
                movieGenres.add(genre);
            }
        }

        return movieGenres;
    }


}
