package com.github.yeeun_yun97.LottoPredictAppBackEnd.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Constant {
    public static final String [] publicApis= {"/user/login","/user/register","/lotto/predict","/lotto/readHitData"};

    public static final String DATA_EMAIL_IS_NOT_EMAIL="이메일을 입력해 주세요";
    public static final int DATA_PASSWORD_REQUIRED_LENGTH=4;
    public static final String DATA_PASSWORD_IS_NOT_ENOUGH_LENGTH="비밀번호가 너무 짧습니다.";
    public static final int DATA_NAME_REQUIRED_LENGTH=1;
    public static final String DATA_NAME_IS_NOT_ENOUGH_LENGTH="닉네임은 한글자 이상이어야 합니다.";

    @AllArgsConstructor
    @Getter
    public enum EExceptionMessage{
        LoginDataWrongException("비밀번호 혹은 이메일이 틀렸습니다."),
        PythonModelLoadFailedException("서버 추천 모델에 장애가 발생하였습니다."),
        SaveNotFoundException("해당하는 정보 데이터가 없습니다.."),
        UserCannotAccessOthersException("타인의 정보에 접근할 수 없습니다."),
        UserEmailDuplicatedException("해당 이메일은 이미 존재하는 계정입니다."),
        UserNotFoundException("해당하는 유저가 존재하지 않습니다."),
        IllegalArgumentException("토큰을 받는 데 실패하였습니다."),
        UserTokenExpiredException("이미 만료된 토큰입니다."),
        UserTokenStartsWithSomethingElse("잘못된 토큰값입니다."),
        LottoApiFailedException("로또 데이터를 불러오는데 실패하였습니다."),
        LottoApiConnectionFailedException("로또 api와의 연결에 실패하였습니다."),
        ;
        private String message;
    }

    public static final String LOTTO_API_BASE_URL="https://www.dhlottery.co.kr/";

    public static final String UNAUTHORIZED_MESSAGE= "UnAuthorized";
    public static final String TOKEN_HEADER_NAME="Authorization";

    public static final String TOKEN_SUFFIX_BEARER="Bearer ";

    public static final String SECRET_STRING="jwtpassword";
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    public static final String CRON_LOTTO_SATURDAY_NIGHT="10 0 21 * * 6";
    public static final String LOTTO_API_METHOD="getLottoNumber";


    public static final String PYTHON_MODEL_PATH="/static/python/ffinal";
    public static final String PYTHON_MODEL_TAG="serve";
    public static final String PYTHON_MODEL_FEED="serving_default_lstm_24_input:0";
    public static final String PYTHON_MODEL_FETCH="StatefulPartitionedCall:0";

    public static final String SUCCESSFUL_REGISTER_MESSAGE ="성공적으로 가입하였습니다.";
    public static final String SUCCESSFUL_SAVE_TO_HISTORY_MESSAGE ="성공적으로 저장하였습니다.";



}
