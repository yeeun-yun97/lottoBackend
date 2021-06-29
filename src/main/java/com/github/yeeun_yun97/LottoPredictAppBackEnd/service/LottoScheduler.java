package com.github.yeeun_yun97.LottoPredictAppBackEnd.service;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.dto.ReadLottoDataResponse;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.entity.Round;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.exception.LottoApiConnectionFailedException;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.exception.LottoApiFailedException;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.exception.SimpleException;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.repository.RoundRepository;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.retrofit.SimpleRetrofit;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.github.yeeun_yun97.LottoPredictAppBackEnd.constant.Constant.CRON_LOTTO_SATURDAY_NIGHT;
import static com.github.yeeun_yun97.LottoPredictAppBackEnd.constant.Constant.LOTTO_API_METHOD;

@AllArgsConstructor
@Component
public class LottoScheduler {
    private RoundRepository roundRepository;

    @Scheduled(cron = CRON_LOTTO_SATURDAY_NIGHT)
    public void addLottoResult() throws SimpleException {
        Round thisRound =roundRepository.findFirstByOrderByRoundNumDesc();

        SimpleRetrofit.getPublicService().readLottoData(LOTTO_API_METHOD,thisRound.getRoundNum()).enqueue(new Callback<ReadLottoDataResponse>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<ReadLottoDataResponse> call, Response<ReadLottoDataResponse> response) {
                if(response.isSuccessful()){
                    ReadLottoDataResponse lottoDataResponse= response.body();
                    thisRound.setFirst(lottoDataResponse.getDrwtNo1());
                    thisRound.setSecond(lottoDataResponse.getDrwtNo2());
                    thisRound.setThird(lottoDataResponse.getDrwtNo3());
                    thisRound.setFourth(lottoDataResponse.getDrwtNo4());
                    thisRound.setFifth(lottoDataResponse.getDrwtNo5());
                    thisRound.setSixth(lottoDataResponse.getDrwtNo6());
                    thisRound.setBonus(lottoDataResponse.getBnusNo());
                    roundRepository.save(thisRound);
                }else{
                    throw new LottoApiFailedException();
                }
            }

            @SneakyThrows
            @Override
            public void onFailure(Call<ReadLottoDataResponse> call, Throwable t) {
                throw new LottoApiConnectionFailedException();
            }
        });

        int defaultNum=0;
        Round nextRound = Round.builder()
                .id(thisRound.getId()+1)
                .roundNum(thisRound.getRoundNum()+1)
                .endDate(thisRound.getEndDate().plusWeeks(1L))
                .first(defaultNum)
                .second(defaultNum)
                .third(defaultNum)
                .fourth(defaultNum)
                .fifth(defaultNum)
                .sixth(defaultNum)
                .bonus(defaultNum)
                .build();
        this.roundRepository.save(nextRound);
    }
}
