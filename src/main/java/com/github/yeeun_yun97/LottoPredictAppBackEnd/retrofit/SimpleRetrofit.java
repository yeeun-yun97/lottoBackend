package com.github.yeeun_yun97.LottoPredictAppBackEnd.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.github.yeeun_yun97.LottoPredictAppBackEnd.constant.Constant.LOTTO_API_BASE_URL;

public class SimpleRetrofit {
    private static String BASE_URL=LOTTO_API_BASE_URL;

    public static LottoService getPublicService() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request newRequest = chain.request().newBuilder()
                    .build();
            return chain.proceed(newRequest);
        }).build();
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).client(client).build().create(LottoService.class);
    }
}
