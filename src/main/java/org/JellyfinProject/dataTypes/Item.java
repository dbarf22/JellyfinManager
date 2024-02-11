package org.JellyfinProject.dataTypes;

import com.google.gson.annotations.SerializedName;

import java.io.Serial;

public class Item {

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
    public String getAirTime() {
        return airTime;
    }

    public String getSourceType() {
        return sourceType;
    }

    public String[] getGenres() {
        return genres;
    }

    public String getOverview() {
        return overview;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public String getSeriesID() {
        return seriesID;
    }

    public boolean isHasSubtitles() {
        return hasSubtitles;
    }

    public String getOfficialRating() {
        return officialRating;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public String getSeasonID() {
        return seasonID;
    }

    public String getMediaType() {
        return mediaType;
    }


    public String getType() {
        return type;
    }
    public int getCompletionPercentage() {
        return completionPercentage;
    }
    public int getIndex() {
        return index;
    }

    public Long getRuntimeTicks() {
        return runtimeTicks;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    @SerializedName("SeasonName")
    public String seasonName;
    @SerializedName("RunTimeTicks")
    public Long runtimeTicks;
    @SerializedName("IndexNumber")
    private int index;
    @SerializedName("Name") //episode or movie name
    private String name;

    @SerializedName("Id") //episode or movie id
    private String id;

    @SerializedName("SourceType")
    private String sourceType;

    @SerializedName("Genres")
    private String[] genres;

    @SerializedName("Overview")
    private String overview;

    @SerializedName("SeriesName")
    private String seriesName;
    
    @SerializedName("SeriesId")
    private String seriesID;

    @SerializedName("HasSubtitles")
    private boolean hasSubtitles;

    @SerializedName("OfficialRating")
    private String officialRating;

    @SerializedName("ProductionYear")
    private int productionYear;

    @SerializedName("SeasonId")
    private String seasonID;
    @SerializedName("MediaType")
    private String mediaType;

    @SerializedName("Type")
    private String type;
    @SerializedName("AirTime") //episode or movie
    private String airTime;

    @SerializedName("CompletionPercentage")
    private int completionPercentage;

} //Item
