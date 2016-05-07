package com.vipulfb.hn.service;

import com.vipulfb.hn.models.Comment;
import com.vipulfb.hn.models.StoryDetail;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Vipul Sharma on 5/7/2016.
 */
public interface HnService {
    @GET("/topstories.json")
    public void getTopStories(Callback<ArrayList<String>> callback);

    @GET("/item/{story_id}.json")
    public void getStoryDetail(@Path("story_id") String storyId, Callback<StoryDetail> callback);

    @GET("/item/{comment_id}.json")
    public void getCommentDetail(@Path("comment_id") String commentId, Callback<Comment> callback);

}

