package com.example.avi_pc.youtubedemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Thumbnails {

    @JsonProperty("default")
    private Default _default;

    @JsonProperty("default")
    public Default getDefault() {
        return _default;
    }

    @JsonProperty("default")
    public void setDefault(Default _default) {
        this._default = _default;
    }
}
