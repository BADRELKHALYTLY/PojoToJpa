package com.generic.jpa.config;

import static com.generic.jpa.config.ApiResponsesService.initApiResponses;

import io.swagger.v3.oas.models.Components;

public final class SwaggerComponentsInitializerService {

    public static Components retrieveComponents() {
        Components components = new Components();
        initApiResponses().entrySet().stream()
            .forEach(entry -> components.addResponses(entry.getKey(), entry.getValue()));
        return components;
    }

    private SwaggerComponentsInitializerService() {
    }
}
