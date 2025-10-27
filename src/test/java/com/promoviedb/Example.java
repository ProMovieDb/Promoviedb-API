package com.promoviedb;

import com.google.gson.JsonObject;
import com.promoviedb.exception.ApiException;
import com.promoviedb.model.common.Credits;
import com.promoviedb.model.movie.MovieDetails;
import com.promoviedb.model.person.PersonDetails;
import com.promoviedb.model.tv.TvSeriesDetails;

/**
 * Example usage of the ProMovieDB SDK
 */
public class Example {
    public static void main(String[] args) {
        // Initialize the client
        // Note: API version defaults to "v1" if not specified
        ProMovieDBClient client = new ProMovieDBClient.Builder()
                .apiKey("your-api-key")
                .baseUrl("https://api.promoviedb.com")
                .apiVersion("v1")  // Optional: defaults to "v1", can be "v2", "v3", etc.
                .language("en")
                .enableLogging(true)
                .build();

        try {
            // Example 1: Get movie details
            System.out.println("=== Movie Details ===");
            MovieDetails movie = client.movieService().getDetails("550");
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Overview: " + movie.getOverview());
            System.out.println("Release Date: " + movie.getReleaseDate());
            System.out.println("Rating: " + movie.getVoteAverage());

            // Example 2: Search for movies
            System.out.println("\n=== Search Movies ===");
            JsonObject searchResults = client.searchService().searchMovie("Inception");
            System.out.println("Search results: " + searchResults);

            // Example 3: Get TV series details
            System.out.println("\n=== TV Series Details ===");
            TvSeriesDetails series = client.tvSeriesService().getDetails("1399");
            System.out.println("Name: " + series.getName());
            System.out.println("First Air Date: " + series.getFirstAirDate());
            System.out.println("Number of Seasons: " + series.getNumberOfSeasons());

            // Example 4: Get person details
            System.out.println("\n=== Person Details ===");
            PersonDetails person = client.personService().getDetails("31");
            System.out.println("Name: " + person.getName());
            System.out.println("Birthday: " + person.getBirthday());
            System.out.println("Place of Birth: " + person.getPlaceOfBirth());

            // Example 5: Get movie credits
            System.out.println("\n=== Movie Credits ===");
            Credits credits = client.movieService().getCredits("550");
            System.out.println("Number of cast members: " + credits.getCast().size());
            System.out.println("Number of crew members: " + credits.getCrew().size());

            // Example 6: Get genres
            System.out.println("\n=== Movie Genres ===");
            JsonObject movieGenres = client.genreService().getMovieGenres();
            System.out.println("Movie genres: " + movieGenres);

            // Example 7: Check API health
            System.out.println("\n=== API Health Check ===");
            boolean isHealthy = client.statusService().isHealthy();
            System.out.println("API is healthy: " + isHealthy);

            // Example 8: Multi-search
            System.out.println("\n=== Multi Search ===");
            JsonObject multiResults = client.searchService().searchMulti("Matrix");
            System.out.println("Multi-search results: " + multiResults);

            // Example 9: Get movie videos
            System.out.println("\n=== Movie Videos ===");
            JsonObject videos = client.movieService().getVideos("550");
            System.out.println("Videos: " + videos);

            // Example 10: Get certifications
            System.out.println("\n=== Movie Certifications ===");
            JsonObject certifications = client.certificationService().getMovieCertifications();
            System.out.println("Certifications: " + certifications);

        } catch (ApiException e) {
            System.err.println("API error: " + e.getMessage());
            System.err.println("Status code: " + e.getStatusCode());
            if (e.getErrorBody() != null) {
                System.err.println("Error body: " + e.getErrorBody());
            }
        } finally {
            // Clean up resources
            client.close();
        }
    }
}
