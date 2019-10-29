package gov.dhs.uscis.bdso.celebrity.config;


import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.github.dozermapper.core.loader.api.FieldsMappingOptions;
import com.github.dozermapper.core.loader.api.TypeMappingOptions;
import gov.dhs.uscis.bdso.celebrity.converter.AgeConverter;
import gov.dhs.uscis.bdso.celebrity.converter.CelebrityActorsConverter;
import gov.dhs.uscis.bdso.celebrity.converter.CelebrityActorsGenreConverter;
import gov.dhs.uscis.bdso.celebrity.converter.ChangeLogConverter;
import gov.dhs.uscis.bdso.celebrity.converter.GenreConverter;
import gov.dhs.uscis.bdso.celebrity.converter.GenreListConverter;
import gov.dhs.uscis.bdso.celebrity.converter.MovieConverter;
import gov.dhs.uscis.bdso.celebrity.converter.OffsetDateTimeConverter;
import gov.dhs.uscis.bdso.celebrity.domain.Actors;
import gov.dhs.uscis.bdso.celebrity.domain.AuditLog;
import gov.dhs.uscis.bdso.celebrity.domain.Companies;
import gov.dhs.uscis.bdso.celebrity.domain.MovieCast;
import gov.dhs.uscis.bdso.celebrity.domain.MovieGenres;
import gov.dhs.uscis.bdso.celebrity.domain.Movies;
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;
import gov.dhs.uscis.bdso.celebrity.model.Cast;
import gov.dhs.uscis.bdso.celebrity.model.Celebrity;
import gov.dhs.uscis.bdso.celebrity.model.ChangeLog;
import gov.dhs.uscis.bdso.celebrity.model.Company;
import gov.dhs.uscis.bdso.celebrity.model.Genre;
import gov.dhs.uscis.bdso.celebrity.model.Movie;

@Configuration
public class MapperConfiguration {
	
    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final String MAP_ID_MINIMAL = "minimal";

    @Bean
    public Mapper mapper() throws BusinessException {
    	Mapper mapper = null;
        try {
        	mapper = DozerBeanMapperBuilder.create()
			        .withCustomConverters(
			                Arrays.asList(
                                    // new ActorsCelebrityConverter(),
			                        new CelebrityActorsConverter(),
			                        new GenreConverter(), 
			                        new GenreListConverter(),
                                    new MovieConverter(), 
                                    new ChangeLogConverter(),
                                    new OffsetDateTimeConverter(), 
                                    new AgeConverter(),
                                    new CelebrityActorsGenreConverter()
                                    ))
			        .withMappingBuilder(mapperBuilder()).build();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
            throw new BusinessException("Unable to initialize data mapper", e);
		}
        return mapper;
    }

    private BeanMappingBuilder mapperBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(Actors.class, Celebrity.class, TypeMappingOptions.oneWay(),
                        TypeMappingOptions.mapId(MAP_ID_MINIMAL), TypeMappingOptions.wildcard(false))
                        .fields("id", "id")
                        .fields("birthName", "birthName")
                        .fields("firstName", "firstName")
                        .fields("lastName", "lastName")
                        .fields("imageUrl", "imageUrl").fields("gender", "gender")
                        .fields("boxOffice", "totalSalary")
                        .fields("dob", "age", FieldsMappingOptions.customConverter(AgeConverter.class))
                        .fields("this", "this", FieldsMappingOptions.customConverter(CelebrityActorsGenreConverter.class));

                mapping(Actors.class, Celebrity.class, TypeMappingOptions.mapNull(false))
                        .fields("id", "id")
                        .fields("imdbId", "imdbId")
                        .fields("movdbId", "movdbId")
                        .fields("birthName", "birthName")
                        .fields("firstName", "firstName")
                        .fields("lastName", "lastName")
                        .fields("nickName", "nickName")
                        .fields("dob", "dob")
                        .fields("birthPlace", "birthPlace")
                        .fields("height", "height")
                        .fields("popularity", "popularity")
                        .fields("boxOffice", "totalBoxOffice")
                        .fields("avgRevenue", "averageBoxOffice")
                        .fields("biography", "biography")
                        .fields("numMovies", "filmography.numberOfMovies")
                        .fields("gender", "gender")
                        .fields("dob", "age", FieldsMappingOptions.customConverter(AgeConverter.class))
                        .fields("this", "this", FieldsMappingOptions.customConverter(CelebrityActorsConverter.class))
                    ;

                mapping(MovieCast.class, Cast.class, TypeMappingOptions.mapNull(false))
                        .fields("actorId", "celebrityId")
                        .fields("movieId", "movieId");

                mapping(AuditLog.class, ChangeLog.class, TypeMappingOptions.oneWay(), TypeMappingOptions.mapNull(false))
                        .fields("changeDate", "changeDate",
                                FieldsMappingOptions.customConverter(OffsetDateTimeConverter.class))
                        .fields("changes", "changes",
                                FieldsMappingOptions.customConverter(ChangeLogConverter.class));

                mapping(Companies.class, Company.class).fields("compName", "companyName");

                mapping(Movies.class, Movie.class)
                        .fields("origLanguage", "originalLanguage")
                        .fields("origTitle", "originalTitle")
                        .fields("releaseDate", "releaseDate")
                        .fields("genres", "genres", FieldsMappingOptions.customConverter(GenreListConverter.class))
                        .fields("this", "this", FieldsMappingOptions.customConverter(MovieConverter.class))
                ;

                mapping(MovieGenres.class, Genre.class)
                        .fields("this", "this", FieldsMappingOptions.customConverter(GenreConverter.class));
            }
        };
    }
}
