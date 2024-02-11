package org.JellyfinProject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.JellyfinProject.dataTypes.*;
import org.JellyfinProject.dataTypes.queueTypes.Media;
import org.JellyfinProject.dataTypes.tmdb.Result;
import org.JellyfinProject.dataTypes.tmdb.SearchResult;
import org.JellyfinProject.dataTypes.tmdb.TMDBMedia;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;

@RestController
public class JellyfinController {

    Properties config = new Properties();
    public String jellyfinApiKey; // Initialized in loadConfig
    public String jellyfinAddress; // Initialized in loadConfig
    public String jellyfinId; // Initialized in loadConfig
    public static String tmdbApiKey; // Initialized in loadConfig
    public static String tmdbAddress; //Initialized in loadConfig
    public static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static void main(String[] args) throws IOException, InterruptedException {
        JellyfinController j = new JellyfinController();
        j.loadConfig();


    } //main
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    /**
     * Loads the config.properties file to get API keys from.
     */
    public void loadConfig() {
        String configPath = "resources/config.properties";
        try (FileInputStream configFileStream = new FileInputStream(configPath)) {
            config.load(configFileStream);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } //try-catch
        jellyfinApiKey = config.getProperty("jellyfin.ApiKey");
        jellyfinAddress = config.getProperty("jellyfin.Address");
        jellyfinId = config.getProperty("jellyfin.UserID");
        tmdbApiKey = config.getProperty("tmdb.ApiKey");
        tmdbAddress = config.getProperty("tmdb.Address");
    } //loadConfig

    /**
     *
     * @return Array of items from Jellyfin.
     * @throws IOException If HTTP response does not equal 200.
     * @throws InterruptedException If HttpResponse is interrupted.
     */

    public Item[] getJellyfinItems() throws IOException, InterruptedException {

        String uri = String.format("%sItems?%s&recursive=true&api_key=%s", jellyfinAddress,
                jellyfinId, jellyfinApiKey);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();
        HttpResponse<String> response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IOException(response.toString());
        } //if

        String jsonString = response.body();

        JellyfinSearchResult j = gson.fromJson(jsonString, JellyfinSearchResult.class);
        Item[] items = j.getItems();

        return items;
    } //getJellyfinItems

    /**
     *
     * @return An arraylist of Jellyfin sessions.
     * @throws IOException If HTTP response does not equal 200.
     * @throws InterruptedException If HttpResponse is interrupted.
     */
    public ArrayList<Sessions> getSessions() throws IOException, InterruptedException {
       // http://100.122.109.103:8096/Devices?api_key=f30857bd110848ef9086e3e2b872adbe

        String uri = String.format("%sSessions?api_key=%s", jellyfinAddress, jellyfinApiKey);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException(response.toString());
        } //if

        String jsonString = response.body();

        Sessions[] s = gson.fromJson(jsonString, Sessions[].class);

        ArrayList<Sessions> sessions = new ArrayList<>();

        for (Sessions i : s) {
            sessions.add(i);
        } //for

        return sessions;
    } //getDeviceIds

    /**
     *
     * @param sessionId The sessionID to connect to.
     * @param itemId The itemID to play.
     * @param playCommand The playCommand to give.
     * @throws IOException If HTTP response does not equal 200.
     * @throws InterruptedException If HttpResponse is interrupted.
     */
    public void playId(String sessionId, String itemId, String playCommand, int subtitleIndex) throws IOException, InterruptedException {

        String uri = String.format("%sSessions/%s/Playing?playCommand=%s&itemIds=%s&subtitleStreamIndex=%d&api_key=%s",
                jellyfinAddress, sessionId, playCommand, itemId, subtitleIndex, jellyfinApiKey);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            System.out.println("Device not available.");
        } else if (response.statusCode() != 204) {
//            throw new IOException(response.toString());
            System.out.println("Hello");
        } //if

        System.out.println("Sent.");

    } //playMedia

    /**
     *
     * @param sessionId The sessionID to connect to.
     * @param playCommand The playCommand to send.
     * @throws IOException If HTTP response does not equal 200.
     * @throws InterruptedException If HttpResponse is interrupted.
     */
    public void sendPlayCommand(String sessionId, String playCommand) throws IOException, InterruptedException {

        String uri = String.format("%sSessions/%s/Playing/%s?api_key=%s",
                jellyfinAddress, sessionId, playCommand, jellyfinApiKey);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            System.out.println("Device not available.");
        } else if (response.statusCode() != 204) {
            throw new IOException(response.toString());
        } //if
    } //sendPlayCommand

    /**
     *
     * @param q The name of the movie to search.
     * @return The runtime of the queried movie in minutes (as int).
     * @throws IOException If HTTP response does not equal 200.
     * @throws InterruptedException If HttpResponse is interrupted.
     */
    public static int grabMovieLength(String q, int year) throws IOException, InterruptedException {

        String query = URLEncoder.encode(q, StandardCharsets.UTF_8);
        String uri = String.format("%s3/search/movie?api_key=%s&query=%s&year=%d",
                tmdbAddress, tmdbApiKey, query, year);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            System.out.println("Error.");
        } else if (response.statusCode() != 200) {
            throw new IOException(response.toString());
        } //if

        String jsonString = response.body();

        SearchResult j = gson.fromJson(jsonString, SearchResult.class);
        Result[] results = j.getResults();
        int id = results[0].getId();
        // Searches TMDB for supplied query, saves the Search Result file to a parsed SearchResult object,
        // takes first result in the result[] of SearchResult (we assume the first result is accurate)
        // and saves that movie's ID. That is then used to search with the next API call.

        String movieLink = String.format("%s3/movie/%s?api_key=%s", tmdbAddress, id, tmdbApiKey);

        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create(movieLink))
                .build();
        HttpResponse<String> response2 = httpClient
                .send(request2, HttpResponse.BodyHandlers.ofString());
        if (response2.statusCode() == 404) {
            System.out.println("Error.");
        } else if (response2.statusCode() != 200) {
            throw new IOException(response.toString());
        } //if

        String jsonString2 = response2.body();
        TMDBMedia tmdbMedia = gson.fromJson(jsonString2, TMDBMedia.class);
        return tmdbMedia.getRuntime();
        // Using the ID from earlier, we search for that movie on TMDB. The result is saved to a TMDBMovie
        // object, which we then call the .getRuntime function on and return that value as an int.

    } //grabMovieLength

    /**
     *
     * @param q The name of the show to search.
     * @param year The year of the show premiere.
     * @param n The episode index.
     * @param s The season string.
     * @return The runtime of the queried movie in minutes (as int).
     * @throws IOException If HTTP response does not equal 200.
     * @throws InterruptedException If HttpResponse is interrupted.
     */
    public static int grabEpisodeLength(String q, int year, int n, String s) throws IOException, InterruptedException {

        String query = URLEncoder.encode(q, StandardCharsets.UTF_8);
        String uri = String.format("%s3/search/tv?api_key=%s&query=%s&year=%d",
                tmdbAddress, tmdbApiKey, query, year);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            System.out.println("Error.");
        } else if (response.statusCode() != 200) {
            throw new IOException(response.toString());
        } //if

        String jsonString = response.body();

        SearchResult j = gson.fromJson(jsonString, SearchResult.class);
        Result[] results = j.getResults();
        int id = results[0].getId();
        // Searches TMDB for supplied query, saves the Search Result file to a parsed SearchResult object,
        // takes first result in the result[] of SearchResult (we assume the first result is accurate)
        // and saves that movie's ID. That is then used to search with the next API call.

        s = s.replaceAll("[^0-9]", "");
        int seasonNum = Integer.parseInt(s);
        String tvLink = String.format("%s3/tv/%s/season/%d/episode/%d?api_key=%s", tmdbAddress, id, seasonNum,
                n, tmdbApiKey);

        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create(tvLink))
                .build();
        HttpResponse<String> response2 = httpClient
                .send(request2, HttpResponse.BodyHandlers.ofString());
        if (response2.statusCode() == 404) {
            System.out.println("Error.");
        } else if (response2.statusCode() != 200) {
            throw new IOException(response.toString());
        } //if

        String jsonString2 = response2.body();
        TMDBMedia tmdbMedia = gson.fromJson(jsonString2, TMDBMedia.class);
        return tmdbMedia.getRuntime();
        // Using the ID from earlier, we search for that movie on TMDB. The result is saved to a TMDBMovie
        // object, which we then call the .getRuntime function on and return that value as an int.

    } //grabMovieLength

    public Media mediaMake(String s) throws IOException, InterruptedException {

        String uri = String.format("%sUsers/%s/Items/%s/?api_key=%s", jellyfinAddress, jellyfinId, s, jellyfinApiKey);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException(response.toString());
        } //if

        String jsonString = response.body();

        Media m = gson.fromJson(jsonString, Media.class);
        m.setLength(grabMovieLength(m.getName(), m.getYear()));

        return m;
    } //getDeviceIds

} //JellyfinResults
