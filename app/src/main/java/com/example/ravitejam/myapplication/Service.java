package com.example.ravitejam.myapplication;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Service {

    @POST("/switch")
    @FormUrlEncoded
    Call<POST> sendToPi(@Field("pinNumber") String pinNumber, @Field("status") String status);
}
