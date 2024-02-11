package org.JellyfinProject;

import org.JellyfinProject.dataTypes.*;
import org.JellyfinProject.dataTypes.queueTypes.*;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Database db = new Database();
        try{
        db.populateDB();
        db.printDatabase();

        ArrayList<Episode> episodeList = db.getTvList();
        ArrayList<Movies> movieList = db.getMoviesList();

        db.movieToQueue(movieList.get(0));
        db.episodeToQueue(episodeList.get(0));
        Queue q = db.getContentQueue();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failure to populate DB");
            } //try-catch
        } // constructor

}