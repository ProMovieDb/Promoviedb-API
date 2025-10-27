package com.promoviedb.service;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.promoviedb.config.ClientConfig;
import com.promoviedb.exception.ApiException;
import com.promoviedb.http.HttpClient;
import com.promoviedb.model.common.Credits;
import com.promoviedb.model.common.Image;
import com.promoviedb.model.common.Video;
import com.promoviedb.model.movie.MovieDetails;
import com.promoviedb.util.UrlBuilder;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Service for movie-related API calls
 */
public class MovieService extends BaseService {

    public MovieService(HttpClient httpClient, ClientConfig config) {
        super(httpClient, config);
    }

    /**
     * Get the top level details of a movie by ID
     *
     * @param movieId           Movie ID
     * @param language          ISO 639-1 code (optional)
     * @param appendToResponse  Comma-separated list of objects to append (optional, max 20)
     * @return Movie details
     * @throws ApiException if the request fails
     */
    public MovieDetails getDetails(String movieId, String language, String appendToResponse) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/movie/" + movieId))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .addQueryParam("append_to_response", appendToResponse)
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, MovieDetails.class);
    }

    /**
     * Get the top level details of a movie by ID
     *
     * @param movieId Movie ID
     * @return Movie details
     * @throws ApiException if the request fails
     */
    public MovieDetails getDetails(String movieId) throws ApiException {
        return getDetails(movieId, null, null);
    }

    /**
     * Get the videos for a movie
     *
     * @param movieId              Movie ID
     * @param language             ISO 639-1 code (optional)
     * @param includeVideoLanguage Filter by language, comma-separated (optional)
     * @return Videos response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getVideos(String movieId, String language, String includeVideoLanguage) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/movie/" + movieId + "/videos"))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .addQueryParam("include_video_language", includeVideoLanguage)
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }

    /**
     * Get the videos for a movie
     *
     * @param movieId Movie ID
     * @return Videos response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getVideos(String movieId) throws ApiException {
        return getVideos(movieId, null, null);
    }

    /**
     * Get the images that belong to a movie
     *
     * @param movieId              Movie ID
     * @param language             ISO 639-1 code (optional)
     * @param includeImageLanguage Filter by language, comma-separated (optional)
     * @return Images response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getImages(String movieId, String language, String includeImageLanguage) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/movie/" + movieId + "/images"))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .addQueryParam("include_image_language", includeImageLanguage)
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }

    /**
     * Get the images that belong to a movie
     *
     * @param movieId Movie ID
     * @return Images response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getImages(String movieId) throws ApiException {
        return getImages(movieId, null, null);
    }

    /**
     * Get the credits for a movie
     *
     * @param movieId  Movie ID
     * @param language ISO 639-1 code (optional)
     * @return Credits
     * @throws ApiException if the request fails
     */
    public Credits getCredits(String movieId, String language) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/movie/" + movieId + "/credits"))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, Credits.class);
    }

    /**
     * Get the credits for a movie
     *
     * @param movieId Movie ID
     * @return Credits
     * @throws ApiException if the request fails
     */
    public Credits getCredits(String movieId) throws ApiException {
        return getCredits(movieId, null);
    }

    /**
     * Get the release dates and certifications for a movie
     *
     * @param movieId Movie ID
     * @return Release dates response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getReleaseDates(String movieId) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/movie/" + movieId + "/release_dates"))
                .addQueryParam("api_key", config.getApiKey())
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }
}
