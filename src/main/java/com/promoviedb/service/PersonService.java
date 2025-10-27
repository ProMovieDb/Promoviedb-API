package com.promoviedb.service;

import com.google.gson.JsonObject;
import com.promoviedb.config.ClientConfig;
import com.promoviedb.exception.ApiException;
import com.promoviedb.http.HttpClient;
import com.promoviedb.model.person.PersonDetails;
import com.promoviedb.util.UrlBuilder;

/**
 * Service for person-related API calls
 */
public class PersonService extends BaseService {

    public PersonService(HttpClient httpClient, ClientConfig config) {
        super(httpClient, config);
    }

    /**
     * Query the top level details of a person
     *
     * @param personId         Person ID
     * @param language         ISO 639-1 code (optional)
     * @param appendToResponse Comma-separated list of objects to append (optional, max 20)
     * @return Person details
     * @throws ApiException if the request fails
     */
    public PersonDetails getDetails(String personId, String language, String appendToResponse) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/person/" + personId))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .addQueryParam("append_to_response", appendToResponse)
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, PersonDetails.class);
    }

    /**
     * Query the top level details of a person
     *
     * @param personId Person ID
     * @return Person details
     * @throws ApiException if the request fails
     */
    public PersonDetails getDetails(String personId) throws ApiException {
        return getDetails(personId, null, null);
    }

    /**
     * Get the TV credits that belong to a person
     *
     * @param personId Person ID
     * @param language ISO 639-1 code (optional)
     * @return TV credits response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getTvCredits(String personId, String language) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/person/" + personId + "/tv_credits"))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }

    /**
     * Get the TV credits that belong to a person
     *
     * @param personId Person ID
     * @return TV credits response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getTvCredits(String personId) throws ApiException {
        return getTvCredits(personId, null);
    }

    /**
     * Get the movie credits for a person
     *
     * @param personId Person ID
     * @param language ISO 639-1 code (optional)
     * @return Movie credits response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getMovieCredits(String personId, String language) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/person/" + personId + "/movie_credits"))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }

    /**
     * Get the movie credits for a person
     *
     * @param personId Person ID
     * @return Movie credits response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getMovieCredits(String personId) throws ApiException {
        return getMovieCredits(personId, null);
    }

    /**
     * Get the profile images that belong to a person
     *
     * @param personId Person ID
     * @return Images response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getImages(String personId) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/person/" + personId + "/images"))
                .addQueryParam("api_key", config.getApiKey())
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }

    /**
     * Get the combined movie and TV credits that belong to a person
     *
     * @param personId Person ID
     * @param language ISO 639-1 code (optional)
     * @return Combined credits response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getCombinedCredits(String personId, String language) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/person/" + personId + "/combined_credits"))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }

    /**
     * Get the combined movie and TV credits that belong to a person
     *
     * @param personId Person ID
     * @return Combined credits response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getCombinedCredits(String personId) throws ApiException {
        return getCombinedCredits(personId, null);
    }

    /**
     * Get the recent changes for a person
     *
     * @param personId  Person ID
     * @param startDate Start date (optional, format: YYYY-MM-DD)
     * @param endDate   End date (optional, format: YYYY-MM-DD)
     * @param page      Page number (optional, default 1)
     * @return Changes response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getChanges(String personId, String startDate, String endDate, Integer page) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/person/" + personId + "/changes"))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("start_date", startDate)
                .addQueryParam("end_date", endDate)
                .addQueryParam("page", page)
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }

    /**
     * Get the recent changes for a person
     *
     * @param personId Person ID
     * @return Changes response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getChanges(String personId) throws ApiException {
        return getChanges(personId, null, null, null);
    }
}
