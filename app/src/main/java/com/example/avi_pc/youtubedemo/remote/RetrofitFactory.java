package com.example.avi_pc.youtubedemo.remote;

import android.content.Context;
import android.util.Log;

import com.example.avi_pc.youtubedemo.BuildConfig;
import com.example.avi_pc.youtubedemo.Constants;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;



public class RetrofitFactory {
    private static final String TAG = RetrofitFactory.class.getSimpleName();
    private static final String SSL_CONTEXT = "SSL";

    public static Retrofit getRetrofit(Context context) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
    }

    private static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
       /* builder.addInterceptor(new Interceptor() {
            @Override public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("key", "AIzaSyCy_c5T8gSUM9j8_s4KtHwXoglwWIi4opw") .build();
                return chain.proceed(request);
            }
        });*/

        builder.addInterceptor(logging);
        return builder.build();
    }

}