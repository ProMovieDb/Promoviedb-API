package com.promoviedb.config;

/**
 * Configuration class for ProMovieDB client
 */
public class ClientConfig {
    private final String apiKey;
    private final String baseUrl;
    private final String apiVersion;
    private final String language;
    private final int connectTimeout;
    private final int readTimeout;
    private final int writeTimeout;
    private final boolean enableLogging;

    private ClientConfig(Builder builder) {
        this.apiKey = builder.apiKey;
        this.baseUrl = builder.baseUrl;
        this.apiVersion = normalizeVersion(builder.apiVersion);
        this.language = builder.language;
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
        this.enableLogging = builder.enableLogging;
    }

    /**
     * Normalize API version to ensure consistent format
     * Examples: "1" -> "v1", "v1" -> "v1", "2" -> "v2"
     */
    private static String normalizeVersion(String version) {
        if (version == null || version.trim().isEmpty()) {
            return "v1";
        }
        version = version.trim();
        if (version.startsWith("v") || version.startsWith("V")) {
            return version.toLowerCase();
        }
        return "v" + version;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getLanguage() {
        return language;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

    public boolean isEnableLogging() {
        return enableLogging;
    }

    public static class Builder {
        private String apiKey;
        private String baseUrl = "https://api.promoviedb.com";
        private String apiVersion = "v1";
        private String language = "en";
        private int connectTimeout = 30;
        private int readTimeout = 30;
        private int writeTimeout = 30;
        private boolean enableLogging = false;

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder apiVersion(String apiVersion) {
            this.apiVersion = apiVersion;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder connectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder readTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder writeTimeout(int writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder enableLogging(boolean enableLogging) {
            this.enableLogging = enableLogging;
            return this;
        }

        public ClientConfig build() {
            if (apiKey == null || apiKey.trim().isEmpty()) {
                throw new IllegalArgumentException("API key is required");
            }
            if (baseUrl == null || baseUrl.trim().isEmpty()) {
                throw new IllegalArgumentException("Base URL is required");
            }
            return new ClientConfig(this);
        }
    }
}
