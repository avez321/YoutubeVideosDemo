package com.example.avi_pc.youtubedemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Id {

    @JsonProperty("videoId")
    private String videoId;

    @JsonProperty("videoId")
    public String getVideoId() {
        return videoId;
    }

    @JsonProperty("videoId")
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

}
