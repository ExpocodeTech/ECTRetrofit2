package com.expocodetech.ectretrofit2.api;

import com.expocodetech.ectretrofit2.model.Comment;
import com.expocodetech.ectretrofit2.model.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by expocodetech on 13/4/17.
 */

public interface JsonPlaceHolderAPI {
    @GET("posts")
    Call<List<Post>> allPosts();

    @GET("comments")
    Call<List<Comment>> getCommentsOfPost(@Query("postId") String postId);
}
