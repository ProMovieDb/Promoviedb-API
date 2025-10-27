package com.promoviedb.http;

import com.promoviedb.config.ClientConfig;
import com.promoviedb.exception.ApiException;
import com.promoviedb.exception.AuthenticationException;
import com.promoviedb.exception.RateLimitException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for HttpClient using MockWebServer
 */
public class HttpClientTest {

    private MockWebServer mockWebServer;
    private HttpClient httpClient;
    private ClientConfig config;

    @Before
    public void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        config = new ClientConfig.Builder()
                .apiKey("test-api-key")
                .baseUrl(mockWebServer.url("/").toString())
                .build();

        httpClient = new HttpClient(config);
    }

    @After
    public void tearDown() throws Exception {
        httpClient.close();
        mockWebServer.shutdown();
    }

    @Test
    public void testGetSuccess() throws Exception {
        String responseBody = "{\"message\":\"success\"}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        String result = httpClient.get(mockWebServer.url("/test").toString());

        assertEquals(responseBody, result);

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/test", request.getPath());
    }

    @Test
    public void testPostSuccess() throws Exception {
        String requestBody = "{\"key\":\"value\"}";
        String responseBody = "{\"message\":\"created\"}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        String result = httpClient.post(mockWebServer.url("/test").toString(), requestBody);

        assertEquals(responseBody, result);

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals(requestBody, request.getBody().readUtf8());
    }

    @Test(expected = AuthenticationException.class)
    public void testGetUnauthorized() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(401)
                .setBody("{\"status_message\":\"Unauthorized\"}"));

        httpClient.get(mockWebServer.url("/test").toString());
    }

    @Test(expected = AuthenticationException.class)
    public void testGetForbidden() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(403)
                .setBody("{\"status_message\":\"Forbidden\"}"));

        httpClient.get(mockWebServer.url("/test").toString());
    }

    @Test(expected = RateLimitException.class)
    public void testGetRateLimited() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(429)
                .setBody("{\"status_message\":\"Too Many Requests\"}"));

        httpClient.get(mockWebServer.url("/test").toString());
    }

    @Test
    public void testGet404() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(404)
                .setBody("{\"status_message\":\"Not Found\"}"));

        try {
            httpClient.get(mockWebServer.url("/test").toString());
            fail("Should throw ApiException");
        } catch (ApiException e) {
            assertEquals(404, e.getStatusCode());
            assertTrue(e.getMessage().contains("Not Found"));
        }
    }

    @Test
    public void testGet500() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setBody("{\"status_message\":\"Internal Server Error\"}"));

        try {
            httpClient.get(mockWebServer.url("/test").toString());
            fail("Should throw ApiException");
        } catch (ApiException e) {
            assertEquals(500, e.getStatusCode());
            assertTrue(e.getMessage().contains("Internal Server Error"));
        }
    }

    @Test
    public void testParseResponse() {
        String json = "{\"id\":1,\"name\":\"Test\"}";

        TestModel result = httpClient.parseResponse(json, TestModel.class);

        assertNotNull(result);
        assertEquals(Integer.valueOf(1), result.id);
        assertEquals("Test", result.name);
    }

    @Test
    public void testGetGson() {
        assertNotNull(httpClient.getGson());
    }

    @Test
    public void testPutSuccess() throws Exception {
        String requestBody = "{\"key\":\"value\"}";
        String responseBody = "{\"message\":\"updated\"}";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        String result = httpClient.put(mockWebServer.url("/test").toString(), requestBody);

        assertEquals(responseBody, result);

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("PUT", request.getMethod());
    }

    @Test
    public void testDeleteSuccess() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(204)
                .setBody(""));

        String result = httpClient.delete(mockWebServer.url("/test").toString());

        assertEquals("", result);

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("DELETE", request.getMethod());
    }

    /**
     * Simple test model for JSON parsing
     */
    private static class TestModel {
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
