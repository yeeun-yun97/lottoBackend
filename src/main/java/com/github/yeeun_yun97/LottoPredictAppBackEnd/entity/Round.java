package com.github.yeeun_yun97.LottoPredictAppBackEnd.entity;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.dto.EScore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="round")
public class Round implements Comparable<Round>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int roundNum;

    private int first;
    private int second;
    private int third;
    private int fourth;
    private int fifth;
    private int sixth;
    private int bonus;

    private LocalDate endDate;

    @OneToMany(mappedBy = "round")
    private Collection<UserRound> userRounds;

    @Override
    public int compareTo(Round target) {
        if(this.roundNum> target.roundNum)return -1;
        else if (this.roundNum==target.roundNum)return 0;
        else return 1;
    }

    public EScore contains(int target) {
        if(this.first==target||this.second==target||this.third==target||this.fourth==target||this.fifth==target||this.sixth==target){
            return EScore.HIT;
        }else if(this.bonus==target){
            return EScore.BONUS_HIT;
        }

            return EScore.FALSE;
    }

    public List<Integer> getHitNumbers(){
        List<Integer> hitNumbers= new ArrayList<>(6);
        hitNumbers.add(this.getFirst());
        hitNumbers.add(this.getSecond());
        hitNumbers.add(this.getThird());
        hitNumbers.add(this.getFourth());
        hitNumbers.add(this.getFifth());
        hitNumbers.add(this.getSixth());
        return hitNumbers;
    }
}
