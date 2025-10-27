package com.promoviedb.config;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for ClientConfig
 */
public class ClientConfigTest {

    @Test
    public void testBuildWithRequiredParams() {
        ClientConfig config = new ClientConfig.Builder()
                .apiKey("test-api-key")
                .baseUrl("https://api.promoviedb.com")
                .build();

        assertEquals("test-api-key", config.getApiKey());
        assertEquals("https://api.promoviedb.com", config.getBaseUrl());
        assertEquals("v1", config.getApiVersion()); // default
        assertEquals("en", config.getLanguage()); // default
        assertEquals(30, config.getConnectTimeout()); // default
        assertEquals(30, config.getReadTimeout()); // default
        assertEquals(30, config.getWriteTimeout()); // default
        assertFalse(config.isEnableLogging()); // default
    }

    @Test
    public void testBuildWithAllParams() {
        ClientConfig config = new ClientConfig.Builder()
                .apiKey("test-api-key")
                .baseUrl("https://api.promoviedb.com")
                .language("zh-CN")
                .connectTimeout(60)
                .readTimeout(90)
                .writeTimeout(120)
                .enableLogging(true)
                .build();

        assertEquals("test-api-key", config.getApiKey());
        assertEquals("https://api.promoviedb.com", config.getBaseUrl());
        assertEquals("zh-CN", config.getLanguage());
        assertEquals(60, config.getConnectTimeout());
        assertEquals(90, config.getReadTimeout());
        assertEquals(120, config.getWriteTimeout());
        assertTrue(config.isEnableLogging());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuildWithoutApiKey() {
        new ClientConfig.Builder()
                .baseUrl("https://api.promoviedb.com")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuildWithEmptyApiKey() {
        new ClientConfig.Builder()
                .apiKey("")
                .baseUrl("https://api.promoviedb.com")
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuildWithWhitespaceApiKey() {
        new ClientConfig.Builder()
                .apiKey("   ")
                .baseUrl("https://api.promoviedb.com")
                .build();
    }

    @Test
    public void testBuildWithDefaultBaseUrl() {
        ClientConfig config = new ClientConfig.Builder()
                .apiKey("test-api-key")
                .build();

        assertEquals("https://api.promoviedb.com", config.getBaseUrl());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuildWithEmptyBaseUrl() {
        new ClientConfig.Builder()
                .apiKey("test-api-key")
                .baseUrl("")
                .build();
    }

    @Test
    public void testBuilderChaining() {
        ClientConfig config = new ClientConfig.Builder()
                .apiKey("key")
                .baseUrl("url")
                .language("ja")
                .connectTimeout(10)
                .readTimeout(20)
                .writeTimeout(30)
                .enableLogging(false)
                .build();

        assertNotNull(config);
        assertEquals("key", config.getApiKey());
    }

    @Test
    public void testDefaultApiVersion() {
        ClientConfig config = new ClientConfig.Builder()
                .apiKey("test-api-key")
                .build();

        assertEquals("v1", config.getApiVersion());
    }

    @Test
    public void testCustomApiVersion() {
        ClientConfig config = new ClientConfig.Builder()
                .apiKey("test-api-key")
                .apiVersion("v2")
                .build();

        assertEquals("v2", config.getApiVersion());
    }

    @Test
    public void testApiVersionNormalization() {
        // Test "1" -> "v1"
        ClientConfig config1 = new ClientConfig.Builder()
                .apiKey("test-api-key")
                .apiVersion("1")
                .build();
        assertEquals("v1", config1.getApiVersion());

        // Test "2" -> "v2"
        ClientConfig config2 = new ClientConfig.Builder()
                .apiKey("test-api-key")
                .apiVersion("2")
                .build();
        assertEquals("v2", config2.getApiVersion());

        // Test "v1" stays "v1"
        ClientConfig config3 = new ClientConfig.Builder()
                .apiKey("test-api-key")
                .apiVersion("v1")
                .build();
        assertEquals("v1", config3.getApiVersion());

        // Test "V1" -> "v1" (uppercase to lowercase)
        ClientConfig config4 = new ClientConfig.Builder()
                .apiKey("test-api-key")
                .apiVersion("V1")
                .build();
        assertEquals("v1", config4.getApiVersion());
    }

    @Test
    public void testApiVersionWithNullAndEmpty() {
        // Test null version defaults to v1
        ClientConfig config1 = new ClientConfig.Builder()
                .apiKey("test-api-key")
                .apiVersion(null)
                .build();
        assertEquals("v1", config1.getApiVersion());

        // Test empty version defaults to v1
        ClientConfig config2 = new ClientConfig.Builder()
                .apiKey("test-api-key")
                .apiVersion("")
                .build();
        assertEquals("v1", config2.getApiVersion());

        // Test whitespace version defaults to v1
        ClientConfig config3 = new ClientConfig.Builder()
                .apiKey("test-api-key")
                .apiVersion("   ")
                .build();
        assertEquals("v1", config3.getApiVersion());
    }

    @Test
    public void testApiVersionWithCustomString() {
        // Test custom version strings (for future flexibility)
        ClientConfig config = new ClientConfig.Builder()
                .apiKey("test-api-key")
                .apiVersion("beta")
                .build();
        assertEquals("vbeta", config.getApiVersion());
    }
}
