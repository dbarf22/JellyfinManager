package org.JellyfinProject.dataTypes.queueTypes;

import java.util.ArrayList;
import java.util.Collections;

public class Queue {

    ArrayList<Media> mediaArray;

    public Queue() {
         mediaArray = new ArrayList<>();
    }

    public void addToQueue(Media m) {
        mediaArray.add(m);
    }

    public void removeFromQueue(Media m) {
        mediaArray.remove(m);
    }

    public void moveUp(Media m) throws IndexOutOfBoundsException {
        int location = mediaArray.indexOf(m);
        if (location - 1 < 0) {
            System.out.println("Can't go any higher.");
        } else {
            Collections.swap(mediaArray, location, location - 1);
        }
    }

    public void moveDown(Media m) throws IndexOutOfBoundsException {
        int location = mediaArray.indexOf(m);
        if (location + 1 > mediaArray.size() - 1) {
            System.out.println("Can't go any lower.");
        } else {
            Collections.swap(mediaArray, location, location + 1);
        }
    }

    public ArrayList<Media> getMediaArray() {
        return mediaArray;
    }

    public void printQueue() {
        for (Media m : mediaArray) {
            System.out.println(m.getName());

        }
    }

}
