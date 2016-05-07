package com.vipulfb.hn.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.LruCache;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.vipulfb.hn.AppConstants;
import com.vipulfb.hn.models.Comment;
import com.vipulfb.hn.models.StoryDetail;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * Created by Vipul Sharma on 5/7/2016.
 */

public class HttpService {
    private static HttpService instance = new HttpService();
    private Context context;
    private LruCache<String, Object> cache = new LruCache<>(4 * 1024 * 1024);
    private HnService hnService;

    private HttpService() {
    }

    public static HttpService getInstance() {
        return instance;
    }

    public void setup(Context context) {
        this.context = context;

        OkHttpClient okHttpClient = new OkHttpClient();
        OkClient okClient = new OkClient(okHttpClient);

        Cache cache = new Cache(context.getCacheDir(), 1024);
        okHttpClient.setCache(cache);
        RestAdapter jsonRestAdapter = new RestAdapter.Builder()
//                .setLogLevel(RestAdapter.LogLevel.FULL).setLog(new AndroidLog("Retrofit"))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(AppConstants.HOST)
                .setClient(okClient)
                .build();
        hnService = jsonRestAdapter.create(HnService.class);
    }

    public SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(AppConstants.PREFERENCE_SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }


    public void getTopStories(Callback<ArrayList<String>> callback) {
        hnService.getTopStories(callback);
    }

    public void getStoryDetail(String storyId, Callback<StoryDetail> callback) {
        hnService.getStoryDetail(storyId, callback);
    }

    public void getCommentDetail(String commentId, Callback<Comment> callback) {
        hnService.getCommentDetail(commentId, callback);
    }

}

