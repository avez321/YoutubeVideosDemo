package com.example.avi_pc.youtubedemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Statistics {

    @JsonProperty("likeCount")
    private String likeCount;

    /**
     * No args constructor for use in serialization
     */
    public Statistics() {
    }

    /**
     * @param likeCount
     */
    public Statistics(String likeCount) {
        super();
        this.likeCount = likeCount;
    }

    @JsonProperty("likeCount")
    public String getLikeCount() {
        return likeCount;
    }

    @JsonProperty("likeCount")
    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

}