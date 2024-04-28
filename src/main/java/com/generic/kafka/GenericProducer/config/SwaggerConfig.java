package com.generic.kafka.GenericProducer.config;

import com.generic.kafka.GenericProducer.pojo.Address;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@OpenAPIDefinition
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI baseOpenAPI(){

        Address adrs = new Address();


        ApiResponse badRequest = new ApiResponse().content(
                new Content().addMediaType("application/json",
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"code\" : 400, \"status\" : \"Bad Request\", \"Message\" : \"Bad Request\"}"))));
        ApiResponse internalServerError = new ApiResponse().content(
                new Content().addMediaType("application/json",
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"code\" : 500, \"status\" : \"internalServerError\", \"Message\" : \"internalServerError\"}"))));
        ApiResponse successfulResponse = new ApiResponse().content(
                new Content().addMediaType("application/json",
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"name\":\"string\",\"surname\":\"string\",\"age\":0}"))));

        Components components = new Components();
        components.addResponses("badRequest",badRequest);
        components.addResponses("internalServerError",internalServerError);
        components.addResponses("successfulResponse",successfulResponse);

        String packageName= "com.generic.kafka.GenericProducer.pojo";
        List<String> classNames = new ArrayList<>();
        try {
            classNames = getClassNames(packageName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return new OpenAPI().components(components).info(new Info().title("Springboot_Swagger Project OpenAPI Docs").
                version("1.0.0").description("Doc Description"))
                .path("/producer-app/publishNotification/"+classNames.get(0) , new PathItem().post(
                        new Operation().requestBody(
                                        new RequestBody().content(new Content().addMediaType("application/json",
                                                new MediaType().schema(new io.swagger.v3.oas.models.media.Schema<>().type("object"))))
                                )
                                .responses(new ApiResponses() )
                ))
                .path("/producer-app/publishNotification/"+classNames.get(1) , new PathItem().post(
                        new Operation().requestBody(
                                        new RequestBody().content(new Content().addMediaType("application/json",
                                                new MediaType().schema(new io.swagger.v3.oas.models.media.Schema<>().type("object"))))
                                )
                                .responses(new ApiResponses() )
                ));
    }

    public   List<String> getClassNames(String packageName) throws ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        List<String> classNames = new ArrayList<>();

        try (Stream<Path> paths = java.nio.file.Files.walk(java.nio.file.Paths.get(classLoader.getResource(path).toURI()))) {
            classNames = paths
                    .filter(java.nio.file.Files::isRegularFile)
                    .map(p -> packageName + "." + p.getFileName().toString().replace(".class", ""))
                    .filter(className -> {
                        try {
                            return !Modifier.isAbstract(Class.forName(className).getModifiers());
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                            return false;
                        }
                    })
                    .map(p -> p.replace(packageName+".",""))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return classNames;
    }


}
