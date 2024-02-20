package Trendithon.SpinOff.domain.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bno;// id
    @Column(length = 50,nullable = false)
    private String title;// 제목
    @Column(length = 500,nullable = false)
    private String content;// 내용
    private Integer boardLike;// 좋아요
    private String imageUrl;// 이미지 URL
    private String writer;
}
