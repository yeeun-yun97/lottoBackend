package com.github.yeeun_yun97.LottoPredictAppBackEnd.service;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.dto.LottoPredictResponse;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.dto.ReadLottoRecentRoundResponse;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.entity.*;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.exception.PythonModelLoadFailedException;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.exception.SimpleException;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.exception.UserNotFoundException;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.repository.RoundRepository;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.repository.SaveRepository;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.repository.UserRepository;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.repository.UserRoundRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.types.TFloat32;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.github.yeeun_yun97.LottoPredictAppBackEnd.constant.Constant.*;

@Service
@AllArgsConstructor
public class LottoServiceImpl implements LottoService {
    private final RoundRepository roundRepository;
    private final SaveRepository saveRepository;
    private final UserRepository userRepository;
    private final UserRoundRepository userRoundRepository;

    @Override
    public ReadLottoRecentRoundResponse readRecentLottoRound() {
        Round round =this.roundRepository.findFirstByFirstNotOrderByRoundNumDesc(0);
        return ReadLottoRecentRoundResponse.of(round);
    }

    @Override
    public LottoPredictResponse getPredictNum(Long user_id) throws SimpleException {

        //find targetUser, round
        User user = this.userRepository.findById(user_id).orElseThrow(UserNotFoundException::new);
        Round round = this.roundRepository.findFirstByOrderByRoundNumDesc();

        Optional<UserRound> optUserRound= this.userRoundRepository.findByUserAndRound(user,round);
        UserRound userRound;
        if(optUserRound.isPresent()) {
             userRound = optUserRound.get();
        }else{
            userRound= UserRound.builder().round(round).user(user).build();
            this.userRoundRepository.save(userRound);
        }
        //predict
        int[] result = this.doPredict();

        //save save
        Save save = Save.builder()
                .first(result[0]).second(result[1])
                .third(result[2]).fourth(result[3])
                .fifth(result[4]).sixth(result[5])
                .saveState(ESaveState.NOT_SAVED)
                .userRound(userRound)
                .build();
        this.saveRepository.save(save);

        //response Entity
        return LottoPredictResponse.of(save);
    }

    private int[] doPredict() throws SimpleException{
        try {
        //Load the model
        String path = new ClassPathResource(PYTHON_MODEL_PATH).getFile().getPath();
        SavedModelBundle model = SavedModelBundle.load(path, PYTHON_MODEL_TAG);

        //get x data
        FloatNdArray input_matrix = this.getNDArray();
        Tensor<TFloat32> input = TFloat32.tensorOf(input_matrix);

        //predict
        Tensor<?> outputs = model
                .session()
                .runner()
                .feed(PYTHON_MODEL_FEED, input)
                .fetch(PYTHON_MODEL_FETCH)
                .run()
                .get(0);

        //output ndArray to length 6 int[]
        FloatNdArray array = (FloatNdArray) outputs.data();
        return this.getResult(array);
        } catch (IOException e) {
            throw new PythonModelLoadFailedException();
        }
    }

    private int[][] getTestData() {
        List<Round> rounds = this.roundRepository.findAll();
        Collections.sort(rounds);

        int[][] array = new int[10][45];
        for (int i = 1; i < 11; i++) {
            Round round = rounds.get(i);
            int[] row = new int[45];
            row[round.getFirst() - 1] = 1;
            row[round.getSecond() - 1] = 1;
            row[round.getThird() - 1] = 1;
            row[round.getFourth() - 1] = 1;
            row[round.getFifth() - 1] = 1;
            row[round.getSixth() - 1] = 1;
            array[i-1] = row;
        }
        return array;
    }

    private FloatNdArray getNDArray() {
        int[][] array = this.getTestData();
        FloatNdArray result_matrix = NdArrays.ofFloats(Shape.of(1, 10, 45));
        FloatNdArray input_matrix = NdArrays.ofFloats(Shape.of(10, 45));
        int i = 0;
        for (int[] arr : array) {
            FloatNdArray row = NdArrays.ofFloats(Shape.of(45));
            int j = 0;
            for (int val : arr) {
                row.setFloat(val, j);
            }
            input_matrix.set(row, i++);
        }
        result_matrix.set(input_matrix, 0);
        return result_matrix;
    }

    private int[] getResult(FloatNdArray array) {//FloatNdArray를 가지고(float로 된 45개의 원핫코드) 6개의 숫자를 꺼낸다.
        int[] result = new int[24];
        for (int i = 0; i < result.length; i++) {
            float max = 0;
            int maxIndex = 0;
            for (int j = 0; j < 45; j++) {
                float val = array.getFloat(0, j);
                if (val > max) {
                    max = val;
                    maxIndex = j;
                }
            }
            result[i] = maxIndex + 1;
            array.setFloat(0, 0, maxIndex);
        }

        int[] retArray = new int[6];
        int i=0;
        while (i< 6) {
            int randomIndex = (int) (Math.random() * 24);
            int val = result[randomIndex];
            if (val != 0) {
                retArray[i++] = val;
                result[randomIndex] = 0;
            }
        }
        Arrays.sort(retArray);
        return retArray;
    }


}
