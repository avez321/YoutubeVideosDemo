package com.example.avi_pc.youtubedemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Default {

    @JsonProperty("url")
    private String url;
    @JsonProperty("width")
    private int width;
    @JsonProperty("height")
    private int height;

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("width")
    public int getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(int width) {
        this.width = width;
    }

    @JsonProperty("height")
    public int getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(int height) {
        this.height = height;
    }
}