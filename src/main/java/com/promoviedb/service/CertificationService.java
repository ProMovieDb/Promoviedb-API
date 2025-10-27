package com.promoviedb.service;

import com.google.gson.JsonObject;
import com.promoviedb.config.ClientConfig;
import com.promoviedb.exception.ApiException;
import com.promoviedb.http.HttpClient;
import com.promoviedb.util.UrlBuilder;

/**
 * Service for certification-related API calls
 */
public class CertificationService extends BaseService {

    public CertificationService(HttpClient httpClient, ClientConfig config) {
        super(httpClient, config);
    }

    /**
     * Get an up to date list of the officially supported movie certifications
     *
     * @return Movie certifications response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getMovieCertifications() throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/certification/movie/list"))
                .addQueryParam("api_key", config.getApiKey())
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }

    /**
     * Get an up to date list of the officially supported TV certifications
     *
     * @return TV certifications response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getTvCertifications() throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/certification/tv/list"))
                .addQueryParam("api_key", config.getApiKey())
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }
}
