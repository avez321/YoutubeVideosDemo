package com.example.avi_pc.youtubedemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Snippet {

    public Snippet(){}
    @JsonProperty("title")
    private String title;
    @JsonProperty("thumbnails")
    private Thumbnails thumbnails;
    @JsonProperty("channelTitle")
    private String channelTitle;

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("thumbnails")
    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    @JsonProperty("thumbnails")
    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    @JsonProperty("channelTitle")
    public String getChannelTitle() {
        return channelTitle;
    }

    @JsonProperty("channelTitle")
    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

}