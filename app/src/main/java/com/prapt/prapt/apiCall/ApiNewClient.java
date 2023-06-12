package com.prapt.prapt.apiCall;



import android.content.Context;
import android.util.Log;
import com.prapt.prapt.utils.Config;
import com.prapt.prapt.utils.SharedPreferencesClass;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiNewClient {
    private static final String BASE_URL = Config.BASEURL;

    private static ApiRequest apiRequests;


    // Singleton Instance of APIRequests
    public static ApiRequest getInstance(Context applicationContext) {
        if (apiRequests == null) {

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @NotNull
                        @Override
                        public Response intercept(@NotNull Chain chain) throws IOException {
                            Request request= chain.request();
                            Request newRequest = request.newBuilder()
                                    .header("token", SharedPreferencesClass.retrieveData(applicationContext,Config.LOGIN_TOKEN))
                                    .header("Content-Type","application/json")
                                    .build();

                            Log.e("TAGgggggggggg", "intercept: "+SharedPreferencesClass.retrieveData(applicationContext,Config.LOGIN_TOKEN) );
                            return chain.proceed(newRequest);
                        }
                    })

                    .addInterceptor(httpLoggingInterceptor)
                    .build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiRequests = retrofit.create(ApiRequest.class);

            return apiRequests;
        }
        else {
            return apiRequests;
        }
    }


}
