package com.vipulfb.hn.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.vipulfb.hn.R;
import com.vipulfb.hn.adapters.StoryAdapter;
import com.vipulfb.hn.service.HttpService;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchTopStories();
    }

    private void fetchTopStories() {
        HttpService.getInstance().getTopStories(new Callback<ArrayList<String>>() {
            @Override
            public void success(ArrayList<String> stringList, Response response) {
                if (stringList.size() > 0) {
                    setupStoryAdapter(stringList);

                } else {
                    Toast.makeText(MainActivity.this, "No story", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }

        });

    }

    private void setupStoryAdapter(ArrayList<String> stringList) {
        RecyclerView storyRecyclerView = (RecyclerView) findViewById(R.id.story_recycler_view);
        storyRecyclerView.setHasFixedSize(true);
        storyRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        StoryAdapter storyAdapter = new StoryAdapter(this, stringList);
        storyRecyclerView.setAdapter(storyAdapter);

    }
}
