package com.tradeschool.bhaskarsabnis.BlueshoreFinancial;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.BlueshoreFinancial.clientapp3.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.tradeschool.bhaskarsabnis.BlueshoreFinancial.youtubeAPI.YoutubeAPI;
import com.tradeschool.bhaskarsabnis.BlueshoreFinancial.youtubemodel.VideoData;
import com.tradeschool.bhaskarsabnis.BlueshoreFinancial.youtubemodel.VideoSucess;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Learn extends AppCompatActivity  {
    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    private RecyclerView recyclerView;
    ImageView iv, iv1, iv2,iv3,iv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
        if (config.screenHeightDp < 616)
        {
            setContentView(R.layout.activity_learn_small);
        }
        else if (config.screenHeightDp >= 616 && config.screenHeightDp <=650)
        {
            setContentView(R.layout.activity_learn_medium);
        }
        else
        {
            setContentView(R.layout.activity_learn);
        }
       // setContentView(R.layout.activity_learn);
        iv = (ImageView) findViewById(R.id.home_learn);
        iv1 = (ImageView) findViewById(R.id.notification_learn);
        iv2 = (ImageView) findViewById(R.id.logout_learn);
        iv3 = (ImageView) findViewById(R.id.contact_learn);
        iv4 = (ImageView) findViewById(R.id.feed_learn);
        //youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        //youTubeView.initialize(Config.YOUTUBE_API_KEY,this);

        recyclerView=findViewById(R.id.youtuberecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Learn.this, MainActivity.class);
                startActivity(i);
            }
        });

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Learn.this, Tips_Notification.class);
                startActivity(i);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();

                Intent i = new Intent(Learn.this, LoginActivity.class);
                finish();
                startActivity(i);
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Learn.this, Contact.class);
                startActivity(i);
            }
        });
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Learn.this, Feeds.class);
                startActivity(i);
            }
        });

        Call<VideoSucess> call=YoutubeAPI.getApiInterface().getVideo();
        call.enqueue(new Callback<VideoSucess>() {
            @Override
            public void onResponse(Call<VideoSucess> call, Response<VideoSucess> response) {
                VideoSucess videoSucess=response.body();
                List<VideoData> videoDataList=videoSucess.getData();
                YouTubeAdapter adapter=new YouTubeAdapter(videoDataList,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<VideoSucess> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT);
            }
        });

    }

   /* @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo("JrGp4ofULzQ"); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), youTubeInitializationResult.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubeView;
    }*/




}
