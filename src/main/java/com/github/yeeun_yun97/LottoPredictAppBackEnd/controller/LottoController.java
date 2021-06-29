package com.github.yeeun_yun97.LottoPredictAppBackEnd.controller;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.dto.LottoPredictResponse;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.dto.ReadLottoRecentRoundResponse;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.exception.SimpleException;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.service.LottoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/lotto")
public class LottoController {
    private final LottoService lottoService;

    @GetMapping("/predict")
    public ResponseEntity<LottoPredictResponse> getPredictedNum(@RequestParam Long user_id) throws SimpleException {
        return ResponseEntity.ok(lottoService.getPredictNum(user_id));
    }

    @GetMapping("/readHitData")
    public ResponseEntity<ReadLottoRecentRoundResponse> readLottoRecentRound() throws SimpleException {
        return ResponseEntity.ok(this.lottoService.readRecentLottoRound());
    }
}
