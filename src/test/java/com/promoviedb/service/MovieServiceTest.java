package com.promoviedb.service;

import com.promoviedb.config.ClientConfig;
import com.promoviedb.http.HttpClient;
import com.promoviedb.model.movie.MovieDetails;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for MovieService
 */
public class MovieServiceTest {

    private MockWebServer mockWebServer;
    private MovieService movieService;
    private HttpClient httpClient;

    @Before
    public void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        ClientConfig config = new ClientConfig.Builder()
                .apiKey("test-api-key")
                .baseUrl(mockWebServer.url("/").toString())
                .build();

        httpClient = new HttpClient(config);
        movieService = new MovieService(httpClient, config);
    }

    @After
    public void tearDown() throws Exception {
        httpClient.close();
        mockWebServer.shutdown();
    }

    @Test
    public void testGetDetails() throws Exception {
        String responseBody = "{\n" +
                "  \"id\": 550,\n" +
                "  \"title\": \"Fight Club\",\n" +
                "  \"original_title\": \"Fight Club\",\n" +
                "  \"overview\": \"A ticking-time-bomb insomniac...\",\n" +
                "  \"release_date\": \"1999-10-15\",\n" +
                "  \"runtime\": 139,\n" +
                "  \"vote_average\": 8.4,\n" +
                "  \"vote_count\": 26000\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        MovieDetails movie = movieService.getDetails("550");

        assertNotNull(movie);
        assertEquals(Integer.valueOf(550), movie.getId());
        assertEquals("Fight Club", movie.getTitle());
        assertEquals("Fight Club", movie.getOriginalTitle());
        assertEquals("1999-10-15", movie.getReleaseDate());
        assertEquals(Integer.valueOf(139), movie.getRuntime());
        assertEquals(Double.valueOf(8.4), movie.getVoteAverage());
        assertEquals(Integer.valueOf(26000), movie.getVoteCount());

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertTrue(request.getPath().contains("/v1/np/3/movie/550"));
        assertTrue(request.getPath().contains("api_key=test-api-key"));
    }

    @Test
    public void testGetDetailsWithLanguage() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"id\":550,\"title\":\"搏击俱乐部\"}")
                .addHeader("Content-Type", "application/json"));

        MovieDetails movie = movieService.getDetails("550", "zh-CN", null);

        assertNotNull(movie);

        RecordedRequest request = mockWebServer.takeRequest();
        assertTrue(request.getPath().contains("language=zh-CN"));
    }

    @Test
    public void testGetDetailsWithAppendToResponse() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"id\":550,\"title\":\"Fight Club\"}")
                .addHeader("Content-Type", "application/json"));

        movieService.getDetails("550", "en", "videos,credits");

        RecordedRequest request = mockWebServer.takeRequest();
        assertTrue(request.getPath().contains("append_to_response=videos%2Ccredits"));
    }

    @Test
    public void testGetVideos() throws Exception {
        String responseBody = "{\n" +
                "  \"id\": 550,\n" +
                "  \"results\": [\n" +
                "    {\n" +
                "      \"id\": \"video1\",\n" +
                "      \"key\": \"SUXWAEX2jlg\",\n" +
                "      \"name\": \"Trailer\",\n" +
                "      \"site\": \"YouTube\",\n" +
                "      \"type\": \"Trailer\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        com.google.gson.JsonObject videos = movieService.getVideos("550");

        assertNotNull(videos);
        assertEquals(550, videos.get("id").getAsInt());
        assertTrue(videos.has("results"));

        RecordedRequest request = mockWebServer.takeRequest();
        assertTrue(request.getPath().contains("/v1/np/3/movie/550/videos"));
    }

    @Test
    public void testGetImages() throws Exception {
        String responseBody = "{\n" +
                "  \"id\": 550,\n" +
                "  \"backdrops\": [],\n" +
                "  \"posters\": []\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        com.google.gson.JsonObject images = movieService.getImages("550");

        assertNotNull(images);
        assertEquals(550, images.get("id").getAsInt());

        RecordedRequest request = mockWebServer.takeRequest();
        assertTrue(request.getPath().contains("/v1/np/3/movie/550/images"));
    }

    @Test
    public void testGetCredits() throws Exception {
        String responseBody = "{\n" +
                "  \"id\": 550,\n" +
                "  \"cast\": [\n" +
                "    {\n" +
                "      \"id\": 287,\n" +
                "      \"name\": \"Brad Pitt\",\n" +
                "      \"character\": \"Tyler Durden\",\n" +
                "      \"order\": 0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"crew\": []\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        com.promoviedb.model.common.Credits credits = movieService.getCredits("550");

        assertNotNull(credits);
        assertEquals(Integer.valueOf(550), credits.getId());
        assertNotNull(credits.getCast());
        assertEquals(1, credits.getCast().size());
        assertEquals("Brad Pitt", credits.getCast().get(0).getName());
        assertEquals("Tyler Durden", credits.getCast().get(0).getCharacter());

        RecordedRequest request = mockWebServer.takeRequest();
        assertTrue(request.getPath().contains("/v1/np/3/movie/550/credits"));
    }

    @Test
    public void testGetReleaseDates() throws Exception {
        String responseBody = "{\n" +
                "  \"id\": 550,\n" +
                "  \"results\": []\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        com.google.gson.JsonObject releaseDates = movieService.getReleaseDates("550");

        assertNotNull(releaseDates);
        assertEquals(550, releaseDates.get("id").getAsInt());

        RecordedRequest request = mockWebServer.takeRequest();
        assertTrue(request.getPath().contains("/v1/np/3/movie/550/release_dates"));
    }
}
