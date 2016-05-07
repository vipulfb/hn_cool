package com.vipulfb.hn.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vipulfb.hn.models.StoryDetail;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vipul Sharma on 5/7/2016.
 */
public class Utils {
    public static StoryDetail getLocalStory(String storyID) {
        final SharedPreferences sharedPreferences = HttpService.getInstance().getSharedPreferences();
        String storyDetailString = sharedPreferences.getString("STORY" + storyID, "");

        if (TextUtils.isEmpty(storyDetailString)) {
            return null;
        } else {
            //Ordering states already saved, get the list with previous Ordering States
            Gson gson = new Gson();
            return gson.fromJson(storyDetailString, StoryDetail.class);
        }
    }

    public static void saveLocalStory(StoryDetail storyDetail) {
        final SharedPreferences sharedPreferences = HttpService.getInstance().getSharedPreferences();
        Gson gson = new Gson();
        String storyDetailString = gson.toJson(storyDetail);
        sharedPreferences.edit().putString("STORY" + storyDetail.getId(), storyDetailString).apply();
    }

}
