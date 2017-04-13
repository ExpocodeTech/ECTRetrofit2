package com.expocodetech.ectretrofit2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.expocodetech.ectretrofit2.R;
import com.expocodetech.ectretrofit2.model.Post;

import java.util.ArrayList;

/**
 * Created by ExpoCode Tech http://expocodetech.com
 */

public class PostRecyclerViewAdapter extends RecyclerView.Adapter {
    private ArrayList<Post> mPostsData;
    public PostRecyclerViewAdapter(ArrayList<Post> posts){
        mPostsData = posts;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return mPostsData.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTvTitle;
        public ListViewHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }

        public void bindView(int position){
            mTvTitle.setText(mPostsData.get(position).getTitle());
        }

        @Override
        public void onClick(View view) {

        }
    }
}
