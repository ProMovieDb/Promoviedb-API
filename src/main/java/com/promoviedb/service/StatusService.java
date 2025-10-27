package com.promoviedb.service;

import com.promoviedb.config.ClientConfig;
import com.promoviedb.exception.ApiException;
import com.promoviedb.http.HttpClient;
import com.promoviedb.util.UrlBuilder;

/**
 * Service for status and monitoring API calls
 */
public class StatusService extends BaseService {

    public StatusService(HttpClient httpClient, ClientConfig config) {
        super(httpClient, config);
    }

    /**
     * Ping endpoint to check API availability
     *
     * @return Response string
     * @throws ApiException if the request fails
     */
    public String ping() throws ApiException {
        String url = UrlBuilder.create(buildUrl("api/openApi/ping/v1"))
                .build();

        return httpClient.get(url);
    }

    /**
     * Check if API is healthy
     *
     * @return true if API is accessible
     */
    public boolean isHealthy() {
        try {
            ping();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
