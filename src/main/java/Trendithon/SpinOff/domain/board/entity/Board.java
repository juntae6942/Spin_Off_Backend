package Trendithon.SpinOff.domain.board.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bno;// id
    //@Column(length = 50,nullable = false)
    private String projectName;// 제목
    //@Column(length =20,nullable= false)
    private String projectDescription;// 한줄 소개
    //@Column(length= 1000,nullable = false)
    private String projectBackground;//배경
    //@Column(length=100,nullable = false)
    private String projectFeatures;//주요기능
    private String projectImage;// 이미지 URL
    private String distribution;//배포 URL
    private String github;//깃허브 링크
    private String member1;
    private String member2;
    private String member3;
    private String member4;
    private String member5;
    private String category;//프로젝트 카테고리
    private Integer boardLike;// 좋아요
    private String writer;
}

