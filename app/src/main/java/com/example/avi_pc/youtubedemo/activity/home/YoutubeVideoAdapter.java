package com.example.avi_pc.youtubedemo.activity.home;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.avi_pc.youtubedemo.Constants;
import com.example.avi_pc.youtubedemo.R;
import com.example.avi_pc.youtubedemo.databinding.RowYoutubeVideoBinding;
import com.example.avi_pc.youtubedemo.injection.ApplicationContext;
import com.example.avi_pc.youtubedemo.model.Item;
import com.example.avi_pc.youtubedemo.util.GlideUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

class YoutubeVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Item> items;
    private Context context;
    private YouTubePlayer prePlayer;


    @Inject
    YoutubeVideoAdapter(@ApplicationContext Context context) {
        items = new ArrayList<>();
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RowYoutubeVideoBinding rowMyAppointmentsBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.row_youtube_video, viewGroup, false);
        return new YoutubeVideoViewHolder(rowMyAppointmentsBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final YoutubeVideoViewHolder youtubeVideoViewHolder = (YoutubeVideoViewHolder) holder;
        final Item item = items.get(position);

        youtubeVideoViewHolder.adapterBinding.txtVideoTitle.setText(item.getSnippet().getTitle());
        GlideUtil.showImage(youtubeVideoViewHolder.adapterBinding.youtubeThumNail, item.getSnippet().getThumbnails().getMeduim().getUrl(), 0);

        youtubeVideoViewHolder.adapterBinding.imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youtubeVideoViewHolder.adapterBinding.youtubeThumNail.setVisibility(View.GONE);
                youtubeVideoViewHolder.adapterBinding.imgPlay.setVisibility(View.GONE);
                youtubeVideoViewHolder.adapterBinding.youTubePlayerView.setVisibility(View.VISIBLE);
                youtubeVideoViewHolder.adapterBinding.youTubePlayerView.initialize(Constants.API_KEY, new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                        youTubePlayer.loadVideo(item.getId().getVideoId());
                        prePlayer = youTubePlayer;

                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
            }
        });

        if(prePlayer!=null) {
            prePlayer.release();
            prePlayer = null;

        }

        youtubeVideoViewHolder.adapterBinding.youtubeThumNail.setVisibility(View.VISIBLE);
        youtubeVideoViewHolder.adapterBinding.imgPlay.setVisibility(View.VISIBLE);
        youtubeVideoViewHolder.adapterBinding.youTubePlayerView.setVisibility(View.GONE);



        youtubeVideoViewHolder.adapterBinding.txtLikes.setText(item.getStatistics().getLikeCount());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setList(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addList(List<Item> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }


    class YoutubeVideoViewHolder extends RecyclerView.ViewHolder {
        private RowYoutubeVideoBinding adapterBinding;

        public YoutubeVideoViewHolder(@NonNull RowYoutubeVideoBinding adapterBinding) {
            super(adapterBinding.getRoot());
            this.adapterBinding = adapterBinding;
        }
    }
}


