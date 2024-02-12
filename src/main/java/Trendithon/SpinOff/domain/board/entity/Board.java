package Trendithon.SpinOff.domain.board.entity;

import Trendithon.SpinOff.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@Entity
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue
    private Long board_id;

    @ManyToOne
    @JoinColumn(name = "user_id") // 매핑할 외부 엔티티의 컬럼명 지정
    private Member user;

    private String board_title;
    private String board_comment;
    private Integer board_like;
    private String image_url;
}
