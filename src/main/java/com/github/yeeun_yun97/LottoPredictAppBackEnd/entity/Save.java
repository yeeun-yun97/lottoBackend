package com.github.yeeun_yun97.LottoPredictAppBackEnd.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="save")
public class Save implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int first;
    private int second;
    private int third;
    private int fourth;
    private int fifth;
    private int sixth;

    @Enumerated(EnumType.STRING)
    private ESaveState saveState;

    @ManyToOne(targetEntity = UserRound.class)
    @JoinColumn(name="user_round_id")
    private UserRound userRound;


    public List<Integer> getNumberList() {
        List<Integer> numbers= new ArrayList<>();
        numbers.add(first);
        numbers.add(second);
        numbers.add(third);
        numbers.add(fourth);
        numbers.add(fifth);
        numbers.add(sixth);
        return numbers;
    }
}
