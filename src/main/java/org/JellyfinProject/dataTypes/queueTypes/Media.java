package org.JellyfinProject.dataTypes.queueTypes;

import com.google.gson.annotations.SerializedName;
import org.JellyfinProject.JellyfinController;

import java.io.IOException;

public class Media {

    JellyfinController jf = new JellyfinController();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSeries() {
        return series;
    }

    @SerializedName("Id")
    private String id;
    @SerializedName("Name")
    private String name;
    private String type;

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    private int length;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @SerializedName("Overview")
    private String overview;

    public int getYear() {
        return year;
    }

    @SerializedName("ProductionYear")
    private int year;
    private String series;


// Id, name, position, duration, season, year


    /**
     *  The media constructor for a movie.
     * @param s Id for the movie.
     * @param n The name of the movie.
     * @param t The type.
     */
    public Media(String s, String n, String t, int r) throws IOException, InterruptedException {
        id = s;
        name = n;
        type = t;
        year = r;
        length = (jf.grabMovieLength(name, year));
    }

    public void printMediaData() {
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Year: " + getYear());
        System.out.println("Length: " + getLength() + " minutes");
    }

    /**
     *
     * @param s The ID for the episode.
     * @param n The name of the episode.
     * @param t The type.
     * @param p The series of the episode.
     */
    public Media(String s, String n, String t, String p) {
        id = s;
        name = n;
        type = t;
        series = p;
    }


}
