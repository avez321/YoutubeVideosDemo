package com.example.avi_pc.youtubedemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {

    @JsonProperty("id")
    private Id id;
    @JsonProperty("snippet")
    private Snippet snippet;

    @JsonProperty("id")
    public Id getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Id id) {
        this.id = id;
    }

    @JsonProperty("snippet")
    public Snippet getSnippet() {
        return snippet;
    }

    @JsonProperty("snippet")
    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

}