package com.promoviedb.service;

import com.promoviedb.config.ClientConfig;
import com.promoviedb.http.HttpClient;

/**
 * Base service class with common functionality
 */
public abstract class BaseService {
    protected final HttpClient httpClient;
    protected final ClientConfig config;

    protected BaseService(HttpClient httpClient, ClientConfig config) {
        this.httpClient = httpClient;
        this.config = config;
    }

    /**
     * Build API endpoint URL with version prefix
     * Example: baseUrl="https://api.promoviedb.com", version="v1", path="np/3/movie/550"
     * Result: "https://api.promoviedb.com/v1/np/3/movie/550"
     */
    protected String buildUrl(String path) {
        String baseUrl = config.getBaseUrl();
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }

        // Insert API version
        baseUrl += config.getApiVersion() + "/";

        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        return baseUrl + path;
    }
}
