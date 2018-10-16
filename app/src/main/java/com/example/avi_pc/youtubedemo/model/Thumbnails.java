package com.example.avi_pc.youtubedemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Thumbnails {

    public Thumbnails(){}

    @JsonProperty("medium")
    private Default medium;

    @JsonProperty("medium")
    public Default getMeduim() {
        return medium;
    }

    @JsonProperty("medium")
    public void setDefault(Default _default) {
        this.medium = _default;
    }
}
