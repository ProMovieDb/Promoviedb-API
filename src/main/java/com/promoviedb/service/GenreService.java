package com.promoviedb.service;

import com.google.gson.JsonObject;
import com.promoviedb.config.ClientConfig;
import com.promoviedb.exception.ApiException;
import com.promoviedb.http.HttpClient;
import com.promoviedb.util.UrlBuilder;

/**
 * Service for genre-related API calls
 */
public class GenreService extends BaseService {

    public GenreService(HttpClient httpClient, ClientConfig config) {
        super(httpClient, config);
    }

    /**
     * Get the list of official genres for movies
     *
     * @param language ISO 639-1 code (optional)
     * @return Genres response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getMovieGenres(String language) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/genre/movie/list"))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }

    /**
     * Get the list of official genres for movies
     *
     * @return Genres response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getMovieGenres() throws ApiException {
        return getMovieGenres(null);
    }

    /**
     * Get the list of official genres for TV shows
     *
     * @param language ISO 639-1 code (optional)
     * @return Genres response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getTvGenres(String language) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/genre/tv/list"))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }

    /**
     * Get the list of official genres for TV shows
     *
     * @return Genres response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getTvGenres() throws ApiException {
        return getTvGenres(null);
    }
}
