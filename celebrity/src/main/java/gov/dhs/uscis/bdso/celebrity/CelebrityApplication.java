package gov.dhs.uscis.bdso.celebrity;

import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.fasterxml.jackson.databind.Module;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class CelebrityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CelebrityApplication.class, args);
	}

    @Bean
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }
}
