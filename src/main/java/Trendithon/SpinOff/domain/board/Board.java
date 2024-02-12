package Trendithon.SpinOff.domain.board;

import Trendithon.SpinOff.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@Entity
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_id;

    @ManyToOne
    @JoinColumn(name = "user_id") // 매핑할 외부 엔티티의 컬럼명 지정
    private Member user;

    private String board_title;
    private String board_comment;
    private Integer board_like;
    private String image_url;
}
