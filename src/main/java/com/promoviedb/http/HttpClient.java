package com.promoviedb.http;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.promoviedb.config.ClientConfig;
import com.promoviedb.exception.ApiException;
import com.promoviedb.exception.AuthenticationException;
import com.promoviedb.exception.RateLimitException;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * HTTP client wrapper for OkHttp
 */
public class HttpClient {
    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final OkHttpClient client;
    private final ClientConfig config;
    private final Gson gson;

    public HttpClient(ClientConfig config) {
        this.config = config;
        this.gson = new Gson();

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(config.getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(config.getReadTimeout(), TimeUnit.SECONDS)
                .writeTimeout(config.getWriteTimeout(), TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(5, 5, TimeUnit.MINUTES))
                .retryOnConnectionFailure(true);

        // Add logging interceptor if enabled
        if (config.isEnableLogging()) {
            builder.addInterceptor(new LoggingInterceptor());
        }

        this.client = builder.build();
    }

    /**
     * Execute GET request
     */
    public String get(String url) throws ApiException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        return executeRequest(request);
    }

    /**
     * Execute POST request
     */
    public String post(String url, String jsonBody) throws ApiException {
        RequestBody body = RequestBody.create(jsonBody, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        return executeRequest(request);
    }

    /**
     * Execute PUT request
     */
    public String put(String url, String jsonBody) throws ApiException {
        RequestBody body = RequestBody.create(jsonBody, JSON);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        return executeRequest(request);
    }

    /**
     * Execute DELETE request
     */
    public String delete(String url) throws ApiException {
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        return executeRequest(request);
    }

    /**
     * Parse JSON response to specified type
     */
    public <T> T parseResponse(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    /**
     * Get Gson instance
     */
    public Gson getGson() {
        return gson;
    }

    /**
     * Close the HTTP client
     */
    public void close() {
        if (client != null) {
            client.dispatcher().executorService().shutdown();
            client.connectionPool().evictAll();
        }
    }

    private String executeRequest(Request request) throws ApiException {
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body() != null ? response.body().string() : "";

            if (!response.isSuccessful()) {
                handleErrorResponse(response.code(), responseBody);
            }

            return responseBody;
        } catch (IOException e) {
            logger.error("HTTP request failed", e);
            throw new ApiException("HTTP request failed: " + e.getMessage(), e);
        }
    }

    private void handleErrorResponse(int statusCode, String responseBody) throws ApiException {
        String errorMessage = "API request failed with status code: " + statusCode;

        // Try to extract error message from response body
        try {
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
            if (jsonObject != null && jsonObject.has("status_message")) {
                errorMessage = jsonObject.get("status_message").getAsString();
            }
        } catch (Exception e) {
            // Ignore parsing errors, use default message
        }

        switch (statusCode) {
            case 401:
            case 403:
                throw new AuthenticationException(statusCode, errorMessage, responseBody);
            case 429:
                throw new RateLimitException(statusCode, errorMessage, responseBody);
            default:
                throw new ApiException(statusCode, errorMessage, responseBody);
        }
    }

    /**
     * Logging interceptor for debugging
     */
    private static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.nanoTime();

            logger.debug("Sending request {} on {}", request.method(), request.url());

            Response response = chain.proceed(request);

            long elapsedTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
            logger.debug("Received response for {} in {}ms, status code: {}",
                    request.url(), elapsedTime, response.code());

            return response;
        }
    }
}
