package com.github.yeeun_yun97.LottoPredictAppBackEnd.retrofit;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.dto.ReadLottoDataResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LottoService {

    @GET("common.do")
    Call<ReadLottoDataResponse> readLottoData(@Query("method")String method,@Query("drwNo")int roundNo);
}
