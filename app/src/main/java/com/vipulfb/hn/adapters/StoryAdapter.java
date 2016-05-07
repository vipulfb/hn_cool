package com.vipulfb.hn.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vipulfb.hn.AppConstants;
import com.vipulfb.hn.R;
import com.vipulfb.hn.activities.WebViewActivity;
import com.vipulfb.hn.models.StoryDetail;
import com.vipulfb.hn.service.HttpService;
import com.vipulfb.hn.service.Utils;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Vipul Sharma on 5/7/2016.
 */
public class StoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mContext;
    private final ArrayList<String> mStoryIdList;

    public StoryAdapter(Context context, ArrayList<String> stringList) {
        this.mStoryIdList = stringList;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_story, parent, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0, 15, 0, 15);
        v.setLayoutParams(layoutParams);

        StoryViewHolder vh = new StoryViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StoryViewHolder) {
            final StoryViewHolder storyViewHolder = (StoryViewHolder) holder;
            View view = storyViewHolder.itemView;

            final String storyId = mStoryIdList.get(position);

            StoryDetail storyDetail = Utils.getLocalStory(storyId);

            if (storyDetail != null) {
                setupStory(storyDetail, storyViewHolder);
            } else {
                storyViewHolder.tvTitle.setText("Title");
                storyViewHolder.tvCommentCount.setText("Total comments: _");
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            }

            loadShowStory(storyId, storyViewHolder);
        }
    }

    private void loadShowStory(final String storyId, final StoryViewHolder storyViewHolder) {
        HttpService.getInstance().getStoryDetail(storyId, new Callback<StoryDetail>() {
            @Override
            public void success(final StoryDetail storyDetail, Response response) {
                setupStory(storyDetail, storyViewHolder);
                Utils.saveLocalStory(storyDetail);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void setupStory(final StoryDetail storyDetail, final StoryViewHolder storyViewHolder) {
        storyViewHolder.tvTitle.setText(storyDetail.getTitle());
        storyViewHolder.tvCommentCount.setText("Total comments: " + String.valueOf(storyDetail.getDescendants()));

        View view = storyViewHolder.itemView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink(storyDetail.getTitle(), storyDetail.getUrl());
            }
        });

    }

    private void openLink(String title, String url) {
        Intent intent = new Intent(mContext, WebViewActivity.class);
        intent.putExtra(AppConstants.INTENT_PARAM_WEBVIEW_URL, url);
        intent.putExtra(AppConstants.INTENT_PARAM_WEBVIEW_HEADER, title);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mStoryIdList.size();
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvCommentCount;

        public StoryViewHolder(View itemView) {
            super(itemView);
            this.tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            this.tvCommentCount = (TextView) itemView.findViewById(R.id.tv_comment_count);
        }
    }
}
