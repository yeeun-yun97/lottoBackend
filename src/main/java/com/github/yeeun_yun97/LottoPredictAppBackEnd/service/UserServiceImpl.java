package com.github.yeeun_yun97.LottoPredictAppBackEnd.service;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.dto.*;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.entity.ESaveState;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.entity.Save;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.entity.User;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.entity.UserRound;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.exception.*;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.repository.SaveRepository;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.repository.UserRepository;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.repository.UserRoundRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.github.yeeun_yun97.LottoPredictAppBackEnd.constant.Constant.SUCCESSFUL_REGISTER_MESSAGE;
import static com.github.yeeun_yun97.LottoPredictAppBackEnd.constant.Constant.SUCCESSFUL_SAVE_TO_HISTORY_MESSAGE;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SaveRepository saveRepository;
    private final UserRoundRepository userRoundRepository;

    public UserServiceImpl(UserRepository userRepository, SaveRepository saveRepository, UserRoundRepository userRoundRepository) {
        this.userRepository = userRepository;
        this.userRoundRepository = userRoundRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.saveRepository = saveRepository;
    }

    @Override
    public RegisterResponse register(String email, String password, String name) throws SimpleException {
        //password Encoding
        String hashedPassword = this.bCryptPasswordEncoder.encode(password);;

        Optional<User> ourUser = this.userRepository.findByEmail(email);
        if (ourUser.isPresent()) throw new UserEmailDuplicatedException();

        User newUser = User.builder().email(email).password(hashedPassword).name(name).build();
        this.userRepository.save(newUser);
        return RegisterResponse.builder().name(name).message(SUCCESSFUL_REGISTER_MESSAGE).build();
    }

    @Override
    public SimpleOkResponse updateSaveToHistory(Long predict_id, Long user_id) throws SimpleException {
        Save save = this.saveRepository.findById(predict_id).orElseThrow(SaveNotFoundException::new);
        if (!save.getUserRound().getUser().getId().equals(user_id)) {
            throw new UserCannotAccessOthersException();
        } else {
            save.setSaveState(ESaveState.SAVED);
            this.saveRepository.save(save);
            return SimpleOkResponse.builder().message(SUCCESSFUL_SAVE_TO_HISTORY_MESSAGE).build();
        }
    }

    @Override
    public Page<ReadSaveResponse> readSaveHistory(int page_size, int page_num, Long user_id) throws SimpleException{
        User user = this.userRepository.findById(user_id).orElseThrow(UserNotFoundException::new);

        Pageable sortedByRound = PageRequest.of(page_num, page_size, Sort.by("round_id").descending().and(Sort.by("id").ascending()));
        Page<UserRound> userRounds = this.userRoundRepository.findAllByUser(user, sortedByRound);

        return userRounds.map(ReadSaveResponse::of);
    }

    @Override
    public ReadUserInfoResponse readUserInfo(Long user_id) throws SimpleException{
        User user = this.userRepository.findById(user_id).orElseThrow(UserNotFoundException::new);
        return  ReadUserInfoResponse.builder().name(user.getName()).build();
    }
}
