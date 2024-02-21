package Trendithon.SpinOff.domain.board.dto;

import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class BoardDto {
//    private Long bno;
//    private String title; // 제목
//    private String content; // 내용
//    private Integer boardLike; // 좋아요
//    private List<String> imageUrl; // 이미지 URL
//    private Long writer_id; // 작성자
    private Long bno;// id
    private String title;// 제목
    private String description;// 한줄 소개
    private String projBackground;//배경
    private String mainFeature;//주요기능
    private String imageUrl;// 이미지 URL
    private List<String> memberPart;//팀원
    private String projUrl;//배포 URL
    private String githubUrl;//깃허브 링크
    private String teamMembers;//팀원
    private String category;//프로젝트 카테고리
    private String content;// 내용
    private Integer boardLike;// 좋아요
    private Long writer_id; // 작성자

}
