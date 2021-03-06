package com.example.avi_pc.youtubedemo.remote;


import com.example.avi_pc.youtubedemo.model.Item;
import com.example.avi_pc.youtubedemo.model.YoutubeSearchResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface YoutubeDemoAppApi {
    @GET("/youtube/v3/search")
    Observable<YoutubeSearchResponse> searchVideos(@Query("key") String key, @Query("q") String query, @Query("part") String part, @Query("maxResults") int maxResults, @Query("order") String order, @Query("fields") String fields, @Query("pageToken") String pageToken);

    @GET("/youtube/v3/videos")
    Observable<YoutubeSearchResponse> getVideoLikes(@Query("id") String videoId, @Query("key") String key, @Query("part") String part, @Query("fields") String fields);


}