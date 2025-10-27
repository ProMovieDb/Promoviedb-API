package com.promoviedb.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for building URLs with query parameters
 */
public class UrlBuilder {
    private final String baseUrl;
    private final Map<String, String> queryParams;

    public UrlBuilder(String baseUrl) {
        this.baseUrl = baseUrl;
        this.queryParams = new HashMap<>();
    }

    /**
     * Add a query parameter
     */
    public UrlBuilder addQueryParam(String key, String value) {
        if (value != null && !value.trim().isEmpty()) {
            queryParams.put(key, value);
        }
        return this;
    }

    /**
     * Add a query parameter (integer value)
     */
    public UrlBuilder addQueryParam(String key, Integer value) {
        if (value != null) {
            queryParams.put(key, value.toString());
        }
        return this;
    }

    /**
     * Add a query parameter (boolean value)
     */
    public UrlBuilder addQueryParam(String key, Boolean value) {
        if (value != null) {
            queryParams.put(key, value.toString());
        }
        return this;
    }

    /**
     * Build the complete URL with query parameters
     */
    public String build() {
        if (queryParams.isEmpty()) {
            return baseUrl;
        }

        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        urlBuilder.append("?");

        boolean first = true;
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (!first) {
                urlBuilder.append("&");
            }
            urlBuilder.append(encodeParam(entry.getKey()))
                    .append("=")
                    .append(encodeParam(entry.getValue()));
            first = false;
        }

        return urlBuilder.toString();
    }

    /**
     * URL encode a parameter
     */
    private String encodeParam(String param) {
        try {
            return URLEncoder.encode(param, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            // UTF-8 is always supported
            throw new RuntimeException(e);
        }
    }

    /**
     * Create a new UrlBuilder instance
     */
    public static UrlBuilder create(String baseUrl) {
        return new UrlBuilder(baseUrl);
    }
}
