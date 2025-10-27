package com.promoviedb.service;

import com.google.gson.JsonObject;
import com.promoviedb.config.ClientConfig;
import com.promoviedb.exception.ApiException;
import com.promoviedb.http.HttpClient;
import com.promoviedb.util.UrlBuilder;

/**
 * Service for search-related API calls
 */
public class SearchService extends BaseService {

    public SearchService(HttpClient httpClient, ClientConfig config) {
        super(httpClient, config);
    }

    /**
     * Search for movies
     *
     * @param query        Search keywords (required)
     * @param language     ISO 639-1 code (optional)
     * @param includeAdult Include adult content (optional, default false)
     * @param page         Page number (optional, default 1, max 10)
     * @param detail       Return detailed results (optional, default true)
     * @param pageSize     Page size (optional, default 20)
     * @return Search results as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject searchMovie(String query, String language, Boolean includeAdult,
                                   Integer page, Boolean detail, Integer pageSize) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/search/movie"))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("query", query)
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .addQueryParam("include_adult", includeAdult)
                .addQueryParam("page", page)
                .addQueryParam("detail", detail)
                .addQueryParam("page_size", pageSize)
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }

    /**
     * Search for movies
     *
     * @param query Search keywords (required)
     * @return Search results as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject searchMovie(String query) throws ApiException {
        return searchMovie(query, null, null, null, null, null);
    }

    /**
     * Search for TV shows
     *
     * @param query        Search keywords (required)
     * @param language     ISO 639-1 code (optional)
     * @param includeAdult Include adult content (optional, default false)
     * @param page         Page number (optional, default 1, max 10)
     * @param detail       Return detailed results (optional, default true)
     * @param pageSize     Page size (optional, default 20)
     * @return Search results as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject searchTv(String query, String language, Boolean includeAdult,
                                Integer page, Boolean detail, Integer pageSize) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/search/tv"))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("query", query)
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .addQueryParam("include_adult", includeAdult)
                .addQueryParam("page", page)
                .addQueryParam("detail", detail)
                .addQueryParam("page_size", pageSize)
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }

    /**
     * Search for TV shows
     *
     * @param query Search keywords (required)
     * @return Search results as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject searchTv(String query) throws ApiException {
        return searchTv(query, null, null, null, null, null);
    }

    /**
     * Search for people
     *
     * @param query        Search keywords (required)
     * @param language     ISO 639-1 code (optional)
     * @param includeAdult Include adult content (optional, default false)
     * @param page         Page number (optional, default 1, max 10)
     * @param detail       Return detailed results (optional, default true)
     * @param pageSize     Page size (optional, default 20)
     * @return Search results as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject searchPerson(String query, String language, Boolean includeAdult,
                                    Integer page, Boolean detail, Integer pageSize) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/search/person"))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("query", query)
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .addQueryParam("include_adult", includeAdult)
                .addQueryParam("page", page)
                .addQueryParam("detail", detail)
                .addQueryParam("page_size", pageSize)
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }

    /**
     * Search for people
     *
     * @param query Search keywords (required)
     * @return Search results as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject searchPerson(String query) throws ApiException {
        return searchPerson(query, null, null, null, null, null);
    }

    /**
     * Multi-search for movies, TV shows, and people
     *
     * @param query        Search keywords (required)
     * @param language     ISO 639-1 code (optional)
     * @param includeAdult Include adult content (optional, default false)
     * @param page         Page number (optional, default 1, max 10)
     * @param detail       Return detailed results (optional, default true)
     * @param pageSize     Page size (optional, default 20)
     * @return Search results as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject searchMulti(String query, String language, Boolean includeAdult,
                                   Integer page, Boolean detail, Integer pageSize) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/search/multi"))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("query", query)
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .addQueryParam("include_adult", includeAdult)
                .addQueryParam("page", page)
                .addQueryParam("detail", detail)
                .addQueryParam("page_size", pageSize)
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }

    /**
     * Multi-search for movies, TV shows, and people
     *
     * @param query Search keywords (required)
     * @return Search results as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject searchMulti(String query) throws ApiException {
        return searchMulti(query, null, null, null, null, null);
    }
}
