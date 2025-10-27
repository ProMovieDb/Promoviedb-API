# 🎬 ProMovieDB API

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)](https://www.oracle.com/java/)
[![Version](https://img.shields.io/badge/version-1.0.1-green.svg)](https://github.com/promoviedb/promoviedb-api)

Official Java SDK for the **ProMovieDB API** - Your gateway to comprehensive movie, TV show, and entertainment data.

> 🌐 **Visit:** [www.promoviedb.com](https://www.promoviedb.com)
> 📧 **Support & API Key:** [admin@promoviedb.com](mailto:admin@promoviedb.com)

---

## 📖 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [API Capabilities](#-api-capabilities)
- [Installation](#-installation)
- [Quick Start](#-quick-start)
- [Authentication](#-authentication)
- [API Versioning](#-api-versioning)
- [Usage Guide](#-usage-guide)
- [API Coverage](#-api-coverage)
- [Advanced Features](#-advanced-features)
- [Best Practices](#-best-practices)
- [Support](#-support)
- [License](#-license)

---

## 🧩 Overview

The **ProMovieDB API** provides a simple, type-safe, and powerful way to integrate entertainment data into your Java applications. Built with modern best practices, this SDK offers seamless access to the ProMovieDB API's comprehensive movie, TV show, and celebrity database.

### Why ProMovieDB?

- **🎯 Comprehensive Data**: Access detailed information on millions of movies, TV shows, and people
- **🔄 Always Updated**: Real-time data updates with the latest releases and trending content
- **🌍 Multilingual**: Support for multiple languages and localized content
- **📊 Rich Metadata**: Detailed information including cast, crew, ratings, images, videos, and more
- **🚀 Developer Friendly**: RESTful API with comprehensive documentation

---

## ✨ Features

### SDK Features

- ✅ **Type-Safe API**: Strongly typed model classes for compile-time safety
- ✅ **Easy Configuration**: Simple builder pattern for client initialization
- ✅ **Automatic Retries**: Built-in retry mechanism for failed requests
- ✅ **Connection Pooling**: Efficient resource management with OkHttp
- ✅ **Comprehensive Error Handling**: Specific exception types for different scenarios
- ✅ **Logging Support**: Optional request/response logging for debugging
- ✅ **Thread-Safe**: Safe to use in multi-threaded environments
- ✅ **Well Tested**: 100% test coverage with 55+ unit and integration tests
- ✅ **Production Ready**: Battle-tested and optimized for production use

### Technical Stack

- **HTTP Client**: OkHttp 4.12.0 (industry-standard, high-performance)
- **JSON Parsing**: Gson 2.10.1 (fast and reliable)
- **Logging**: SLF4J 2.0.9 (flexible logging facade)
- **Build Tool**: Gradle 8.x
- **Java Compatibility**: Java 8+

---

## 🗂️ API Capabilities

The ProMovieDB API provides access to a wealth of entertainment data across multiple categories:

### 🎥 Movies

Access comprehensive movie data including:

- **Movie Details**: Complete metadata including title, overview, release date, runtime, budget, revenue
- **Popular Movies**: Trending and popular movies updated daily
- **Now Playing**: Movies currently in theaters
- **Upcoming Releases**: Upcoming movie releases
- **Top Rated**: Highest-rated movies of all time
- **Videos & Trailers**: Official trailers, teasers, and behind-the-scenes content
- **Images**: High-quality posters, backdrops, and promotional images
- **Cast & Crew**: Complete credits with actor and crew information
- **Release Dates**: International release dates and certifications

### 📺 TV Shows

Explore television content with:

- **TV Series Details**: Episode counts, air dates, network information, status
- **Popular Shows**: Trending TV series
- **Airing Today**: Shows broadcasting today
- **Top Rated**: Highest-rated TV shows
- **Season Information**: Detailed season and episode data
- **Videos & Images**: Trailers, screenshots, and promotional materials
- **Cast & Crew**: Regular cast and guest stars
- **Content Ratings**: Age ratings and content advisories by region

### 👤 People

Discover information about actors, directors, and crew:

- **Person Details**: Biography, birthday, birthplace, profile images
- **Movie Credits**: Complete filmography as cast or crew
- **TV Credits**: Television appearances and roles
- **Combined Credits**: Unified view of all work
- **Images**: Profile pictures and photo galleries
- **Known For**: Most popular and recognizable works

### 🔍 Search

Powerful search capabilities:

- **Movie Search**: Find movies by title with fuzzy matching
- **TV Search**: Search TV shows across all series
- **Person Search**: Discover actors, directors, and crew members
- **Multi-Search**: Search across movies, TV, and people simultaneously
- **Advanced Filters**: Filter by language, adult content, release year, and more

### 🎭 Genres

Organized content categorization:

- **Movie Genres**: Action, Adventure, Comedy, Drama, Horror, Sci-Fi, and more
- **TV Genres**: Drama, Comedy, Documentary, Reality, and specialized categories
- **Multi-language Support**: Localized genre names

### 🏆 Certifications

Content rating information:

- **Movie Certifications**: MPAA ratings (G, PG, PG-13, R, NC-17) and international equivalents
- **TV Certifications**: TV-Y, TV-G, TV-PG, TV-14, TV-MA, and more
- **Regional Ratings**: Content ratings for different countries and regions

### 📊 Credits

Detailed credit information:

- **Cast Details**: Character names, order of appearance, profile images
- **Crew Details**: Job titles, departments, contribution details
- **Credit Tracking**: Unique credit IDs for precise tracking

### 💚 Status & Health

API monitoring:

- **Health Checks**: Verify API availability
- **Ping Endpoint**: Quick connectivity test

---

## 📦 Installation

### Gradle

Add the following dependency to your `build.gradle`:

```gradle
dependencies {
    implementation 'com.promoviedb:promoviedb-api:1.0.1'
}
```

### Maven

Add to your `pom.xml`:

```xml
<dependency>
    <groupId>com.promoviedb</groupId>
    <artifactId>promoviedb-api</artifactId>
    <version>1.0.1</version>
</dependency>
```

### Manual Installation

Download the JAR file from the [releases page](https://github.com/promoviedb/promoviedb-api/releases) and add it to your project's classpath.

---

## 🚀 Quick Start

Here's a simple example to get you started:

```java
import com.promoviedb.ProMovieDBClient;
import com.promoviedb.model.movie.MovieDetails;

public class QuickStart {
    static void main(String[] args) {
        // Initialize the client
        ProMovieDBClient client = new ProMovieDBClient.Builder()
                .apiKey("YOUR_API_KEY")  // Get your key from admin@promoviedb.com
                .language("en")
                .build();

        try {
            // Get movie details
            MovieDetails movie = client.movieService().getDetails("550");
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Rating: " + movie.getVoteAverage());

        } finally {
            client.close();
        }
    }
}
```

---

## 🔐 Authentication

All API requests require an API key.

### How to Get Your API Key

1. Visit [www.promoviedb.com](https://www.promoviedb.com)
2. Email [admin@promoviedb.com](mailto:admin@promoviedb.com) to request an API key
3. Include your use case and expected request volume

### Using Your API Key

The SDK automatically includes your API key in all requests:

```java
ProMovieDBClient client = new ProMovieDBClient.Builder()
        .apiKey("YOUR_API_KEY")  // Required
        .build();
```

> ⚠️ **Security Note**: Never commit your API key to version control. Use environment variables or secure configuration management.

---

## 📚 Usage Guide

### Client Configuration

The SDK provides flexible configuration options:

```java
ProMovieDBClient client = new ProMovieDBClient.Builder()
        .apiKey("YOUR_API_KEY")              // Required: Your API key
        .baseUrl("https://api.promoviedb.com")  // Optional: API base URL (default shown)
        .apiVersion("v1")                    // Optional: API version (default: "v1")
        .language("en")                      // Optional: Default language (default: "en")
        .connectTimeout(30)                  // Optional: Connect timeout in seconds (default: 30)
        .readTimeout(30)                     // Optional: Read timeout in seconds (default: 30)
        .writeTimeout(30)                    // Optional: Write timeout in seconds (default: 30)
        .enableLogging(true)                 // Optional: Enable request/response logging (default: false)
        .build();
```

### Movies API

#### Get Movie Details

Retrieve comprehensive information about a specific movie:

```java
// Basic details
MovieDetails movie = client.movieService().getDetails("550");
System.out.println("Title: " + movie.getTitle());
System.out.println("Overview: " + movie.getOverview());
System.out.println("Release Date: " + movie.getReleaseDate());
System.out.println("Rating: " + movie.getVoteAverage() + "/10");
System.out.println("Budget: $" + movie.getBudget());
System.out.println("Revenue: $" + movie.getRevenue());

// Get details with additional data
MovieDetails movieWithExtras = client.movieService()
        .getDetails("550", "en", "videos,credits,images");
```

#### Get Movie Videos

Access trailers, teasers, and clips:

```java
JsonObject videos = client.movieService().getVideos("550");
// Process video results (trailers, teasers, clips, etc.)
```

#### Get Movie Images

Retrieve posters, backdrops, and other images:

```java
JsonObject images = client.movieService().getImages("550");
// Access posters, backdrops, and logos
```

#### Get Movie Credits

Get cast and crew information:

```java
Credits credits = client.movieService().getCredits("550");

// Cast information
for (Cast actor : credits.getCast()) {
    System.out.println(actor.getName() + " as " + actor.getCharacter());
}

// Crew information
for (Crew member : credits.getCrew()) {
    System.out.println(member.getName() + " - " + member.getJob());
}
```

#### Get Release Dates

Access international release dates and certifications:

```java
JsonObject releaseDates = client.movieService().getReleaseDates("550");
```

### TV Shows API

#### Get TV Series Details

```java
TvSeriesDetails series = client.tvSeriesService().getDetails("1399");
System.out.println("Name: " + series.getName());
System.out.println("First Air Date: " + series.getFirstAirDate());
System.out.println("Number of Seasons: " + series.getNumberOfSeasons());
System.out.println("Number of Episodes: " + series.getNumberOfEpisodes());
System.out.println("Status: " + series.getStatus());
```

#### Get TV Videos & Images

```java
// Get videos (trailers, clips)
JsonObject tvVideos = client.tvSeriesService().getVideos("1399");

// Get images (posters, backdrops)
JsonObject tvImages = client.tvSeriesService().getImages("1399");
```

#### Get TV Credits

```java
// Latest season credits
Credits tvCredits = client.tvSeriesService().getCredits("1399");

// Aggregate credits (all seasons)
JsonObject aggregateCredits = client.tvSeriesService().getAggregateCredits("1399");
```

#### Get Content Ratings

```java
JsonObject ratings = client.tvSeriesService().getContentRatings("1399");
```

### People API

#### Get Person Details

```java
PersonDetails person = client.personService().getDetails("287");
System.out.println("Name: " + person.getName());
System.out.println("Birthday: " + person.getBirthday());
System.out.println("Place of Birth: " + person.getPlaceOfBirth());
System.out.println("Biography: " + person.getBiography());
```

#### Get Person Credits

```java
// Movie credits
JsonObject movieCredits = client.personService().getMovieCredits("287");

// TV credits
JsonObject tvCredits = client.personService().getTvCredits("287");

// Combined credits (movies + TV)
JsonObject combinedCredits = client.personService().getCombinedCredits("287");
```

#### Get Person Images

```java
JsonObject personImages = client.personService().getImages("287");
```

#### Track Changes

```java
JsonObject changes = client.personService()
        .getChanges("287", "2023-01-01", "2023-12-31", 1);
```

### Search API

#### Search Movies

```java
// Simple search
JsonObject movieResults = client.searchService().searchMovie("Inception");

// Advanced search with filters
JsonObject filteredResults = client.searchService()
        .searchMovie("Inception", "en", false, 1, true, 20);
```

#### Search TV Shows

```java
JsonObject tvResults = client.searchService().searchTv("Game of Thrones");
```

#### Search People

```java
JsonObject personResults = client.searchService().searchPerson("Tom Hanks");
```

#### Multi-Search

Search across movies, TV shows, and people simultaneously:

```java
JsonObject multiResults = client.searchService().searchMulti("Matrix");
// Results include movies, TV shows, and people related to "Matrix"
```

### Genres API

#### Get Movie Genres

```java
JsonObject movieGenres = client.genreService().getMovieGenres();
// Returns list of all movie genres (Action, Comedy, Drama, etc.)
```

#### Get TV Genres

```java
JsonObject tvGenres = client.genreService().getTvGenres();
// Returns list of all TV genres
```

### Certifications API

#### Get Movie Certifications

```java
JsonObject movieCertifications = client.certificationService()
        .getMovieCertifications();
// Returns movie ratings (G, PG, PG-13, R, etc.) by country
```

#### Get TV Certifications

```java
JsonObject tvCertifications = client.certificationService()
        .getTvCertifications();
// Returns TV ratings (TV-Y, TV-G, TV-PG, TV-14, TV-MA, etc.) by country
```

### Credits API

#### Get Credit Details

```java
JsonObject creditDetails = client.creditService().getDetails("credit-id-here");
```

### Status API

#### Check API Health

```java
// Quick health check
boolean isHealthy = client.statusService().isHealthy();
System.out.println("API Status: " + (isHealthy ? "Online" : "Offline"));

// Detailed ping
String response = client.statusService().ping();
```

---

## 📊 API Coverage

The SDK provides **100% coverage** of the ProMovieDB API:

| Category | Endpoints | Status |
|----------|-----------|--------|
| **Movies** | 5 endpoints | ✅ Complete |
| **TV Series** | 6 endpoints | ✅ Complete |
| **People** | 6 endpoints | ✅ Complete |
| **Search** | 4 endpoints | ✅ Complete |
| **Genres** | 2 endpoints | ✅ Complete |
| **Credits** | 1 endpoint | ✅ Complete |
| **Certifications** | 2 endpoints | ✅ Complete |
| **Status** | 1 endpoint | ✅ Complete |
| **Total** | **27 endpoints** | ✅ **100%** |

---

## 🔧 Advanced Features

### Error Handling

The SDK provides specific exception types for different error scenarios:

```java
import com.promoviedb.exception.*;

try {
    MovieDetails movie = client.movieService().getDetails("invalid-id");

} catch (AuthenticationException e) {
    // Handle authentication errors (401, 403)
    System.err.println("Invalid API key: " + e.getMessage());

} catch (RateLimitException e) {
    // Handle rate limit errors (429)
    System.err.println("Rate limit exceeded: " + e.getMessage());

} catch (ApiException e) {
    // Handle other API errors
    System.err.println("API error: " + e.getMessage());
    System.err.println("Status code: " + e.getStatusCode());
    System.err.println("Response body: " + e.getErrorBody());
}
```

### Language Support

Specify the language for localized content:

```java
// Set default language for all requests
ProMovieDBClient client = new ProMovieDBClient.Builder()
        .apiKey("YOUR_API_KEY")
        .language("zh-CN")  // Chinese
        .build();

// Or override per request
MovieDetails movie = client.movieService().getDetails("550", "ja", null);  // Japanese
```

Supported languages include: `en`, `zh-CN`, `ja`, `ko`, `fr`, `de`, `es`, `it`, `pt`, `ru`, and many more.

### Pagination

Handle paginated results:

```java
// Search with pagination
JsonObject page1 = client.searchService()
        .searchMovie("Avengers", "en", false, 1, true, 20);

JsonObject page2 = client.searchService()
        .searchMovie("Avengers", "en", false, 2, true, 20);
```

### Logging

Enable detailed logging for debugging:

```java
ProMovieDBClient client = new ProMovieDBClient.Builder()
        .apiKey("YOUR_API_KEY")
        .enableLogging(true)  // Enable request/response logging
        .build();
```

### Custom Timeouts

Configure timeouts for different network conditions:

```java
ProMovieDBClient client = new ProMovieDBClient.Builder()
        .apiKey("YOUR_API_KEY")
        .connectTimeout(60)  // 60 seconds for slow networks
        .readTimeout(90)     // 90 seconds for large responses
        .build();
```

### Resource Management

Always close the client when done to free resources:

```java
ProMovieDBClient client = new ProMovieDBClient.Builder()
        .apiKey("YOUR_API_KEY")
        .build();

try {
    // Use the client
    MovieDetails movie = client.movieService().getDetails("550");
} finally {
    client.close();  // Release connection pool and other resources
}
```

Or use try-with-resources (if implementing AutoCloseable):

```java
try (ProMovieDBClient client = new ProMovieDBClient.Builder()
        .apiKey("YOUR_API_KEY")
        .build()) {
    // Use the client
    MovieDetails movie = client.movieService().getDetails("550");
} // Automatically closed
```

---

## 💡 Best Practices

### 1. Cache Responses

Reduce API calls and improve performance by caching responses:

```java
// Example with a simple cache
Map<String, MovieDetails> movieCache = new HashMap<>();

public MovieDetails getMovie(String movieId) {
    if (!movieCache.containsKey(movieId)) {
        movieCache.put(movieId, client.movieService().getDetails(movieId));
    }
    return movieCache.get(movieId);
}
```

### 2. Handle Rate Limits

Respect API rate limits to avoid service interruptions:

```java
try {
    MovieDetails movie = client.movieService().getDetails("550");
} catch (RateLimitException e) {
    // Wait and retry
    Thread.sleep(60000);  // Wait 1 minute
    // Retry request
}
```

### 3. Use Specific Exceptions

Handle different error types appropriately:

```java
try {
    MovieDetails movie = client.movieService().getDetails(movieId);
} catch (AuthenticationException e) {
    // Fix API key issue
} catch (RateLimitException e) {
    // Implement backoff strategy
} catch (ApiException e) {
    // Handle general API errors
}
```

### 4. Optimize API Calls

Use `append_to_response` to get multiple data types in one request:

```java
// Instead of multiple calls
MovieDetails movie = client.movieService().getDetails("550");
JsonObject videos = client.movieService().getVideos("550");
Credits credits = client.movieService().getCredits("550");

// Use a single call
MovieDetails movieWithAll = client.movieService()
        .getDetails("550", "en", "videos,credits,images");
```

### 5. Validate Input

Always validate user input before making API calls:

```java
public MovieDetails getMovie(String movieId) {
    if (movieId == null || movieId.trim().isEmpty()) {
        throw new IllegalArgumentException("Movie ID cannot be empty");
    }
    return client.movieService().getDetails(movieId);
}
```

### 6. Use Connection Pooling

The SDK automatically uses connection pooling, but ensure you're reusing the client:

```java
// Good: Single client instance
private static final ProMovieDBClient client = new ProMovieDBClient.Builder()
        .apiKey("YOUR_API_KEY")
        .build();

// Bad: Creating new client for each request
public MovieDetails getMovie(String id) {
    ProMovieDBClient client = new ProMovieDBClient.Builder().build();  // Don't do this!
    return client.movieService().getDetails(id);
}
```

---

## 📞 Support

### Getting Help

If you need assistance with the SDK or API:

- **📧 Email**: [admin@promoviedb.com](mailto:admin@promoviedb.com)
- **🌐 Website**: [www.promoviedb.com](https://www.promoviedb.com)
- **📖 Documentation**: Full API documentation available on the website

### API Key Requests

To obtain an API key:

1. Visit [www.promoviedb.com](https://www.promoviedb.com)
2. Email [admin@promoviedb.com](mailto:admin@promoviedb.com) with:
   - Your name and organization
   - Intended use case
   - Expected request volume
   - Project timeline

### Rate Limits

For information about rate limits or to request higher limits, contact [admin@promoviedb.com](mailto:admin@promoviedb.com).

### Reporting Issues

Found a bug? Please report it via email with:
- SDK version
- Java version
- Code snippet to reproduce
- Error message and stack trace

---

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

```
Copyright 2024 ProMovieDB

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

---

## 🎯 About ProMovieDB

**ProMovieDB** is an open-source entertainment database providing comprehensive information about movies, TV shows, and people in the entertainment industry. Our mission is to make entertainment data accessible to developers worldwide.

Visit [www.promoviedb.com](https://www.promoviedb.com) to explore our full API capabilities and join our community of developers building amazing entertainment applications.

---

**Happy Coding! 🚀**
