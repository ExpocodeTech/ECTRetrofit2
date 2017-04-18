package com.expocodetech.ectretrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.expocodetech.ectretrofit2.adapter.PostRVAdapter;
import com.expocodetech.ectretrofit2.api.JsonPlaceHolderAPI;
import com.expocodetech.ectretrofit2.model.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements PostRVAdapter.PostRVAdapterListener, Callback<List<Post>> {
    private static final String TAG = MainActivity.class.getName();

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private PostRVAdapter mPostRVAdapter;
    private ArrayList<Post> mPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPosts = new ArrayList<Post>();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rcView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mPostRVAdapter = new PostRVAdapter(this, mPosts);
        mRecyclerView.setAdapter(mPostRVAdapter);

        //loadFakePosts();
        loadPosts();

    }

    public void loadFakePosts() {
        for (int i = 0; i < 10; i++) {
            Post aPost = new Post();
            aPost.setId(String.valueOf(i));
            aPost.setUserId(String.valueOf(i));
            aPost.setTitle("Title ".concat(String.valueOf(i)));
            aPost.setBody("Body ".concat(String.valueOf(i)));
            mPosts.add(aPost);
        }
        mPostRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnItemClicked(Post aPost) {
        Toast.makeText(this, aPost.getTitle(), Toast.LENGTH_SHORT).show();
    }

    public void loadPosts() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        Call<List<Post>> call = jsonPlaceHolderAPI.getAllPosts();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
        if(response.isSuccessful()) {
            List<Post> postsList = response.body();
            for (Post post : postsList) {
                mPosts.add(post);
            }
            mPostRVAdapter.notifyDataSetChanged();
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {
        t.printStackTrace();
    }
}









  /*public void loadPosts() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        Call<List<Post>> call = jsonPlaceHolderAPI.allPosts();
        call.enqueue(this);
    }*/

    /*@Override
    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
        if(response.isSuccessful()) {
            List<Post> postsList = response.body();
            for (Post post : postsList) {
                mPosts.add(post);
            }
            mPostRVAdapter.notifyDataSetChanged();
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {
        t.printStackTrace();
    }*/
