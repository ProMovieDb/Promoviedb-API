package com.promoviedb.service;

import com.google.gson.JsonObject;
import com.promoviedb.config.ClientConfig;
import com.promoviedb.exception.ApiException;
import com.promoviedb.http.HttpClient;
import com.promoviedb.util.UrlBuilder;

/**
 * Service for credit-related API calls
 */
public class CreditService extends BaseService {

    public CreditService(HttpClient httpClient, ClientConfig config) {
        super(httpClient, config);
    }

    /**
     * Get a movie or TV credit details by ID
     *
     * @param creditId Credit ID
     * @return Credit details response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getDetails(String creditId) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/credit/" + creditId))
                .addQueryParam("api_key", config.getApiKey())
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }
}
