package com.tradeschool.bhaskarsabnis.BlueshoreFinancial.youtubeAPI;

import com.tradeschool.bhaskarsabnis.BlueshoreFinancial.youtubemodel.VideoSucess;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class YoutubeAPI {
    private static final String BASE_URL = "http://recommended-spools.000webhostapp.com/BlueshoreFinancial/html/api/";
    public static  ApiInterface apiInterface=null;
    public static ApiInterface getApiInterface(){
        if(apiInterface== null){
            Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            apiInterface=retrofit.create(ApiInterface.class);
        }
        return apiInterface;
    }
    public interface ApiInterface {
        //GET method name (" ")its end point.
        @GET("getVideos.php")
        Call<VideoSucess> getVideo();

    }
}
