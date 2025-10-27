package com.promoviedb;

import com.promoviedb.config.ClientConfig;
import com.promoviedb.http.HttpClient;
import com.promoviedb.service.*;

/**
 * Main client class for ProMovieDB API
 * <p>
 * Example usage:
 * <pre>{@code
 * ProMovieDBClient client = new ProMovieDBClient.Builder()
 *     .apiKey("your-api-key")
 *     .baseUrl("https://api.promoviedb.com")
 *     .language("en")
 *     .build();
 *
 * // Use services
 * MovieDetails movie = client.movieService().getDetails("550");
 * }</pre>
 */
public class ProMovieDBClient {
    private final ClientConfig config;
    private final HttpClient httpClient;

    // Services
    private MovieService movieService;
    private TvSeriesService tvSeriesService;
    private PersonService personService;
    private SearchService searchService;
    private GenreService genreService;
    private CreditService creditService;
    private CertificationService certificationService;
    private StatusService statusService;

    private ProMovieDBClient(ClientConfig config) {
        this.config = config;
        this.httpClient = new HttpClient(config);
    }

    /**
     * Get movie service for movie-related API calls
     */
    public MovieService movieService() {
        if (movieService == null) {
            movieService = new MovieService(httpClient, config);
        }
        return movieService;
    }

    /**
     * Get TV series service for TV-related API calls
     */
    public TvSeriesService tvSeriesService() {
        if (tvSeriesService == null) {
            tvSeriesService = new TvSeriesService(httpClient, config);
        }
        return tvSeriesService;
    }

    /**
     * Get person service for person-related API calls
     */
    public PersonService personService() {
        if (personService == null) {
            personService = new PersonService(httpClient, config);
        }
        return personService;
    }

    /**
     * Get search service for search-related API calls
     */
    public SearchService searchService() {
        if (searchService == null) {
            searchService = new SearchService(httpClient, config);
        }
        return searchService;
    }

    /**
     * Get genre service for genre-related API calls
     */
    public GenreService genreService() {
        if (genreService == null) {
            genreService = new GenreService(httpClient, config);
        }
        return genreService;
    }

    /**
     * Get credit service for credit-related API calls
     */
    public CreditService creditService() {
        if (creditService == null) {
            creditService = new CreditService(httpClient, config);
        }
        return creditService;
    }

    /**
     * Get certification service for certification-related API calls
     */
    public CertificationService certificationService() {
        if (certificationService == null) {
            certificationService = new CertificationService(httpClient, config);
        }
        return certificationService;
    }

    /**
     * Get status service for status/monitoring API calls
     */
    public StatusService statusService() {
        if (statusService == null) {
            statusService = new StatusService(httpClient, config);
        }
        return statusService;
    }

    /**
     * Get the client configuration
     */
    public ClientConfig getConfig() {
        return config;
    }

    /**
     * Close the client and release resources
     */
    public void close() {
        if (httpClient != null) {
            httpClient.close();
        }
    }

    /**
     * Builder for creating ProMovieDBClient instances
     */
    public static class Builder {
        private final ClientConfig.Builder configBuilder;

        public Builder() {
            this.configBuilder = new ClientConfig.Builder();
        }

        public Builder apiKey(String apiKey) {
            configBuilder.apiKey(apiKey);
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            configBuilder.baseUrl(baseUrl);
            return this;
        }

        public Builder apiVersion(String apiVersion) {
            configBuilder.apiVersion(apiVersion);
            return this;
        }

        public Builder language(String language) {
            configBuilder.language(language);
            return this;
        }

        public Builder connectTimeout(int connectTimeout) {
            configBuilder.connectTimeout(connectTimeout);
            return this;
        }

        public Builder readTimeout(int readTimeout) {
            configBuilder.readTimeout(readTimeout);
            return this;
        }

        public Builder writeTimeout(int writeTimeout) {
            configBuilder.writeTimeout(writeTimeout);
            return this;
        }

        public Builder enableLogging(boolean enableLogging) {
            configBuilder.enableLogging(enableLogging);
            return this;
        }

        public ProMovieDBClient build() {
            ClientConfig config = configBuilder.build();
            return new ProMovieDBClient(config);
        }
    }
}
