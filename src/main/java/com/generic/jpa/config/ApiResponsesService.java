package com.generic.jpa.config;

import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import java.util.HashMap;
import java.util.Map;

public final class ApiResponsesService {
    private static final String BAD_REQUEST = "\"Bad Request\"";
    private static final int HTTP_BAD_REQUEST_STATUS = 400;
    private static final int HTTP_INTERNAL_SERVER_ERROR_STATUS = 500;
    private static final String INTERNAL_SERVER_ERROR = "\"Internal Server Error\"";
    private static final String DEFAULT_KEY = "default";
    private static final String CUSTOM_RESPONSE_CONTENT = "{\"name\":\"string\",\"surname\":\"string\",\"age\":0}";

    public static Map<String, ApiResponse> initApiResponses() {
        Map<String, ApiResponse> apiResponses = new HashMap<>();
        apiResponses.put("badRequest", new ApiResponse().content(constructRequestContent(constructResponseMessage(HTTP_BAD_REQUEST_STATUS, BAD_REQUEST))));
        apiResponses.put("internalServerError", new ApiResponse().content(constructRequestContent(constructResponseMessage(HTTP_INTERNAL_SERVER_ERROR_STATUS, INTERNAL_SERVER_ERROR))));
        apiResponses.put("successfulResponse", new ApiResponse().content(constructRequestContent(CUSTOM_RESPONSE_CONTENT)));
        return apiResponses;
    }

    private static Content constructRequestContent(String messageContent) {
        return new Content().addMediaType("application/json",
            new MediaType().addExamples(DEFAULT_KEY,
                new Example().value(messageContent)));
    }

    private static String constructResponseMessage(int httpStatus, String responseErrorMessage) {
        return "{\"code\" : " + httpStatus + ", \"status\" : " + responseErrorMessage + ", \"Message\"" + " : " + responseErrorMessage + "}";
    }

    private ApiResponsesService() {}
}
