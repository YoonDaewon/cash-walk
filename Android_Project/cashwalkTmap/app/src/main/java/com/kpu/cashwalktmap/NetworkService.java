package com.kpu.cashwalktmap;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ydwin on 2016-08-30.
 */
public interface NetworkService {
    @POST("/users")
    Call<Data> post_food(@Body Data food);

    @GET("/users/{id}")
    Call<Data> getLoginId(@Path("id") String id );

}