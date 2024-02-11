package org.JellyfinProject.dataTypes;
import org.JellyfinProject.*;
import org.JellyfinProject.dataTypes.queueTypes.Episode;
import org.JellyfinProject.dataTypes.queueTypes.Media;
import org.JellyfinProject.dataTypes.queueTypes.Movies;
import org.JellyfinProject.dataTypes.queueTypes.Queue;

import java.io.IOException;
import java.util.ArrayList;
public class Database {

    ArrayList<Episode> tvList;
    ArrayList<Movies> moviesList;
    Queue contentQueue;

    public ArrayList<Episode> getTvList() {
        return tvList;
    }

    public ArrayList<Movies> getMoviesList() {
        return moviesList;
    }

    public Queue getContentQueue() {
        return contentQueue;
    }

    public void populateDB() throws IOException, InterruptedException {
        JellyfinController jellyfinController = new JellyfinController();
        jellyfinController.loadConfig();
        Item[] rawData = jellyfinController.getJellyfinItems();
        moviesList  = new ArrayList<Movies>();
        tvList = new ArrayList<Episode>();
        contentQueue = new Queue();

        for (int i = 0; i < rawData.length; i++) {
            if (rawData[i].getType().equals("Episode")) {
                tvList.add(new Episode(rawData[i].getId()));
                tvList.get(tvList.size() - 1).setEpisodeTitle(rawData[i].getName());
                tvList.get(tvList.size() - 1).setSeriesName(rawData[i].getSeriesName());
                tvList.get(tvList.size() - 1).setSeriesId(rawData[i].getSeriesID());
                tvList.get(tvList.size() - 1).setIndexNumber(rawData[i].getIndex());
                tvList.get(tvList.size() - 1).setSeasonID(rawData[i].getSeriesID());
                tvList.get(tvList.size() - 1).setSeasonName(rawData[i].getSeasonName());
                tvList.get(tvList.size() - 1).setGenres(rawData[i].getGenres());
                tvList.get(tvList.size() - 1)
                        .setLength(jellyfinController.grabEpisodeLength(rawData[i].getSeriesName(),
                                rawData[i].getProductionYear(), rawData[i].getIndex(),
                                rawData[i].getSeasonName()));
            } else if (rawData[i].getType().equals("Movie")) {
                moviesList.add(new Movies(rawData[i].getId()));
                moviesList.get(moviesList.size() - 1).setMovieTitle(rawData[i].getName());
                moviesList.get(moviesList.size() - 1).setGenres(rawData[i].getGenres());
                moviesList.get(moviesList.size() - 1).setProductionYear(rawData[i].getProductionYear());
                moviesList.get(moviesList.size() - 1)
                        .setLength(jellyfinController.grabMovieLength(rawData[i].getName(),
                                rawData[i].getProductionYear()));
            } //if sorting movies and tv show
        }  //for - loop through raw data array
    } //populateDB


    public void printDatabase() {
        for (int i = 0; i < tvList.size(); i++) {
            System.out.println("Episode Title: " + tvList.get(i).episodeTitle);
            System.out.println("Episode ID: " + tvList.get(i).id);
            System.out.println("Episode index: " + tvList.get(i).indexNumber);
            System.out.println("Series Name: " + tvList.get(i).seriesName);
            System.out.println("Series ID: " + tvList.get(i).seriesId);
            System.out.println("Episode length: " + tvList.get(i).getLength());
            System.out.println("");
        }
        for (int i = 0; i < moviesList.size(); i++) {
            System.out.println("Movie Title: " + moviesList.get(i).movieTitle);
            System.out.println("Movie ID: " + moviesList.get(i).id);
            System.out.println("Movie Length: " + moviesList.get(i).getLength());
            System.out.println("");
        } //for - movies
    } //printDatabase

    /**
     * Converts Movie into generic "media" type with id, name, and content type to be used for the queue.
     * @param m The movie to supply to be added to the queue
     */
    public void movieToQueue(Movies m) throws IOException, InterruptedException {
        Media s = new Media(m.getId(), m.getMovieTitle(), "Movie", m.getProductionYear());
        contentQueue.addToQueue(s);
    } // movieToQueue

    /**
     * Converts Episode into generic "media" type with id, name, and content type to be used for the queue.
     * @param m The episode to supply to be added to the queue
     */
    public void episodeToQueue(Episode m) throws IOException, InterruptedException {
        Media s = new Media(m.getId(), m.getEpisodeTitle(), "Episode", m.getProductionYear());
        contentQueue.addToQueue(s);
    } // movieToQueue



} //Database
