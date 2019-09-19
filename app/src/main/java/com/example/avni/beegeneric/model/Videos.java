package com.example.avni.beegeneric.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Videos{
    private int id;
    private String videoUrl;
    public Videos(){

    }

    public Videos(int id, String videoUrl) {
        this.id = id;
        this.videoUrl = videoUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
