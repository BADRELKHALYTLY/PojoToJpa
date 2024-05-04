package com.generic.jpa.config;

import static com.generic.jpa.config.ClassesExtractor.retrieveClasseNamesFrom;
import static com.generic.jpa.config.SwaggerComponentsInitializerService.retrieveComponents;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponses;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {

    public static final String PRODUCER_APP_PUBLISH_NOTIFICATION_ENDPOINT = "/producer-app/publishNotification/";

    @Bean
    public OpenAPI baseOpenAPI() {

        String packageName = "com.generic.jpa.pojo";
        List<String> classNames = retrieveClasseNamesFrom(packageName);

        return new OpenAPI().components(retrieveComponents()).info(new Info().title("Spring boot Quick Api").
                version("1.0.0").description("Doc Description"))
            .path(PRODUCER_APP_PUBLISH_NOTIFICATION_ENDPOINT + classNames.get(0), constructPathItem())
            .path(PRODUCER_APP_PUBLISH_NOTIFICATION_ENDPOINT + classNames.get(1), constructPathItem());
    }

    private static PathItem constructPathItem() {
        return new PathItem().post(
            new Operation()
                .requestBody(
                    new RequestBody().content(new Content().addMediaType("application/json",
                        new MediaType().schema(new io.swagger.v3.oas.models.media.Schema<>().type("object"))))
                )
                .responses(new ApiResponses())
        );
    }
}
