package com.github.yeeun_yun97.LottoPredictAppBackEnd.entity;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.dto.EScore;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "user_round")
public class UserRound implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(targetEntity = Round.class)
    @JoinColumn(name="round_id")
    private Round round;

    @OneToMany(mappedBy = "userRound")
    @Where(clause="save_state='SAVED'")
    private Collection<Save> saves;

    public List<List<Integer>> getNumberList() {
        List<List<Integer>> retList= new ArrayList<>();
        for(Save save:saves){
            List<Integer> row= new ArrayList<>();
            row.add(save.getFirst());
            row.add(save.getSecond());
            row.add(save.getThird());
            row.add(save.getFourth());
            row.add(save.getFifth());
            row.add(save.getSixth());
            retList.add(row);
        }
        return retList;
    }

    public List<List<EScore>> getScoreList() {
        List<List<EScore>> retList= new ArrayList<>();
        for(Save save:saves){
            List<EScore> row= new ArrayList<>();
            Round round=save.getUserRound().getRound();
            row.add(round.contains(save.getFirst()));
            row.add(round.contains(save.getSecond()));
            row.add(round.contains(save.getThird()));
            row.add(round.contains(save.getFourth()));
            row.add(round.contains(save.getFifth()));
            row.add(round.contains(save.getSixth()));
            retList.add(row);
        }
        return retList;
    }
}
