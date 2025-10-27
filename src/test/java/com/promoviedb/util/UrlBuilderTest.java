package com.promoviedb.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for UrlBuilder
 */
public class UrlBuilderTest {

    @Test
    public void testBuildWithoutParams() {
        String url = UrlBuilder.create("https://api.promoviedb.com/endpoint")
                .build();

        assertEquals("https://api.promoviedb.com/endpoint", url);
    }

    @Test
    public void testBuildWithSingleParam() {
        String url = UrlBuilder.create("https://api.promoviedb.com/endpoint")
                .addQueryParam("key", "value")
                .build();

        assertEquals("https://api.promoviedb.com/endpoint?key=value", url);
    }

    @Test
    public void testBuildWithMultipleParams() {
        String url = UrlBuilder.create("https://api.promoviedb.com/endpoint")
                .addQueryParam("key1", "value1")
                .addQueryParam("key2", "value2")
                .addQueryParam("key3", "value3")
                .build();

        assertTrue(url.contains("key1=value1"));
        assertTrue(url.contains("key2=value2"));
        assertTrue(url.contains("key3=value3"));
        assertTrue(url.startsWith("https://api.promoviedb.com/endpoint?"));
    }

    @Test
    public void testBuildWithIntegerParam() {
        String url = UrlBuilder.create("https://api.promoviedb.com/endpoint")
                .addQueryParam("page", 1)
                .addQueryParam("size", 20)
                .build();

        assertTrue(url.contains("page=1"));
        assertTrue(url.contains("size=20"));
    }

    @Test
    public void testBuildWithBooleanParam() {
        String url = UrlBuilder.create("https://api.promoviedb.com/endpoint")
                .addQueryParam("active", true)
                .addQueryParam("deleted", false)
                .build();

        assertTrue(url.contains("active=true"));
        assertTrue(url.contains("deleted=false"));
    }

    @Test
    public void testBuildWithNullParam() {
        String url = UrlBuilder.create("https://api.promoviedb.com/endpoint")
                .addQueryParam("key1", "value1")
                .addQueryParam("key2", (String) null)
                .addQueryParam("key3", "value3")
                .build();

        assertTrue(url.contains("key1=value1"));
        assertFalse(url.contains("key2"));
        assertTrue(url.contains("key3=value3"));
    }

    @Test
    public void testBuildWithEmptyParam() {
        String url = UrlBuilder.create("https://api.promoviedb.com/endpoint")
                .addQueryParam("key1", "value1")
                .addQueryParam("key2", "")
                .addQueryParam("key3", "value3")
                .build();

        assertTrue(url.contains("key1=value1"));
        assertFalse(url.contains("key2"));
        assertTrue(url.contains("key3=value3"));
    }

    @Test
    public void testBuildWithSpecialCharacters() {
        String url = UrlBuilder.create("https://api.promoviedb.com/endpoint")
                .addQueryParam("query", "hello world")
                .addQueryParam("special", "a&b=c")
                .build();

        assertTrue(url.contains("query=hello+world"));
        assertTrue(url.contains("special=a%26b%3Dc"));
    }

    @Test
    public void testBuildWithNullInteger() {
        String url = UrlBuilder.create("https://api.promoviedb.com/endpoint")
                .addQueryParam("page", (Integer) null)
                .addQueryParam("size", 20)
                .build();

        assertFalse(url.contains("page"));
        assertTrue(url.contains("size=20"));
    }

    @Test
    public void testBuildWithNullBoolean() {
        String url = UrlBuilder.create("https://api.promoviedb.com/endpoint")
                .addQueryParam("active", (Boolean) null)
                .addQueryParam("deleted", false)
                .build();

        assertFalse(url.contains("active"));
        assertTrue(url.contains("deleted=false"));
    }
}
