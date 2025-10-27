package com.promoviedb;

import com.google.gson.JsonObject;
import com.promoviedb.config.ClientConfig;
import com.promoviedb.model.common.Credits;
import com.promoviedb.model.movie.MovieDetails;
import com.promoviedb.model.person.PersonDetails;
import com.promoviedb.model.tv.TvSeriesDetails;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Integration tests for ProMovieDBClient
 * <p>
 * These tests verify the complete flow from client initialization through service calls
 * using a mock web server to simulate API responses.
 * </p>
 */
public class ProMovieDBClientIntegrationTest {

    private MockWebServer mockWebServer;
    private ProMovieDBClient client;

    @Before
    public void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        client = new ProMovieDBClient.Builder()
                .apiKey("test-api-key")
                .baseUrl(mockWebServer.url("/").toString())
                .language("en")
                .enableLogging(false)
                .build();
    }

    @After
    public void tearDown() throws Exception {
        client.close();
        mockWebServer.shutdown();
    }

    @Test
    public void testMovieServiceGetDetails() throws Exception {
        String responseBody = "{\n" +
                "  \"id\": 550,\n" +
                "  \"title\": \"Fight Club\",\n" +
                "  \"overview\": \"An insomniac office worker...\",\n" +
                "  \"release_date\": \"1999-10-15\",\n" +
                "  \"runtime\": 139\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        MovieDetails movie = client.movieService().getDetails("550");

        assertNotNull(movie);
        assertEquals(Integer.valueOf(550), movie.getId());
        assertEquals("Fight Club", movie.getTitle());
    }

    @Test
    public void testMovieServiceGetCredits() throws Exception {
        String responseBody = "{\n" +
                "  \"id\": 550,\n" +
                "  \"cast\": [\n" +
                "    {\"id\": 287, \"name\": \"Brad Pitt\", \"character\": \"Tyler Durden\"}\n" +
                "  ],\n" +
                "  \"crew\": []\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        Credits credits = client.movieService().getCredits("550");

        assertNotNull(credits);
        assertEquals(1, credits.getCast().size());
        assertEquals("Brad Pitt", credits.getCast().get(0).getName());
    }

    @Test
    public void testTvSeriesServiceGetDetails() throws Exception {
        String responseBody = "{\n" +
                "  \"id\": 1399,\n" +
                "  \"name\": \"Game of Thrones\",\n" +
                "  \"first_air_date\": \"2011-04-17\",\n" +
                "  \"number_of_seasons\": 8\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        TvSeriesDetails series = client.tvSeriesService().getDetails("1399");

        assertNotNull(series);
        assertEquals(Integer.valueOf(1399), series.getId());
        assertEquals("Game of Thrones", series.getName());
        assertEquals(Integer.valueOf(8), series.getNumberOfSeasons());
    }

    @Test
    public void testPersonServiceGetDetails() throws Exception {
        String responseBody = "{\n" +
                "  \"id\": 287,\n" +
                "  \"name\": \"Brad Pitt\",\n" +
                "  \"birthday\": \"1963-12-18\",\n" +
                "  \"place_of_birth\": \"Shawnee, Oklahoma, USA\"\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        PersonDetails person = client.personService().getDetails("287");

        assertNotNull(person);
        assertEquals(Integer.valueOf(287), person.getId());
        assertEquals("Brad Pitt", person.getName());
        assertEquals("1963-12-18", person.getBirthday());
    }

    @Test
    public void testSearchServiceSearchMovie() throws Exception {
        String responseBody = "{\n" +
                "  \"page\": 1,\n" +
                "  \"results\": [\n" +
                "    {\"id\": 550, \"title\": \"Fight Club\"}\n" +
                "  ],\n" +
                "  \"total_pages\": 1,\n" +
                "  \"total_results\": 1\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        JsonObject results = client.searchService().searchMovie("Fight Club");

        assertNotNull(results);
        assertEquals(1, results.get("page").getAsInt());
        assertTrue(results.has("results"));
    }

    @Test
    public void testSearchServiceSearchTv() throws Exception {
        String responseBody = "{\n" +
                "  \"page\": 1,\n" +
                "  \"results\": [\n" +
                "    {\"id\": 1399, \"name\": \"Game of Thrones\"}\n" +
                "  ]\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        JsonObject results = client.searchService().searchTv("Game of Thrones");

        assertNotNull(results);
        assertTrue(results.has("results"));
    }

    @Test
    public void testSearchServiceSearchPerson() throws Exception {
        String responseBody = "{\n" +
                "  \"page\": 1,\n" +
                "  \"results\": [\n" +
                "    {\"id\": 287, \"name\": \"Brad Pitt\"}\n" +
                "  ]\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        JsonObject results = client.searchService().searchPerson("Brad Pitt");

        assertNotNull(results);
        assertTrue(results.has("results"));
    }

    @Test
    public void testSearchServiceSearchMulti() throws Exception {
        String responseBody = "{\n" +
                "  \"page\": 1,\n" +
                "  \"results\": [\n" +
                "    {\"id\": 550, \"title\": \"Fight Club\", \"media_type\": \"movie\"},\n" +
                "    {\"id\": 287, \"name\": \"Brad Pitt\", \"media_type\": \"person\"}\n" +
                "  ]\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        JsonObject results = client.searchService().searchMulti("Fight");

        assertNotNull(results);
        assertTrue(results.has("results"));
    }

    @Test
    public void testGenreServiceGetMovieGenres() throws Exception {
        String responseBody = "{\n" +
                "  \"genres\": [\n" +
                "    {\"id\": 28, \"name\": \"Action\"},\n" +
                "    {\"id\": 12, \"name\": \"Adventure\"}\n" +
                "  ]\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        JsonObject genres = client.genreService().getMovieGenres();

        assertNotNull(genres);
        assertTrue(genres.has("genres"));
    }

    @Test
    public void testGenreServiceGetTvGenres() throws Exception {
        String responseBody = "{\n" +
                "  \"genres\": [\n" +
                "    {\"id\": 10759, \"name\": \"Action & Adventure\"},\n" +
                "    {\"id\": 16, \"name\": \"Animation\"}\n" +
                "  ]\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        JsonObject genres = client.genreService().getTvGenres();

        assertNotNull(genres);
        assertTrue(genres.has("genres"));
    }

    @Test
    public void testCreditServiceGetDetails() throws Exception {
        String responseBody = "{\n" +
                "  \"credit_id\": \"52fe4284c3a36847f8014a11\",\n" +
                "  \"person\": {\n" +
                "    \"id\": 287,\n" +
                "    \"name\": \"Brad Pitt\"\n" +
                "  }\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        JsonObject credit = client.creditService().getDetails("52fe4284c3a36847f8014a11");

        assertNotNull(credit);
        assertTrue(credit.has("credit_id"));
    }

    @Test
    public void testCertificationServiceGetMovieCertifications() throws Exception {
        String responseBody = "{\n" +
                "  \"certifications\": {\n" +
                "    \"US\": [\n" +
                "      {\"certification\": \"G\", \"meaning\": \"General Audiences\"}\n" +
                "    ]\n" +
                "  }\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        JsonObject certifications = client.certificationService().getMovieCertifications();

        assertNotNull(certifications);
        assertTrue(certifications.has("certifications"));
    }

    @Test
    public void testCertificationServiceGetTvCertifications() throws Exception {
        String responseBody = "{\n" +
                "  \"certifications\": {\n" +
                "    \"US\": [\n" +
                "      {\"certification\": \"TV-G\", \"meaning\": \"General Audiences\"}\n" +
                "    ]\n" +
                "  }\n" +
                "}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        JsonObject certifications = client.certificationService().getTvCertifications();

        assertNotNull(certifications);
        assertTrue(certifications.has("certifications"));
    }

    @Test
    public void testStatusServicePing() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("pong"));

        String response = client.statusService().ping();

        assertNotNull(response);
        assertEquals("pong", response);
    }

    @Test
    public void testStatusServiceIsHealthy() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("OK"));

        boolean isHealthy = client.statusService().isHealthy();

        assertTrue(isHealthy);
    }

    @Test
    public void testStatusServiceIsNotHealthy() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setBody("Internal Server Error"));

        boolean isHealthy = client.statusService().isHealthy();

        assertFalse(isHealthy);
    }

    @Test
    public void testClientConfig() {
        ClientConfig config = client.getConfig();

        assertNotNull(config);
        assertEquals("test-api-key", config.getApiKey());
        assertEquals("en", config.getLanguage());
    }

    @Test
    public void testAllServicesInitialized() {
        assertNotNull(client.movieService());
        assertNotNull(client.tvSeriesService());
        assertNotNull(client.personService());
        assertNotNull(client.searchService());
        assertNotNull(client.genreService());
        assertNotNull(client.creditService());
        assertNotNull(client.certificationService());
        assertNotNull(client.statusService());
    }

    @Test
    public void testServiceSingleton() {
        // Services should be singleton per client instance
        assertSame(client.movieService(), client.movieService());
        assertSame(client.tvSeriesService(), client.tvSeriesService());
        assertSame(client.personService(), client.personService());
        assertSame(client.searchService(), client.searchService());
    }
}
