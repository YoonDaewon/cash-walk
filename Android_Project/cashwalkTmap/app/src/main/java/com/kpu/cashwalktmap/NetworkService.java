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
    @POST("/Food")
    Call<Data> post_food(@Body Data food);

    @GET("/Food")
    Call <List<Data>> getAllFood();

    @GET("/Food/{name}")
    Call <Data> getNameFood( @Path("name") String name );
}