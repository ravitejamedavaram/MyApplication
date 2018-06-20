package com.example.ravitejam.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service {

    @POST("/switch")
    @FormUrlEncoded
    Call<Message> sendToPii(@Field("pinNumber") String pinNumber, @Field("status") String status);


    @POST("/switchPin")
    Call<Message> sendToPi(@Body Message message);

    @GET("/")
    Call<Object> test();
}
