package com.kpu.cashwalktmap;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ydwin on 2016-08-30.
 */
public interface NetworkService {

    // ID를 인자값으로 받아오는
    @GET("/users/{id}")
    Call<Data> getLoginId(@Path("id") String id );

    // 데이터 형식의 데이터를 아이디를 찾아서 넣어줌
    //@FormUrlEncoded
    @PUT("users/{id}/")
    Call<Data> updateData(@Path("id") String id, @Body Data data);
}