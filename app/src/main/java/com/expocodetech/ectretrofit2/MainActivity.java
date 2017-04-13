package com.expocodetech.ectretrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.expocodetech.ectretrofit2.adapter.PostRecyclerViewAdapter;
import com.expocodetech.ectretrofit2.model.Post;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PostRecyclerViewAdapter mPostRecyclerViewAdapter;
    private ArrayList<Post> mPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPosts = new ArrayList<Post>();

        mRecyclerView = (RecyclerView) findViewById(R.id.rcView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mPostRecyclerViewAdapter = new PostRecyclerViewAdapter(mPosts);
        mRecyclerView.setAdapter(mPostRecyclerViewAdapter);

        loadFakePosts();
        loadPosts();
    }

    public void loadFakePosts() {
        int i = 0;
        do {
            Post aPost = new Post();
            aPost.setId(String.valueOf(i));
            aPost.setUserId(String.valueOf(i));
            aPost.setTitle("Title ".concat(String.valueOf(i)));
            aPost.setBody("Body ".concat(String.valueOf(i)));
            mPosts.add(aPost);
        } while (i < 10);
        mPostRecyclerViewAdapter.notifyDataSetChanged();
    }

    public void loadPosts() {

    }
}
