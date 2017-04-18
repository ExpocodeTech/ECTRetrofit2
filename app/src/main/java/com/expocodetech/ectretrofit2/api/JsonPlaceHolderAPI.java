package com.expocodetech.ectretrofit2.api;

import com.expocodetech.ectretrofit2.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by expocodetech on 13/4/17.
 */

public interface JsonPlaceHolderAPI {
    @GET("posts")
    Call<List<Post>> getAllPosts();
}
