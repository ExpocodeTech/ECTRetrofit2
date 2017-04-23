package com.expocodetech.ectretrofit2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.expocodetech.ectretrofit2.R;
import com.expocodetech.ectretrofit2.model.Comment;
import com.expocodetech.ectretrofit2.model.Post;

import java.util.ArrayList;

/**
 * Created by ExpoCode Tech http://expocodetech.com
 */

public class CommentRVAdapter extends RecyclerView.Adapter<CommentRVAdapter.ViewHolder> {
    private ArrayList<Comment> mDataset;
    private Context mContext;

    public interface PostRVAdapterListener{
        void OnItemClicked(Comment aComment);
    }

    public CommentRVAdapter(Context context, ArrayList<Comment> comments){
        mDataset = comments;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Comment aComment= mDataset.get(position);
        holder.mTvTitle.setText(aComment.getBody());
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mContext instanceof PostRVAdapterListener) {
                    ((PostRVAdapterListener) mContext).OnItemClicked(aComment);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        TextView mTvTitle;
        ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            mTvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}
