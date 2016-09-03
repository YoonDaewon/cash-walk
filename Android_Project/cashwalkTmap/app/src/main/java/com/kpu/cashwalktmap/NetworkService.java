package com.kpu.cashwalktmap;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by ydwin on 2016-08-30.
 */
public interface NetworkService {
    @POST("/Data")
    Call<Data> post_food(@Body Data food);

    @GET("/Data")
    Call <List<Data>> getAllFood();

    @GET("/Data/{id}")
    Call <Data> getNameFood( @Path("id") String name );

    @GET("/users/{id}")
    Call<Data> login(@Path("id") String userId);

    @GET("/")
    Call<Data> index();
}