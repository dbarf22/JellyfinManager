package org.JellyfinProject.dataTypes.queueTypes;

import com.google.gson.annotations.SerializedName;

public class Episode {
    public Episode(String id) {
        this.id = id;
    } //Episode - constructor

    public String getEpisodeTitle() {
        return episodeTitle;
    }

    public String getSeasonID() {
        return seasonID;
    }

    public void setSeasonID(String seasonID) {
        this.seasonID = seasonID;
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public void setEpisodeTitle(String episodeTitle) {
        this.episodeTitle = episodeTitle;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAirTime(String airTime) {
        this.airTime = airTime;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public boolean isHasSubtitles() {
        return hasSubtitles;
    }

    public void setHasSubtitles(boolean hasSubtitles) {
        this.hasSubtitles = hasSubtitles;
    }

    public String getId() {
        return id;
    }

    public String getAirTime() {
        return airTime;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    @SerializedName("ProductionYear")
    public int productionYear;

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    String seasonID;
    String seasonName;
    public int indexNumber;
    public String seriesId;
    public String seriesName;
    public String episodeTitle;
    public String id;
    String airTime;
    public String[] genres;
    boolean hasSubtitles;
    private int length;

} //Episode