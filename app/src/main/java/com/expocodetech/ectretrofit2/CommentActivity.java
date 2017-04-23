package com.expocodetech.ectretrofit2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.expocodetech.ectretrofit2.adapter.CommentRVAdapter;
import com.expocodetech.ectretrofit2.adapter.PostRVAdapter;
import com.expocodetech.ectretrofit2.api.JsonPlaceHolderAPI;
import com.expocodetech.ectretrofit2.model.Comment;
import com.expocodetech.ectretrofit2.model.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentActivity extends AppCompatActivity implements PostRVAdapter.PostRVAdapterListener,
        Callback<List<Comment>> {
    private static final String TAG = MainActivity.class.getName();

    public static final String POST_ID = "post-id";
    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
    private CommentRVAdapter mCommentRVAdapter;
    private ArrayList<Comment> mComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mComments = new ArrayList<Comment>();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rcView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mCommentRVAdapter = new CommentRVAdapter(this, mComments);
        mRecyclerView.setAdapter(mCommentRVAdapter);

        Intent intent = getIntent();
        if (intent != null) {
            String postId = intent.getStringExtra(POST_ID);
            getCommentsOfPost(postId);
        }
    }

    public void getCommentsOfPost(String postId) {
        if (postId == null)
            return;
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        Call<List<Comment>> call = jsonPlaceHolderAPI.getCommentsOfPost(postId);
        call.enqueue(this);
    }

    @Override
    public void OnItemClicked(Post aPost) {
        Toast.makeText(this, aPost.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
        if(response.isSuccessful()) {
            List<Comment> CommentsList = response.body();
            mComments.clear();
            for (Comment comment : CommentsList) {
                mComments.add(comment);
            }
            mCommentRVAdapter.notifyDataSetChanged();
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Comment>> call, Throwable t) {
        t.printStackTrace();
    }

}
