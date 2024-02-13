package Trendithon.SpinOff.domain.board.entity;

import Trendithon.SpinOff.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long board_id;// id
    private String board_title;// 제목
    private String board_context;// 내용
    private Integer board_like;// 좋아요
    private String image_url;// 이미지 URL

//    @ManyToOne(fetch = FetchType.EAGER)// 일단 EAGER로 해놓자
//    @JoinColumn(name = "user_id") // 매핑할 외부 엔티티의 컬럼명 지정
//    private Member member =new Member();// 작성자
}
