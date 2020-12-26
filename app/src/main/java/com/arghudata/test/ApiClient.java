package com.arghudata.test;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Binaya Gurung on 17/03/18.
 */

public class ApiClient {

    public static final String BASE_URL = "https://wrestlenews.net/busyhh/test/GET/";
    public static Retrofit retrofit;

    public static Retrofit getApiClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}