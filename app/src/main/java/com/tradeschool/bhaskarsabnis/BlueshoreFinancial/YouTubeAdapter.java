package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.BlueshoreFinancial.clientapp3.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.tradeschool.bhaskarsabnis.BlueshoreFinancial.youtubemodel.VideoData;

import java.util.List;

public class YouTubeAdapter extends RecyclerView.Adapter<YouTubeAdapter.YouTubeViewHolder> {
List<VideoData> videoData;

Context context;
String url=null;
    public YouTubeAdapter(List<VideoData> videoData,Context context) {
        this.videoData = videoData;
        this.context=context;
    }

    @NonNull
    @Override
    public YouTubeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_youtuberecycler,parent,false);
        return new YouTubeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YouTubeViewHolder holder, int position) {
        VideoData data=videoData.get(position);
        url=data.getVIDEOURL();
        //holder.youTubeView.initialize(Config.YOUTUBE_API_KEY,this);
       holder.youTubeView.loadUrl(url);
        holder.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoData.size();
    }


    /* @Override
     public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
         youTubePlayer.cueVideo(url);
     }

     @Override
     public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
         if (youTubeInitializationResult.isUserRecoverableError()) {
             youTubeInitializationResult.getErrorDialog((Activity)context, 1).show();
         } else {
             String error = String.format("Error in Youtube Player", youTubeInitializationResult.toString());
             Toast.makeText(context, error, Toast.LENGTH_LONG).show();
         }
     }
 */
    public class YouTubeViewHolder extends RecyclerView.ViewHolder {
        WebView youTubeView;
      // YouTubePlayerView youTubeView;
        Button show;
        public YouTubeViewHolder(View itemView) {
            super(itemView);
            youTubeView=itemView.findViewById(R.id.youtube_view);
            show=itemView.findViewById(R.id.showbtn);
            youTubeView.setWebViewClient(new WebViewClient());
            youTubeView.getSettings().setJavaScriptEnabled(true);
            youTubeView.setWebChromeClient(new WebChromeClient());
        }
    }
}
