package com.example.ravitejam.myapplication;

public class ApiUtils {
 
//    public ApiUtils(String url) {this.BASE_URL = url;}
 
    public static String BASE_URL;
 
    public static Service getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(Service.class);
    }
}