package com.promoviedb.service;

import com.google.gson.JsonObject;
import com.promoviedb.config.ClientConfig;
import com.promoviedb.exception.ApiException;
import com.promoviedb.http.HttpClient;
import com.promoviedb.model.common.Credits;
import com.promoviedb.model.tv.TvSeriesDetails;
import com.promoviedb.util.UrlBuilder;

/**
 * Service for TV series-related API calls
 */
public class TvSeriesService extends BaseService {

    public TvSeriesService(HttpClient httpClient, ClientConfig config) {
        super(httpClient, config);
    }

    /**
     * Get the details of a TV show
     *
     * @param seriesId         TV series ID
     * @param language         ISO 639-1 code (optional)
     * @param appendToResponse Comma-separated list of objects to append (optional, max 20)
     * @return TV series details
     * @throws ApiException if the request fails
     */
    public TvSeriesDetails getDetails(String seriesId, String language, String appendToResponse) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/tv/" + seriesId))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .addQueryParam("append_to_response", appendToResponse)
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, TvSeriesDetails.class);
    }

    /**
     * Get the details of a TV show
     *
     * @param seriesId TV series ID
     * @return TV series details
     * @throws ApiException if the request fails
     */
    public TvSeriesDetails getDetails(String seriesId) throws ApiException {
        return getDetails(seriesId, null, null);
    }

    /**
     * Get the videos that belong to a TV show
     *
     * @param seriesId             TV series ID
     * @param language             ISO 639-1 code (optional)
     * @param includeVideoLanguage Filter by language, comma-separated (optional)
     * @return Videos response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getVideos(String seriesId, String language, String includeVideoLanguage) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/tv/" + seriesId + "/videos"))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .addQueryParam("include_video_language", includeVideoLanguage)
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }

    /**
     * Get the videos that belong to a TV show
     *
     * @param seriesId TV series ID
     * @return Videos response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getVideos(String seriesId) throws ApiException {
        return getVideos(seriesId, null, null);
    }

    /**
     * Get the images that belong to a TV series
     *
     * @param seriesId             TV series ID
     * @param language             ISO 639-1 code (optional)
     * @param includeImageLanguage Filter by language, comma-separated (optional)
     * @return Images response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getImages(String seriesId, String language, String includeImageLanguage) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/tv/" + seriesId + "/images"))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .addQueryParam("include_image_language", includeImageLanguage)
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }

    /**
     * Get the images that belong to a TV series
     *
     * @param seriesId TV series ID
     * @return Images response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getImages(String seriesId) throws ApiException {
        return getImages(seriesId, null, null);
    }

    /**
     * Get the latest season credits of a TV show
     *
     * @param seriesId TV series ID
     * @param language ISO 639-1 code (optional)
     * @return Credits
     * @throws ApiException if the request fails
     */
    public Credits getCredits(String seriesId, String language) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/tv/" + seriesId + "/credits"))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, Credits.class);
    }

    /**
     * Get the latest season credits of a TV show
     *
     * @param seriesId TV series ID
     * @return Credits
     * @throws ApiException if the request fails
     */
    public Credits getCredits(String seriesId) throws ApiException {
        return getCredits(seriesId, null);
    }

    /**
     * Get the content ratings that have been added to a TV show
     *
     * @param seriesId TV series ID
     * @return Content ratings response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getContentRatings(String seriesId) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/tv/" + seriesId + "/content_ratings"))
                .addQueryParam("api_key", config.getApiKey())
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }

    /**
     * Get the aggregate credits (cast and crew) that have been added to a TV show
     *
     * @param seriesId TV series ID
     * @param language ISO 639-1 code (optional)
     * @return Aggregate credits response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getAggregateCredits(String seriesId, String language) throws ApiException {
        String url = UrlBuilder.create(buildUrl("np/3/tv/" + seriesId + "/aggregate_credits"))
                .addQueryParam("api_key", config.getApiKey())
                .addQueryParam("language", language != null ? language : config.getLanguage())
                .build();

        String response = httpClient.get(url);
        return httpClient.parseResponse(response, JsonObject.class);
    }

    /**
     * Get the aggregate credits (cast and crew) that have been added to a TV show
     *
     * @param seriesId TV series ID
     * @return Aggregate credits response as JsonObject
     * @throws ApiException if the request fails
     */
    public JsonObject getAggregateCredits(String seriesId) throws ApiException {
        return getAggregateCredits(seriesId, null);
    }
}
