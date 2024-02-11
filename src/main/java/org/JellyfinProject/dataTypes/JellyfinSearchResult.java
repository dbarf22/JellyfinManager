package org.JellyfinProject.dataTypes;

import com.google.gson.annotations.SerializedName;

public class JellyfinSearchResult {

    @SerializedName("Items")
    public Item[] items;

    public Item[] getItems() {
        return items;
    }
}
